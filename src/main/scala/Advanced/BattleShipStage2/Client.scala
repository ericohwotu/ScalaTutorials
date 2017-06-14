package Advanced.BattleShipStage2

import java.io._
import java.net._
import scala.io._
import BattleField.{opponent}

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
      val s = server.accept()
      //read input stream
      val in = new BufferedSource(s.getInputStream()).getLines()
      val player2 = in.next()
      //opponent = player2

      //write output stream
      val out = new PrintStream(s.getOutputStream())
      out.println(player2)
      out.flush()

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
    val in = new ObjectInputStream(s.getInputStream())
    val player1 = in.readObject().asInstanceOf[Player]
    opponent = player1

    //send the data
    val out = new ObjectOutputStream(s.getOutputStream())
    out.writeObject(player)
    out.flush()

    s.close()
  }

  def attackShip(): Unit ={
    println("Attack")
  }

}
