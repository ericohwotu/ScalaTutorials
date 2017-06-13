package Advanced.BattleShipStage2

import java.awt.Color

import scala.collection.mutable.ListBuffer
import scala.swing._
import Advanced.BattleShipStage2.BattleField.{phaseTwo, matchOver, opponent, player1, playerTurn}

import scala.swing.event.{MouseClicked, MousePressed}

/**
  * Created by Administrator on 13/06/2017.
  */
class FieldSquareUI(parent: FieldSection, i: Int, j: Int, popup: PopupMenu) extends Button with PopupUpdate{

  init()
  addListener()
  addMouseListener()


  def init(): Unit =  {
    name = s"$i,$j"
    focusPainted = false
    if(!phaseTwo)enabled = true else enabled = false
    background = Color.BLUE
  }

  def update(): Unit ={
    val gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head

    if(!phaseTwo)enabled = true else enabled = false
    //println(player1.ships)
    if(gridSquare.ship != null)background = Color.GREEN
    if(gridSquare.attacked)background = Color.WHITE
    if(gridSquare.attacked && gridSquare.ship != null)background = Color.RED
    if(gridSquare.attacked && gridSquare.ship != null && gridSquare.ship.destroyed)background = Color.GRAY

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

  def addMouseListener(): Unit = {
    listenTo(mouse.clicks, mouse.moves)
    reactions += {
      case evt: MousePressed if evt.peer.getButton == 3 =>
        var pos = evt.peer.getLocationOnScreen
        initPopup(parent, popup, player1, i, j)
        popup.show(this, 0, this.bounds.height)

        //matchOver = true
      case e =>
        println(s"weeee ${e.getClass}")
        //matchOver = true
    }
  }
}

