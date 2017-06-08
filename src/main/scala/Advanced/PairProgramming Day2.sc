val errMsg = ""
def calc2(x: Int, y: Int, z: Int): String = {
  val data = List(x,y,z)
  var newData = ""
  data.foreach(i => data.filter(item => item != i).foreach(j => data.filter(item => (item != j && item != i)).foreach(k => newData = k match {
    case _ if i * j == k => {newData += s"$i * $j = $k ";newData}
    case _ if i + j == k => {newData += s"$i + $j = $k "; newData}
    case _ if i - j == k => {newData += s"$i - $j = $k "; newData}
    case _ if i / j == k => {newData += s"$i / $j = $k "; newData}
    case _ => errMsg
  })))
  newData
}
println(calc2(9,12,108))