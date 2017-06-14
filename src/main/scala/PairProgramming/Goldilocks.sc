def goldilocks (input: String) : String = {
  val slices = input.split("\n").toList
  val porridge = slices.map(s => s.split(" ").toList)
  val goldData = porridge.head
  var results = " "
  println(porridge)
  for (i <- 1 to porridge.length -1) {
    val isSeatOk = goldData(0).toInt <= porridge(i)(0).toInt
    val isPorridgeOk = goldData(1).toInt >= porridge(i)(1).toInt
    if (isSeatOk && isPorridgeOk) {
      results += s"$i "
    }
  }
  results
}

goldilocks("100 120\n297 90\n66 110\n257 113\n276 191\n280 129\n219 163\n254 193\n86 153\n206 147\n71 137\n104 40\n238 127\n52 146\n129 197\n144 59\n157 124\n210 59\n11 54\n268 119\n261 121\n12 189\n186 108\n174 21\n77 18\n54 90\n174 52\n16 129\n59 181\n290 123\n248 132")

//def goldilocks (input: String) : String = {
//  val slices = input.split("\n").toList
//  val porridge = slices.map(s => s.split(" ").toList)
//  val goldData = porridge.head
//  var results = " "
//  var int= 0
//  println(porridge)
//  porridge.tail.foreach(x => {
//    if ((goldData(0).toInt <= x(0).toInt) && (goldData(1).toInt >= x(1).toInt)) {
//      results += s"$int "
//    }
//    int +=1
//    //println(int)
//  } )
//
//  results.replace("0","")
//}
//
//goldilocks("100 120\n297 90\n66 110\n257 113\n276 191\n280 129\n219 163\n254 193\n86 153\n206 147\n71 137\n104 40\n238 127\n52 146\n129 197\n144 59\n157 124\n210 59\n11 54\n268 119\n261 121\n12 189\n186 108\n174 21\n77 18\n54 90\n174 52\n16 129\n59 181\n290 123\n248 132")