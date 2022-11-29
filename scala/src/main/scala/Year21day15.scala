package com.adrean

import scala.Console
import scala.io.Source
import scala.collection.mutable.Map


class ChitonsExplorer(val chitons:Array[Array[Int]]) {

  var lowestRiskPath: Int = Int.MaxValue
  var lowestRisks: Map[(Int,Int), Int] = Map[(Int,Int),Int]().withDefaultValue(Int.MaxValue)
  val xMax:Int = chitons.length
  val yMax: Int = chitons.last.length

  def run(): Unit = {
    val start:(Int,Int) = (0,0)
    follow((start._1 + 1, start._2), 0)
    follow((start._1 + 1, start._2), 0)
  }

  def follow(coord:(Int,Int), risk:Int): Unit = {
    val newrisk: Int = risk + chitons(coord._1)(coord._2)
    if newrisk < lowestRisks(coord) then
      lowestRisks(coord) = newrisk
    else return
    if coord._1 < xMax - 1 then
      follow((coord._1 + 1, coord._2), newrisk)
    if coord._2 < yMax - 1 then
      follow((coord._1, coord._2 + 1), newrisk)
    if coord == (xMax - 1, yMax - 1) then
      if newrisk < lowestRiskPath then
        println(s"Found new safest path with risk of $newrisk")
        lowestRiskPath = newrisk
  }

}

object Year21day15 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 15
    val chitons: Array[Array[Int]] = Source.fromResource(s"2021/$day/$env.txt")
      .getLines()
      .map(line => line.toCharArray.map(_.asDigit)).
      toArray
    ChitonsExplorer(chitons).run()
  }
}
