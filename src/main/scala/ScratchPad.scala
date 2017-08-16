import api.hue.HueModule
import api.ApiModule
import api.hue.dao.attribute.{Brightness, Hue, On, Saturation}
import api.hue.endpoint.{Groups, Lights}
import com.google.inject.Guice
import net.codingwell.scalaguice.ScalaModule

import scala.concurrent.duration._
import scala.concurrent.Await

/**
  * @author ddexter
  */
object ScratchPad {

  def main(args: Array[String]): Unit = {
    val injector = Guice.createInjector(new ScalaModule() {
      override def configure(): Unit = {
        install(new ApiModule)
        install(new HueModule)
      }
    })

    import net.codingwell.scalaguice.InjectorExtensions._
    val groups: Groups = injector.instance[Groups]
    val lights: Lights = injector.instance[Lights]

    val on = On(true)
    val bri = Brightness(Brightness.MAX_BRIGHTNESS)
    val hue = Hue(39391)
    val sat = Saturation(14)
    println(Await.result(groups.get, 30.seconds))
    println(Await.result(lights.get, 30.seconds))
    //groups.put("2", on, bri, sat, hue)
  }
}
