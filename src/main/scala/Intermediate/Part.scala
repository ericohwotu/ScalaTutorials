//package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
case class Part(id: Int, cost: Double, broken: Boolean, fixTime: Int) {
  //def getCost(): Double = cost
  override def toString(): String = f"{ID: $id Cost: $cost%2.2f Broken: $broken}, "
}
