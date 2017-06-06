/**
  * Created by Administrator on 06/06/2017.
  */
abstract class Vehicle {
  var broken: Boolean = true

  val id: Int

  var parts: scala.collection.mutable.ArrayBuffer[Part] = scala.collection.mutable.ArrayBuffer()

  val vehicleType: VehicleType.Value

  def toString(): String

  var repairCost: Double = 0
}

