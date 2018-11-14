package com.zenathark.reservation.view

import com.zenathark.reservation.model.UsersModel

import scala.swing.event.ValueChanged
import scala.swing.{Action, BoxPanel, Button, FlowPanel, GridPanel, Label, Orientation, Panel, TextField}
import scala.util.Try

object UsersPanelFactory {

  val model: UsersVM = new UsersVM

  def getPanel : Panel = {
    new BoxPanel(Orientation.Vertical) {
      contents += inputFormField(Array(
        ("ID", 10, (s) => model.id = Try(s.toInt).getOrElse(0)),
        ("Name", 10, (s) => model.name = s)
      ))
      contents += new FlowPanel {
        contents += new Button(Action("Guardar") { saveUser() })
        contents += new Button(Action("Cancelar") { println(model) })
      }
    }
  }

  private def inputFormField(fields: Array[(String, Int, (String) => Unit)]): GridPanel = {
    new GridPanel(fields.length, 2) {
      for ((label, cols, updateF) <- fields) {
        contents += new Label(label)
        contents += new TextField(cols) {
          reactions += {
            case ValueChanged(component) => updateF(component.asInstanceOf[TextField].text)
          }
        }
      }
    }
  }

  private def saveUser(): Unit = {
    if (model.id == 0) {
      println("Saving new user")
      UsersModel.insertUser(model.id, model.name)
    } else {
      UsersModel.updateUser(model.id, model.name)
    }
  }
}

case class UsersVM(var id: Int = 0, var name: String = "")
