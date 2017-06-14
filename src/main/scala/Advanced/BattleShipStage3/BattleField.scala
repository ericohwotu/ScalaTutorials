package Advanced.BattleShipStage3

import scala.io.StdIn.readLine

/**
  * Created by Eric on 10/06/2017.
  * Battleship Stage 1
  * TODO: Implement Stage
  */
object BattleField extends App with SetupPhase{

  /**
    * Phase 0: Initiate the board and a assign ships to player
    *
    * */
  //TODO: Implement GUI - completed
  //global variable declaration: variables used in multiple classes in the project
  val bounds = (12,12)
  var matchOver = false
  var phaseTwo = false //TODO: change to false when phase one implemented
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
  player1.initBoard(bounds._1,bounds._2)
  opponent.initBoard(bounds._1,bounds._2)

  //instantiate server handlers
  val server = new Server()
  val client = new Client()

  //TODO: Add Automatic ship placement for CPU player - completed

  //placeAllShips(player1)
  //placeAllShips(opponent)
  opponent.board.showGrid()


  //show the user interface
  val ui = new BattleShipUI
  ui.visible = true
  ui.updateAllButtons()

  while (!matchOver) {
    while (!phaseTwo) {
      playerTurn match {

        case true =>
          println(s"ready: $ready readyCount: $readyCount")
          if (player1.ships.length == 0 && ready==false) {
            ready = true
            readyCount+=1

            //send player info over network
            if(isClient)client.sendPlayerInfo(player1)
            if(isHost)server.sendPlayerInfo(player1)
          }
          if(readyCount==2 && !playAgainstCpu){
            phaseTwo=true
            ui.showPhaseTwoDialog()
            ui.updateAllButtons()
          }
        case false => setupPhase(opponent)
      }
    }
    //println("entered")
    if (!matchOver) matchPhase()
  }
  var winner = player1.toString
  player1.lost match {
    case true => winner = opponent.toString
    case false => winner = player1.toString
  }
  ui.showEndDialog(winner)
  //ui.close()

  //------------------------------------------------- function definitions --------------------------------------------//


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

  def placeAllShips(player: Player): Unit ={
    for(i<-1 to 7){
      //declare variables for each ship
      var x: Int = 0
      var y: Int = 0
      var o = ShipOrientation.VERTICAL

      do {
        x = (Math.random() * bounds._1).toInt
        y = (Math.random() * bounds._2).toInt
        (Math.random() * 2).toInt match{
          case 0 => o = ShipOrientation.VERTICAL
          case 1 => o = ShipOrientation.HORIZONTAL
          case _ => println(s"Oops placeAllShips: $i")
        }

      }while(!player.placeShip(x,y,i,o))
    }
  }

  def closeUi(): Unit ={
    ui.close()
  }
}
