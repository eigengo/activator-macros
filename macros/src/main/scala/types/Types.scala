package types

import language.experimental.macros

import scala.reflect.macros.Context

object Types {

  def methodNames[A]: List[String] = macro methodNames_impl[A]

  def methodNames_impl[A : c.WeakTypeTag](c: Context): c.Expr[List[String]] = {
    import c.universe._

    val methods: List[String] = c.weakTypeOf[A].typeSymbol.typeSignature.
      declarations.toList.filter(_.isMethod).map(_.name.toString)

    val listApply = Select(reify(List).tree, newTermName("apply"))

    c.Expr[List[String]](Apply(listApply, List(methods.map(x => Literal(Constant(x))):_*)))
  }

}
