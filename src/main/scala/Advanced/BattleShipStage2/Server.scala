package Advanced.BattleShipStage2

import java.net._
import java.io._
import scala.io._
import BattleField.{player1,opponent}
/**
  * Created by Eric on 13/06/2017.
  */
class Server {
  var run: Boolean = true
  var running: Boolean = false


  def startServer() {
    running=true
    val server = new ServerSocket(9999)
    println("starting server")
    while (run) {
      val s = server.accept()
      //read input stream
      val in = new ObjectInputStream(s.getInputStream())
      val player2 = in.readObject().asInstanceOf[Player]
      opponent = player2

      //write output stream
      val out = new ObjectOutputStream(s.getOutputStream())
      out.writeObject(player1)
      out.flush()

//      println("Received: " + player2.id)
      s.close()
    }
    running = false
  }

  def stopServer(): Unit ={
    run = false
  }


  def sendPlayerInfo(player: Player): Unit ={
//    println("writing")
//    val s = new Socket(InetAddress.getByName("localhost"), 9998)
//    println("writing")
//    val in = new BufferedSource(s.getInputStream()).getLines()
//    println("writing")
//    val out = new ObjectOutputStream(s.getOutputStream())
//    println("writing")
//    out.writeObject(player)
//    println("writing")
//    out.flush()
//    println("writing")
//    //println(in.next())
//    println("writing")
//    s.close()
//    println("writing")
//    println("closed")
  }

}
