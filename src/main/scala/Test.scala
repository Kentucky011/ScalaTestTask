object Test extends App {

  val strings: List[Option[String]] = List(Some("one"), None, None, Some("two"), Some("three"))
  val numbers: List[Option[Int]] = List(Some(5), Some(3), Some(6), None, None, Some(10))
  val booleans: List[Option[Boolean]] = List(Some(true), None, None, Some(false), Some(true))
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

  def optConvert[T](list: List[Option[T]]): Option[List[T]] = {
    list match {
      case Nil => Option(List.empty[T])
      case ::(head, tail) =>
        val result = head match {
          case Some(value: T) => Option(List(value: T))
          case None => Option(List.empty[T])
        }
        optConvert(tail).flatMap { x =>
          result.map { y =>
            y ::: (x)
          }
        }
    }

  }

  val optNum: Option[List[Int]] = optConvert(numbers)
  //println(optNum)
  val optBool: Option[List[Boolean]] = optConvert(booleans)
  //println(optBool)
  val optStrings: Option[List[String]] = optConvert(strings)
  //println(optStrings)
}
