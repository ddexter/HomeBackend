package api.hue.dao.attribute

import play.api.libs.json.{JsObject, JsValue, Reads, Writes}

/**
  * Philips Hue API endpoint attributes
 *
  * @author ddexter
  */
trait Attribute {
  def name: String
  def toJs: JsObject
}
