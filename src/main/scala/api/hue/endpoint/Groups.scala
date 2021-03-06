package api.hue.endpoint

import api.hue.Bridge
import api.hue.dao.Group
import api.hue.dao.attribute._
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

  override def get: Future[Map[String, Group]] =
    bridge.get(path).map { js => js.as[JsObject].fields.toMap.map { case (k, v) => (k, v.as[Group]) } }

  /**
    *
    * @param groupName The group name to search for
    * @return The group id corresponding to a group name or none if DNE
    */
  def getGroupId(groupName: String): Future[Option[String]] = {
    get.map { groups => { groups.find(entry => entry._2.name == groupName).map(_._1) } }
  }

  override protected def path: String = PATH

  override protected def supportedPutAttributes: Set[String] = SUPPORTED_PUT_ATTRIBUTES
}

object Groups {
  private val SUPPORTED_PUT_ATTRIBUTES: Set[String] = Set(
    Brightness.NAME,
    Hue.NAME,
    On.NAME,
    Saturation.NAME,
    TransitionTime.NAME
  )

  val NAME: String = "groups"
  val PATH: String = "/groups"
}
