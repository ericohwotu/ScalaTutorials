import scala.collection.mutable.{HashMap, ListBuffer, MultiMap, Set}

object Advanced{

    def main(args: Array[String]): Unit = {
        var p: ListBuffer[Int] = ListBuffer(2)
        
        val beginning = System.currentTimeMillis()
        println("Beginning prime number calculation")

        val n=10000000
        var lb: ListBuffer[Boolean] = ListBuffer.fill(n/2)(true)
        var cur: Int = 3;

        //val primeNumbers = {3 to n by 2}.toBuffer.filter(item => {if(p.forall(item % _ != 0)){if(item < Math.sqrt(n)){p += item};true} else false }).size + 1
        val incr: (Int) => Int = (x) => {
            x match{
                case b if (x % 2 != 0 ) && (x % 5 != 0) => 4
                case b if (x % 2 != 0 ) => 2

            }
        }
        val primeNumbers = lb.filter(item => {if(p.forall(cur % _ != 0)){if(cur < Math.sqrt(n)){p += cur};incr(cur);true} else {incr(cur);false} }).size + 1

        //p += 5
        //  .filter(item => if(item != 5)(item % 5 != 0) else true)


        val ending = System.currentTimeMillis()
        val elapsedTime = (ending - beginning)/1000
        println(s"The amount of primes found is $primeNumbers taking $elapsedTime seconds to run \n$p + count ${p.length}")

    }
}