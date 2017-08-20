package api.hue.dao.attribute

import api.hue.endpoint.{Groups, Lights}
import play.api.libs.json._

/**
  * The Hue of the bulb(s).  Red is both 0 and 65535, green is 25500, and blue is 46920
  * @author ddexter
  */
case class Hue(hue: Int) extends Attribute {
  import Hue._

  if (hue < 0 || hue > Hue.MAX_HUE) throw new IllegalStateException("Illegal Hue: " + hue)

  override def name: String = NAME
  override def toJs: JsObject = Json.obj("hue" -> hue)
}

object Hue {
  val NAME: String = "hue"
  val MAX_HUE: Int = 65535

  implicit val reads: Reads[Hue] = (__ \ "hue").read[Int].map(Hue(_))
}
