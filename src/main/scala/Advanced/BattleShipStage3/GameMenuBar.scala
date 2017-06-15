package Advanced.BattleShipStage3

/**
  * Created by Administrator on 13/06/2017.
  */

import java.net.InetAddress

import BattleField.{client, clientAddress, hostAddress, isClient, isHost, server}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.swing._

class GameMenuBar extends MenuBar {
  //background = Color.LIGHT_GRAY
  contents += new Menu("Game") {
    contents += new Menu("Play vs CPU") {
      contents += new MenuItem("Easy")
      contents += new MenuItem("Medium")
      contents += new MenuItem("Hard")
      contents += new MenuItem("You Will Lose")
    }
    contents += new Menu("Play vs Player") {
      contents += new MenuItem(Action("Play as host") {
        isHost = true
        if (!server.running) {
          Future {
            var response = Dialog.showInput(this, s"Host Running on port 9999\n Give second player: ${InetAddress.getLocalHost.toString.split("/")(1)}. \nInput client IP", "Client IP", Dialog.Message.Info, initial = clientAddress)
            response match {
              case Some(s) => clientAddress = s
              case None => println("None")
            }
            server.startServer()
          }
        }
      })
      contents += new MenuItem(Action("Play as client") {
        isClient = true
        if (!client.running) {
          Future {
            var response = Dialog.showInput(this, s"Client Running on port 9998\n Give host player: ${InetAddress.getLocalHost.toString.split("/")(1)}. \n Input host IP", "Host IP", Dialog.Message.Info, initial = hostAddress)
            response match {
              case Some(s) => hostAddress = s
              case None => println("None")
            }
            //Dialog.showInput(this, "Client Server Running on port 9998", "client")
            client.startClientServer()
          }

        }
      })

      contents += new MenuItem(Action("Update Host Address") {
        isClient = true
        if (!client.running) {

          var response = Dialog.showInput(this, "Input host IP", "Update Host IP", Dialog.Message.Info, initial = hostAddress)
          response match {
            case Some(s) => hostAddress = s
            case None => println("None")
          }

        }
      })

      contents += new MenuItem(Action("Update Client Address") {
        isClient = true
        if (!client.running) {

          var response = Dialog.showInput(this, "Input client IP", "Update Client IP", Dialog.Message.Info, initial = clientAddress)
          response match {
            case Some(s) => clientAddress = s
            case None => println("None")
          }

        }
      })
    }
    contents += new MenuItem("Reset")
  }
}
