package Advanced.BattleField

/**
  * Created by Eric on 11/06/2017.
  */
abstract class Ship {
  var destroyed = false
  var orient = Orientation.VERTICAL
  var hits = 0
  val shipType: ShipType.Value
  val len: Int
  val id:Int

  def sink(): Unit = {
    destroyed = true
    println(s"The enemy's $shipType has been destroyed")
  }

  def hit(): Unit = {
    hits+=1
    println(s"Hit!!!")
    if (hits == len) sink()
  }

  override def toString: String = s"\nShip $id is a $shipType which is $len long and its destroyed state is $destroyed"
}
