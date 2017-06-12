package Advanced.BattleShipStage2

/**
  * Created by Eric on 11/06/2017.
  */

import java.awt.Color
import javax.swing.border.LineBorder

import Advanced.BattleShipStage2.BattleField.{opponent, player1, playerTurn, matchOver}

import scala.collection.mutable.ListBuffer
import scala.swing._

class BattleShipUI extends MainFrame {

  title = "Battle Ship v2"
  preferredSize = new Dimension(400, 800)

  var attackButton = initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2)
  var fieldButton = initFieldArea(player1.board.bounds._1,player1.board.bounds._2)
  var attackSection = new GridPanel(opponent.board.bounds._1,opponent.board.bounds._2)
  var fieldSection = new GridPanel(player1.board.bounds._1,player1.board.bounds._1)

  resetFields()
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
    contents += attackSection
    contents += new Label(" ")
    contents += fieldSection
  }

  def initAttackArea(x: Int, y: Int): ListBuffer[Button]={
    var attackSquares = new ListBuffer[Button]
    for(i<- 0 until x; j<- 0 until y)attackSquares+=getAttackSquare(j,i)
    attackSquares
  }

  def getAttackSquare(i: Int, j: Int): Button = {
    //println(s"Get attack = $playerTurn")
    new Button() {
      var gridSquare = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
      name = s"id-$i-$j-attack"
      focusPainted = false
      borderPainted = true
      background = Color.BLACK
      if(gridSquare.attacked == true){background = Color.WHITE; enabled = false}
      if(gridSquare.attacked == true && gridSquare.ship != null)background = Color.GREEN

      listenTo(this)
      reactions += {
        case e: event.ButtonClicked =>
          background =  opponent.board.hitc(i,j)
          contentAreaFilled = true
          enabled = false
          if (opponent.board.hitc(i,j)==Color.WHITE){playerTurn = false; resetFields()}
          if (opponent.lost) matchOver = true

          println(player1)
      }
    }
  }

  def initFieldArea(x: Int, y: Int): ListBuffer[Button]={
    var fieldSquares = new ListBuffer[Button]
    for(i<- 0 until x; j<- 0 until y)fieldSquares+=getFieldSquare(j,i)
    fieldSquares
  }

  def getFieldSquare(i: Int, j: Int): Button = {
    //println(s"Get field = $playerTurn")
    new Button() {
      var gridSquare = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
      name = s"id-$i-$j"
      focusPainted = false
      borderPainted = true
      enabled = false
      background = Color.BLUE
      if(gridSquare.ship != null)background = Color.GREEN
      if(gridSquare.attacked == true)background = Color.YELLOW
      if(gridSquare.attacked == true && gridSquare.ship != null)background = Color.RED

      //println(player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head.ship != null)
      listenTo(this)
      reactions += {
        case e: event.ButtonClicked =>
          if (player1.board.hit(i, j)) background = Color.GREEN else background = Color.WHITE
          contentAreaFilled = true
          enabled = false
      }
    }
  }

  def resetFields(): Unit ={
    //println(s"player turn = $playerTurn")
    attackButton = initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2)
    fieldButton = initFieldArea(player1.board.bounds._1,player1.board.bounds._2)

    //revalidate the attack section
    attackSection.enabled = playerTurn
    attackSection.contents.clear()
    attackSection.border = new LineBorder(Color.BLACK)
    attackButton.foreach(b=> {attackSection.contents += b})//; println(s"adding ${b.name}")})
    attackSection.revalidate()

    //revalidate the field section
    fieldSection.contents.clear()
    fieldSection.border = new LineBorder(Color.BLACK)
    fieldButton.foreach(b=> fieldSection.contents += b)
    fieldSection.revalidate()

  }

}
