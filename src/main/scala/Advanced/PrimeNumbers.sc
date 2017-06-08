import java.util.Calendar
import scala.concurrent._
import java.util.concurrent.Executors
import scala.collection.mutable.{HashMap, ListBuffer, MultiMap, Set}
import ExecutionContext.Implicits.global


var p: ListBuffer[Int] = ListBuffer(2)

var primeNumbers = {2 to 3000000}.toList.filter(item => {
  if(p.forall(item % _ != 0)){
    p += item
    true
  }else{
    false
  }
}).size

////def isPrime(n: Int) = (2 until n) forall (n % _ != 0)
//def isPrime(n: Int): Boolean = {
//  var ans = false
//  var primeStates: Int = 0
//  for (i <- p) {
//    if (n % i != 0){
//    }else{
//      primeStates += 1
//    }
//  }
//  if(primeStates < 2){
//    p += n
//    ans = true
//  }
//  ans
//}
//
////def getPrimes1(n: Int) {
////  val startMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val startSecond = Calendar.getInstance().get(Calendar.SECOND)
////  println(s"start: $startMinute:$startSecond")
////  val primes = {1 to n}.toList.map(item => (2 until item).forall(item % _ != 0)).filter(x => x.equals(true)).size
////  println(primes)
////  val endMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val endSecond = Calendar.getInstance().get(Calendar.SECOND)
////  //println(primes)
////  println(s"end: $endMinute:$endSecond + $primes + ${endSecond-startSecond}")
////}
////def getPrimes2(n: Int) {
////  val startMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val startSecond = Calendar.getInstance().get(Calendar.SECOND)
////  println(s"start: $startMinute:$startSecond")
////  val primes = {1 to n}.toList.filter(x => isPrime(x)).size
////  val endMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val endSecond = Calendar.getInstance().get(Calendar.SECOND)
////    //println(primes)
////  println(s"end: $endMinute:$endSecond + $primes + ${endSecond-startSecond}")
////}
////getPrimes1(600000)
////getPrimes2(600000)
//
//{1 to 20000}.toList.map(item => p.forall(item % _ != 0))
//
//                        //for(n<- p) (n % _ != 0)
////
////def getPrimes(n: Int) {
////  val startMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val startSecond = Calendar.getInstance().get(Calendar.SECOND)
////  println(s"start: $startMinute:$startSecond")
////  var numbers: List[Int] = {1 to n}.toList
////  var number = 1
//////  for (i <- 1 to n){
//////    if(isPrime(i)){
//////      number = number + 1
//////    }
//////  }
////
////  var primes = numbers.filter(x => isPrime(x))
////  val endMinute = Calendar.getInstance().get(Calendar.MINUTE)
////  val endSecond = Calendar.getInstance().get(Calendar.SECOND)
////  //println(primes)
////  println(s"end: $endMinute:$endSecond + {primes.size} +number + ${endSecond-startSecond}")
////}
//
////getPrimes(3000000)
//
////def isPrime(n: Int) = (2 until n) forall (n % _ != 0)
////def isPrime2(n: Int): Boolean = {
////  var ans = false
////  var primeStates: Int = 0
////
////  {1 to n}.toList.map(item => p.forall(item % _ != 0))
////
////  for (i <- p) {
////    if (n % i != 0){
////    }else{
////      primeStates += 1
////    }
////  }
////  if(primeStates < 2){
////    p += n
////    ans = true
////  }
////  ans
////}