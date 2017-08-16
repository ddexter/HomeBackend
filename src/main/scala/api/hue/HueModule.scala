package api.hue

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.typesafe.config.ConfigFactory
import net.codingwell.scalaguice.ScalaModule

/**
  * @author ddexter
  */
class HueModule() extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    val config = ConfigFactory.load().getConfig("hue")

    bind[String].annotatedWith(Names.named("hueAddress")).toInstance(config.getString("address"))
    bind[String].annotatedWith(Names.named("hueKey")).toInstance(config.getString("key"))
  }
}
