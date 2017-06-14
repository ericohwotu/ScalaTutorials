package Advanced.BattleShipStage3

import java.io.{ObjectOutputStream, PrintStream}
import java.net.{InetAddress, Socket}

import BattleField.{opponent, player1, playerTurn, readyCount}

import scala.swing.Dialog
/**
  * Created by Administrator on 14/06/2017.
  */
trait SocketActions {

  def attackPlayer(x: Int, y: Int): Unit = {
    val s = new Socket(InetAddress.getByName("localhost"), 9999)
    //val in = new BufferedSource(s.getInputStream()).getLines()
    val out = new PrintStream(s.getOutputStream())

    out.println(s"$x,$y")
    out.flush()

    playerTurn = false
    s.close()
  }

  def sendPlayerInfo(player: Player): Unit ={
    val s = new Socket(InetAddress.getByName("localhost"), 9999)
    //recieve the data coming in
    //    val in = new ObjectInputStream(s.getInputStream())
    //    val player1 = in.readObject().asInstanceOf[Player]
    //    opponent = player1

    //send the data
    val out = new ObjectOutputStream(s.getOutputStream())
    out.writeObject(player)
    out.flush()

    Dialog.showMessage(null,s"${opponent.shipsInPlay.length} + the ships left are ${opponent.shipsInPlay.length}", "sent")

    s.close()
  }
}
