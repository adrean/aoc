package com.adrean

import scala.Console
import scala.io.Source

object Year21day10 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 10
    val scores:Map[Char, Int] = Map(')' -> 3, ']' -> 57, '}' -> 1197, '>' -> 25137)
    val lines: Array[Array[Char]] = Source.fromResource(s"2021/$day/$env.txt").getLines().map(line => line.toCharArray).toArray
    var score: Int = 0
    for line <- lines do
      var expectedClosingChars:Array[Char] = Array[Char]()
      var corrupted: Boolean = false
      for
        c <- line
        if !corrupted
      do
        c match
          case '(' => expectedClosingChars :+= ')'
          case '[' => expectedClosingChars :+= ']'
          case '{' => expectedClosingChars :+= '}'
          case '<' => expectedClosingChars :+= '>'
          case c =>
            if c == expectedClosingChars.last then expectedClosingChars = expectedClosingChars.dropRight(1) else corrupted = true
        if corrupted then
          score += scores(c)

    println(s"Score is $score")

  }
}
