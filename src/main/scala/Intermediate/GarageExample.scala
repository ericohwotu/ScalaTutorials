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
    Garage.addEmployee(new Employee(1,"Eric"))
    Garage.addEmployee(new Employee(2,"John"))
    Garage.addEmployee(new Employee(3,"Julia"))
    Garage.addEmployee(new Employee(4,"Juliet"))
//    Garage.addEmployee(new Employee(5,"Ramon"))
//    Garage.addEmployee(new Employee(6,"Chris"))
//    Garage.addEmployee(new Employee(7,"Jack"))
//    Garage.addEmployee(new Employee(8,"Mark"))
//    Garage.addEmployee(new Employee(9,"Erica"))
//    Garage.addEmployee(new Employee(10,"Johna"))
//    Garage.addEmployee(new Employee(11,"Juliaa"))
//    Garage.addEmployee(new Employee(12,"Julieta"))
//    Garage.addEmployee(new Employee(13,"Ramona"))
//    Garage.addEmployee(new Employee(14,"Chrisa"))
//    Garage.addEmployee(new Employee(15,"Jacka"))
//    Garage.addEmployee(new Employee(16,"Marka"))
    //println(Garage.displayInventory())


    Garage.open()
    //println(Garage.displayInventory())
  }

}

