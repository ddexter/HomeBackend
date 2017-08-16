package api.hue.endpoint

import api.hue.Bridge
import api.hue.dao.Group
import com.google.inject.Inject
import play.api.libs.json.JsObject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * /groups endpoint
  * @author ddexter
  */
class Groups @Inject() (bridgeInst: Bridge) extends Endpoint[Group] {
  import Groups._

  override def bridge: Bridge = bridgeInst
  override def name: String = NAME
  override protected def path: String = PATH

  override def get: Future[Map[String, Group]] =
    bridge.get(path).map { js => js.as[JsObject].fields.toMap.map { case (k, v) => (k, v.as[Group]) } }
}

object Groups {
  val NAME: String = "groups"
  val PATH: String = "/groups"
}
