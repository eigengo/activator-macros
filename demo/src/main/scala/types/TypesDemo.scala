package types

object TypesDemo extends App {

  class A {
    def a: Int = 42
    def b: String = "b"
  }

  class B {
    def beta: String = "beta"
  }

  import Types._

  println(methodNames[A])
  println(methodNames[B])

}
