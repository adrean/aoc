package com.adrean

import scala.Console
import scala.io.Source

object Year21day10 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 10
    val part1scoresMap:Map[Char, Int] = Map(')' -> 3, ']' -> 57, '}' -> 1197, '>' -> 25137)
    val part2scoresMap:Map[Char, Int] = Map(')' -> 1, ']' -> 2, '}' -> 3, '>' -> 4)
    var part2scores:Array[Long] = Array[Long]()
    val lines: Array[Array[Char]] = Source.fromResource(s"2021/$day/$env.txt").getLines().map(line => line.toCharArray).toArray
    var part1Score: Int = 0
    for line <- lines do
      var part2score:Long = 0
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
          part1Score += part1scoresMap(c)

      if !corrupted then
        for c <- expectedClosingChars.reverse do
          part2score = part2score * 5 + part2scoresMap(c)
        part2scores :+= part2score
        
    val part2Score:Long = part2scores.sorted(Ordering.Long.reverse)(part2scores.length / 2)
    println(s"Part 1 score is $part1Score")
    println(s"Part 2 score is $part2Score")

  }
}
