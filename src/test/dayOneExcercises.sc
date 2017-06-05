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
def joinString(a: String, b: String, c: Char, d: Char): String = a.concat(b+c+d)
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
  }else if((b==0)){
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
def iterate2(text: String, count: Int): Unit ={
  for(i<-1 to count)println(text*count)
}
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

