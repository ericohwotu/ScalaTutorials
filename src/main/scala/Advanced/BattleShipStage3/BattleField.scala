package Advanced.BattleShipStage3

import scala.io.StdIn.readLine

/**
  * Created by Eric on 10/06/2017.
  * Battleship Stage 1
  * TODO: Implement Stage
  */
object BattleField extends App with SetupPhase with MatchPhase {

  /**
    * Phase 0: Initiate the board and a assign ships to player
    *
    **/
  //TODO: Implement GUI - completed
  //global variable declaration: variables used in multiple classes in the project
  val bounds = (12, 12)
  var matchOver = false
  var phaseTwo = false
  var playerTurn = true
  var playAgainstCpu = false
  var isHost = false
  var isClient = false
  var ready = false
  var readyCount = 0;

  //initialise player
  println("Initiating Player please wait")
  var player1 = new Player(1)
  var opponent = new Player(2)
  player1.initBoard(bounds._1, bounds._2)
  opponent.initBoard(bounds._1, bounds._2)

  //instantiate server handlers
  val server = new Server()
  val client = new Client()


  //show the user interface
  val ui = new BattleShipUI
  ui.visible = true
  ui.updateAllButtons()

  //game loop
  while (!matchOver) {
    //phase one
    while (!phaseTwo) {
      // check if playerTurn
      if (!playAgainstCpu) {
        if (isHost) playerTurn = true
        if (isClient) playerTurn = false
      }

      //check if its the current players turn
      println(s"ready:$ready readyCount:$readyCount host:$isHost or client:$isClient my turn:$playerTurn")
      if (player1.ships.length == 0 && ready == false) {

        //check the amount of people ready
        //max is two people
        ready = true
        readyCount += 1

        //send player info over network -  works
        if (isClient) client.sendPlayerInfo(player1)
        if (isHost) server.sendPlayerInfo(player1)
      }

      if (readyCount == 2 && !playAgainstCpu) {
        phaseTwo = true
        ui.showPhaseTwoDialog()
        ui.updateAllButtons()
      }

    }

    println(s"Phase2:$phaseTwo ready:$ready readyCount:$readyCount host:$isHost or client:$isClient my turn:$playerTurn")
  }
  var winner = player1.toString
  player1.lost match {
    case true => winner = opponent.toString
    case false => winner = player1.toString
  }
  ui.showEndDialog(winner)
  //ui.close()

  //------------------------------------------------- function definitions --------------------------------------------//
  def placeAllShips(player: Player): Unit = {
    for (i <- 1 to 7) {
      //declare variables for each ship
      var x: Int = 0
      var y: Int = 0
      var o = ShipOrientation.VERTICAL

      do {
        x = (Math.random() * bounds._1).toInt
        y = (Math.random() * bounds._2).toInt
        (Math.random() * 2).toInt match {
          case 0 => o = ShipOrientation.VERTICAL
          case 1 => o = ShipOrientation.HORIZONTAL
          case _ => println(s"Oops placeAllShips: $i")
        }

      } while (!player.placeShip(x, y, i, o))
    }
  }

  def closeUi(): Unit = {
    ui.close()
  }
}
