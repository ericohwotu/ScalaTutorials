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

    val result = isLegal(age)
    println(s"Name: $name Age: $age")
    println(s"$name's ability to drink is $result")

    //new function returns
    def fn : Int => String = (x) => s"return message = $x"
    def fn2 : (Int, String) => String = (x,s) => s"name: $s and age: $x"

    val ns = fn(12)
    val ns2 = fn2((Math.floor(Math.random()*30)+1).toInt,"Eric")
    println(s"new checklist $ns / ($ns2)")
  }

  def isLegal(age: Int): Boolean = {
      if (age>= 18){
        true
      }else{
        false
      }
  }
}
