package Advanced.BattleShipStage2

import java.awt.Color
import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{opponent, player1, playerTurn, matchOver, closeUi}

/**
  * Created by Administrator on 13/06/2017.
  */
class AttackSquareUI(i: Int, j: Int) extends Button {
  //initialise and add listener once
  init()
  addListener()

  //initialise the square UI
  def init() {
    name = s"$i-$j"
    focusPainted = false
    background = Color.BLACK
  }

  //update the field
  def update(): Unit = {
    //val i = name.split("-")(0).toInt
    //val j = name.split("-")(1).toInt
    val gridSquare: Square = opponent.board.squares.filter(x => x.pos_x == i && x.pos_y == j).head

    if (gridSquare.attacked) {
      background = Color.WHITE; enabled = false
    }
    if (gridSquare.attacked && gridSquare.ship != null) background = Color.GREEN
    //if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.RED
    revalidate()
  }

  // add listener and reaction
  def addListener() {
    listenTo(this)
    reactions += {
      case e: event.ButtonClicked =>
        background = opponent.board.hitc(i, j)
        contentAreaFilled = true
        enabled = false
        if (background == Color.WHITE) {
          playerTurn = false
        }
        if (opponent.lost) matchOver = true

        println(player1)
    }
  }

}
