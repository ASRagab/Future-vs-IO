@color[#eb4500](Introduction to ZIO)

+++

@snap[north]
@color[#eb4500](ZIO Usage and Evaluation Modes)
@snapend

```scala
IO.point("Hello World") // Pure IO (Lazy)
IO.now("Hello World") // Pure IO (Strict)
val z: IO[Nothing, Long] = IO.sync(System.nanoTime()) // Infallible IO
IO.syncException(FileUtils.readFileToByteArray(new File(name))): IO[Exception, Array[Byte]] // Fallible IO
def makeRequest(req: Request): IO[HttpException, Response] = IO.async(cb => Http.req(req, cb)) // Async IO
```

---?code=assets/src/future-vs-io/src/main/scala/example/FutureVsIO.scala&lang=scala&title=ZIO Console Example   