package api.hue

import api.hue.dao.attribute.{Attribute, TransitionTime}
import api.hue.endpoint.{Groups, Lights}
import com.google.inject.Inject
import com.github.nscala_time.time.Imports._
import play.api.libs.json.JsValue

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Convenience methods for setting a group or light to various pre-defined states
  * @author ddexter
  */
class State @Inject() (groupsEndpoint: Groups, lightsEndpoint: Lights) {

  /**
    * Slowly fade into dim sunset
    * @param groupName The name of the group to apply fade
    */
  def sunset(groupName: String, endTime: DateTime): Future[JsValue] = {
    groupsEndpoint.getGroupId(groupName).flatMap(maybeGroupId => {
      val groupId = maybeGroupId match {
        case Some(id) => id
        case _ => throw new IllegalArgumentException("Group name not found")
      }
      val now = DateTime.now
      val transitionDuration =
        if (now > endTime) 1.second.toDuration.toScalaDuration
        else now.to(endTime).toDuration.toScalaDuration
      val transitionTime = TransitionTime(transitionDuration)
      groupsEndpoint.put(groupId, ColorConstants.SUNSET :+ transitionTime:_*)
    })
  }

  def off(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.OFF)

  def on(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.ON)

  def maxBright(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.MAX_BRIGHT)

  private def simpleRun(groupName: String, attributes: Seq[Attribute]): Future[JsValue] = {
    groupsEndpoint.getGroupId(groupName).flatMap(maybeGroupId => {
      val groupId = maybeGroupId match {
        case Some(id) => id
        case _ => throw new IllegalArgumentException("Group name not found")
      }
      groupsEndpoint.put(groupId, attributes:_*)
    })
  }
}
