Math.floor(Math.random()*10).toInt

case class me(in: Int)

var a1 = me(1)
import scala.collection.mutable.ArrayBuffer

var ab: ArrayBuffer[me] = ArrayBuffer()

ab += a1

var s = ab.toArray


//var x = ab.lift(1)


