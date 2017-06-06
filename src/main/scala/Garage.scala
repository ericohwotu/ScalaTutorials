/**
  * Created by Administrator on 06/06/2017.
  */
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
object Garage {
  var vehicles: scala.collection.mutable.ListBuffer[Vehicle] = scala.collection.mutable.ListBuffer()
  var fixedVehicles: scala.collection.mutable.ListBuffer[Vehicle] = scala.collection.mutable.ListBuffer()

  //def vehiclesQ: scala.collection.mutable.Queue[Vehicle] = scala.collection.mutable.Queue()
  var employees: scala.collection.mutable.ListBuffer[Employee] = scala.collection.mutable.ListBuffer()

  var opened: Boolean = false

  //METHODS
  def open(): Unit = {
    opened = true

    while(!vehicles.isEmpty){
      if(!employees.isEmpty){
        Future{fixVehicle(vehicles.head, employees.head)}
      }
    }
  }

  def addVehicle(vehicle: Vehicle): Unit = vehicles += vehicle

  def addEmployee(employee: Employee): Unit = employees += employee

  def removeVehicle(id: Int): Unit = for (v <- vehicles) if (v.id == id) {
    vehicles -= v
  }
  def removeVehicle(vehicleType: VehicleType.Value): Unit = for (v <- vehicles) if (v.vehicleType == vehicleType) {
    vehicles -= v
  }

  def registerEmployee(employee: Employee): Unit = employees += employee //vehicles.filter(x => (x.id == id)||(x.vehicleType == vehicleType))

  def fixVehicle(vehicle: Vehicle, employee: Employee): String = {
    println(System.currentTimeMillis() + " >>> " +employee.toString() + "is working on" + vehicle.toString())
    //remove the employee from the list and remove the vehicle from the vehicles list
    employees -= employees.head
    vehicles -= vehicles.head

    //calculate the total time
    var totalTime = 0
    for (part <- vehicle.parts) {
      if (part.broken) {
        totalTime += part.fixTime
      }
    }
    Thread.sleep(totalTime*10)

    fixedVehicles += vehicle
    employees += employee
    "TODO: Implement"
  }

  //for(v <- vehicles)if(v.state == VehicleState.BROKEN)for(v.parts)
  def calculateBills(vehicle: Vehicle): Double = {
    var totalCost: Double = 0.0
      if (vehicle.broken == true) {
        for (part <- vehicle.parts) {
          if (part.broken) {
            totalCost += part.cost
          }
        }
      }

    println(f"Total: $totalCost%2.2f")
    totalCost
  }

  def displayInventory(): String = {
    println("Vehicle length "+ vehicles.size)
    var vehiclesInfo: String = ""
    for (v <- vehicles) {
      vehiclesInfo += f"${v.toString()} Total Cost: ${calculateBills(v)}%2.2f \n"
    }
    s"Garage is $opened the vehicles added are listed below: \nVehicles: \n($vehiclesInfo)"
  }

  def close(): Unit = opened = false

}