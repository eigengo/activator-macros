package introduction

import language.experimental.macros

trait JsonWriter[A] {
  def write(a: A): String
}

object JsonWriter {

  implicit def writer[A]: JsonWriter[A] = macro writer_impl[A]

  import reflect.macros.Context

  def writer_impl[A : c.WeakTypeTag](c: Context): c.Expr[JsonWriter[A]] = {
    import c.universe._
    import Flag._

    //val wtt = Literal(Constant(weakTypeOf[A]))
    val sym = weakTypeOf[A]
    println(sym.typeSymbol.typeSignature.declarations)



    val x = reify {
      new JsonWriter[A] {
        def write(a: A): String = ">>> A"
      }
    }

    println(showRaw(x.tree))

    x
  }


}