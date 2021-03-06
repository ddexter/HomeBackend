package api.hue.dao.attribute

import play.api.libs.json._

import scala.concurrent.duration._

/**
  * Time to take transitioning from one state to another
 *
  * @author ddexter
  */
case class TransitionTime(duration: Duration) extends Attribute {
  import TransitionTime._

  def this(deciseconds: Long) = {
    this((deciseconds * 10).seconds)
  }

  if (duration.gt(MAX_TRANSITION_TIME)) throw new IllegalStateException("Illegal Transition Time: " + duration)

  override def name: String = NAME
  override def toJs: JsObject = Json.obj("transitiontime" -> duration.toSeconds * 10)
}

object TransitionTime {
  val NAME: String = "transitiontime"
  val MAX_TRANSITION_TIME: Duration = 6553500.millisecond

  implicit val reads: Reads[TransitionTime] = (__ \ "transitiontime").read[Long].map(new TransitionTime(_))
}
