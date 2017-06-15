package Advanced.BattleShipStage3

import java.io._
import java.net._

import BattleField.{ui, opponent, player1, playerTurn, readyCount, clientAddress}

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
      running = true

      println("Client running")

      val s = server.accept()

      //read input stream
      val in = new ObjectInputStream(s.getInputStream())

      val player2 = in.readObject().asInstanceOf[Player]

      opponent = player2

      //process data and close socket
      readyCount += 1

      //Dialog.showMessage(null,s"${opponent.shipsInPlay.length} + the ships left are ${opponent.shipsInPlay.length}", "Recieved")

      println("Received: " + player2)

      run = false

      s.close()
    }
    running = false
    startAttackServer()
  }

  def startAttackServer(): Unit ={
    run=true
    val server = new ServerSocket(9997)
    println("Start Attack Server running")
    while (run) {

      running = true

      println("Attack Client running")

      val s = server.accept()
      playerTurn = true
      //read input stream
      val in = new BufferedSource(s.getInputStream()).getLines()
      playerTurn = true
      val position = in.next()

      val x = position.split(",").head.toInt

      val y = position.split(",")(1).toInt

      //Dialog.showMessage(null,s"Attacking Player (x,y) $x and $y", "Received")

      if(!player1.board.hit(x,y))playerTurn = true else playerTurn = false

      ui.updateAllButtons()

      println("Been Attacked")

      s.close()
    }
    running = false
  }

  def stopServer(): Unit ={
    run = false
  }


  def sendPlayerInfo(player: Player): Unit ={
    val s = new Socket(InetAddress.getByName(clientAddress), 9998)
    //receive the data coming in
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
    //Dialog.showMessage(null,s"Attacking Player (x,y) $x and $y", "Sent")
    val s = new Socket(InetAddress.getByName(clientAddress), 9996)
    //val in = new BufferedSource(s.getInputStream()).getLines()
    println(s"attacking player at $x and $y")

    val out = new PrintStream(s.getOutputStream())
    out.println(s"$x,$y")
    out.flush()

    s.close()
  }
}
