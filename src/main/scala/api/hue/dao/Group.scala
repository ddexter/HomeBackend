package api.hue.dao

import api.hue.dao.attribute.{Brightness, Hue, On, Saturation}
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * A group of lightbulbs
  * @author ddexter
  */
case class Group(name: String, on: On, bri: Brightness, hue: Hue, sat: Saturation) extends Controller {}

object Group extends Controller {

  implicit val fromJs: Reads[Group] = (
    (__ \ "name").read[String] and
    (__ \ "action").read[On] and
    (__ \ "action").read[Brightness] and
    (__ \ "action").read[Hue] and
    (__ \ "action").read[Saturation]
  )(Group.apply _)
}
