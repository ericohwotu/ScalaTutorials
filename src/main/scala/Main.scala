/**
  * Created by Administrator on 05/06/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("hello world")

    //define new class
    case class Person(name: String, age: Int)
    val person = Person("Eric", 25)
    var name = person.name;
    var age = person.age;

    //print out the data
    var result = isLegal(age)
    println(s"Name: $name Age: $age")
    println(s"$name's ability to drink is $result")

    //create new child
    val child = Person("John", 12)
    name = child.name;
    age = child.age;

    result = isLegal(age)
    println(s"Name: $name Age: $age")
    println(s"$name's ability to drink is $result")
  }

  def isLegal(age: Int): Boolean = {
      if (age>= 25){
        true
      }else{
        false
      }
  }
}
