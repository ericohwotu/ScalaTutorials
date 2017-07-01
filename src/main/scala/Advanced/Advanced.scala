import scala.collection.mutable.{HashMap, ListBuffer, MultiMap, Set}

object Advanced {

  def main(args: Array[String]): Unit = {
    var p: ListBuffer[Int] = ListBuffer(2)

    val beginning = System.currentTimeMillis()
    println("Beginning prime number calculation")

    val n = 100000000


    val primeNumbers = {3 to n by 2}.toBuffer.filter(item => {if(p.forall(item % _ != 0)){if(item < Math.sqrt(n)){p += item};true} else false }).size + 1


    val ending = System.currentTimeMillis()
    val elapsedTime = (ending - beginning) / 1000
    println(s"The amount of primes found is $primeNumbers taking $elapsedTime seconds to run \n$p + count ${p.length}")

  }
}