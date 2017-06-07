//first task
println("Hello World")

//second task
val helloWorld = "Hello World"
println(s"$helloWorld")

//third task
def acceptString(name: String): Unit = println(s"$name")

acceptString("hello World")

//fourth task
def returnString(): String = "Hello World"

println(returnString())

//fifth task
def returnType(in: Any) = println(in)

returnType(1.8)
returnType(1)
returnType("hi")
returnType(true)

// strings
def trimString(term: String, index: Int): String = term.substring(term.length()-index)
val trimmedString = trimString("hello",3)

//strings 2
def joinString(a: String, b: String, c: Char, d: Char): String = a.concat(b).replace(c,d)
val jointString= joinString("hel", "lo ", 'm', 'e')

//operators
def sum(a: Int, b: Int): Int = a+b
val newSum = sum(12,5)

//conditionals
def calculate(a: Int, b: Int, opt: Boolean): Int = if(opt){a+b}else{a*b}
val calculatedValueTrue = calculate(12,5,true)
val calculatedValueFalse = calculate(12,5,false)

//conditionals 2
def calculateThird(a: Int, b: Int, opt: Boolean): Int = {
  if(a==0 && b==0){
    0
  }
  else if(a==0){
    b
  }else if(b==0){
    a
  }else if(opt){
    a+b
  }else{
    a*b
  }
}
val calculatedThirdTrue = calculate(12,0,true)
val calculatedThirdFalse = calculate(0,0,false)

//iteration
def iterate(text: String, count: Int): Unit ={
  for(i<-1 to count)println(text)
}
iterate("hello",6)

//iteration 2
def iterate2(text: String, count: Int): Unit = for(i<-1 to count)println(text*count)
iterate2("h",6)

//iteration 3
def fizzBuzz(first: String, second: String, count: Int): Unit = {
  for(i<-1 to count){
    if(i % 3 == 0 && i % 5 == 0){
      println(first.concat(second))
    }else if(i % 5 == 0){
      println(second)
    }else if(i % 3 == 0){
      println(first)
    }else{
      println(i)
    }
  }
}
fizzBuzz("Hello", "Bye", 20)

//iteration 4
//method recursion 1
def iterateRecursion(text: String, count: Int, cur: Int = 1): Unit ={
  println(text)
  var index = cur + 1
  if (cur <= count) {
    iterateRecursion(text, count, index)
  }
}
iterateRecursion("hello",6)

//method recursion 2
def iterateRecursion2(text: String, count: Int, cur: Int = 1): Unit ={
  println(text*count)
  var index = cur + 1
  if (cur <= count) {
    iterateRecursion2(text, count, index)
  }
}
iterateRecursion2("he",6)

//method recursion 3
def fizzBuzzRecursion(first: String, second: String, count: Int, cur: Int = 1): Unit = {
  if(cur % 3 == 0 && cur % 5 == 0){
    println(first.concat(second))
  }else if(cur % 5 == 0){
    println(second)
  }else if(cur % 3 == 0){
    println(first)
  }else{
    println(cur)
  }
  var index = cur + 1
  if (cur <= count){
    fizzBuzzRecursion(first, second, count, index)
  }
}
fizzBuzzRecursion("Hello", "Bye", 20)

//pattern Matching 1
def calculateMatch(a: Int, b: Int, opt: Boolean): Int = {
  opt match{
    case true => a+b
    case false => a*b
  }
}
val matchedValueTrue = calculate(12,5,true)
val matchedValueFalse = calculate(12,5,false)

//conditionals 2
def calculateMatch3(a: Int, b: Int, opt: Boolean): Int = {
  a match {
    case 0=> b match {
      case 0=> 0
      case _=> b
    }
    case _=> b match {
      case 0=> a
      case _=> calculateMatch(a, b, opt)
    }
  }
}

val calculatedMatch1 = calculateMatch3(12,0,true)
val calculatedMatch2 = calculateMatch3(0,2,false)
val calculatedMatch3 = calculateMatch3(12,2,true)
val calculatedMatch4 = calculateMatch3(12,2,false)

// pattern Matching 2
def swapValues[T](arr: T) = {
  arr match {
    case Array(x,y) => Array(y,x)
    case arrItem: Array[Int] => Array(arrItem(1),arrItem(0))
    case (x, y) => Tuple2(y,x)
    case (x, y,_) => Tuple2(y,x)
    case (x, y,_,_) => Tuple2(y,x)
    case (x, y,_,_,_) => Tuple2(y,x)
    case List(x,y) => List(y,x)
    case x :: y :: _ => List(y,x)
    case _ => "Not a valid type"
  }
}


swapValues(Array(3,6,8,9,3,5,3))
swapValues((4,19))
swapValues(List(2,5,7,8,93))
swapValues((4,19,6))

(1,2,3,4,5).getClass.isInstance((1,2,3,4,5).getClass)
