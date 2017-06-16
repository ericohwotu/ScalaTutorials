//Black Jack
def playBlackJack(a: Int, b: Int): Int = a match {
  case _ if (a > 21 && b > 21)=> 0
  case _ if (a < 21 && b > 21) || a > b => a
  case _ if (a > 21 && b < 21)||(a < b)=> b
  case _ => 0
}

val ret = playBlackJack(14,21)

//add uniques
def addUniques(a: Int, b: Int, c: Int): Int = {
  var ls = List(a,b,c)
  ls.map(x => if (ls.filter(_ == x).length == 1) x else 0).sum
}

var uniques = addUniques(-7,2,6)

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
