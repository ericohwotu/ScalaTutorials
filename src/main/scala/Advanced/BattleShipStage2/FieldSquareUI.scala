package Advanced.BattleShipStage2

import java.awt.Color
import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{opponent, player1, playerTurn, matchOver, closeUi}

/**
  * Created by Administrator on 13/06/2017.
  */
class FieldSquareUI(i: Int, j: Int) extends Button{
  init()
  addListener()

  def init(): Unit =  {
    name = s"$i,$j"
    focusPainted = false
    enabled = false
    background = Color.BLUE
  }

  def update(): Unit ={
    val gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head

    if(gridSquare.ship != null)background = Color.GREEN
    if(gridSquare.attacked)background = Color.WHITE
    if(gridSquare.attacked && gridSquare.ship != null)background = Color.RED
    revalidate()
  }
  def addListener() : Unit = {
    listenTo(this)
    reactions += {
      case _: event.ButtonClicked =>

        if (player1.board.hit(i, j)) background = Color.GREEN else background = Color.WHITE
        contentAreaFilled = true
        enabled = false
    }
  }
}
