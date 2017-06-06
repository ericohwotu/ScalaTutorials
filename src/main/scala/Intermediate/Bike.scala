//package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
case class Bike(id: Int, vehicleType: VehicleType.Value = VehicleType.BIKE) extends Vehicle {

  // val brokenChance = 0.6 // the chance of a part being broken 

  // add parts when Object is initiated
  addParts()

  //overide to string function
  override def toString() = {
    
    var partsInfo: String = ""

    for (i <- parts) {
      partsInfo += i.toString()
    }
    //Return new String
    s"ID: $id \nType: $vehicleType \nParts: ($partsInfo)"
  }

  // def addParts(): Unit = {
  //   var partsCount = (Math.random() * 10).toInt + 10 // Minimum 10 parts maximum 20

  //   for (i <- 1 to partsCount) {

  //     var brokenProbability = Math.random()
  //     var partPrice = Math.random() * 35 + 5
  //     var repairTime = (Math.random()*5).toInt + 1
  //     var isBroken =  brokenProbability match {
  //       case _ if brokenProbability < brokenChance => true
  //       case _ => false
  //     }
      
  //     parts += new Part(i, partPrice, isBroken, repairTime) //Add new part to vehicle
  //   }
  // }
}
