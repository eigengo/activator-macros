package validator

import scala.reflect.macros.Context
import scala.annotation.StaticAnnotation

trait Validator[A] {
  def validate(a: A): Boolean
}

case class max(value: Int) extends StaticAnnotation

import language.experimental.macros

object Validators {

  implicit def deriveValiator[A]: Validator[A] = macro deriveValidator_impl[A]

  def deriveValidator_impl[A : c.WeakTypeTag](c: Context): c.Expr[Validator[A]] = {
    import c.universe._

    reify {
      new Validator[A] {
        def validate(a: A): Boolean = true
      }
    }
  }

}
