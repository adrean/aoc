package com.adrean

import scala.Console
import scala.io.Source

object Year21day12 {

  def main(args: Array[String]): Unit = {
    val env = "test"
    val day = 12
    val lines: Array[Array[Char]] = Source.fromResource(s"2021/$day/$env.txt")
      .getLines()
      .map(line => line.toCharArray).
      toArray
  }
}
