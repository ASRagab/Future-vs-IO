@color[#eb4500](Introduction to IO)

+++

@snap[north]
@color[#eb4500](What we need)
@snapend

- Fine grained control over when and how to execute |
  - Blocking |
  - Concurrently |
  - In Parallel |
  
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
@color[#eb4500](IOs of a different name aren't the same)
@snapend

- fs2 and Monix Task |
  - All the evaluation modes |
  - Performant |
  - But Tied to the larger framework |

- Scalaz 7 IO |
  - Doesn't really support asynchronous computation (i.e. callbacks) |
  - Only lazy effect capturing (cannot strictly evaluate) |

+++

@snap[north]
@color[#eb4500](Better IOs)
@snapend

- cats-effect [(https://typelevel.org/cats-effect/)](https://typelevel.org/cats-effect/)
- zio [(https://scalaz.github.io/scalaz-zio/)](https://scalaz.github.io/scalaz-zio/)
