@color[#eb4500](Problems with Future)

+++

@snap[north]
@color[#eb4500](Problems with Future: Setup)
@snapend

```scala
import java.io.{File, PrintWriter}

object Helper {
  var externalState = 0
  
  def writeToFile(path: String): Unit = {
    externalState = externalState + 10
    val w = new PrintWriter(new File(path))
    w.write(externalState.toString)
    w.close()
  }
}
```

+++

@snap[north]
@color[#eb4500](Referential Opaqueness)
@snapend

```scala
  import Helper._
  import scala.concurrent.ExecutionContext.Implicits.global
  
  val path = "/Users/ahmadragab/Desktop/test.txt"
  val fut1 = Future { throw new Exception("Oops!") }
  val fut2 = Future { writeToFile(path) }
  
  val ftr = for {
    _ <- fut1
    _ <- fut2
  } yield ()
  
  println(externalState)
  Await.result(ftr, Duration.Inf)
  ```

@[5-6](What happens if I make these `lazy`)
@[5-6](What happens if I make these `defs`) 

+++

@snap[north]
@color[#eb4500](Referential Opaqueness: Inlined)
@snapend

```scala
  val ftr = for {
    _ <- Future { throw new Exception("Oops!") }
    _ <- Future { writeToFile(path) }
  } yield ()
```
@[4](Does the file get written to the desktop?)

+++

@snap[north]
@color[#eb4500](Memoization Issues)
@snapend

```scala
  lazy val randomFuture = Future.successful {
    println("Computing next random Int")
    scala.util.Random.nextInt()
  }
  
  println(randomFuture)
  println(randomFuture)
```
@[6-7](How many randoms are produced?)
@[6-7](How many times does "Computing next..." get printed out?)

+++

@snap[north]
@color[#eb4500](Other Challenges with Future)
@snapend

- Uses JVM Threads | 
- Abstractions over early termination missing |
- Fails with Throwable, and only Throwable |

+++

@snap[north]
@color[#eb4500](Exception Handling)
@snapend

- Handles certain exceptions differently from others | 
  - Errors |
  - scala.runtime.NonLocalReturnControl[_] |
  - InterruptedException |

```scala
// Promise.scala 
private def resolver[T](throwable: Throwable): Try[T] = throwable match {
  case t: scala.runtime.NonLocalReturnControl[_] => Success(t.value.asInstanceOf[T]) // more on this shortly
  case t: scala.util.control.ControlThrowable    => Failure(new ExecutionException("Boxed ControlThrowable", t))
  case t: InterruptedException                   => Failure(new ExecutionException("Boxed InterruptedException", t))
  case e: Error                                  => Failure(new ExecutionException("Boxed Error", e))
  case t                                         => Failure(t)
}
```

