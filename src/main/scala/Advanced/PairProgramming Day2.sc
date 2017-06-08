val errMsg = "Stop, What are you doing"
def calc(x: Int, y: Int, z: Int): Any = (x,y,z) match{
  case _ if (x + y == z) => s"$x + $y = $z"
  case _ if (x - y == z) => s"$x - $y = $z"
  case _ if (x / y == z) => s"$x / $y = $z"
  case _ if (x * y == z) => s"$x * $y = $z"
  case _ if (y + z == x) => s"$y + $z = $x"
  case _ if (y - z == x) => s"$y - $z = $x"
  case _ if (y / z == x) => s"$y / $z = $x"
  case _ if (y * z == x) => s"$y * $z = $x"
  case _ if (z + x == y) => s"$z + $x = $y"
  case _ if (z - x == y) => s"$z - $x = $y"
  case _ if (z / x == y) => s"$z / $x = $y"
  case _ if (z * x == y) => s"$z * $x = $y"
  case _ => errMsg
}

println(calc(4,2,8))


def calcNew(x: Int, y: Int, z: Int, w: Int): Any = (x,y,z,w) match{
  case _ if (x + y == z) => s"$x + $y = $z"
  case _ if (x - y == z) => s"$x - $y = $z"
  case _ if (x / y == z) => s"$x / $y = $z"
  case _ if (x * y == z) => s"$x * $y = $z"
  case _ if (y + z == x) => s"$y + $z = $x"
  case _ if (y - z == x) => s"$y - $z = $x"
  case _ if (y / z == x) => s"$y / $z = $x"
  case _ if (y * z == x) => s"$y * $z = $x"
  case _ if (z + x == y) => s"$z + $x = $y"
  case _ if (z - x == y) => s"$z - $x = $y"
  case _ if (z / x == y) => s"$z / $x = $y"
  case _ if (z * x == y) => s"$z * $x = $y"
  case _ => errMsg
}

println(calcNew(3,5,5,3))

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
def calc2(x: Int, y: Int, z: Int): String = {
  var data = List(x,y,z)
  var newData = ""
  data.foreach(i => data.filter(item => item != i).foreach(j => data.filter(item => (item != j && item != i)).foreach(k => k match {
    case _ if i * j == k => {newData+= s"$i * $j = $k\n"; newData}
    case _ if i + j == k => {newData+= s"$i + $j = $k\n"; newData}
    case _ if i - j == k => {newData+= s"$i - $j = $k\n"; newData}
    case _ if i / j == k => {newData+= s"$i / $j = $k\n"; newData}
    case _ => ""
  })))
  newData
}
println(calc2(2,8,16))
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






//def calc3(x: Int, y: Int, z: Int, w: Int=0): String = {
//  var data = List(x,y,z)
//  var data1= w
//  var newData = ""
//  data.foreach(i => data.filter(item => item != i).foreach(j => data.filter(item => (item != j && item != i)).foreach(k => k match {
//    case _ if i * j == k => {newData+= s"$i * $j = $k\n"; newData}
//    case _ if i + j == k => {newData+= s"$i + $j = $k\n"; newData}
//    case _ if i - j == k => {newData+= s"$i - $j = $k\n"; newData}
//    case _ if i / j == k => {newData+= s"$i / $j = $k\n"; newData}
//    case _ => ""
//  })))
//
//  if (data1 >0){
//    data1 match{
//      case _ if
//    }
//  }
//
//  newData
//}
//println(calc3(3,5,5,3))