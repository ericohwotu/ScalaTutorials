package Advanced.BattleShipStage3

import java.awt.Color

import BattleField.{matchOver, opponent, phaseTwo, playerTurn, isHost, server, isClient, client}

import scala.swing._

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
    if(!phaseTwo)enabled = false else enabled = true
  }

  //update the field
  def update(): Unit = {
    val gridSquare: Square = opponent.board.squares.filter(x => x.pos_x == i && x.pos_y == j).head
    if(!playerTurn)enabled = false else enabled = true

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
        if(isHost)server.attackPlayer(i,j)
        if(isClient)client.attackPlayer(i,j)
        parent.update()
        println(opponent.shipsInPlay)
    }
  }

}
