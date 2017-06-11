package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */
import java.awt.Dimension
import javax.swing._

class BattleShipUI extends JFrame {
  setTitle("Battle Ship")
  setSize(new Dimension(320, 240))
  var cp = getContentPane
  cp.add(new JLabel("Here is the contents!"))
}
