//exercise 7
def endsLy(str: String): Boolean ={
  str.takeRight(2)=="ly"
}

endsLy("oddy")

def stringClean(str: String): String ={
  def clean(str: String, char1: Char, char2:Char, checked:String): String ={
    var result = checked
    if(char1 != char2){
      result = checked + char1
    }
    if(str.length>0) {
      clean(str.drop(1), str.toCharArray.head, char1, result)
    }else{
      result
    }
  }
  clean(str,str.toCharArray.head,' ',"")
}

stringClean("hello")

//excercise 8
def printFilledDiamond(char: Char): Unit ={
  var result = ""
  val n=10

  for(i<- 0 to n){
    for(j<- 0 to n){
      if(((j <= (n/2)+(i+1))&&(j>=(n/2)-(i-1)))&&(j-1 <= (n/2)+((n/2)-(i-n/2)))&&(j-1 >= (n/2)-((n/2)-(i-n/2))))result+="a"//&&(j>=(n/2)-(i-((n/2)+1))))&& i>n/2)result+="a"
      else result += " "

    }
    result += "\n"
  }
  println(result)
}
printFilledDiamond('c')

def printBlankCheat(): Unit ={
  println("     x      \n")
  println("    x x     \n")
  println("   x   x    \n")
  println("    x x    \n")
  println("     x     \n")
}

printBlankCheat()