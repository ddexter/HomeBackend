package api.hue.dao.attribute

import api.hue.endpoint.Lights
import play.api.libs.json._

/**
  * Whether or not a light is reachable
 *
  * @author ddexter
  */
case class Reachable(reachable: Boolean) extends Attribute {
  import Reachable._

  override def name: String = NAME
  override def toJs: JsObject = Json.obj("reachable" -> reachable)
}

object Reachable {
  val NAME: String = "reachable"

  implicit val reads: Reads[Reachable] = (__ \ "reachable").read[Boolean].map(Reachable(_))
}
