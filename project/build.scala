import sbt._
import Keys._

object MacrosActivatorBuild extends Build {
  import BuildSettings._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings) aggregate (macros, demo)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(libraryDependencies <++=
      (scalaVersion)(v => Seq(("org.scala-lang" % "scala-compiler" % v), ("org.scala-lang" % "scala-reflect" % v)))))

//      Dependencies.scala_lang("scala-compiler", "scala-reflect")))

  lazy val demo: Project = Project(
    "demo",
    file("demo"),
    settings = buildSettings) dependsOn (macros)

}

object Dependencies {

  def scala_lang(artifact: String): Def.Initialize[ModuleID] = (scalaVersion)("org.scala-lang" % artifact % _)

}

object BuildSettings {

  lazy val buildSettings = 
  	Defaults.defaultSettings ++ Seq(
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.7", "-deprecation", "-unchecked", "-Ywarn-dead-code"),
    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.7", "-Xlint:unchecked", "-Xlint:deprecation", "-Xlint:-options"),
    javaOptions += "-Djava.util.logging.config.file=logging.properties",
    javaOptions += "-Xmx2G",
    //scalaVersion := "2.11.0-M5",
    outputStrategy := Some(StdoutOutput),
    fork := true,
    maxErrors := 1,
    addCompilerPlugin("org.scala-lang.plugins" % "macro-paradise" % "2.0.0-SNAPSHOT" cross CrossVersion.full),
    resolvers ++= Seq(
      Resolver.mavenLocal,
      Resolver.sonatypeRepo("releases"),
      Resolver.typesafeRepo("releases"),
      Resolver.typesafeRepo("snapshots"),
      Resolver.sonatypeRepo("snapshots")
    ),
    parallelExecution in Test := false
  )


}

/*  "org.specs2"             %% "specs2"                % "2.2.2"        % "test"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

parallelExecution in Test := false

*/