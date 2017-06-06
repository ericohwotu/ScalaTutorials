//package Intermediate

/**
  * Created by Administrator on 06/06/2017.
  */
abstract class Vehicle {
  val brokenChance = 0.6 // the chance of a part being broken
  
  var broken: Boolean = true

  val id: Int

  var parts: scala.collection.mutable.ArrayBuffer[Part] = scala.collection.mutable.ArrayBuffer()

  val vehicleType: VehicleType.Value

  def toString(): String

  var repairCost: Double = 0

  def addParts(): Unit = {
    var partsCount = (Math.random() * 10).toInt + 10 // Minimum 10 parts maximum 20

    for (i <- 1 to partsCount) {

      var brokenProbability = Math.random()
      var partPrice = Math.random() * 35 + 5
      var repairTime = (Math.random()*5).toInt + 1
      var isBroken =  brokenProbability match {
        case _ if brokenProbability < brokenChance => true
        case _ => false
      }
      
      parts += new Part(i, partPrice, isBroken, repairTime) //Add new part to vehicle
    }
  }
}

