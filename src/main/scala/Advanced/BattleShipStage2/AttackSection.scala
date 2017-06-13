package Advanced.BattleShipStage2

import java.awt.Color
import javax.swing.border.LineBorder

import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{closeUi, matchOver, opponent, player1, playerTurn}
/**
  * Created by Administrator on 13/06/2017.
  */

class AttackSection(x:Int, y:Int) extends GridPanel(x,y){

  var attackButtons: ListBuffer[AttackSquareUI] = init(opponent.board.bounds._1,opponent.board.bounds._2)

  //initialise area
  def init(x: Int, y: Int): ListBuffer[AttackSquareUI]={

    contents.clear()

    background = Color.DARK_GRAY

    border = new LineBorder(Color.DARK_GRAY)

    var attackSquares = new ListBuffer[AttackSquareUI]

    for(i<- 0 until x; j<- 0 until y)attackSquares+=new AttackSquareUI(j,i)

    attackSquares.foreach(b=> {contents += b})

    revalidate()

    attackSquares
  }

  //update squares in area
  def update(): Unit ={
    attackButtons.foreach(x => x.update())
  }

  //function to get the individual squares
//  def getAttackSquare(i: Int, j: Int): Button = {
    //println(s"Get attack = $playerTurn")
//    new Button() {
//      var gridSquare: Square = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
//      name = s"$i-$j"
//      focusPainted = false
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

  //update each square in the section
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


}
