import scala.io.StdIn
import scala.math._
import scala.util._

object TestFunction extends App {

  (f: Int) => f * f
  (1 to 9).map(0.1 * _)
  //(1 to 9).map("*" * _).foreach(println)
  (1 to 9).filter(_ < 0)
  (1 to 9).reduceLeft(_ * _) // можно укоротить используя .product
  "Mary had a little lamb".split(" ").sortWith(_.length < _.length) // Array("a", "had", "Mary", "lamb", "little").

  def sqrt(x: Int) = (x: Int) => x * x

  //def mulOneAtATime(x: Int)(y: Int) = x * y
  val mulOneAtATime = (x: Int) => (y: Int) => x * y
  // mulOneAtATime: Int => (Int => Int)
  //runInThread { () => println("Hi"); Thread.sleep(10000); println("Bye") }
  //runInThread { println("Hi"); Thread.sleep(10000); println("Bye") }
  /* def until(condition: => Boolean)(block: => Unit) {
     if (!condition) {
       block
       until(condition)(block)
     }
   }

    var x = 10
    until (x == 0) {
      x -= 1
      println(x)
    }*/
  //val sqr: Int => Int = (x: Int) => x * x
  //println(sqr(2))

  //З А Д А Н И Я
  // 1) Написать функцию с использованием типа Function1[Int, Int] которая возвращает квадрат переданного числа
  val sqr1 = new((Int) => Int) {
    override def apply(v1: Int): Int = v1 * v1
  }
  //println(sqr1(5))

  // 2) Написать чистую функцию аргументом которого будет функция - Чистая функция — это функция,
  // которая зависит только от своих объявленных входных данных и своей реализации для получения результата.
  // Она только вычисляет свой результат, не завися от внешнего мира и не изменяя его.

  //чистая функция должна принимать функцию вида f: String => Boolean и на основании результата вычисления функции f
  // формировать 1 или 2, 1 в случае если f вернет true и 2 если false
  // def validString(str: String): Boolean  = str.contains("valid")
  def emptyStr(str: String): Boolean = str.isEmpty
  //println(emptyStr(""))

  // 3) написать любую функцию с побочным эффектом

  /*val sqr2 = new((Int) => Int) {
    override def apply(v1: Int): Int = v1 * v1

    println("Я посчитала квадрат числа и теперь это можно где-то использовать")
  }*/

  // 4) Понять что такое функция высшего порядка(Чистая функция зачастую является HOF - higher-order function) - Функции высшего порядка
  // могут принимать другие функции в качестве параметров или возвращать функцию в качестве результата.


  // 5) Понять что такое замыкание на функции - догнал, замыкание это  функция первого класса,
  //в теле которой присутствуют ссылки на переменные, объявленные ВНЕ тела этой функции в окружающем коде
  //и не являющиеся её параметрами. Говоря другим языком, замыкание — это
  //функция, которая ссылается на свободные переменные в своей области видимости.
  // например:
  //def method1()={}
  //def method2() = {
  // method1() // замыкание мы используем на примере функцию которая находится рядом. Как один из элементов функции
  //}


  // 6) Что такое частично определенная функция, написать любую функцию на основе примера

  /*val multyply: (Int, Int) => Int = (x, y) => x * y
    val multyply2: Int => Int = multyply(2, _)
  println(multyply2(3))                         //  Оказалось, это замыкание))
  val add: (Int, Int, Int) => Int = (a, b, c) => a + b + c
  val add2: Int => Int = add(2, 3, _)
  println(add2(5))*/

  val num1: PartialFunction[Int, String] = {
    case i if i + 1 == 0 => "i это минус единица!"
  }

  val tru1: PartialFunction[Int, Boolean] = {
    case i if i * 1 == 0 && i * 2 == 0 => true
  }

  val num2: PartialFunction[Int, String] = {
    case i if i - 1 == 0 => "i это единица!"
  }
  val default: PartialFunction[Int, String] = {
    case i => i.toString
  }
  val testList = List(num1, num2, tru1, default).reduce(_ orElse _)

  //(-5 to 5).foreach(i => println(testList(i)))

  val posNum: PartialFunction[Int, Boolean] = {
    case a if a > 0 => true
  }
  val negNum: PartialFunction[Int, Boolean] = {
    case a if a < 0 => false
  }
  val nil: PartialFunction[Int, String] = {
    case a if a == 0 => "Не знаю зачем, но а = 0"
  }
  val default1: PartialFunction[Int, Char] = {
    case a => a.toChar
  }
  val caseList = List(posNum, negNum, nil, default1).reduce(_ orElse _)

  //(-5 to 5).foreach(a => println(caseList(a)))

  // частично определенная функция - Множество предложений case, заключенных в фигурные скобки,
  //образует частично определенную функцию (partial function) –
  //функцию, которая может быть определена не для всех входных значений.
  //Такие функции являются экземплярами класса PartialFunction[A, B].
  //(A – тип параметра, B – тип возвращаемого значения.) Этот класс
  //имеет два метода: apply, вычисляющий значение функции из сопо-
  //ставления с образцом, и isDefinedAt, возвращающий true, если вход-
  //ное значение совпадает хотя бы с одним образцом.


