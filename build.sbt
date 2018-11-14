import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.zenathark",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    scalacOptions += "-Ypartial-unification", // 2.11.9+

    libraryDependencies ++= Seq(
      // Start with this one
      "org.tpolecat" %% "doobie-core"      % "0.6.0",

      // And add any of these as needed
      "org.tpolecat" %% "doobie-h2"        % "0.6.0",          // H2 driver 1.4.197 + type mappings.
      "org.tpolecat" %% "doobie-hikari"    % "0.6.0",          // HikariCP transactor.
      "org.tpolecat" %% "doobie-postgres"  % "0.6.0",          // Postgres driver 42.2.5 + type mappings.
      "org.tpolecat" %% "doobie-specs2"    % "0.6.0" % "test", // Specs2 support for typechecking statements.
      "org.tpolecat" %% "doobie-scalatest" % "0.6.0" % "test",  // ScalaTest support for typechecking statements.
    ),
    libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3",
    name := "Main",
    mainClass in (Compile, run) := Some("Main"),
    libraryDependencies += scalaTest % Test
  )
