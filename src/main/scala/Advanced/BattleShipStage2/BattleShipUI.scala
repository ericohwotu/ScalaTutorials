package Advanced.BattleShipStage2

/**
  * Created by Eric on 11/06/2017.
  */

import java.awt.Color
import javax.swing.border.LineBorder

import Advanced.BattleShipStage2.BattleField.{opponent, player1}

import scala.collection.mutable.ListBuffer
import scala.swing._

class BattleShipUI extends MainFrame {
  title = "Battle Ship v2"
  preferredSize = new Dimension(400, 800)
  contents = new BoxPanel(Orientation.Vertical) {
    //add menu
    menuBar = new MenuBar(){
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
    //contents += new Label("Attack Section")
    contents += new GridPanel(12, 12) {
      border = new LineBorder(Color.BLACK)
      name = "ATTACK"
      initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2).foreach(s => contents += s)
    }
    contents += new Label(" ")
    contents += new GridPanel(12, 12) {
      border = new LineBorder(Color.BLACK)

      initFieldArea(player1.board.bounds._1,player1.board.bounds._2).foreach(s => contents += s)
    }
  }

  def initAttackArea(x: Int, y: Int): ListBuffer[Button]={
    var attackSquares = new ListBuffer[Button]
    for(i<- 0 until x; j<- 0 until y)attackSquares+=getAttackSquare(j,i)
    attackSquares
  }

  def getAttackSquare(i: Int, j: Int): Button = {
    new Button() {
      name = s"id-$i-$j"
      focusPainted = false
      borderPainted = true
      background = Color.BLACK

      listenTo(this)
      reactions += {
        case e: event.ButtonClicked =>
          background =  opponent.board.hitc(i,j)
          contentAreaFilled = true
          enabled = false
      }
    }
  }

  def initFieldArea(x: Int, y: Int): ListBuffer[Button]={
    var fieldSquares = new ListBuffer[Button]
    for(i<- 0 until x; j<- 0 until y)fieldSquares+=getFieldSquare(j,i)
    fieldSquares
  }

  def getFieldSquare(i: Int, j: Int): Button = {
    new Button() {
      name = s"id-$i-$j"
      focusPainted = false
      borderPainted = true
      enabled = false
      background = Color.BLUE
      if(player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head.ship != null)background = Color.GREEN
      println(player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head.ship != null)
      listenTo(this)
      reactions += {
        case e: event.ButtonClicked =>
          if (player1.board.hit(i, j)) background = Color.GREEN else background = Color.WHITE
          contentAreaFilled = true
          enabled = false
      }
    }
  }

}
