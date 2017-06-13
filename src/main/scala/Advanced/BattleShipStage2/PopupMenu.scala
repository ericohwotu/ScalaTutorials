package Advanced.BattleShipStage2

/**
  * Created by Administrator on 13/06/2017.
  */
import scala.swing._
import javax.swing.JPopupMenu

class PopupMenu extends Component
{
  override lazy val peer : JPopupMenu = new JPopupMenu


  def add(item:MenuItem) : Unit = { peer.add(item.peer) }
  def setVisible(visible:Boolean, x: Int,y: Int) : Unit = {
    peer.setVisible(visible)
    peer.setLocation(x,y)
  }
  def closeNow(): Unit ={
    peer.setVisible(false)
  }

  /* Create any other peer methods here */
}
