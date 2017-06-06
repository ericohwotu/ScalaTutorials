/**
  * Created by Administrator on 05/06/2017.
  */
//import scala.collection.mutable.Queue
object GarageExample {

  def main(args: Array[String]): Unit = {

    var employee = Employee(1, "Eric")
    var str = employee.toString()

    var car1 = new Bike(1)
    car1.addParts()
    println(car1.toString())

    //add vehicles to the garage
    var addPercentage = 0.0

    for(i<- 1 to Math.floor(Math.random()*10).toInt+10){
      addPercentage =  Math.random()
      addPercentage match{
        case _ if addPercentage >= 0.7 => Garage.addVehicle(new Bike(i))
        case _ => Garage.addVehicle(new Car(i))
      }
    }
    println(Garage.fixVehicles())
    println(Garage.displayInventory())
  }

}

