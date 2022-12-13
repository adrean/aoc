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
    var coords: List[((Int,Int), Int)] = List[((Int,Int), Int)]()
    coords :+= ((0,0), 0)
    var cpt:Int = 0
    while coords.length > 0 do
      cpt += 1
      coords = coords.flatMap((coord, risk) =>
        var newCoords:List[((Int,Int), Int)] = List[((Int,Int), Int)]()
        var coords:Array[(Int,Int)] = Array((coord._1 - 1, coord._2),(coord._1 + 1, coord._2),(coord._1, coord._2 - 1),(coord._1, coord._2 + 1))
          coords
            .filter((x:Int,y:Int) => x >= 0 && y >= 0 && x < xMax && y < yMax)
            .filter((x,y) => lowestRisks((x,y)) > risk + chitons(x)(y))
              .map((x,y) =>
                val newRisk = risk + chitons(x)(y)
                lowestRisks((x,y)) = newRisk
                ((x,y), newRisk)
              )
      ).groupBy(_._1).map(x => (x._1,x._2.minBy(_._2)._2)).toList
    println(lowestRisks(xMax - 1, yMax - 1))
  }

  private def follow(coord:(Int,Int), risk:Int): Unit = {
    val newrisk: Int = risk + chitons(coord._1)(coord._2)
    if newrisk < lowestRisks(coord) then
      lowestRisks(coord) = newrisk
      if coord == (xMax - 1, yMax - 1) then
        if newrisk < lowestRiskPath then
          println(s"Found new safest path with risk of $newrisk")
          lowestRiskPath = newrisk
      else
        if coord._1 < xMax - 1 then
          follow((coord._1 + 1, coord._2), newrisk)
        if coord._1 > 0 then
          follow((coord._1 - 1, coord._2), newrisk)
        if coord._2 < yMax - 1 then
          follow((coord._1, coord._2 + 1), newrisk)
        if coord._2 > 0 then
          follow((coord._1, coord._2 - 1), newrisk)
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

    var fiveChitons = chitons.map(line => (0 to 4).toArray.flatMap(k => line.map(chiton =>
      var value = chiton + k
      if value > 9 then value -= 9
      value
    )))
    fiveChitons = (0 to 4).flatMap(k => fiveChitons.map(_.map(chiton =>
      var value = chiton + k
      if value > 9 then value -= 9
      value
    ))).toArray
    ChitonsExplorer(fiveChitons).run()

  }
}
