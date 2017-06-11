package Advanced.BattleField

import scala.io.StdIn.{readLine}

/**
  * Created by Eric on 10/06/2017.
  * Battleship Stage 1
  */
object BattleField extends App {

  /**
    * Phase 0: Initiate the board and a assign ships to player
    */
  val bounds = (3,3)
  var matchOver = false
  var phaseTwo = true //TODO: change to false when phase one implemented
  var playerTurn = true

  //initialise player
  println("Initiating Player please wait")
  val player1 = new Player(1)
  val cpu = new Player(2)
  player1.initBoard(bounds._1,bounds._2)
  cpu.initBoard(bounds._1,bounds._2)

  //TODO: Add Automatic ship placement for CPU player
  player1.placeShip((Math.random()*3).toInt,(Math.random()*3).toInt, ShipType.PATROL, Orientation.VERTICAL)
  player1.placeShip((Math.random()*3).toInt,(Math.random()*3).toInt, ShipType.PATROL, Orientation.VERTICAL)
  cpu.placeShip((Math.random()*3).toInt,(Math.random()*3).toInt, ShipType.PATROL, Orientation.VERTICAL)
  cpu.placeShip((Math.random()*3).toInt,(Math.random()*3).toInt, ShipType.PATROL, Orientation.VERTICAL)

  //game loop
  while(!matchOver) {
    while(!phaseTwo){
      //setupPhase() // prompt user to place there ships
    }
    matchPhase()
  }

  def setupPhase(): Unit ={
    //TODO: Implement grid to show users position of their ships
    //TODO: Implement more stringent ErrorHandling
    println("Place ship by typing the following syntax (x y id orientation(v/h)) example: 1 1 3 v:")
    println(player1.ships)
    val input = readLine("Place ship (Type exit to quit): ")

    input match{
      case "exit" => matchOver = true; phaseTwo = true
      case _ if input.contains("exit") => matchOver = true
      case _ if input.split(" ").length == 4 => {
        // break the input into its parts
        val x = input.split(" ")(0).toInt
        val y = input.split(" ")(1).toInt
        val shipID = input.split(" ")(2).toInt
        var o = Orientation.VERTICAL

        //check the fourth entry to see whether vertical or horizontal has been selected
        input.split(" ")(3) match {
          case "v" | "vertical" | "V" | "Vertical" => o = Orientation.VERTICAL
          case "h" | "horizontal" | "H" | "Horizontal" => o = Orientation.HORIZONTAL
          case _ => println("Input not registered orientation set to default")
        }
        player1.placeShip(x,y,shipID, o)
      }
      case _ => println("wrong input type")
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
      val input = readLine(s"${player} Input Hit Coordinates (x y): ")

      //if there is a hit replay else next players turn
      if(!opponent.board.hit(input.split(" ")(0).toInt,input.split(" ")(1).toInt))playerTurn = !playerTurn

      // add this try to the players tries
      player.tries += Tuple2(input.split(" ")(0).toInt,input.split(" ")(1).toInt)

      //check if the opponent has lost
      if(opponent.lost) matchOver = true
    }
  }

}
