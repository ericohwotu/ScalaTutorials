package Advanced.BattleShipStage2

/**
  * Created by Administrator on 13/06/2017.
  */

import java.awt.Color
import javax.swing.border.LineBorder

import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{closeUi, matchOver, opponent, player1, playerTurn}


class FieldSection(x: Int, y: Int) extends GridPanel(x,y) {
  var fieldButtons: ListBuffer[FieldSquareUI] = init(opponent.board.bounds._1,opponent.board.bounds._2)

  //initialise area
  def init(x: Int, y: Int): ListBuffer[FieldSquareUI]={
    contents.clear()

    background = Color.DARK_GRAY

    border = new LineBorder(Color.DARK_GRAY)

    var fieldSquares = new ListBuffer[FieldSquareUI]

    for(i<- 0 until x; j<- 0 until y)fieldSquares+= new FieldSquareUI(j,i)

    fieldSquares.foreach(b=> {contents += b})

    revalidate()

    fieldSquares
  }

  //update squares in area
  def update(): Unit ={
    fieldButtons.foreach(x => x.update())
  }

}
