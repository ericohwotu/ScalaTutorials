import scala.io.Source
import scala.collection.mutable.{ListBuffer, HashMap, MultiMap, Set}
def fileName = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\src\\main\\scala\\Advanced\\Report-for-6.txt"
def wordsList = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\words-list.txt"
//def wordsList = "words-list.txt"
//Hashmaps - anagrams
//first task
val readFile = (fName: String) => Source.fromFile(fName).getLines().toArray

//second task
val getOrderedString = (word: String) => word.sortWith(_>_)

//get the anagrams
def getAnagrams(keys: Array[String], words: Array[String]): Map[String, ListBuffer[String]] = {
  var map: Map[String,ListBuffer[String]] = Map()
  for(f<-0 until keys.length){
    map += (keys(f)) -> (map.get(keys(f)).getOrElse(ListBuffer())++=ListBuffer(words(f)))
  }
  map
}

//score anagrams
val scoreAnagrams = (anagrams:Map[String,ListBuffer[String]]) => anagrams.toSeq.sortWith(_._2.size>_._2.size).filter(x => x._2.size >= anagrams.maxBy(_._2.size)._2.size)


var words = readFile(wordsList)

var keys =  words.map(x => getOrderedString(x))

val anagrams = getAnagrams(keys, words)

val best = scoreAnagrams(anagrams)

//var map: Map[String, ListBuffer[String]] = Map()
//keys.zipWithIndex.foreach{case (key, i) => map += key -> (map.get(key).getOrElse(ListBuffer()) ++= ListBuffer(words(i))) }
//map


