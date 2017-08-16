package api.hue

import api.WSApiExecutor
import api.hue.dao.Group
import com.google.inject.Inject
import com.google.inject.name.Named
import play.api.libs.json.{JsObject, JsValue}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author ddexter
  */
class Bridge @Inject() (api: WSApiExecutor,
                        @Named("hueAddress") address: String,
                        @Named("hueKey") key: String) {

  private val prefix: String = address + "/api/" + key

  def get(path: String): Future[JsValue] = api.get(prefix + path)

  def put(path: String, data: JsValue): Future[JsValue] = api.put(prefix + path, data)
}
