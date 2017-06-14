package Advanced.BattleShipStage2

/**
  * Created by Administrator on 13/06/2017.
  */

import scala.swing._
import scala.concurrent.Future
import scala.io.StdIn.readLine
import scala.concurrent.ExecutionContext.Implicits.global

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
        println("playing as host")
      })
      contents += new MenuItem(Action("Play as client") {
        println("playing as client")
      })
    }
    contents += new MenuItem("Reset")
  }
}
