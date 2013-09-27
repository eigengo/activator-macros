package introduction

object IntroUsage extends App {
  Intro.hello()

  Intro.xprintln("Hello, world")

  val x = 10
  Intro.debug(x)
  Intro.debug(10)
}
