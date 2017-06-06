//package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
abstract class Person {
  val id: Int

  val personType: PersonType.Value

  val name: String

  def toString(): String
}
