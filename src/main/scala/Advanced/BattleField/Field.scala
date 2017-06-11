package Advanced.BattleField

import scala.collection.mutable.ListBuffer

/**
  * Created by Eric on 10/06/2017.
  */
abstract class Board {


  def placeObject(start: Int, end: Int, size: Int)

  def getShips()
}
