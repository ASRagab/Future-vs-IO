import Dependencies._

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization  := "com.example",
      scalaVersion  := "2.12.7",
      version       := "0.1.0-SNAPSHOT",
      scalacOptions := Seq(
        "-language:_",
        "-Ypartial-unification",
        "-Xfatal-warnings"
      )
    )),
    trapExit := false,
    name := "Future-vs-IO",
    libraryDependencies := Seq(zio, scalaTest % Test),
    initialCommands in Compile in console := """
                                               |import scalaz._
                                               |import scalaz.zio._
                                               |import scalaz.zio.console._
                                               |object replRTS extends RTS {}
                                               |import replRTS._
                                               |implicit class RunSyntax[E, A](io: IO[E, A]){ def unsafeRun: A = replRTS.unsafeRun(io) }
                                             """.stripMargin
  
  )


