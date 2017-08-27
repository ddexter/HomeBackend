name := "Home"

version := "0.1"

scalaVersion := "2.12.3"

resolvers += "mvnrepository" at "https://mvnrepository.com/artifact"

fork in run := true

libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "2.16.0",
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.play" % "play-ahc-ws-standalone_2.12" % "1.0.4",
  "com.typesafe.play" % "play-ws-standalone_2.12" % "1.0.4",
  "com.typesafe.play" % "play-ws-standalone-json_2.12" % "1.0.4",
  "net.codingwell" %% "scala-guice" % "4.1.0"
)
