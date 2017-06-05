/**
  * Created by Administrator on 05/06/2017.
  */

abstract class Person{
  def name: String
  def toString()
}
abstract class Vehicle{
  def name: String
  def toString()
}
object GarageExample {



  def main(args: Array[String]): Unit{
    var me =  case class Employee(name: String) extends Person {def toString() = s"name $name"}
    case class Customer(name: String) extends Person {def toString() = s"name $name"}
    case class Car(name: String) extends Vehicle {def toString() = s"name $name"}
    case class Bike(name: String) extends Vehicle {def toString() = s"name $name"}

    var employee = Employee("Eric")
    var str = employee.toString()
  }

}

