// *****************************************************************************
// Projects
// *****************************************************************************

lazy val root =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.zio,
        library.zioTest    % Test,
        library.zioTestSbt % Test
      ),
      publishArtifact := false,
      testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val zio = "1.0.0-RC18-2"
    }
    val zio        = "dev.zio" %% "zio"          % Version.zio
    val zioTest    = "dev.zio" %% "zio-test"     % Version.zio
    val zioTestSbt = "dev.zio" %% "zio-test-sbt" % Version.zio
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
    scalafmtSettings ++
    commandAliases

lazy val commonSettings =
  Seq(
    name := "$name$",
    scalaVersion := "2.13.1",
    organization := "com.example",
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",    // source files are in UTF-8
      "-deprecation",          // warn about use of deprecated APIs
      "-unchecked",            // warn about unchecked type parameters
      "-feature",              // warn about misused language features
      "-language:higherKinds", // allow higher kinded types without `import scala.language.higherKinds`
      "-Xlint",                // enable handy linter warnings
      "-Xfatal-warnings"       // turn compiler warnings into errors
    ) // http://downloads.typesafe.com/website/presentations/ScalaDaysSF2015/Toward%20a%20Safer%20Scala%20@%20Scala%20Days%20SF%202015.pdf

  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )

lazy val commandAliases =
  addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt") ++
    addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
