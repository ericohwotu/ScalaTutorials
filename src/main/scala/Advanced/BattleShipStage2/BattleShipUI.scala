package Advanced.BattleShipStage2

/**
  * Created by Eric on 11/06/2017.
  */

import java.awt.Color
import javax.swing.border.LineBorder

import Advanced.BattleShipStage2.BattleField.{opponent, player1, playerTurn, matchOver, closeUi}

import scala.collection.mutable.ListBuffer
import scala.swing._

class BattleShipUI extends MainFrame {
  //TODO: Rounded buttons for hit RoundButton()
  //TODO: Stop Stuttering () update buttons by name
  title = "Battle Ship v2"
  preferredSize = new Dimension(400, 800)

  var attackButton: ListBuffer[Button] = initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2)
  var fieldButton: ListBuffer[Button] = initFieldArea(player1.board.bounds._1,player1.board.bounds._2)
  var attackSection: GridPanel = new GridPanel(opponent.board.bounds._1,opponent.board.bounds._2)
  var fieldSection: GridPanel = new GridPanel(player1.board.bounds._1,player1.board.bounds._1)


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
      var gridSquare: Square = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
      name = s"$i-$j"
      focusPainted = false
      borderPainted = true
      background = Color.BLACK

      listenTo(this)
      reactions += {
        case e: event.ButtonClicked =>
          background =  opponent.board.hitc(i,j)
          contentAreaFilled = true
          enabled = false
          if (background==Color.WHITE){playerTurn = false}
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
      var gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head
      name = s"$i,$j"
      focusPainted = false
      borderPainted = true
      enabled = false
      background = Color.BLUE
      /*if(gridSquare.ship != null)background = Color.GREEN
      if(gridSquare.attacked)background = Color.WHITE
      if(gridSquare.attacked && gridSquare.ship != null)background = Color.RED
      //if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.BLACK*/

      //println(player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head.ship != null)
      listenTo(this)
      reactions += {
        case _: event.ButtonClicked =>

          if (player1.board.hit(i, j)) background = Color.GREEN else background = Color.WHITE
          contentAreaFilled = true
          enabled = false
      }
    }
  }

  def updateAllButtons(): Unit ={
    attackButton.foreach(x => updateAttackSquare(x))
    fieldButton.foreach(y => updateFieldSquare(y))
  }

  def updateAttackSquare(button: Button): Unit ={

    val i = button.name.split("-")(0).toInt
    val j = button.name.split("-")(1).toInt
    val gridSquare: Square = opponent.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head

    if(gridSquare.attacked){button.background = Color.WHITE; button.enabled = false}
    if(gridSquare.attacked && gridSquare.ship != null)button.background = Color.GREEN
    //if(gridSquare.ship != null && gridSquare.ship.destroyed && gridSquare.attacked == true)background = Color.RED
    button.revalidate()
  }
  def updateFieldSquare(button: Button): Unit ={
    val i = button.name.split(",")(0).toInt
    val j = button.name.split(",")(1).toInt
    val gridSquare: Square = player1.board.squares.filter(x=> x.pos_x==i && x.pos_y==j).head

    if(gridSquare.ship != null)button.background = Color.GREEN
    if(gridSquare.attacked)button.background = Color.WHITE
    if(gridSquare.attacked && gridSquare.ship != null)button.background = Color.RED
    button.revalidate()
  }

  def resetFields(): Unit ={
    //println(s"player turn = $playerTurn")
    attackButton = initAttackArea(opponent.board.bounds._1,opponent.board.bounds._2)
    fieldButton = initFieldArea(player1.board.bounds._1,player1.board.bounds._2)

    //revalidate the attack section
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

  def showEndDialog(winner: String): Unit={
    //var s = Dialog.showMessage(contents.head, s"$winner is the winner", title="Game Over")
    val s = Dialog.showConfirmation(contents.head,s"$winner is the winner",title="Game Over")
    if (s == Dialog.Result.Ok){
      closeUi()
    }
  }

}
