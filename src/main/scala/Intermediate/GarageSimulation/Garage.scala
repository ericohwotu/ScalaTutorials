package Intermediate.GarageSimulation

import java.util._

import scala.concurrent._
import java.util.concurrent.Executors
import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import scala.collection.mutable._


object Garage {
  var vehicles: ListBuffer[Vehicle] = ListBuffer()
  var fixedVehicles: ListBuffer[Vehicle] = ListBuffer()
  var employees: ListBuffer[Employee] = ListBuffer()
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
  def open(): Unit = opened = true


  def operate(): Unit = {
    if(opened) {
      implicit val ec = ExecutionContext.fromExecutorService(Executors.newWorkStealingPool(employees.length)) // set the amount of working pools to the amount of employees

      //begin writing the first lines tot he files
      printWriter.write(s"There are currently ${vehicles.length} vehicles in the garage and ${employees.length} employees working today\n")
      csvWriter.write("Vehicle ID,Vehicle Type,Bad Parts,Employee ID,Employee Name,Fix Time,Start Time,End Time,Cost")

      val totalEarned = calculateBills()
      val totalVehicles = vehicles.length
      val totalEmployees = employees.length

      while (!vehicles.isEmpty || employeesWorking != 0) {
        employees.isEmpty match {
          case false => {
            //select the first employee and remove them from the list
            val e = employees.head
            employees -= employees.head
            //schedule the fix vehicle routine with current employee
            Future {
              fixVehicle(vehicles.head, e)
            }
          }
          case true => "do nothing"
        }
        Thread.sleep(100) //allow for time to update
      }
      printWriter.write(f"Total Earned: ${totalEarned}%2.2f \nEND")
      csvWriter.write(f"\n\nVehicles,$totalVehicles,Employees,$totalEmployees,Total Earned,${totalEarned}%2.2f")
      printWriter.close()
      csvWriter.close()
    }else println("Garage is closed")
  }

  def addVehicle(vehicle: Vehicle): Unit = vehicles += vehicle

  def addEmployee(employee: Employee): Unit = employees += employee

  def removeVehicle(id: Int): Unit = for (v <- vehicles) if (v.id == id) {
    vehicles -= v
  }

  def removeVehicle(vehicleType: VehicleType.Value): Unit = for (v <- vehicles) if (v.vehicleType == vehicleType) {
    vehicles -= v
  }

  def registerEmployee(employee: Employee): Unit = employees += employee

  def fixVehicle(vehicle: Vehicle, employee: Employee): Unit = {
    employeesWorking += 1

    //get start times
    var now = Calendar.getInstance()
    val startHour = now.get(Calendar.HOUR)
    val startMinute = now.get(Calendar.MINUTE)
    val startSecond = now.get(Calendar.SECOND)

    println(startMinute + ":" + startSecond + " >>> " + employee.name + " is working on " + vehicle.vehicleType + " " + vehicle.id)

    //remove the vehicle from the vehicles list
    vehicles -= vehicles.head

    //calculate the total time
    var totalTime = 0
    var totalCost = 0.0
    var total = 0

    for (part <- vehicle.parts) {
      part.broken match {
        case true => {
          part.broken = false
          totalTime += part.fixTime //get the total time to complete the work
          totalCost += part.cost //get the total cost of the parts
          total += 1
        }
        case false => {
          totalTime += 0 //get the total time to complete the work
          totalCost += 0 //get the total cost of the parts
          total += 0
        }
      }
    }

    Thread.sleep(totalTime * 1000)

    fixedVehicles += vehicle //add the fixed vehicles to the new list
    employees += employee

    employeesWorking -= 1 //decrement counter as this employee has finished working

    //get the time for fix
    val end = Calendar.getInstance()
    val endHour = end.get(Calendar.HOUR)
    val endMinute = end.get(Calendar.MINUTE)
    val endSecond = end.get(Calendar.SECOND)

    println(endMinute + ":" + endSecond + " >>> " + employee.name + " has finished working on working on " + vehicle.vehicleType + " " + vehicle.id)
    println(endMinute + ":" + endSecond + " >>> Total Elapsed Time = " + totalTime + f" seconds Total Cost = £${totalCost}%2.2f")
    printWriter.write(f"\nVehicle Record: \nID: ${vehicle.id} \nType: ${vehicle.vehicleType}\nAssigned Employee: ${employee.name}\nCost: £${totalCost}%2.2f\nFix Time: ${totalTime} minutes")
    csvWriter.write(f"\n${vehicle.id},${vehicle.vehicleType},$total,${employee.id},${employee.name},$totalTime s,$startHour:$startMinute:$startSecond,$endHour:$endMinute:$endSecond,£${totalCost}%2.2f")
  }

  //for(v <- vehicles)if(v.state == VehicleState.BROKEN)for(v.parts)
  def calculateBills(): Double = {
    var totalCost: Double = 0.0
    vehicles.foreach(v => v.parts.foreach(part => part.broken match {
      case true => totalCost += part.cost
      case _ => totalCost += 0.0
    }))
    //})

    //println(f"Total: $totalCost%2.2f")
    totalCost
  }

  def displayInventory(): String = {
    println("Vehicle length " + vehicles.size)
    var vehiclesInfo: String = ""

    for (v <- vehicles) {
      var vehicleCost: Double = 0
      for (p <- v.parts) vehicleCost += p.cost
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