  // 7) Каррирование Написать функцию, которая принимает 2 набора параметров
  // def someFunc(str: String)(maybeValue: Option[T]): Boolean = {// TODO}
  //которая будет учитывать наличие значения в maybeValue в случае если оно есть, проверять str и если
  //str == "Valid" возвращать результата true, в случае если не выполняется ни одно условие то возвращать false
  //Но только если второй символ str будет иметь верхний регистр то надо возвращать true в не зависимости от переданного значения
  // к этому этапу стек зе оверфлоу а ещё 5 заданий >_<

  def someFunc(str: String)(maybeValue: Option[String]): Boolean = {
    maybeValue match {
      case Some(value) => if (str == "Valid") {
        true
      } else {
        str match {
          case str if str.isEmpty => false
          case str if str.length < 2 => false
          case str if str.charAt(1).isUpper => true
          case str if str.charAt(1).isLower => false
        }
      }

      case None => false
    }
  }
  // упрощенный вариант
  //def someFunc(str: String)(maybeValue: Option[String]): Boolean = {
  //    maybeValue -> str match { // создаем кортеж на ходу и матчим уже его
  //      case (Some(_), str) if str.length >= 2 && str != "Valid" => str.charAt(1).isUpper
  //      case (Some(_), str) if str.length < 2 => false
  //      case (Some(_), "Valid") => true
  //      case (None, _) => false
  //    }
  //  }

  //println(someFunc("Valid")(None))
  //println(someFunc("raAkAtUnA")(Some("фыафып")))


  // 8)Анонимная функция/ Лямбда функция


  // 9) Как создавать функции, Параметризация функции: Перевести рекурсивный метод с типа String на тип T,
  // для того что бы можно было использовать любой список из опциональных значений
  // в Test.scala


  // 10) Что такое type и как его использовать


  // 11) Подробнее про мап, флетмап
  //map
  //Применяет функцию к каждому элементу из списка, возвращается список с тем же числом элементов.
  //
  //scala> numbers.map((i: Int) => i * 2)
  //res0: List[Int] = List(2, 4, 6, 8)
  //или передается частично вызываемая функция
  //
  //
  //scala> def timesTwo(i: Int): Int = i * 2
  //timesTwo: (i: Int)Int
  //
  //scala> numbers.map(timesTwo _)
  //res0: List[Int] = List(2, 4, 6, 8)

  //flatMap
  //flatMap это часто используемый комбинатор, который объединяет map и flatten. flatMap берет функцию,
  //которая работает с вложенными списками и объединяет результаты.
  //
  //scala> val nestedNumbers = List(List(1, 2), List(3, 4))
  //nestedNumbers: List[List[Int]] = List(List(1, 2), List(3, 4))
  //
  //scala> nestedNumbers.flatMap(x => x.map(_ * 2))
  //res0: List[Int] = List(2, 4, 6, 8)
  //Думайте об этом, как о коротком способе использования map, а затем применения flatten к результату:
  //
  //scala> nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten
  //res1: List[Int] = List(2, 4, 6, 8)
  //в этом примере вызывается map, а позднее flatten, как пример “комбинаторной” природы этих функций.
  /* def squared(a: Double): Option[Double] = {
     Option.when(a > 0)(math.pow(a, 2.0))
     // решение в лоб
   }*/

  /* def pythMatch(a: Double, b: Double): Option[Double] = {
   squared(a) match {
     case None => None
     case Some(value) =>
       squared(b) match {
         case None => None
         case Some(value) => Some(math.sqrt(a + b))
       }
   }
 }*/
  // Альтернатива с помощью flatmap
  /*  def pythMatch[T](a: Double, b: Double): Option[Double] = {
      squared(a).flatMap { a =>
        squared(b).flatMap { b =>
          Some(math.sqrt(a + b))
        }
      }

    }*/
  val names = List("bob", "peter", "paul")
  //println(names.map(_.toUpperCase)) // идентичен следующему варианту
  //println(for (n <- names) yield n.toUpperCase()) // идентичен варианту с Мар

  def ulcase(s: String) = Vector(s.toUpperCase(), s.toLowerCase())
  //println(names.map(ulcase))
  //println(names.flatMap(ulcase))
  // Совет. Если метод flatMap используется с функцией, возвращающей зна-
  //чение типа Option, возвращаемая коллекция будет содержать все значе-
  //ния v, для которых функция вернет Some(v).

  //val prices = List(5.0, 20.0, 9.95)
  //val quantities = List(10, 2, 1)
  //println(prices zip quantities)

  /*case class User(login: String = "not login", name: String = "not name") {
  }

  val max = new User()
  val x = 10
  val newUser = if (x > 5) max.copy(login = "kaka") else max.copy(name = "pupupu")
  println(max)
  println(newUser)*/

  /*val a = Array("Sanya", "Molodec")
  val b = Array("sanya", "molodec")
  println(a.corresponds(b)(_.equalsIgnoreCase(_)))*/

  //(x: Int) => x * 3
  /*val list: List[Any] = List(
    "a string",
    732, // целое число
    'c', // символ
    true, // логическое значение
    () => "анонимная функция возвращающая строку"
  )

  list.foreach(element => println(element))*/
  /* println("-3+4".collect {
     case '+' => 1
     case '-' => -1
   }.toList)*/
  /*class Pair[T <: Comparable[T]](val first: T, val second: T) {
    def smaller = if (first.compareTo(second) < 0) first else second
  }
  val p = new Pair("Fred", "Brooks")
  println(p.smaller)*/

  val number: Option[Int] = Some(4)
  println(number.getOrElse("Нет того что нужно было"))
}