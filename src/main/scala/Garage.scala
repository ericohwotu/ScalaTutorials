/**
  * Created by Administrator on 06/06/2017.
  */
object Garage {
  var vehicles: scala.collection.mutable.ArrayBuffer[Vehicle] = scala.collection.mutable.ArrayBuffer()

  //def vehiclesQ: scala.collection.mutable.Queue[Vehicle] = scala.collection.mutable.Queue()
  var employees: scala.collection.mutable.Set[Person] = scala.collection.mutable.Set()

  var opened: Boolean = false

  //METHODS
  def open(): Unit = opened = true

  def addVehicle(vehicle: Vehicle): Unit = vehicles += vehicle

  def removeVehicle(id: Int, vehicleType: VehicleType.Value): Unit = for (v <- vehicles) if ((v.id == id) || (v.vehicleType == vehicleType)) {
    vehicles -= v
  }

  def registerEmployee(employee: Person): Unit = employees += employee //vehicles.filter(x => (x.id == id)||(x.vehicleType == vehicleType))

  def fixVehicles(): String = "TODO: Implement"

  //for(v <- vehicles)if(v.state == VehicleState.BROKEN)for(v.parts)
  def calculateBills(vehicle: Vehicle): Double = {
    var totalCost: Double = 0.0
    for (v <- vehicles) {
      if (v.broken == true) {
        for (part <- v.parts) {
          totalCost += part.getCost()
        }
      }
    }
    totalCost
  }

  def displayInventory(): String = {
    println("Vehicle length "+ vehicles.size)
    var vehiclesInfo: String = ""
    for (v <- vehicles) {
      vehiclesInfo += v.toString() + "\n"
    }
    s"Garage is $opened the vehicles added are listed below: \nVehicles: \n($vehiclesInfo)"
  }

  def close(): Unit = opened = false

}