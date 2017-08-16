package api.hue.dao.attribute

import api.hue.endpoint.Groups
import play.api.libs.json._

/**
  * Whether or not a light is reachable
 *
  * @author ddexter
  */
case class Reachable(reachable: Boolean) extends Attribute {
  import Reachable._

  override def endpoints: Set[String] = ENDPOINTS
  override def name: String = NAME
  override def toJs: JsObject = Json.obj("reachable" -> reachable)
}

object Reachable {
  private val ENDPOINTS: Set[String] = Set(Groups.NAME)

  val NAME: String = "reachable"

  implicit val reads: Reads[Reachable] = (__ \ "reachable").read[Boolean].map(Reachable(_))
}