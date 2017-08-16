package api.hue.dao.attribute

import api.hue.endpoint.{Groups, Lights}
import play.api.libs.json._

/**
  * Represents on/off state
 *
  * @author ddexter
  */
case class On(on: Boolean) extends Attribute {
  import On._

  override def endpoints: Set[String] = ENDPOINTS
  override def name:String = NAME
  override def toJs: JsObject = Json.obj("on" -> on)
}

object On {
  private val ENDPOINTS: Set[String] = Set(Groups.NAME, Lights.NAME)

  val NAME: String = "on"

  implicit val reads: Reads[On] = (__ \ "on").read[Boolean].map(On(_))
}
