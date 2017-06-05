/**
  * Created by Administrator on 05/06/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("Hello World")

    //define new class
    case class Person(name: String, age: Int)
    val person = Person("Eric", (Math.floor(Math.random()*30)+1).toInt)
    val name = person.name
    val age = person.age

    //print out the data
    val result = isLegal(age)
    println(s"Name: $name Age: $age")
    println(s"$name's ability to drink is $result")

  }

  def isLegal(age: Int): Boolean = {
      if (age>= 18){
        true
      }else{
        false
      }
  }
}
