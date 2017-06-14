import scala.collection.mutable.ListBuffer

//Exercises Taken on 14/06/2017
def doubleChar(str: String): String = {
  var newString = str.split("").toSeq.map(x => x * 2)
  var result = ""
  newString.foreach(x => result += x)
  println(result)
  result
}
doubleChar("hello")

//exercise 2
def getSandwich(str: String): String = {
  val firstBread = str.indexOf("bread")
  val lastBread = str.lastIndexOf("bread")
  var result = str.drop(firstBread)
  result = result.dropRight(result.length - (result.lastIndexOf("bread")))

  (lastBread - firstBread) match {
    case x if x > 1 =>
      result = result.replaceFirst("bread", "");
      result = result.replace("bread", "")
    case _ => result = ""
  }
  result
}

getSandwich("breadcokebre")

//excercise 3
def evenlySpaced(n1: Int, n2: Int, n3: Int): Boolean = {
  var list = ListBuffer(n1, n2, n3)
  var max = list.max
  var min = list.min
  list -= max
  list -= min
  var med = list.head

  if (max - med == med - min) true else false
}
evenlySpaced(6, 2, 4)

//excercise 4
def finobacci(n: Int, prev: Int=0, prev2: Int=0, count: Int = 0): Int = {
  var nPrev = prev
  if (count==1)nPrev = 1
  if (count <= n) {
    finobacci(n, prev2 + prev, nPrev, count + 1)
  } else {
    prev2 + nPrev
  }
}

finobacci(9)

//excecise 5
def bunnyEars(n: Int, count: Int = 0, incr: Int = 0): Int ={
  println(incr)
  if (count<n){
    bunnyEars(n, count+1, incr+2)
  }else incr
}

bunnyEars(4)

//exercise 6
def nTwice(str: String,n: Int): String ={
  val firstPart = str.take(n)
  val lastPart = str.takeRight(n)

  firstPart + lastPart
}

nTwice("Hello",2)




