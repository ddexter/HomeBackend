package api.hue.dao

import api.hue.dao.attribute._
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * A light bulb
  * @author ddexter
  */
case class Light(name: String,
                 modelId: String,
                 softwareVersion: String,
                 lightType: String,
                 on: On,
                 bri: Brightness,
                 hue: Hue,
                 sat: Saturation,
                 reachable: Reachable) extends Controller {}

object Light extends Controller {

  implicit val fromJs: Reads[Light] = (
    (__ \ "type").read[String] and
    (__ \ "name").read[String] and
    (__ \ "modelid").read[String] and
    (__ \ "swversion").read[String] and
    (__ \ "state").read[On] and
    (__ \ "state").read[Brightness] and
    (__ \ "state").read[Hue] and
    (__ \ "state").read[Saturation] and
    (__ \ "state").read[Reachable]
  )(Light.apply _)
}
