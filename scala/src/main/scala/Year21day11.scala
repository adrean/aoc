package com.adrean

import scala.Console
import scala.io.Source

object Year21day11 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 11
    val octopuses: Array[Array[Int]] = Source.fromResource(s"2021/$day/$env.txt")
      .getLines()
      .map(line => line.toCharArray.map(_.asDigit)).
      toArray
    val octopusesCount = octopuses.map(line => line.length).sum
    println(s"$octopusesCount octopuses")
    var flashCount:Int = 0
    var syncedOctopuses: Boolean = false
    var step = 0
    while !syncedOctopuses do
      step += 1
      var flashingOctopuses:Set[(Int,Int)] = Set[(Int,Int)]()
      for
        i <- 0 until octopuses.length
        j <- 0 until octopuses(i).length
      do
        octopuses(i)(j) += 1
        if octopuses(i)(j) > 9 then
          flashingOctopuses += (i,j)
      var stepFlashCount:Int = 0

      var findNewFlashingOctopuses:Boolean = true
      var newFlashingOctopuses:Set[(Int,Int)] = flashingOctopuses
      while newFlashingOctopuses.size > 0 do
        flashingOctopuses ++= newFlashingOctopuses
        findNewFlashingOctopuses = false
        var adjacentOctopuses:Array[(Int,Int)] = newFlashingOctopuses.toArray.map((x,y) =>
          Array((x-1,y-1),(x-1,y),(x-1,y+1),(x,y-1),(x,y+1),(x+1,y-1),(x+1,y),(x+1,y+1))
            .filter(octopus =>
              octopus._1 >= 0  && octopus._1 < octopuses.length
                 && octopus._2 >= 0 && octopus._2 < octopuses(octopus._1).length
            )
            .filter(octopus => !flashingOctopuses.contains(octopus))
        ).flatten
        newFlashingOctopuses = newFlashingOctopuses.empty
        for adjacentOctopus <- adjacentOctopuses do
          octopuses(adjacentOctopus._1)(adjacentOctopus._2) += 1
          if octopuses(adjacentOctopus._1)(adjacentOctopus._2) > 9 then
            newFlashingOctopuses += adjacentOctopus
            findNewFlashingOctopuses = true

      flashCount += flashingOctopuses.size
      for flashingOctopus <- flashingOctopuses do
        octopuses(flashingOctopus._1)(flashingOctopus._2) = 0

      println(s"Step $step: $flashCount flashing octopuses")
      if flashingOctopuses.size == octopusesCount then syncedOctopuses = true

  }
}
