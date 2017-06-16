package Intermediate;

/**
  * Created by Administrator on 06/06/2017.
  */
case class Car(id: Int, vehicleType: VehicleType.Value = VehicleType.CAR) extends Vehicle {
  addParts()

  override def toString() = {
    var partsInfo: String = ""
    for (i <- parts) {
      partsInfo += i.toString()
    }
    s"ID: $id \nType: $vehicleType \nParts: ($partsInfo)"
  }

}
