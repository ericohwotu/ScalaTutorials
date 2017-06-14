package Advanced.BattleShipStage3

import java.io._
import java.net._

import BattleField.{opponent,readyCount}

import scala.io._
import scala.swing._

/**
  * Created by Eric on 13/06/2017.
  */
class Client {
  var run: Boolean = true
  var running: Boolean = false
  def startClientServer(): Unit ={
    val server = new ServerSocket(9998)
    while (run) {
      running = true
      println("Client running")
      val s = server.accept()
      //read input stream
      val in = new ObjectInputStream(s.getInputStream())
      val player2 = in.readObject().asInstanceOf[Player]
      opponent = player2
      //opponent = player2

      //write output stream
//      val out = new PrintStream(s.getOutputStream())
//      out.println(player2)
//      out.flush()
      Dialog.showMessage(null,s"${opponent.shipsInPlay.length} + the ships left are ${opponent.shipsInPlay.length}", "Recieved")
      readyCount += 1
      println("Received: " + player2)
      s.close()
    }
    running = false
  }
  def stopServer(): Unit ={
    run = false
  }

  def attackPlayer(): Unit = {
    val s = new Socket(InetAddress.getByName("localhost"), 9999)
    val in = new BufferedSource(s.getInputStream()).getLines()
    val out = new PrintStream(s.getOutputStream())
    out.println("attack player ()")
    out.flush()
    println(in.next())
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

  def attackShip(): Unit ={
    println("Attack")
  }

}
