object Test extends App {

  val strings: List[Option[String]] = List(Some("one"), None, None, Some("two"), Some("three"))
  val list: Option[List[String]] = Some(List("one", "two", "three"))
  val result2: Boolean = list match {
    case Some(value) => value.contains("one")
    case None => false
  }
  //println(result2)
  val list2: Option[List[String]] = list.map(x => x ::: (List.empty[String]))
  val result1: Boolean = strings.exists {
    case Some(value) => value.contains("five")
    case None => false
  }
  //println(result1)

  def optConvert(list: List[Option[String]]): Option[List[String]] = {
    list match {
      case Nil => Option(List.empty[String])
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

  val optStrings: Option[List[String]] = optConvert(strings)
  //println(optStrings)

}
