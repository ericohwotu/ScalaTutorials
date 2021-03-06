package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */

import java.awt.Color

class Square(pos: (Int,Int)) {
  val pos_x: Int = pos._1
  val pos_y: Int = pos._2
  var ship: Ship = _
  var attacked = false

  def assignShip(s: Ship): Unit = ship = s

  def unAssignShip(): Unit = ship = null

  def hit(): Boolean = ship != null match{
      case true => ship.hit(); true
      case false => attacked = true; println("Miss!!!"); false
  }
  override def toString: String = s"$pos_x + $pos_y + $ship"
}
