package validator

object ValidatorsDemo {
  import Validators._

  def validate[A : Validator](a: A): Boolean = implicitly[Validator[A]].validate(a)

  case class Foo(name: String)

  case class Bar(name: String, @max(40) age: Int)

  validate(Foo("asdsd"))
  validate(Bar("asdsd", 41))

}
