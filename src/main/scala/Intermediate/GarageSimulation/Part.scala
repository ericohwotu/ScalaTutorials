package Intermediate.GarageSimulation

/**
  * Created by Administrator on 06/06/2017.
  */
case class Part(id: Int, cost: Double, var broken: Boolean, fixTime: Int) {

  override def toString(): String = f"{ID: $id Cost: $cost%2.2f Broken: $broken}"
}
