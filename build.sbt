ThisBuild / scalaVersion := "2.13.12"
ThisBuild / version := "1.0.0"
lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % "2.8.19",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "com.typesafe.play" %% "play-guice" % "2.8.19",
      "org.mongodb.scala" %% "mongo-scala-driver" % "4.7.1",
      "com.github.jwt-scala" %% "jwt-play-json" % "9.1.1"
    )
  )
