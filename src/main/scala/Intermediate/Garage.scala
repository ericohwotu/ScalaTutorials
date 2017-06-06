package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
import java.util._

import scala.concurrent._
import java.util.concurrent.Executors
import java.io.{BufferedWriter, File, FileWriter, PrintWriter}




object Garage {
  var vehicles: scala.collection.mutable.ListBuffer[Vehicle] = scala.collection.mutable.ListBuffer()
  var fixedVehicles: scala.collection.mutable.ListBuffer[Vehicle] = scala.collection.mutable.ListBuffer()
  var employees: scala.collection.mutable.ListBuffer[Employee] = scala.collection.mutable.ListBuffer()
  var employeesWorking = 0
  var opened: Boolean = false

  val date = Calendar.getInstance().get(Calendar.DATE)
  val month = Calendar.getInstance().get(Calendar.MONTH)
  val year = Calendar.getInstance().get(Calendar.YEAR)
  val report = new File(s"Report-for-$date-$month-$year.txt")
  val csv = new File(s"Report-for-$date-$month-$year.csv")
  val printWriter = new PrintWriter(report)
  val csvWriter = new PrintWriter(csv)

  //METHODS
  def open(): Unit = {
    opened = true
    implicit val ec = ExecutionContext.fromExecutorService(Executors.newWorkStealingPool(employees.length)) // set the amount of working pools to the amount of employees
    printWriter.write(s"There are currently ${vehicles.length} vehicles in the garage and ${employees.length} employees working today\n")
    csvWriter.write("Vehicle ID,Vehicle Type,Bad Parts,Employee ID,Employee Name,Fix Time,Cost")

    val totalEarned = calculateBills()
    val totalVehicles = vehicles.length
    val totalEmployees = employees.length

    while(!vehicles.isEmpty || employeesWorking!=0){
      if(!employees.isEmpty){
        //select the first employee and remove them from the list
        val e = employees.head
        employees -= employees.head
        //schedule the fix vehicle routine with current employee
        Future{fixVehicle(vehicles.head, e)}
      }
      Thread.sleep(100) //allow for time to update
    }
    printWriter.write(f"Total Earned: ${totalEarned}%2.2f \nEND")
    csvWriter.write(f"\n\nVehicles,$totalVehicles,Employees,$totalEmployees,Total Earned,${totalEarned}%2.2f")
    printWriter.close()
    csvWriter.close()
    close()
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

  def fixVehicle(vehicle: Vehicle, employee: Employee): Unit = {
    employeesWorking += 1
    var now = Calendar.getInstance()
    println(now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) + " >>> " +employee.name + " has finished working on working on " + vehicle.vehicleType + " " + vehicle.id)

    //remove the vehicle from the vehicles list
    vehicles -= vehicles.head

    //calculate the total time
    var totalTime = 0
    var totalCost = 0.0
    var total =0
    for (part <- vehicle.parts) {
      if (part.broken) {
        totalTime += part.fixTime //get the total time to complete the work
        totalCost += part.cost //get the total cost of the parts
        total += 1
      }
    }
    Thread.sleep(totalTime*1000)

    fixedVehicles += vehicle //add the fixed vehicles to the new list
    employees += employee

    employeesWorking -= 1 //decrement counter as this employee has finished working
    now = Calendar.getInstance()

    println(now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) +" >>> " +employee.name + " has finished working on working on " + vehicle.vehicleType + " " + vehicle.id)
    println(now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND) +" >>> Total Elapsed Time = " + totalTime + f"minutes Total Cost = £${totalCost}%2.2f" )
    printWriter.write(f"\nVehicle Record: \nID: ${vehicle.id} \nType: ${vehicle.vehicleType}\nAssigned Employee: ${employee.name}\nCost: £${totalCost}%2.2f\nFix Time: ${totalTime} minutes")
    csvWriter.write(f"\n${vehicle.id},${vehicle.vehicleType},$total,${employee.id},${employee.name},$totalTime minutes,£${totalCost}%2.2f")
  }

  //for(v <- vehicles)if(v.state == VehicleState.BROKEN)for(v.parts)
  def calculateBills(): Double = {
    var totalCost: Double = 0.0
    for(v <- vehicles) {
      if (v.broken == true) {
        for (part <- v.parts) {
          if (part.broken) {
            totalCost += part.cost
          }
        }
      }
    }
    //println(f"Total: $totalCost%2.2f")
    totalCost
  }

  def displayInventory(): String = {
    println("Vehicle length "+ vehicles.size)
    var vehiclesInfo: String = ""

    for (v <- vehicles) {
      var vehicleCost: Double = 0
      for (p <- v.parts)vehicleCost += p.cost
      vehiclesInfo += f"${v.toString()} Total Cost: ${vehicleCost}%2.2f \n"
    }
    s"Garage is $opened the vehicles added are listed below: \nVehicles: \n($vehiclesInfo)"
  }

  def close(): Unit = {
    opened = false
    fixedVehicles.clear()
    employees.clear()
  }

}