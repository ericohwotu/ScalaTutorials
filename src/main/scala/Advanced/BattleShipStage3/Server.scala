package Advanced.BattleShipStage3

import java.io._
import java.net._

import BattleField.{opponent, player1,readyCount}

import scala.swing._

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
      println("Host running")
      val s = server.accept()
      //read input stream
      val in = new ObjectInputStream(s.getInputStream())
      val player2 = in.readObject().asInstanceOf[Player]
      opponent = player2

      //write output stream
      //val out = new ObjectOutputStream(s.getOutputStream())
      //out.writeObject(player1)
      //out.flush()
      println("recieved")
      readyCount += 1
      Dialog.showMessage(null,s"${opponent.shipsInPlay.length} + the ships left are ${opponent.shipsInPlay.length}", "Recieved")

//      println("Received: " + player2.id)
      s.close()
    }
    running = false
  }

  def stopServer(): Unit ={
    run = false
  }


  def sendPlayerInfo(player: Player): Unit ={
    val s = new Socket(InetAddress.getByName("localhost"), 9998)
    //recieve the data coming in
    //val in = new ObjectInputStream(s.getInputStream())
    //val player1 = in.readObject().asInstanceOf[Player]
    //opponent = player1

    //send the data
    val out = new ObjectOutputStream(s.getOutputStream())
    out.writeObject(player)
    out.flush()

    Dialog.showMessage(null,s"${opponent.shipsInPlay.length} + the ships left are ${opponent.shipsInPlay.length}", "Sent")

    s.close()
  }

}
