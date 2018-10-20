package example

import java.io.IOException

import scalaz.zio.{App, IO}
import scalaz.zio.console._

object FutureVsIO extends App {
  override def run(args: List[String]): IO[Nothing, FutureVsIO.ExitStatus.ExitNow] = {
    val multiple = for {
      _ <- notMemoized
      _ <- program
      _ <- transparent
    } yield ()
    
    multiple.attempt.map(_.fold(failed => throw failed , _ => ExitStatus.ExitNow(0)))
  }
  
  def program: IO[IOException, Unit] =
    for {
      _    <- putStrLn("Hello!, What is your name?")
      name <- getStrLn
      _    <- putStrLn("Hello, " + name + ", good to meet you")
    } yield ()
  
  
  import Helper._
  def transparent: IO[Throwable, Unit] = {
    val path = "/Users/ahmadragab/Desktop/test.txt"
    val e = IO.fail(new Error("Oops!"))
    val w = IO.sync(writeToFile(path))
  
    for {
      _ <- e
      _ <- w
    } yield ()
  }
  
  def notMemoized: IO[Nothing, Unit] = {
    val nextRandom = IO.point(println(s"Computing next Random: ${scala.util.Random.nextInt}"))
    
    for {
      _ <- nextRandom
      _ <- nextRandom
    } yield ()
  }
}