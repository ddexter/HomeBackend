package api.hue.dao.attribute

import api.hue.endpoint.{Groups, Lights}
import play.api.libs.json._

/**
  * Saturation attribute
  * @author ddexter
  */
case class Saturation(sat: Short) extends Attribute {
  import Saturation._

  if (sat < 0 || sat > MAX_SATURATION) throw new IllegalStateException("Illegal Saturation: " + sat)

  override def endpoints: Set[String] = ENDPOINTS
  override def name: String = NAME
  override def toJs: JsObject = Json.obj("sat" -> sat)

}

object Saturation {
  private val ENDPOINTS: Set[String] = Set(Groups.NAME, Lights.NAME)

  val NAME: String = "sat"
  val MAX_SATURATION: Short = 254

  implicit val reads: Reads[Saturation] = (__ \ "sat").read[Short].map(Saturation(_))
}
