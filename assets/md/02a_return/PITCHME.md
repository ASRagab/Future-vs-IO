@snap[north]
@color[#eb4500](return: Off topic rant)
@snapend

[Don't Use Return in Scala](https://tpolecat.github.io/2014/05/09/return.html)

```scala  
def happy: Int = {
    val sumFold: List[Int] => Int = _.foldLeft(0)((n, m) => n + m) // you could just use sum here
    sumFold(List(0,1,2)) + sumFold(List(3,4,5))
}
// scala> happy
// res0: Int = 15

def wtf: Int = {
    val sumFold: List[Int] => Int = _.foldLeft(0)((n, m) => return n + m)
    sumFold(List(0,1,2)) + sumFold(List(3,4,5))
}
```
@[13](res1: Int = 0 ...ARE YOU NOT ENTERTAINED?)

+++
@snap[north]
@color[#eb4500](NLRCs: Abanoned Non-Local Computations)
@snapend

```scala
case t: scala.runtime.NonLocalReturnControl[_] => Success(t.value.asInstanceOf[T])
```
@[1](resolver casts the throwable's underlying value and then wraps it into a `Success`)
@[1](NLRC extends `NoStackTrace`, so...NO. STACK. TRACE.)