import java.util.TimeZone.getAvailableIDs


//function 1
//val g = getAvailableIDs.filter(_.contains("/")).map(value => value.split("/")(1)).grouped(10).toArray.flatten.take(20)
val g = getAvailableIDs.filter(_.contains("/")).map(value => value.split("/")(1)).grouped(10).take(20)

//Black Jack
def playBlackJack(a: Int, b: Int): Int = {
  a match {
    case _ if (a > 21 && b > 21)=> 0
    case _ if (a < 21 && b > 21) => a
    case _ if (a > 21 && b < 21)=> b
    case _ if a > b => a
    case _ if (a < b) => b
    case _ => 0
  }
}

val ret = playBlackJack(14,22)

//add uniques
def isUnique (target: Int, reqa: Int, reqb: Int): Boolean = {
  target match {
    case _ if target == reqa => false
    case _ if target == reqb => false
    case _ => true
  }
}

def addUniques(a: Int, b: Int, c: Int): Int = {
  var total = 0;

  println(isUnique(a,b,c) + " " + isUnique(b,a,c) + " " + isUnique(c,b,a))
  if (isUnique(a,b,c)){total += a}
  if (isUnique(b,c,a)){total += b}
  if (isUnique(c,a,b)){total += c}

  total

}

var uniques = addUniques(7,2,3)

//Temperature
def isTempAcceptable(temp: Int, isSummer: Boolean): Boolean ={
  temp match {
    case _ if ((temp <= 100 && temp >=60) && isSummer)=> true
    case _ if ((temp <= 90 && temp >=60) && !isSummer)=> true
    case _ => false
  }
}

isTempAcceptable(20,false)
isTempAcceptable(80, false)
isTempAcceptable(95, false)
isTempAcceptable(100, true)

//class tutorials




















