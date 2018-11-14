package com.zenathark.reservation.model

import cats.effect.IO
import doobie.util.transactor.Transactor

import scala.concurrent.ExecutionContext

object Connection {
  implicit val cs = IO.contextShift(ExecutionContext.global)
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:reservation", "postgres", "root"
  )
}
