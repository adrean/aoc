package com.adrean

import scala.io.Source

object Year21day09 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val lines: Array[Array[Int]] = Source.fromResource(s"2021/09/$env.txt").getLines().map(line => line.toCharArray.map(c => c.asDigit)).toArray
    var lowpoints: Array[(Int, Int)] = Array[(Int, Int)]()
    for(i <- 0 until lines.length) {
      for(j<- 0 until lines(i).length) {
        val neighbours = Array((i-1, j),(i+1, j),(i, j-1),(i, j+1)).filter((a,b) => a >= 0 && b >= 0 && a < lines.length  && b < lines(i).length)
        if(neighbours.forall((a,b) => lines(a)(b) > lines(i)(j))) {
          lowpoints :+= (i,j)
        }
      }
    }
    var sumRisk = 0
    lowpoints.foreach((i,j) => sumRisk += lines(i)(j) + 1)
    println(s"Sum of risk levels is $sumRisk")
  }
}
