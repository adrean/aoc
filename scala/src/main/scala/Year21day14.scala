package com.adrean

import scala.Console
import scala.io.Source
import scala.collection.mutable.Map


class PolymerProcessor(val steps: Int, val rules:Map[String,Char], val initialPolymer: String) {

  def process(): Unit = {
    var scores:Map[String,Long] = initialPolymer.sliding(2).toArray.groupBy(identity).map(t => (t._1, t._2.length.toLong)).to(Map)
    for i <- 1 to steps do
      var newScores:Map[String,Long] = Map[String,Long]().withDefaultValue(0)
      scores.foreach(pairScore =>
        val output = rules(pairScore._1)
        newScores(s"${pairScore._1.head}$output") += pairScore._2
        newScores(s"$output${pairScore._1.last}") += pairScore._2
      )
      scores = newScores
    val finalScores:Map[Char, Long] = scores
      .map(pair => Array((pair._1.head, pair._2), (pair._1.last, pair._2)))
      .flatten.toArray
      .groupBy(_._1)
      .map(pair => (pair._1, pair._2.map(_._2).sum / 2)).to(Map)

    finalScores(initialPolymer.head) += 1
    finalScores(initialPolymer.last) += 1
    println(finalScores.maxBy(_._2)._2 - finalScores.minBy(_._2)._2)

  }

}
object Year21day14 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 14
    val lines: Array[String] = Source.fromResource(s"2021/$day/$env.txt").getLines().toArray

    val polymer = lines(0)
    val rules:Map[String,Char] = Map[String,Char]()
    for i <- 2 until lines.length do
      rules += (lines(i).split(" -> ")(0) -> lines(i).split(" -> ")(1).head)
    PolymerProcessor(10, rules, polymer).process()
    PolymerProcessor(40, rules, polymer).process()
  }

}
