import api.hue.{HueModule, Room, State}
import api.ApiModule
import api.hue.endpoint.{Groups, Lights}
import com.google.inject.Guice
import net.codingwell.scalaguice.ScalaModule
import org.joda.time.DateTime

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
    val states: State = injector.instance[State]

    println(Await.result(groups.get, 30.seconds))
    println(Await.result(lights.get, 30.seconds))

    val tenThirty = DateTime.now.withTimeAtStartOfDay.plusHours(22).plusMinutes(30)
    //Await.result(states.sunset(Room.BEDROOM, tenThirty), 30.seconds)
    Await.result(states.on(Room.BEDROOM), 10.second)
  }
}
