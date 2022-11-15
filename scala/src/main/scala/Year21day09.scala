package com.adrean

import scala.Console
import scala.io.Source

object Year21day09 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val lines: Array[Array[Int]] = Source.fromResource(s"2021/09/$env.txt").getLines().map(line => line.toCharArray.map(c => c.asDigit)).toArray
    var lowpoints: Array[(Int, Int)] = Array[(Int, Int)]()
    for
      i <- 0 until lines.length
      j<- 0 until lines(i).length
    do
      val neighbours = Array((i-1, j),(i+1, j),(i, j-1),(i, j+1)).filter((a,b) => a >= 0 && b >= 0 && a < lines.length  && b < lines(i).length)
      if(neighbours.forall((a,b) => lines(a)(b) > lines(i)(j))) then
        lowpoints :+= (i,j)
      end if

    var sumRisk = 0
    lowpoints.foreach((i,j) => sumRisk += lines(i)(j) + 1)
    println(s"Sum of risk levels is $sumRisk")

    var basins:Array[Array[(Int,Int)]] = Array[Array[(Int,Int)]]()
    for lowpoint <- lowpoints
    do
      var basin: Array[(Int,Int)] = Array(lowpoint)
      var neighbours: Array[(Int,Int)] = Array(lowpoint)
      var value = lines(lowpoint._1)(lowpoint._2) + 1
      while(neighbours.length > 0) {
        neighbours = neighbours.map((i,j) =>
          Array((i-1, j),(i+1, j),(i, j-1),(i, j+1))
            .filter((a,b) => a >= 0 && b >= 0 && a < lines.length  && b < lines(i).length)
            .filter((a,b) => lines(a)(b) > lines(i)(j) && lines(a)(b) < 9)
        ).flatten.distinct
        basin ++= neighbours
      }
      basins :+= basin.distinct

    val basinpoints:Array[(Int,Int)] = basins.flatten.distinct
    val largestbasinpoints:Array[(Int,Int)] = basins.sortBy(_.length).takeRight(3).flatten.distinct
    for
      i <- 0 until lines.length
      j <- 0 until lines(i).length
    do
      val color = if largestbasinpoints.contains((i,j)) then Console.RED else if basinpoints.contains((i,j)) then Console.BLUE else Console.WHITE
      print(s"$color${lines(i)(j)}")
      if j == lines(i).length - 1 then println
    val result = basins.map(_.length).sorted.takeRight(3).reduce((a,b) => a * b)
    println(s"Multiplying sizes of the 3 largest basins gives $result")
  }
}
