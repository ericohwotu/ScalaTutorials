package Advanced.BattleShipStage3

import scala.io.StdIn.readLine
import BattleField.{playerTurn, player1, playAgainstCpu, ui,matchOver, opponent, bounds}

/**
  * Created by Eric on 14/06/2017.
  */
trait MatchPhase {
  def matchPhase(): Unit ={
    playerTurn match {
      case true => " "//println(" ");//ui.resetFields()//runRound(player1,opponent)
      case false if playAgainstCpu => ui.updateAllButtons(); runCPU(opponent,player1)
      case false if !playAgainstCpu=> ui.updateAllButtons(); runRound(opponent,player1)
    }

    def runCPU(player: Player, opponent: Player): Unit = {
      var x = (Math.random()*12).toInt
      var y = (Math.random()*12).toInt
      while(player.tries.contains((x,y))){
        x = (Math.random()*12).toInt
        y = (Math.random()*12).toInt
      }
      if (!opponent.board.hit(x, y)) {
        playerTurn = ! playerTurn
        ui.updateAllButtons()
      }
      player.tries += Tuple2(x,y)
      if(opponent.lost)matchOver = true
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
          if (! opponent.board.hit (arr(0).trim.toInt, arr(1).trim.toInt) ) {playerTurn = ! playerTurn; ui.updateAllButtons()}

          // add this try to the players tries
          player.tries += Tuple2 (arr(0).trim.toInt, arr(1).trim.toInt)
      }
      //check if the opponent has lost
      if(opponent.lost) matchOver = true
    }
  }
}
