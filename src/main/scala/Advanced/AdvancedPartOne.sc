import scala.io.Source
def fileName = "C:\\Users\\Administrator\\IdeaProjects\\ScalaTutorialsSecond\\src\\main\\scala\\Advanced\\Report-for-6.txt"
//Hashmaps - anagrams
//first task
def readfile(fName: String): Array[String] = {
  Source.fromFile(fName).getLines().flatMap(_.split("\\W+")).toArray
}

val words = readfile(fileName)

//second task
def getOrderedString(word: String) = {
  word.map(x => {word.max; word.drop(word.indexOf(word.max))})
}

val orderedWord = getOrderedString("hello")
