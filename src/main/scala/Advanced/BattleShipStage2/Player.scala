package Advanced.BattleShipStage2

import scala.collection.mutable.ListBuffer

/**
  * Created by Eric on 11/06/2017.
  */
class Player(id: Int) {
  //TODO: Implement remove ship method to allow for players to change their minds
  //TODO: Track user guesses and prevent from guessing again
  var ships = new ListBuffer[Ship]
  var shipsInPlay = new ListBuffer[Ship]
  var tries = new ListBuffer[(Int,Int)]
  var board = new Board()
  var lost = false


  initShips() //called on creation of player to initialise the ships

  def initBoard(x: Int, y: Int): Unit = board.init(x,y,this)

  def initShips(): Unit ={
    addShip(PatrolBoat(1))
    addShip(PatrolBoat(2))
    addShip(BattleShip(3))
    addShip(BattleShip(4))
    addShip(Submarine(5))
    addShip(Destroyer(6))
    addShip(Carrier(7))
  }

  def addShip(ship: Ship): Unit = ships += ship

  def placeShip(x: Int, y: Int, shipType: ShipType.Value, o: ShipOrientation.Value): Boolean = {
    //TODO: Ensure Ship is not moved to in game ships if not placed
    val shipTypeCount = ships.filter(s => s.shipType == shipType)

    shipTypeCount.length match {
      case 0 => println(s"Sorry0 $id has already been placed"); false

      case _ if shipTypeCount.nonEmpty => board.placeShip(x, y, shipTypeCount.head, o) match {
          case true =>
            ships -= shipTypeCount.head
            shipsInPlay += shipTypeCount.head
            println("success its been placed")
            true
          case false => println("Sorry en error occurred in placing Ship"); false
        }

      case _ => println("How did you manage to break it"); false
    }
  }

  def placeShip(x: Int, y: Int, id: Int, o: ShipOrientation.Value): Boolean = {
    //TODO: Ensure Ship is not moved to in game ships if not placed
    val shipTypeCount = ships.filter(s => s.id == id)

    shipTypeCount.length match {
      case 0 => println(s"Sorry0 $id has already been placed"); false

      case _ if shipTypeCount.nonEmpty =>
        board.placeShip(x, y, shipTypeCount.head, o) match {
          case true =>
            ships -= shipTypeCount.head
            shipsInPlay += shipTypeCount.head
            true
          case false => println("Sorry en error occurred in placing Ship"); false
        }

      case _ => println("How did you manage to break it"); false
    }
  }

  def destroyShip(id: Int): Unit = {
    //TODO: Ensure ship is only deleted if it exists
    try {
      shipsInPlay -= shipsInPlay.filter(s => s.id == id).head
    }catch {
      case x: Throwable => println(x.getMessage)
      case _: Throwable => println("You messed up!!!")
    }

    if (shipsInPlay.isEmpty) {
      println(s"Player ${this.id} has lost")
      lost = true
    }
  }

  override def toString = s"Player $id"
}
