package Intermediate

/**
  * Created by Administrator on 05/06/2017.
  */
//import scala.collection.mutable.Queue
object GarageExample {

  def main(args: Array[String]): Unit = {

    var employee = Employee(1, "Eric")
    var str = employee.toString()


    //add vehicles to the garage
    var addPercentage = 0.0 // percentage to see whether are car or a bike should be added

    for(i<- 1 to Math.floor(Math.random()*10).toInt+10){
      addPercentage =  Math.random()
      addPercentage match{
        case _ if addPercentage >= 0.7 => Garage.addVehicle(new Bike(i))
        case _ => Garage.addVehicle(new Car(i))
      }
    }

    //adding employees
    val firstNames = Array("Eric","John","Jim","George","Julia","Juliet")
    val lastNames = Array("Johnson","Lavall","Williams","Davis","Edwards","Adams","Lockette")

    for(i<- 1 to (Math.random()*10).toInt)
      Garage.addEmployee(new Employee(i,s"${firstNames(i % firstNames.length)} ${lastNames(i % lastNames.length)}"))

    Garage.open()
    //println(Garage.displayInventory())
  }

}

