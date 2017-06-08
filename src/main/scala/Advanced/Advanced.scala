import scala.collection.mutable.{HashMap, ListBuffer, MultiMap, Set}

object Advanced{

    class PipedObject[T] (value:T)
    {
        def |>[R] (f : T => R) = f(this.value)
    }
    implicit def toPiped[T] (value:T) = new PipedObject[T](value)

    def main(args: Array[String]): Unit = {
        var p: ListBuffer[Int] = ListBuffer(2)
        
        val beginning = System.currentTimeMillis()
        println("Beginning prime number calculation")

        var primeNumbers = {2 to 1000000}.toList.filter(item => {if(p.forall(item % _ != 0)){p += item;true} else false }).size + 1

        //var primeNumbers = (n: Int) => (2 to n) |> (r => r.foldLeft(r.toSet)((ps, x) => if (ps(x)) ps -- (x * x to n by x) else ps))

        //var x = primeNumbers(1000000).size

        val ending = System.currentTimeMillis()
        val elapsedTime = (ending - beginning)/1000
        println(s"The amount of primes found is $primeNumbers taking $elapsedTime seconds to run")
    }
}