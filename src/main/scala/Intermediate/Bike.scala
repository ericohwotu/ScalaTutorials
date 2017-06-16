package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
case class Bike(id: Int, vehicleType: VehicleType.Value = VehicleType.BIKE) extends Vehicle {
  // add parts when Object is initiated
  addParts()

  override def toString() = {
    var partsInfo: String = ""
    for (i <- parts) {
      partsInfo += i.toString()
    }
    s"ID: $id \nType: $vehicleType \nParts: ($partsInfo)"
  }
}
