package introduction

object TypeclassesUsage extends App {
  import JsonWriter._

  case class A()
  case class B(name: String, count: Int)

  def toJson[A : JsonWriter](value: A): String = implicitly[JsonWriter[A]].write(value)

  println(toJson(A())(writer[A]))
  println(toJson(B("a", 1)))
  println(toJson(B("b", 10)))

}
