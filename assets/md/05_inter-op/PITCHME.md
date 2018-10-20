@snap[north]
@color[#eb4500](Interopability with in ZIO)
@snapend

```scala
def fromFuture[A](ftr: () => Future[A])(ec: ExecutionContext): IO[Throwable, A] =
def toFuture: IO[Nothing, Future[A]]
```
<br>
1. `ftr` is a thunk
1. failed futures produce `Throwable`