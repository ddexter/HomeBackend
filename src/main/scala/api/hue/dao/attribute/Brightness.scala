package api.hue.dao.attribute

import play.api.libs.json._

/**
  * The brightness of the bulb(s).  Note: 0 is not off, it's just min brightness
  * @author ddexter
  */
case class Brightness(bri: Short) extends Attribute {
  import Brightness._

  if (bri < 0 || bri > MAX_BRIGHTNESS) throw new IllegalStateException("Illegal Brightness: " + bri)

  override def name: String = NAME
  override def toJs: JsObject = Json.obj("bri" -> bri)
}

object Brightness {
  val NAME: String = "bri"
  val MAX_BRIGHTNESS: Short = 254

  implicit val reads: Reads[Brightness] = (__ \ "bri").read[Short].map(Brightness(_))
}
