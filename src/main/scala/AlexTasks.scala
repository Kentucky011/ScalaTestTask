import AlexTasks.C.{a, res}

import List._

object AlexTasks extends App {

  //Создать кейс класс User(name, age, isMale)
  //Создать для него объект компаньон в котором переопределить метод apply и unnaply
  //2 метода apply() которые будут принимать только 2 разны параметра, третий параметр будет иметь значение по дефолту.
  // Создать инстанс класса user с 3 параметрами. то есть val user = User("Vasya", 23, true)
  //
  //вопрос получится ли создать такой инстанс, если получится, то почему, если нет, то почему
  case class User(name: String, age: Int, isMale: Boolean)

  object User {
    def apply(age: Int, isMale: Boolean): User = new User("Kolya", age, isMale)

    def apply(name: String, age: Int): User = new User(name, age, false)

  }

  val user1 = User("Юзер из User", 15, true)
  println(user1)

  //ответ да, потому что потому что добавили 2 метода, которые принимают по 2 параметра,
  // а 1 параметр будет использован по умолчанию, а в кейс классе 3 параметра изначально
  //потому, что у кейс класса создается объект компаньон с методом apply


  //Реализовать кейс класс UserNew, который будет содержать такое же количество параметров что и User
  //Вызвать у UserNew метод tupled созданий объекта при помощи тайпла
  //val newUser = UserNew.tupled(// поля инициализации)
  //Вызвать у User аналогично tupled, почему у UserNew имеется tupled а у User его нет?
  //из-за того, что tupled => apply, а так как мы apply переопределили в User, то не работает tupled

  /*case class UserNew(name: String, age: Int, isMale: Boolean)

    val newUser = UserNew("Юзер из UserNew обычным способом", 21, false)

    val newUser1 = UserNew.tupled("Юзер из UserNew с помощью tupled", 15, true)

    val tuplNewUser: String => Int => Boolean => UserNew = UserNew.curried

    val newUser2: UserNew = tuplNewUser("Юзер из UserNew с помощью curried")(21)(true)

    println(newUser)
    println(newUser1)
    println(newUser2)*/

  //curried. У класса UserNew достать метод curried
  //val tuplNewUser = NewUser.curried указать тип у tuplNewUser
  //при помощи этой функции сконструировать новый инстанс класса newUser1 = tupleNewUser ???? тут реализация

  // Создать тайпл  из четырех полей (String, Int, Boolean, Double)
  // распаковать кортеж с помощью сопоставление с образцом, не используя match

  /*val firstTuple = ("Строка", 1, true, 5.0)
  val (name, count, isBoolean, isDouble) = firstTuple
  println(name)
  println(count)
  println(isBoolean)
  println(isDouble)
  // распаковать кортеж только для типа String и Double. Остальные заменить плейсхолдером
  val (string, _, _, double) = firstTuple
  println(string)
  println(double)*/

  //Создать список типа строка, наполнить данными, используя map пройтись по списку и для каждого элемента длина которого больше 3,
  //текущий элемент заменить на true, если условие не выполняется то заменить элемент на false
  val list: List[String] = List("1", "22", "много", "три", "пять", "12")
  //println(list.map(_.length > 3)) // ЛЯМБДА ФУНКЦИЯ ЧЕРЕЗ ПЛЕЙСХОЛДЕР
  // 1. для аргумента функции map написать анонимную функцию использую синтаксический сахар
  //    вида val func = (String => Boolean) приминить на map для списка эту функцию
  //println(list.map((x: String) => x.length > 3)) // АНОНИМНАЯ ФУНКЦИЯ
  // 2. Для аргумента функции map написать лямбду функцию которая будет проверять элемент, использовать блок кода
  //println(list.map(x => x.length > 3)) // ЛЯМБДА ФУНКЦИЯ

  // 3. для аргумента функции map написать действительную функцию через def которая будет принимать String и возвращать Boolean
  //def f(str: String): Boolean = str.length > 3

  //println(list.map(f))

  //Для списка создать иплицитный класс, который будет расширять функциональность списка List реализовывать в себе метод
  //replaceStingWhenThreeEvenToTrueElseFalse()
  //при вызове которого List("asasf", "rrqwr",  "arr", "s").replaceStingWhenThreeEvenToTrueElseFalse()
  //будет возвращать List(true, true, true, false)

  object ListReplace {
    implicit class replaceList(list: List[String]) {
      def replaceStingWhenThreeEvenToTrueElseFalse(): List[Boolean] = {
        list.map(str => if (str.length >= 3) true else false)
      }
    }
  }

  import AlexTasks.ListReplace._

  val list1 = List("1", "2", "три", "четыре", "пять")
  //println(list1.replaceStingWhenThreeEvenToTrueElseFalse())

  trait A {
    def a: String = "Я из трейта А"
  }

  trait B {
    def a: String = "Я из трейта В"
  }

  object C extends A with B {
    override def a: String = super[A].a
    val res = super[B].a
  }
  println(a)
  println(res)


}
