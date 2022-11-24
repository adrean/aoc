package com.adrean

import scala.Console
import scala.io.Source
import scala.collection.mutable.Map

object Year21day14 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 14
    val lines: Array[String] = Source.fromResource(s"2021/$day/$env.txt").getLines().toArray

    var polymer = lines(0)
    var rules:Map[String,String] = Map[String,String]()
    for i <- 2 until lines.length do
      rules += (lines(i).split(" -> ")(0) -> lines(i).split(" -> ")(1))

    for step <- 1 to 10 do
      polymer=polymer.sliding(2).map(pair =>
        rules.get(pair) match
          case None => pair.dropRight(1)
          case Some(output) => s"${pair(0)}$output${pair(0)}".dropRight(1)
      ).mkString("","",polymer.last.toString)

    val sorted = polymer.groupBy(identity).toArray.map(element => (element._1, element._2.size)).sortBy(_._2)
    sorted.foreach((elt, cpt) => println(s"$elt : $cpt"))
    println(sorted.last._2 - sorted.head._2)


  }
}
