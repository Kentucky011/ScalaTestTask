
import ex.UserException

import scala.util.{Failure, Success, Try}

object EitherTest extends App {

  def randomExceptionOrValid(): String = {
    val n1: Int = (math.random() * 10).toInt
    val n2: Int = (math.random() * 10).toInt
    if (n1 > n2) {
      throw UserException("Возникла исключительная ситуация! Имей это ввиду")
    } else {
      "Работа продолжается"
    }
  }
  //(1 to 10).foreach(_ => println(randomExceptionOrValid()))

  def processingExOrValid(f: => Int): Option[Int] = {
    Try(f).toOption
    }

    /*try { // Альтернатива
      Right(f)
    } catch {
      case ex: UserException => Left(ex.getMessage)
    }*/



}
