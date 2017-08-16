package api

import play.api.libs.json.JsValue

import scala.concurrent.Future

/**
  * Simple get/post executor for communication with HTTP APIs
  * @author ddexter
  */
trait ApiExecutor {

  def get(path: String): Future[JsValue]
  def put(path: String, data: JsValue): Future[JsValue]
}
