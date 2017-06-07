import scala.io.Source
import scala.collection.mutable.ListBuffer
def fileName = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\src\\main\\scala\\Advanced\\Report-for-6.txt"
def wordsList = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\words-list.txt"
//Hashmaps - anagrams
//first task
def readfile(fName: String): Array[String] = {
  Source.fromFile(fName).getLines().flatMap(_.split("\\W+")).toArray
}

val words = readfile(wordsList)

//second task
def getOrderedString(word: String) = word.sortWith(_>_)

val orderedWord = getOrderedString("hello")
var anagrams = words
var map1: Map[String,ListBuffer[String]] = Map()


anagrams = words.map(x => getOrderedString(x))

for(f<-0 to anagrams.length-1){
  anagrams(f) match{
  case _ if (anagrams.count(x => x == anagrams(f))>1) => {

    //check if the key exists
    (map1.keySet.contains(anagrams(f))) match {
      case true =>  {
        var prevList = map1(anagrams(f)) // create a container to hold the list in the pair
        prevList += words(f) //add the new string to the list
        map1 += (anagrams(f) -> prevList) //update the key with the new list
      }
      case _ => map1 += (anagrams(f)->ListBuffer(words(f))); //if the key doesn't exist create the new pair
    }
  }
  case _ => map1 += (anagrams(f)->ListBuffer(words(f))); //else create the new pair
  }
}

map1
anagrams.count(x => x=="dog")

List(1,2,3).getClass

var nmap = Map(1 -> "s", 2 -> "v")
nmap(1)
nmap.keySet.contains(1)