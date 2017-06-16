package Intermediate.GarageSimulation

/**
  * Created by Administrator on 06/06/2017.
  */
case class Employee(id: Int, name: String, personType: PersonType.Value = PersonType.EMPLOYEE) extends Person {
  override def toString() = s"ID: $id \nName: $name \nType: $personType"
}
