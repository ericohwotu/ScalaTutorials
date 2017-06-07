import scala.io.Source
import scala.collection.mutable.ListBuffer
def fileName = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\src\\main\\scala\\Advanced\\Report-for-6.txt"
def wordsList = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\words-list.txt"
//Hashmaps - anagrams
//first task
def readfile(fName: String): Array[String] = {
  Source.fromFile(fName).getLines().flatMap(_.split("\\W+")).toArray
}
//second task
def getOrderedString(word: String) = word.sortWith(_>_)

//get the anagrams
def getAnagrams(keys: Array[String], words: Array[String]): Map[String, ListBuffer[String]] = {
  var map: Map[String,ListBuffer[String]] = Map()
  for(f<-0 to keys.length-1){
        //check if the key exists
        (map.keySet.contains(keys(f))) match {
          case true =>  {
            var prevList = map(keys(f)) // create a container to hold the list in the pair
            prevList += words(f) //add the new string to the list
            map += (keys(f) -> prevList) //update the key with the new list
          }
          case _ => map += (keys(f)->ListBuffer(words(f))); //if the key doesn't exist create the new pair
        }
  }
  map
}

def scoreAnagrams(anagrams: Map[String, ListBuffer[String]]): Unit ={
  var score: ListBuffer[Tuple2[String, Int]] = ListBuffer(("",0))
  // for each item in the anagrams
  // check the Lists and see how many there are
  // if it has a value larger than current max replace max
  // and add the current position to the array
  for (f<- anagrams){

  }
}

val words = readfile(wordsList)
val orderedWord = getOrderedString("hello")
var keys =  words.map(x => getOrderedString(x))
val anagrams = getAnagrams(keys,words)

//anagrams.filter(x => (x._2.size>1)).filter(x => x._1.length >= 5)
//anagrams.toSeq.sortWith(_._2.size>_._2.size).dropWhile(x => x._2 < anagrams.max(x._2.size))

