package api.hue.endpoint

import javax.inject.Inject

import api.hue.Bridge
import api.hue.dao.Light
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
  override protected def path: String = PATH

  override def get: Future[Map[String, Light]] =
    bridge.get(path).map { js => js.as[JsObject].fields.toMap.map { case (k, v) => (k, v.as[Light]) } }
}

object Lights {
  val NAME: String = "lights"
  val PATH: String = "/lights"
}
