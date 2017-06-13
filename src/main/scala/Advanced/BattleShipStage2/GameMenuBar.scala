package Advanced.BattleShipStage2

/**
  * Created by Administrator on 13/06/2017.
  */
import scala.swing._

class GameMenuBar extends MenuBar{
  //background = Color.LIGHT_GRAY
  contents += new Menu("Game"){
    contents += new Menu("Play vs CPU"){
      contents += new MenuItem("Easy")
      contents += new MenuItem("Medium")
      contents += new MenuItem("Hard")
      contents += new MenuItem("You Will Lose")
    }
    contents += new CheckMenuItem("Play vs Player")
    contents += new MenuItem("Reset")
  }
}
