package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */

import scala.swing._
import BattleField.{opponent, player1}
import java.awt.Color
import javax.swing.border.LineBorder

import scala.collection.mutable.ListBuffer
import scala.swing.event.MouseClicked

class BattleShipUI extends MainFrame {
  title = "Battle Ship"
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

//  def updateAttackSquare(button: Button): Unit ={
//
//    val i = button.name.split("-")(0).toInt
//    val j = button.name.split("-")(1).toInt
//    val gridSquare: Square = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
//
//    if(gridSquare.attacked){button.background = Color.WHITE; button.enabled = false}
//    if(gridSquare.attacked && gridSquare.ship != null)button.background = Color.GREEN
//    //if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.RED
//    button.revalidate()
//  }
//  def updateFieldSquare(button: Button): Unit ={
//    val i = button.name.split(",")(0).toInt
//    val j = button.name.split(",")(1).toInt
//    val gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
//
//    if(gridSquare.ship != null)button.background = Color.GREEN
//    if(gridSquare.attacked)button.background = Color.WHITE
//    if(gridSquare.attacked && gridSquare.ship != null)button.background = Color.RED
//    button.revalidate()
//  }

//def resetFields(): Unit ={
  //    //println(s"player turn = $playerTurn")
  //    attackButton = initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2)
  //    fieldButton = initFieldArea(player1.board.bounds._1,player1.board.bounds._2)

  //revalidate the attack section
  //attackSection.contents.clear()
  //attackSection.background = Color.DARK_GRAY
  //    attackSection.border = new LineBorder(Color.DARK_GRAY)
  //    //attackButton.foreach(b=> {attackSection.contents += b})//; println(s"adding ${b.name}")})
  //    attackSection.revalidate()

  //revalidate the field section
  //    fieldSection.contents.clear()
  //    fieldSection.background = Color.DARK_GRAY
  //    fieldSection.border = new LineBorder(Color.DARK_GRAY)
  //    fieldButton.foreach(b=> fieldSection.contents += b)
  //    fieldSection.revalidate()
//}

//  def initAttackArea(x: Int, y: Int): ListBuffer[Button]={
//    var attackSquares = new ListBuffer[Button]
//    for(i<- 0 until x; j<- 0 until y)attackSquares+=getAttackSquare(j,i)
//    attackSquares
//  }

//  def getAttackSquare(i: Int, j: Int): Button = {
//    //println(s"Get attack = $playerTurn")
//    new Button() {
//      var gridSquare: Square = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
//      name = s"$i-$j"
//      focusPainted = false
//      borderPainted = true
//      background = Color.BLACK
//
//      listenTo(this)
//      reactions += {
//        case e: event.ButtonClicked =>
//          background =  opponent.board.hitc(i,j)
//          contentAreaFilled = true
//          enabled = false
//          if (background==Color.WHITE){playerTurn = false}
//          if (opponent.lost) matchOver = true
//
//          println(player1)
//      }
//    }
//  }

//  def initFieldArea(x: Int, y: Int): ListBuffer[Button]={
//    var fieldSquares = new ListBuffer[Button]
//    for(i<- 0 until x; j<- 0 until y)fieldSquares+=getFieldSquare(j,i)
//    fieldSquares
//  }

//  def getFieldSquare(i: Int, j: Int): Button = {
//    //println(s"Get field = $playerTurn")
//    new Button() {
//      var gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
//      name = s"$i,$j"
//      focusPainted = false
//      borderPainted = true
//      enabled = false
//      background = Color.BLUE
//      /*if(gridSquare.ship != null)background = Color.GREEN
//      if(gridSquare.attacked)background = Color.WHITE
//      if(gridSquare.attacked && gridSquare.ship != null)background = Color.RED
//      //if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.BLACK*/
//
//      //println(player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head.ship != null)
//      listenTo(this)
//      reactions += {
//        case _: event.ButtonClicked =>
//
//          if (player1.board.hit(i, j)) background = Color.GREEN else background = Color.WHITE
//          contentAreaFilled = true
//          enabled = false
//      }
//    }
//  }