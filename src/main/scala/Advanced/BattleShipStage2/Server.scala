package Advanced.BattleShipStage2

import java.net._
import java.io._
import scala.io._
/**
  * Created by Eric on 13/06/2017.
  */
class Server {
  var run: Boolean = true
  def startServer() {
    val server = new ServerSocket(9999)
    while (run) {
      val s = server.accept()
      val in = new BufferedSource(s.getInputStream()).getLines()
      val out = new PrintStream(s.getOutputStream())
      val x = in.next()
      out.println(x + "nice to meet you")
      out.flush()
      println("Received: " + x)
      s.close()
    }
  }

  def stopServer(): Unit ={
    run = false
  }

  def sendPlayerInfo(player: Player): Unit ={
    println("writing")
    val s = new Socket(InetAddress.getByName("localhost"), 9998)
    println("writing")
    val in = new BufferedSource(s.getInputStream()).getLines()
    println("writing")
    val out = new ObjectOutputStream(s.getOutputStream())
    println("writing")
    out.writeObject(player)
    println("writing")
    out.flush()
    println("writing")
    //println(in.next())
    println("writing")
    s.close()
    println("writing")
    println("closed")
  }

}
