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

  case class Employee(name: String) extends Person
  case class Customer(name: String) extends Person
  case class Car(name: String) extends Vehicle
  case class Bike(name: String) extends Vehicle


}

