object Test extends App {

  val strings: List[Option[String]] = List(Some("one"), None, None, Some("two"), Some("three"))
  // Some(List("one", "two", "three"))
  val list: Option[List[String]] = Some(List("one", "two", "three"))
  val result2: Boolean = list match {
    case Some(value) => value.contains("five")
    case None => false
  }
  println(result2)
  val list2: Option[List[String]] = list.map(x => x ::: (List.empty[String]))
  // List("one", "two", "three")
  val stringHead = strings.head
  val stringsTail = strings.tail
  val result1: Boolean = strings.exists {
    case Some(value) => value.contains("five")
    case None => false
  }
  //println(result1)
  def optConvert(list: List[Option[String]]): Option[List[String]] = {
    list match {
      case Nil => Option(List.empty[String]) // Some(List()))
      case ::(head, tail) =>
        val result = head match {
          case Some(value) => Option(List(value))
          case None => Option(List.empty[String])
        }
        optConvert(tail).flatMap { x =>
          result.map { y =>
            y ::: (x)
          }
        }
    }

  }
  /*val list1: List[Option[String]] = (1 to 10000).map(x => Some(s"value$x")).toList
  val optlist1 = optConvert(list1)
  println(optlist1)*/
// List(Some(), Some(), Some())
  // (Some() * (Some() * (Some())))
  val optStrings: Option[List[String]] = optConvert(strings)
 // println(optStrings)
}


//(value) :: tail =>
//        optConvert(tail) match {
//          case Some(tailValue) => Some(value :: tailValue)
//          case None => Some(List(s"$value + $tail"))