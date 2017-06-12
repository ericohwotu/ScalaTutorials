package Advanced.BattleField

import scala.io.StdIn.readLine

/**
  * Created by Eric on 10/06/2017.
  * Battleship Stage 1
  * TODO: Implement Stage
  */
object BattleField extends App {

  /**
    * Phase 0: Initiate the board and a assign ships to player
    *
    * */
    //TODO: Implement GUI - started

  val ui = new BattleShipUI
  ui.visible = true


  val bounds = (12,12)
  var matchOver = false
  var phaseTwo = false //TODO: change to false when phase one implemented
  var playerTurn = true


  //initialise player
  println("Initiating Player please wait")
  val player1 = new Player(1)
  val cpu = new Player(2)
  player1.initBoard(bounds._1,bounds._2)
  cpu.initBoard(bounds._1,bounds._2)


  //TODO: Add Automatic ship placement for CPU player
  /*player1.placeShip((Math.random()*bounds._1).toInt,(Math.random()*bounds._2).toInt, ShipType.PATROL, Orientation.VERTICAL)
  player1.placeShip((Math.random()*bounds._1).toInt,(Math.random()*bounds._2).toInt, ShipType.PATROL, Orientation.HORIZONTAL)
  cpu.placeShip((Math.random()*bounds._1).toInt,(Math.random()*bounds._2).toInt, ShipType.PATROL, Orientation.VERTICAL)
  cpu.placeShip((Math.random()*bounds._1).toInt,(Math.random()*bounds._2).toInt, ShipType.PATROL, Orientation.VERTICAL)*/

  /*player1.placeShip(3,4, 1, ShipOrientation.VERTICAL)
  player1.placeShip(8,8, 2, ShipOrientation.HORIZONTAL)
  player1.placeShip(0,0, 7, ShipOrientation.VERTICAL)
  player1.placeShip(1,1, 3, ShipOrientation.VERTICAL)
  player1.placeShip(7,6, 4, ShipOrientation.HORIZONTAL)
  player1.placeShip(6,11, 6, ShipOrientation.HORIZONTAL)
  player1.placeShip(6,9, 5, ShipOrientation.VERTICAL)

  cpu.initBoard(bounds._1,bounds._2)
  cpu.placeShip(2,2, ShipType.PATROL, ShipOrientation.VERTICAL)
  cpu.placeShip(8,8, ShipType.PATROL, ShipOrientation.HORIZONTAL)
  cpu.placeShip(0,0, ShipType.CARRIER, ShipOrientation.VERTICAL)
  cpu.placeShip(7,5, ShipType.BATTLESHIP, ShipOrientation.HORIZONTAL)
  cpu.placeShip(7,6, ShipType.BATTLESHIP, ShipOrientation.HORIZONTAL)
  cpu.placeShip(6,11, ShipType.SUBMARINE, ShipOrientation.HORIZONTAL)
  cpu.placeShip(6,9, ShipType.DESTROYER, ShipOrientation.HORIZONTAL)*/


  //player1.board.showGrid()
  //cpu.board.showGrid()

  //game loop
  while(!matchOver) {
    while(!phaseTwo){
      playerTurn match {
        case true => setupPhase(player1)
        case false => setupPhase(cpu)
      }
    }
    if(!matchOver) matchPhase()
  }

  def setupPhase(player: Player): Unit ={
    //TODO: Implement grid to show users position of their ships - completed
    //TODO: Implement more stringent ErrorHandling
    println("To place ship (id x y orientation(v/h)) example: 1 1 3 v")
    println("Type exit to quit")
    println("Type ships to show available ships")
    println("Type grid to show Grid")

    val input = readLine("Place ship: ")

    input match{
      case "exit" | "e" | "Exit" | "E" => matchOver = true; phaseTwo = true
      case "ships" | "s" | "Ships" | "S" => println(player.ships)
      case "grid" | "g" | "Grid" | "g" => println(player.board.showGrid())
      case _ if input.split(" ").length == 4 =>
        // break the input into its parts
        val x = input.split(" ")(1).toInt
        val y = input.split(" ")(2).toInt
        val shipID = input.split(" ")(0).toInt
        var o = ShipOrientation.VERTICAL

        //check the fourth entry to see whether vertical or horizontal has been selected
        input.split(" ")(3) match {
          case "v" | "vertical" | "V" | "Vertical" => o = ShipOrientation.VERTICAL
          case "h" | "horizontal" | "H" | "Horizontal" => o = ShipOrientation.HORIZONTAL
          case _ => println("Input not registered orientation set to default")
        }
        player.placeShip(x,y,shipID, o)

      case _ => println("wrong input type")
    }
    if(player.ships.isEmpty){
      if (!playerTurn) {phaseTwo = true}
      playerTurn = !playerTurn
    }
  }

  def matchPhase(): Unit ={
    playerTurn match {
      case true => runRound(player1,cpu)
      case false => runRound(cpu,player1)
    }

    def runRound(player: Player, opponent: Player): Unit ={
      //TODO: Implement Error handling for different input combinations
      //print tries
      println(player.tries)

      // read input from file
      val input = readLine(s"$player Input Hit Coordinates (x,y): ")

      input.split(",") match {
        case arr if arr(0)==" "||arr(1)==" " => println(s"Input Error")
        case arr if arr(0).trim.toInt > (bounds._1 -1) => println(s"Max X value allowed is ${bounds._1 -1}")
        case arr if arr(1).trim.toInt > (bounds._1 -1) => println(s"Max Y value allowed is ${bounds._2 -1}")
        case arr =>
          //if there is a hit replay else next players turn
          if (! opponent.board.hit (arr(0).trim.toInt, arr(1).trim.toInt) ) playerTurn = ! playerTurn

          // add this try to the players tries
          player.tries += Tuple2 (arr(0).trim.toInt, arr(1).trim.toInt)
      }

      //check if the opponent has lost
      if(opponent.lost) matchOver = true
    }
  }

}
