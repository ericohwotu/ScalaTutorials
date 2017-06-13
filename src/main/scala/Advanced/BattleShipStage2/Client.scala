package Advanced.BattleShipStage2

import java.io._
import java.net._

import scala.io._

/**
  * Created by Eric on 13/06/2017.
  */
class Client {
  var run: Boolean = true

  def startClientServer(): Unit ={
    val server = new ServerSocket(9998)
    while (run) {
      var player: Player = null
      val s = server.accept()
      println("recieved")
      val in = new ObjectInputStream(s.getInputStream())
      println("recieved")
      val out = new PrintStream(s.getOutputStream())
      println("recieved")
      player = (Player) in.readObject()
      println("recieved")
      out.println("nice to meet you")
      out.flush()
      println("Received: ")
      s.close()
    }
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

  def sendPlayerInfo(): Unit ={
    val s = new Socket(InetAddress.getByName("localhost"), 9999)
    val in = new BufferedSource(s.getInputStream()).getLines()
    val out = new PrintStream(s.getOutputStream())
    out.println("sendPlayerInfo")
    out.flush()
    println(in.next())
    s.close()
  }

  def attackShip(): Unit ={

  }

}
