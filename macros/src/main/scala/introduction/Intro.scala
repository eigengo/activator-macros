package introduction

import language.experimental.macros

import reflect.macros.Context

object Intro {

  def debug(param: Any): Unit = macro debug_impl


  def debug_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._

    // see the constructed trees
    println(show(reify { println("x") } tree))
    println(showRaw(reify { println("x") } tree))

    val paramRep = show(param.tree)
    val paramRepTree = Literal(Constant(paramRep))
    val paramRepExpr = c.Expr[String](paramRepTree)

    reify {
      println(paramRepExpr.splice + " = " + param.splice)
    }
  }

  def xprintln(param: Any): Unit = macro xprintln_impl

  def xprintln_impl(c: Context)(param: c.Expr[Any]): c.Expr[Unit] = {
    import c.universe._

    reify { println("x" + param.splice) }
  }

  def hello(): Unit = macro hello_impl

  def hello_impl(c: Context)(): c.Expr[Unit] = {
    import c.universe._

    reify {
      println("Hello, world")
    }
  }

}
