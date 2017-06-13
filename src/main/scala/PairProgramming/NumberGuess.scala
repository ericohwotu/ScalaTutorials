package PairProgramming
import scala.util.control.Breaks

/**
  * Created by Administrator on 13/06/2017.
  */
object NumberGuess extends App {
  def higher(): Unit = {

  }

  def playGame(myNumber: Int) {
    val random = new scala.util.Random
    val loop = new Breaks
    var playing = true
    var inputting = true
    var computerGuess:Int  = random.nextInt(1000)
    var multiplier = 1.0
    var multEffect = 0
    var tempEffect = 0.0
    loop breakable {
      while (playing) {
        computerGuess = (computerGuess * multiplier).toInt
        multiplier = 1
        inputting = true
        while(inputting) {
          if(computerGuess == myNumber) {println(s"Your number is $myNumber"); loop.break}
          try{
          var highLow = scala.io.StdIn.readLine(s"Is your number higher or lower than $computerGuess" +"?")
          highLow match {
            case _ if (highLow == "higher") => multEffect = 1
            case _ if (highLow == "lower") => multEffect = -1
          }
          var temperature = scala.io.StdIn.readLine("Your options are: boiling - super warm - warm - cold - super cold - freezing")
          temperature match {
            case _ if (temperature == "boiling") => {tempEffect = 0.01 * multEffect; multiplier = multiplier + tempEffect; inputting = false}
            case _ if (temperature == "super warm") => {tempEffect = 0.2 * multEffect; multiplier = multiplier + tempEffect;; inputting = false}
            case _ if (temperature == "warm") => {tempEffect = 0.35 * multEffect; multiplier = multiplier + tempEffect;; inputting = false}
            case _ if (temperature == "cold") => {tempEffect = 0.55 * multEffect; multiplier = multiplier + tempEffect;; inputting = false}
            case _ if (temperature == "super cold") => {tempEffect = 0.75 * multEffect; multiplier = multiplier + tempEffect;; inputting = false}
            case _ if (temperature == "freezing") => {tempEffect = 0.95 * multEffect; multiplier = multiplier + tempEffect;; inputting = false}
            case _ => println("Input must be higher or lower")
          }
         }catch{
            case e: MatchError => println("Try again")
          }
        }
      }
    }
  }
  playGame(175)
}
