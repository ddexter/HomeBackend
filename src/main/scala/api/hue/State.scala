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

  def sunrise(groupName: String, endTime: DateTime): Future[JsValue] =
    timedRun(groupName, endTime, ColorConstants.MAX_BRIGHT)

  def sunset(groupName: String, endTime: DateTime): Future[JsValue] =
    timedRun(groupName, endTime, ColorConstants.SUNSET)

  def off(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.OFF)

  def on(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.ON)

  def maxBright(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.MAX_BRIGHT)

  def wakeUp(groupName: String): Future[JsValue] = simpleRun(groupName, ColorConstants.SUNSET)

  private def simpleRun(groupName: String, attributes: Seq[Attribute]): Future[JsValue] = {
    groupsEndpoint.getGroupId(groupName).flatMap(maybeGroupId => {
      val groupId = maybeGroupId match {
        case Some(id) => id
        case _ => throw new IllegalArgumentException("Group name not found")
      }
      groupsEndpoint.put(groupId, attributes:_*)
    })
  }

  /**
    * Slowly fade into provided attributes
    * @param groupName The name of the group to apply fade
    */
  private def timedRun(groupName: String, endTime: DateTime, attributes: Seq[Attribute]): Future[JsValue] = {
    groupsEndpoint.getGroupId(groupName).flatMap(maybeGroupId => {
      val groupId = maybeGroupId match {
        case Some(id) => id
        case _ => throw new IllegalArgumentException("Group name not found")
      }
      val now = DateTime.now
      val transitionDuration =
        if (now > endTime) 1.second.toDuration.toScalaDuration
        else if (now.to(endTime).toDuration.toScalaDuration > TransitionTime.MAX_TRANSITION_TIME)
          TransitionTime.MAX_TRANSITION_TIME
        else now.to(endTime).toDuration.toScalaDuration
      val transitionTime = TransitionTime(transitionDuration)
      groupsEndpoint.put(groupId, attributes :+ transitionTime:_*)
    })
  }
}
