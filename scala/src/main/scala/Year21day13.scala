package com.adrean

import scala.Console
import scala.io.Source

object Year21day13 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 13
    var dots:Array[(Int,Int)] = Array[(Int,Int)]()
    var foldingRules:Array[(String,Int)] = Array[(String,Int)]()
    Source.fromResource(s"2021/$day/$env.txt")
      .getLines()
      .foreach(line =>
        if line.startsWith("fold") then
          val foldingRule:Array[String] = line.split(" ")(2).split("=")
          foldingRules :+= (foldingRule(0), foldingRule(1).toInt)
        else if !line.isEmpty then
          val coord:Array[String] = line.split(",")
          dots :+= (coord(0).toInt, coord(1).toInt)
      )

    for foldingRule <- foldingRules do
      var newDots:Array[(Int,Int)] = Array[(Int,Int)]()
      if foldingRule._1 == "y" then
        for dot <- dots do
          if dot._2 > foldingRule._2 then
            newDots :+= (dot._1, foldingRule._2 - (dot._2 - foldingRule._2))
          else
            newDots :+= dot
      if foldingRule._1 == "x" then
        for dot <- dots do
          if dot._1 > foldingRule._2 then
            newDots :+= (foldingRule._2 - (dot._1 - foldingRule._2), dot._2)
          else
            newDots :+= dot
      dots = newDots.distinct
      println(dots.length)
    printDots(dots)
  }

  def printDots(dots:Array[(Int,Int)]): Unit = {
    val maxX = dots.maxBy(dot => dot._2)._2
    val maxY = dots.maxBy(dot => dot._1)._1
    println
    for
      x <- 0 to maxX
      y <- 0 to maxY
    do
      if dots.contains((y, x)) then
        print("#")
      else
        print(".")
      if y == maxY then println
  }
}
