/**
  * Created by Administrator on 06/06/2017.
  */
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import java.util._

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
        var e = employees.head
        employees -= employees.head
        Future{fixVehicle(vehicles.head, e)}
      }
      Thread.sleep(100) //allow for time to update
    }
    Thread.sleep(10000) // ensure all are finished before ending loop
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
    val now = Calendar.getInstance()
    println(now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + " >>> " +employee.name + " is working on" + vehicle.id)
    //remove the employee from the list and remove the vehicle from the vehicles list
    vehicles -= vehicles.head

    //calculate the total time
    var totalTime = 0
    for (part <- vehicle.parts) {
      if (part.broken) {
        totalTime += part.fixTime
      }
    }
    Thread.sleep(totalTime*100)

    fixedVehicles += vehicle
    employees += employee

    println(now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) +" >>> " +employee.name + " has finished working on working on" + vehicle.id)
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