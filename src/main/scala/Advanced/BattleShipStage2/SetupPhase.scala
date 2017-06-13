package Advanced.BattleShipStage2

import scala.io.StdIn.readLine
import Advanced.BattleShipStage2.BattleField.{matchOver,phaseTwo, playerTurn}

/**
  * Created by Administrator on 13/06/2017.
  */
trait SetupPhase {
  def setupPhase(player: Player): Unit ={
    //TODO: Implement grid to show users position of their ships - completed
    //TODO: Implement more stringent ErrorHandling
    println(s"***********$player*************")
    println(">>> Type exit to quit")
    println(">>> Type ships to show available ships")
    println(">>> Type grid to show Grid")
    println(">>> To place ship (id x y orientation(v/h)) example: 1 1 3 v")
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
}
