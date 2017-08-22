package api.hue

import api.hue.dao.attribute._

/**
  * Some light color states
 *
  * @author ddexter
  */
object ColorConstants {
  val MAX_BRIGHT: Seq[Attribute] = Vector(
    On(true),
    Brightness(Brightness.MAX_BRIGHTNESS),
    Hue(39391),
    Saturation(14)
  )
  val SUNSET: Seq[Attribute] = Vector(
    On(true),
    Brightness(38),
    Hue(5291),
    Saturation(202)
  )
  val OFF: Seq[Attribute] = Vector(On(false))
  val ON: Seq[Attribute] = Vector(On(true))
}
