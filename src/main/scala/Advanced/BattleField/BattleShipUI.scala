package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */

import scala.swing._
import BattleField.cpu

class BattleShipUI extends MainFrame {
  var bf = BattleField
  title = "Battle Ship"
  preferredSize = new Dimension(400, 800)
  contents = new BoxPanel(Orientation.Vertical){

      //contents += new Label("Attack Section")
      contents += new GridPanel(12,12) {
        for (i <- 0 until 12; j<- 0 until 12) contents += new Button() {
          focusPainted = false
          borderPainted = true
          contentAreaFilled = false
          println(s"$i + $j")
        }
      }
      contents += new Label(" ")
      contents += new GridPanel(12,12) {
        for (i <- 1 to 144) contents += new Button() {
          focusPainted = false
          borderPainted = true
          contentAreaFilled = false
        }
      }
    }


}
