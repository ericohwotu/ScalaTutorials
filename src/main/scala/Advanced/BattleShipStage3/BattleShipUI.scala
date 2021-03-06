package Advanced.BattleShipStage3

import java.awt.Color

import BattleField.{closeUi, opponent, player1}

import scala.swing._

/**
  * Created by Eric on 11/06/2017.
  */
class BattleShipUI extends MainFrame {
  //TODO: Rounded buttons for hit RoundButton()
  //TODO: Stop Stuttering () update buttons by name - completed

  val popup: PopupMenu = new PopupMenu

  title = "Battle Ship v3"
  preferredSize = new Dimension(400, 800) //opening dimension ? ecuates to size in javax.swing

  //initialise the field areas
  var attackSection: AttackSection = new AttackSection(opponent.board.bounds._1,opponent.board.bounds._2,popup)
  var fieldSection: FieldSection = new FieldSection(player1.board.bounds._1,player1.board.bounds._1,popup)

  //add to the contents
  contents = new BoxPanel(Orientation.Vertical) {
    background = Color.DARK_GRAY

    //add menu
    menuBar = new GameMenuBar

    // add attack section
    contents += attackSection

    // add Separator
    contents += new Label(" ")

    // add field section
    contents += fieldSection
  }


  def updateAllButtons(): Unit ={

    attackSection.update()

    fieldSection.update()
  }

  def showPhaseTwoDialog(): Unit ={
    Dialog.showMessage(contents.head,s"Setup Complete: Start Phase 2",title="Setup Complete")
    updateAllButtons()
  }

  def showEndDialog(winner: String): Unit={

    val s = Dialog.showConfirmation(contents.head,s"$winner is the winner",title="Game Over")

    if (s == Dialog.Result.Ok){
      closeUi()
    }
  }

}
