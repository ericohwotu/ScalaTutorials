package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
case class Customer(id: Int, name: String, personType: PersonType.Value = PersonType.CUSTOMER) extends Person {
  override def toString() = s"ID: $id \nName: $name \nType: $personType"
}
