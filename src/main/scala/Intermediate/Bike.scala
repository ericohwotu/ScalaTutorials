package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
case class Bike(id: Int, vehicleType: VehicleType.Value = VehicleType.BIKE) extends Vehicle {
  addParts()
  override def toString() = {
    //println("parts length "+ parts.length)
    var partsInfo: String = ""
    for (i <- parts) {
      partsInfo += i.toString()
    }
    s"ID: $id \nType: $vehicleType \nParts: ($partsInfo)"
  }

  def addParts(): Unit = {
    for (i <- 1 to (Math.floor(Math.random() * 10).toInt) + 10) {
      parts += new Part(i, Math.random() * 35 + 5, if (Math.random() > 0.6) {
        false
      } else {
        true
      },
        (Math.random()*5).toInt+1)
    }
  }
}
