package api

import javax.inject.Inject

import play.api.libs.json.JsValue
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.JsonBodyWritables._
import play.api.libs.ws._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Play-ws implementation for [[ApiExecutor]]
  * @author ddexter
  */
class WSApiExecutor @Inject() (ws: StandaloneWSClient) extends ApiExecutor {

  override def get(path: String): Future[JsValue] = ws.url(path).get().map { response => response.body[JsValue] }

  override def put(path: String, data: JsValue): Future[JsValue] =
    ws.url(path).put(data).map { response => response.body[JsValue] }
}
