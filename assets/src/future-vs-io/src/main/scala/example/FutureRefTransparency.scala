package example

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

object FutureRefTransparency extends App {
  import Helper._
  import scala.concurrent.ExecutionContext.Implicits.global

  val path = "/Users/ahmadragab/Desktop/test.txt"
  
//  val fut1 = Future { throw new Exception("Oops!") }
//  val fut2 = Future { writeToFile(path) }
  
//  val ftr = for {
//    _ <- fut1
//    _ <- fut2
//  } yield ()
  
  val ftr = for {
    _ <- Future { throw new Exception("Oops!") }
    _ <- Future { writeToFile(path) }
  } yield ()
  
  println(externalState)
  
  Await.result(ftr, Duration.Inf)
}
