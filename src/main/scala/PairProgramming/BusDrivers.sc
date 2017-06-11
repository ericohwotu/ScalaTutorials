import scala.collection.mutable.Buffer

//function to get the current stop number
def getStopNumber[T](allStops: Buffer[T], curStop: Int): Int = curStop % allStops.length //circular count

//check if two drivers are at the same stop
def isSame[T](stopName1: T, stopName2: T): Boolean = stopName1 == stopName2

//function to get drivers and routes
def mapRouteToDriver(routes: String): Buffer[Buffer[String]] = {
  val splitRoutes = routes.split("\n").toBuffer
  val splitStops = splitRoutes.map(stops => stops.split(" ").toBuffer)
  println(s"There are ${splitRoutes.length} drivers in the simulation.\nBuffer = $splitStops")
  splitStops
}

//define a function that runs a loop to exchange the secrets
def runBuses(rs: String): Unit = {
  var secrets, counter = 0
  val allRoutes = mapRouteToDriver(rs)
  val len = allRoutes.length

  while (secrets < len ){
    for(i <- 0 until len){
      val stop1 = allRoutes(i % len)(getStopNumber(allRoutes(i % len),counter))
      val stop2 = allRoutes((i + 1) % len)(getStopNumber(allRoutes((i+1) % len),counter))
      if (isSame(stop1,stop2)) secrets += 1
    }
    counter += 1
  }
  println(s"It took $counter stops to exchange all secrets")
}

runBuses("12 6 8 9 10\n11 12 8 7\n33 6 8 9 7 12 32")
runBuses("3 1 2 3\n3 2 3 1\n4 2 3 4 5")
runBuses("12 23 15 2 8 20 21 3 23 3 27 20 0\n21 14 8 20 10 0 23 3 24 23 0 19 14 12 10 9 12 12 11 6 27 5\n8 18 27 10 11 22 29 23 14\n13 7 14 1 9 14 16 12 0 10 13 19 16 17")
runBuses("12 23 15 2 8 20 21 3 23 3 27 20 0\n21 14 8 20 10 0 23 3 24 23 0 19 14 12 10 9 12 12 11 6 27 5\n8 18 27 10 11 22 29 23 14\n13 7 14 1 9 14 16 12 0 10 13 19 16 17\n24 25 21 4 6 19 1 3 26 11 22 28 14 14 27 7 20 8 7 4 1 8 10 18 21\n13 20 26 22 6 5 6 23 26 2 21 16 26 24\n6 7 17 2 22 23 21\n23 14 22 28 10 23 7 21 3 20 24 23 8 8 21 13 15 6 9 17 27 17 13 14\n23 13 1 15 5 16 7 26 22 29 17 3 14 16 16 18 6 10 3 14 10 17 27 25\n25 28 5 21 8 10 27 21 23 28 7 20 6 6 9 29 27 26 24 3 12 10 21 10 12 17\n26 22 26 13 10 19 3 15 2 3 25 29 25 19 19 24 1 26 22 10 17 19 28 11 22 2 13\n8 4 25 15 20 9 11 3 19\n24 29 4 17 2 0 8 19 11 28 13 4 16 5 15 25 16 5 6 1 0 19 7 4 6\n16 25 15 17 20 27 1 11 1 18 14 23 27 25 26 17 1")

