import java.time._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object ScalaBookTasks1 extends App {
  // 1. Рассмотрите выражение
  //for (n1 <- Future { Thread.sleep(1000) ; 2 }
  //n2 <- Future { Thread.sleep(1000); 40 })
  //println(n1 + n2)
  //Как это выражение преобразуется в вызовы методов map и flatMap?                                             Не знаю
  //Как выполняются задания Future, параллельно или после-
  //довательно? В каком потоке произойдет вызов println?                              Думаю что последовательно, в main.


  //2. Напишите функцию doInOrder, принимающую функции f: T =>
  //Future[U] и g: U => Future[V] и возвращающую функцию T =>
  //Future[U], которая для заданного значения t в конечном счете
  //возвращает g(f(t)).

  def sqr1(x: Int): Future[Int] = Future(x * x)

  def add1(y: Int): Future[Int] = Future(y + 5)

  def doInOrder[T, U, V](fun1: T => Future[V], fun2: V => Future[U]): T => Future[U] = {
    t: T => fun1(t).flatMap(i => fun2(i))
  }

  def fun3: Int => Future[Int] = doInOrder(sqr1, add1)

  val result: Future[Int] = fun3(5)
  //println(result)
  //result.foreach(println)

  //3. Повторите предыдущее упражнение для произвольной после-
  //довательности функций типа T => Future[T].

  def sqr2(x: Int): Future[Int] = Future(x * x)

  def add2(y: Int): Future[Int] = Future(y + 5)

  def myl2(z: Int): Future[Int] = Future(z * 2)

  def doInOrder(fun1: Int => Future[Int], fun2: Int => Future[Int], fun3: Int => Future[Int]): Int => Future[Int] = {
    t => fun1(t).flatMap(i => fun2(i).flatMap(a => fun3(a)))
  }

  def newFun: Int => Future[Int] = doInOrder(sqr2, add2, myl2)

  val result2: Future[Int] = newFun(2)
  //println(result2)
  //result2.foreach(println)

  //4. Напишите функцию doTogether, принимающую функции f:
  //T => Future[U] и g: U => Future[V] и возвращающую функцию
  //T => Future[(U, V)], которая выполняет два задания параллель-
  //но и для заданного значения t в конечном счете возвращает
  //(f(t), g(t)).

  def doTogether[T, U, V](f: T => Future[U], g: T => Future[V]): T => Future[(U, V)] = {
    t: T =>
      val futureU = f(t)
      val futureV = g(t)

      for {
        u <- futureU
        v <- futureV
      } yield (u, v)
  }

  def f(t: Int): Future[String] = Future {
    Thread.sleep(1000)
    s"\"$t\""
  }

  def g(t: Int): Future[Int] = Future {
    Thread.sleep(2000)
    t
  }

  val combFun = doTogether(f, g)

  val resultFuture: Future[(String, Int)] = combFun(1)

  resultFuture.onComplete {
    case Success((u, v)) => println(s"Результат: ($u, $v)")
    case Failure(exception) => println(s"ОШИБКА: $exception")
    case _ => println("Что-то пошло не так")
  }
  Thread.sleep(3000)
  println(resultFuture)

  /*def computeAnswer(arg: String) = {
    val p = Promise[Int]()
    Future {
      val n = workHard(arg)
      p.success(n)
      workOnSomethingElse()
    }
    p.future
  }*/

  /*val n: Future[(U, V)] = for (a1 <- f; b2 <- g) yield (a1, b2)*/


  /*fut1 onComplete {
    case Success(n1: U) =>
      fut2 onComplete {
        case Success(n2: V) => val n = (n1: U, n2: V)
        case Failure(ex) => (ex.getMessage)
      }
    case Failure(ex) => (ex.getMessage)
  }*/





}