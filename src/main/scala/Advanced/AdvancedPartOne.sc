import scala.io.Source
import scala.collection.mutable.{ListBuffer, HashMap, MultiMap, Set}
def fileName = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\src\\main\\scala\\Advanced\\Report-for-6.txt"
def wordsList = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\words-list.txt"
//def wordsList = "words-list.txt"
//Hashmaps - anagrams
//first task
val readFile = (fName: String) => Source.fromFile(fName).getLines().flatMap(_.split("\\W+")).toArray

//second task
val getOrderedString = (word: String) => word.sortWith(_>_)

//get the anagrams
def getAnagrams(keys: Array[String], words: Array[String]): Map[String, ListBuffer[String]] = {
  var map: Map[String,ListBuffer[String]] = Map()
  for(f<-0 to keys.length-1){
    map += (keys(f)) -> (map.get(keys(f)).getOrElse(ListBuffer())++=ListBuffer(words(f)))
  }
  map
}

//score anagrams
val scoreAnagrams = (anagrams:Map[String,ListBuffer[String]]) => anagrams.toSeq.sortWith(_._2.size>_._2.size).filter(x => x._2.size >= anagrams.maxBy(_._2.size)._2.size).maxBy(_._1.length)


var words = readFile(wordsList)//.foreach(item1 => (tupleList += Tuple2("",item1)))
var keys =  words.map(x => getOrderedString(x))//.foreach(item => (tupleList += Tuple2(item,"")))
val anagrams = getAnagrams(keys, words)
val best = scoreAnagrams(anagrams)

//(keys zip words).toMap
//val getAnagrams = (keys: Array[Tuple2[String,String]]) => {keys.foreach(x => map += (x._1 -> (map.get(x._1).getOrElse(ListBuffer())++=ListBuffer(x._2)))); map}
//get anagrams
//var map: Map[String,ListBuffer[String]] = Map()
//map.get("hi").getOrElse(ListBuffer(""))+="s"
//var mm = new HashMap[Int, Set[String]] with MultiMap[Int, String]
//var tupleList : ListBuffer[Tuple2[String,String]] = ListBuffer()

