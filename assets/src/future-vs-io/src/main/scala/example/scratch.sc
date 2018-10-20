import scalaz.zio.{App, IO}
import scalaz.zio
import scalaz.zio.console._
import scalaz.zio.syntax._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import java.io.{File, PrintWriter}


object scratch {
  
  lazy val randomFuture = Future.successful {
    println("Computing next random Int")
    scala.util.Random.nextInt()
  }
  
  println(randomFuture)
  println(randomFuture)
  
  
  def wtf: Int = {
    val sumFold: List[Int] => Int = _.foldLeft(0)((n, m) => n + m)
    sumFold(List(0,1,2)) + sumFold(List(3,4,5))
  }
  
  wtf
}