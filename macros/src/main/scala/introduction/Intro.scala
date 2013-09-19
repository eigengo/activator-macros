package introduction

import language.experimental.macros

import reflect.macros.Context

object Intro {

  def hello(): Unit = macro hello_impl

  def hello_impl(c: Context)(): c.Expr[Unit] = null

}
