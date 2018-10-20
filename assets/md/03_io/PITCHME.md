@color[#eb4500](Introduction to IO)

+++

@snap[north]
@color[#eb4500](Substitutional Impurity)
@snapend

#### Why do we say that println is impure?

```scala
println("Hello World")
// Any => Unit or ()
```

- It breaks substitution because I cannot replace () with println without changing the effect my program has

+++

@snap[north]
@color[#eb4500](Purity is not enough)
@snapend

```scala
object Pure {
  def println(msg: String) = () => Predef.println(msg)
}

// Pure.println("IO is cool") is equivalent to () => Predef.println(msg)
```

- Thunks EVERYWHERE? |
- At some point we've got to dispatch these actions |
- Also, we'd like some abstractions for different evaluation modes  |
  - Strict (Eager) |
  - Lazy (Synchronous) |
  - Asyncronously (Callbacks) |
  - Failable (Exceptions) |


+++

@snap[north]
@color[#eb4500](General Behavior of IO)
@snapend

- Monad! |
- Capture effects into Data Structures |
- Build description(s) of a program(s) |
- explicit dispatch or invocation mechanism |
  - run |
  - unsafePerformIO | 

+++

@snap[north]
@color[#eb4500](Not all IOs are built the same)
@snapend

- fs2 and Monix Task |
  - All the evaluation modes |
  - Performant |
  - Tied to the larger framework |

- Scalaz 7 IO |
  - Doesn't really support asynchronous computation (i.e. callbacks) |
  - Only lazy effect capturing (cannot strictly evaluate) |

+++

@snap[north]
@color[#eb4500](Better IOs)
@snapend

- cats-effect [(https://typelevel.org/cats-effect/)](https://typelevel.org/cats-effect/)
- zio [(https://scalaz.github.io/scalaz-zio/)](https://scalaz.github.io/scalaz-zio/)
