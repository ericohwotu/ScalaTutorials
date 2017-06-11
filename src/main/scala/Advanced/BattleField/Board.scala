package Advanced.BattleField

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Eric on 11/06/2017.
  */
class Board {
  var squares: ArrayBuffer[Square] = new ArrayBuffer[Square]
  var bounds = (0,0)
  var owner: Player = null

  def init(x: Int, y: Int, player: Player): Unit = {
    for (i<- 0 until x; j<- 0 until y) squares += new Square(i,j)
    bounds = (x,y)
    owner = player;
    println(owner)
  }

  def hit(x: Int, y: Int): Boolean = {

    val square = squares.filter(s => s.pos_x == x && s.pos_y == y)
    val isHit = square(0).hit

    if (square(0).ship != null && square(0).ship.destroyed){
      println(square(0).ship.id)
      owner.destroyShip(square(0).ship.id)
    }

    isHit //return true if hit or false if not
  }

  def placeShip(x: Int, y: Int, ship: Ship, o: Orientation.Value): Boolean ={
    o match {
      case _ if !squares.filter(s => (s.pos_x >= x || s.pos_x <= x + ship.len)&& s.pos_y == y).forall(s => s.ship == null) => println("sorry squares occupied"); false
      case Orientation.VERTICAL if x + ship.len -1 > bounds._1 => println("sorry ship wont fit in that orientation"); false
      case Orientation.HORIZONTAL if y + ship.len -1 > bounds._2 => println("sorry ship wont fit in that orientation"); false
      case Orientation.VERTICAL => squares.filter(s => (s.pos_x >= x || s.pos_x <= x + ship.len-1)&& s.pos_y == y).foreach(s => s.assignShip(ship)); true
      case Orientation.HORIZONTAL => squares.filter(s => (s.pos_y >= y || s.pos_y <= y + ship.len-1)&& s.pos_x == x).foreach(s => s.assignShip(ship)); true
      case _ => println("I have no idea how you got here"); false
    }
  }
}
