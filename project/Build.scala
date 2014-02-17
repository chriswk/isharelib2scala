import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {
  val appName = "isharelib"
  val appVersion = "1.0"

  val Repos = Seq(
    "anormCypher" at "http://repo.anormcypher.org/",
    "typesafe" at "http://repo.typesafe.com/typesafe/releases/"
  )

  val appDependencies = Seq(jdbc, anorm)
  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers ++= Repos,
    libraryDependencies ++= Seq(
      "org.anormcypher" %% "anormcypher" % "0.4.4"
    )
  )
}