import scala.collection.mutable.ListBuffer

val s1 = "Hithisisthefirstlstringcall"// is the first string"
val s2 = "Hithisisthesecondstringidont"
def lcs[T]: (List[T], List[T]) => List[T] = {
  case (_, Nil) => Nil
  case (Nil, _) => Nil
  case (x :: xs, y :: ys) if x == y => x :: lcs(xs, ys)
  case (x :: xs, y :: ys)           => {
    (lcs(x :: xs, ys), lcs(xs, y :: ys)) match {
      case (xs, ys) if xs.length > ys.length => xs
      case (xs, ys)                          => ys
    }
  }
}
lcs(s1.toList,s2.toList).mkString("")

