package example

import scala.concurrent.Future

object FutureMemoization extends App {
  val randomFuture = Future.successful {
    println("Computing next random Int")
    scala.util.Random.nextInt()
  }
  
  println(randomFuture)
  println(randomFuture)
}
