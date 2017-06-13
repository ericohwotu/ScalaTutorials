package Advanced.BattleShipStage2

import java.awt.Color
import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{opponent, player1, playerTurn, matchOver, phaseTwo}

/**
  * Created by Administrator on 13/06/2017.
  */
class AttackSquareUI(parent: AttackSection, i: Int, j: Int, popup: PopupMenu) extends Button with PopupUpdate {
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
    val gridSquare: Square = opponent.board.squares.filter(x => x.pos_x == i && x.pos_y == j).head

    if (gridSquare.attacked) {
      background = Color.WHITE; enabled = false
    }
    if (gridSquare.attacked && gridSquare.ship != null) background = Color.GREEN

    if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.GRAY

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
          playMiss()
        }else{
          playHit()
        }
        if (opponent.lost) matchOver = true
        parent.update();

        println(player1)
    }
  }

}
