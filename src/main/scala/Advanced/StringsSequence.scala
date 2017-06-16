object StringsSequence{
    val s1 = "hello how are you you are a cool"
    val s2 = "there is a lot you to do in the work and should still be"
    def main(args: Array[String]){
        val cs1 = s1.replace(" ","").map(item => item)
        val cs2 = s1.replace(" ","").map(item => item)
        var subString = ""
        var iter = 0

        val a: Char => Unit = (c) => {subString += c; println("add")}
        val r: () => Unit = () => {subString += "-"; println("remove")}

        val newString = cs1.foreach(i => cs2.foreach(j => if (i == j) a(i) else r()))

        
        println(subString)
    }
}