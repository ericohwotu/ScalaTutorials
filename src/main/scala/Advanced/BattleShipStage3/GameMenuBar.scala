package Advanced.BattleShipStage3

/**
  * Created by Administrator on 13/06/2017.
  */

import BattleField.{client, isClient, isHost, server}

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
        isHost=true
        if (!server.running) {
          Future {
            Dialog.showMessage(this, "Host Server Running on port 9999 tell opponent to play as client", "host")
            server.startServer()
          }
          Dialog.showMessage(null, "Host Server Running on port 9999 tell opponent to play as client", "host")
        }
      })
      contents += new MenuItem(Action("Play as client") {
        isClient = true
        if (!client.running) {
          Future {
            Dialog.showMessage(this, "Client Server Running on port 9998", "client")
            client.startClientServer()
          }

        }
      })
    }
    contents += new MenuItem("Reset")
  }
}
