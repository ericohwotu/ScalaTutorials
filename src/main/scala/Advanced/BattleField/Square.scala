package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */
class Square(pos: Tuple2[Int,Int]) {
  val pos_x = pos._1
  val pos_y = pos._2
  var ship: Ship = null
  var attacked = false
  //var missile: {}= null

  def assignShip(s: Ship): Unit = ship = s

  def unAssignShip(): Unit = {ship = null}

  def hit: Boolean = if (ship != null) {
    ship.hit
    true
  } else {
    attacked = true;
    println("Miss!!!")
    false
  }
}
