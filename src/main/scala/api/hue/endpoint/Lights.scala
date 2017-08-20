package api.hue.endpoint

import javax.inject.Inject

import api.hue.Bridge
import api.hue.dao.Light
import api.hue.dao.attribute._
import play.api.libs.json.JsObject

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * /lights endpoint
  * @author ddexter
  */
class Lights @Inject() (bridgeInst: Bridge) extends Endpoint[Light] {
  import Lights._

  override def bridge: Bridge = bridgeInst

  override def name: String = NAME

  override def get: Future[Map[String, Light]] =
    bridge.get(path).map { js => js.as[JsObject].fields.toMap.map { case (k, v) => (k, v.as[Light]) } }

  override protected def path: String = PATH

  override protected def supportedPutAttributes: Set[String] = SUPPORTED_PUT_ATTRIBUTES
}

object Lights {
  private val SUPPORTED_PUT_ATTRIBUTES: Set[String] = Set(
    Brightness.NAME,
    Hue.NAME,
    On.NAME,
    Saturation.NAME
  )
  val NAME: String = "lights"
  val PATH: String = "/lights"
}
