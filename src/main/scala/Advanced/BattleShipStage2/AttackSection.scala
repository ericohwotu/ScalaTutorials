package Advanced.BattleShipStage2

import java.awt.Color
import javax.swing.border.LineBorder

import scala.collection.mutable.ListBuffer
import scala.swing._
/**
  * Created by Administrator on 13/06/2017.
  */

class AttackSection(x:Int, y:Int, popupMenu: PopupMenu) extends GridPanel(x,y){

  var attackButtons: ListBuffer[AttackSquareUI] = init(x,y)


  //initialise area
  def init(x: Int, y: Int): ListBuffer[AttackSquareUI]={

    contents.clear()

    background = Color.DARK_GRAY

    border = new LineBorder(Color.DARK_GRAY)

    var attackSquares = new ListBuffer[AttackSquareUI]

    for(i<- 0 until x; j<- 0 until y)attackSquares+=new AttackSquareUI(j,i,popupMenu)

    //add each square to the layout
    attackSquares.foreach(b=> {contents += b})

    //update the field with the new settings
    revalidate()

    attackSquares
  }

  //update squares in area
  def update(): Unit ={

    attackButtons.foreach(x => x.update())
  }
}
