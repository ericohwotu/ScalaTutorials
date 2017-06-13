package Advanced.BattleShipStage2

import java.awt.Color

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Eric on 11/06/2017.
  */
class Board {
  var squares: ArrayBuffer[Square] = new ArrayBuffer[Square]
  var bounds: (Int,Int) = (0,0)
  var owner: Player = _

  def init(x: Int, y: Int, player: Player): Unit = {
    for (i<- 0 until x; j<- 0 until y) squares += new Square(i,j)
    bounds = (x,y)
    owner = player
    println(owner)
  }

  def hit(x: Int, y: Int): Boolean = {

    val square = squares.filter(s => s.pos_x == x && s.pos_y == y)
    val isHit = square.head.hit()

    if (square(0).ship != null && square(0).ship.destroyed){
      println(square(0).ship.id)
      owner.destroyShip(square(0).ship.id)
    }

    isHit //return true if hit or false if not
  }

  def hitc(x: Int, y: Int): Color = {

    val square = squares.filter(s => s.pos_x == x && s.pos_y == y)

    var isHit = Color.BLACK

    square.head.hit() match {
      case true => isHit = Color.GREEN
      case false => isHit = Color.WHITE
    }

    if (square(0).ship != null && square(0).ship.destroyed){
      println(square(0).ship.id)
      owner.destroyShip(square(0).ship.id)
    }

    isHit //return true if hit or false if not
  }

  def placeShip(x: Int, y: Int, ship: Ship, o: ShipOrientation.Value): Boolean ={
    o match {
      case _ if !squares.filter(s => (s.pos_x >= x && s.pos_x <= x + ship.len-1)&& s.pos_y == y).forall(s => s.ship == null) => println(s"sorry squares occupied : $ship"); false
      case _ if !squares.filter(s => (s.pos_y >= y && s.pos_y <= y + ship.len-1)&& s.pos_x == x).forall(s => s.ship == null) => println(s"sorry squares occupied : $ship"); false
      case ShipOrientation.VERTICAL if y + ship.len -1 > (bounds._2 - 1) => println(s"sorry ship wont fit in that orientation : $ship"); false
      case ShipOrientation.HORIZONTAL if x + ship.len -1 > (bounds._1 - 1) => println(s"sorry ship wont fit in that orientation : $ship"); false
      case ShipOrientation.HORIZONTAL => squares.filter(s => (s.pos_x >= x && s.pos_x <= x + ship.len-1)&& s.pos_y == y).foreach(s => s.assignShip(ship)); true
      case ShipOrientation.VERTICAL => squares.filter(s => (s.pos_y >= y && s.pos_y <= y + ship.len-1)&& s.pos_x == x).foreach(s => s.assignShip(ship)); true
      case _ => println("I have no idea how you got here"); false
    }
  }

  def showGrid(): Unit={
    var msg: String = ""
    //(0 until bounds._1).toBuffer.foreach(p => {msg += s"\n$p" ; (0 until bounds._2).toBuffer.foreach(p => msg += " 0 ")})
    for(i <- 0 until bounds._1){
      msg += "\n"
      for(j <- 0 until bounds._2){

        squares.filter(s => s.pos_x == j && s.pos_y == i ).head match{
          case x if x.ship != null => msg += s"| ${x.ship.id} |"
          case _ => msg += "|   |"
        }

      }
    }
    println(msg)
  }
}
