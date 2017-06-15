def countChange(money: Int, li: List[Int]): Int ={
  //println(money +" "+li)
  money match {
    case 0 => 1
    case x if (x>0 && li.nonEmpty)  => countChange(money - li.head, li) + countChange(money, li.tail)
  }

}

println(countChange(301,List(5)))

def sum(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a+1 ,f(a) + acc)
  }
  loop(a,0)
}

sum(x=> x*10)(2,1000000)