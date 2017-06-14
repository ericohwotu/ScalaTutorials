package Advanced.BattleShipStage3

import java.io._
import java.net._

import BattleField.{opponent, player1, playerTurn, readyCount}

import scala.io.BufferedSource
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
    startAttackServer()
  }

  def startAttackServer(): Unit ={
    run=true
    val server = new ServerSocket(9999)
    while (run) {

      running = true

      println("Attack Client running")

      val s = server.accept()

      //read input stream
      val in = new BufferedSource(s.getInputStream()).getLines()

      val position = in.next()

      val x = position.split(",").head.toInt

      val y = position.split(",")(1).toInt

      player1.board.hit(x,y)

      println("Been Attacked")

      playerTurn = true
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

  def attackPlayer(x: Int, y: Int): Unit = {
    val s = new Socket(InetAddress.getByName("localhost"), 9998)
    //val in = new BufferedSource(s.getInputStream()).getLines()
    val out = new PrintStream(s.getOutputStream())
    out.println(s"$x,$y")
    out.flush()

    playerTurn = false
    s.close()
  }
}
