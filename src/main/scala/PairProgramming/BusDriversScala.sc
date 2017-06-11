val calc: (String) => Unit = (str) => {
  println(str.split("\n").toSeq.map(x => x.split(" ").toBuffer))
}

val getStop = (arr: Array[Int], int: Int) => int % arr.length-1
calc("12 3 5 6\n33 4 5")