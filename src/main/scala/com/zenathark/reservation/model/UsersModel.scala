package com.zenathark.reservation.model

import doobie._
import doobie.implicits._
import cats._
import cats.data._
import cats.effect.IO
import cats.implicits._
import scala.concurrent.ExecutionContext

case class Users(var id: Int, var name: String)

object UsersModel extends UsersLike { }

trait UsersLike {

  def find(id: Int) =
    sql"select id, name from users where id = $id"
      .query[Users]
      .option
      .transact(Connection.xa)
      .unsafeRunSync()

  def findAllByName(name: String) : List[Users] =
    sql"select id, name from users where name = $name"
      .query[Users]
      .to[List]
      .transact(Connection.xa)
      .unsafeRunSync

  def insertUser(newUser: Users): Unit =
    newUser match {
      case Users(id, name) => sql"insert into users values($name)"
        .update
        .withUniqueGeneratedKeys("id", "name")
        .transact(Connection.xa)
        .unsafeRunSync
    }

  def insertUser(id: Int, name: String): Unit =
    insertUser(Users(id, name))

  def deleteUser(anUser: Users): Unit =
    anUser match {
      case Users(id, name) => sql"delete from users where id = $id"
        .update
        .withUniqueGeneratedKeys("id", "name")
        .transact(Connection.xa)
        .unsafeRunSync
    }

  def deleteUserById(id: Int): Unit =
    deleteUser(Users(id, ""))

  def updateUser(anUser: Users): Unit =
    anUser match {
      case Users(id, name) => sql"update users set name = $name where id = $id"
        .update
        .withUniqueGeneratedKeys("id", "name")
        .transact(Connection.xa)
        .unsafeRunSync
    }

  def updateUser(id: Int, name: String): Unit =
    updateUser(Users(id, name))
}
