package api.hue.endpoint

import api.hue.Bridge
import api.hue.dao.Controller
import api.hue.dao.attribute.Attribute
import play.api.libs.json._

import scala.concurrent.Future

/**
  * API endpoint (e.g. groups or lights)
  * @todo Figure out a way to make Reads/Writes generic
  * @author ddexter
  */
trait Endpoint[T <: Controller] {
  def bridge: Bridge

  def name: String

  def get: Future[Map[String, T]]

  def put(id: String, attributes: Attribute*): Future[JsValue] = {
    validate(attributes)
    val data = attributes.foldLeft(Json.obj())((l, r) => l.deepMerge(r.toJs))
    bridge.put(path + "/" + id + "/action", data)
  }

  protected def path: String

  protected def supportedPutAttributes: Set[String]

  protected def validate(attributes: Seq[Attribute]): Unit = {
    if (!attributes.map(_.name).forall(supportedPutAttributes(_)))
      throw new IllegalStateException("Invalid attribute for " + path + " endpoint")
  }

}
