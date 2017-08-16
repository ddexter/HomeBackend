package api

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject._
import net.codingwell.scalaguice.ScalaModule
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

/**
  * Guice module for api package
  * @author ddexter
  */
class ApiModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[StandaloneWSClient].toProvider[WSClientProvider]
    bind[ApiExecutor].to[WSApiExecutor]
  }
}

@Singleton
class WSClientProvider extends Provider[StandaloneWSClient] {

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  lazy val get = StandaloneAhcWSClient()
}
