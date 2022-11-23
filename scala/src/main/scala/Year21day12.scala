package com.adrean

import scala.Console
import scala.io.Source
import scala.collection.mutable.Map
object Year21day12 {

  def main(args: Array[String]): Unit = {
    val env = "prod"
    val day = 12
    val lines: Array[String] = Source.fromResource(s"2021/$day/$env.txt")
      .getLines()
      .toArray
    val routes: Map[String, Array[String]] = Map[String, Array[String]]()
    for line <- lines do
      val orig = line.split("-")(0)
      val dest = line.split("-")(1)
      if routes.contains(orig) then routes(orig) :+= dest else routes += (orig -> Array[String](dest))
      if routes.contains(dest) then routes(dest) :+= orig else routes += (dest -> Array[String](orig))

    println(s"found ${getPath(routes, 1).length} complete paths")
    println()
    println(s"found ${getPath(routes, 2).length} complete paths")
  }

  def getPath(routes: Map[String, Array[String]], part:Int): Array[Array[String]] = {

    var paths: Array[Array[String]] = Array[Array[String]](Array("start"))
    var newPaths: Array[Array[String]] = Array[Array[String]]()
    var completed: Boolean = false
    while !completed do
      completed = true
      for pathIndex <- 0 until paths.length do
        val path = paths(pathIndex)
        if path.last == "end" then
          newPaths :+= path
        else
          val destinations = routes(path.last)
          for destination <- destinations do
            destination match
              case "start" =>
              case "end" =>
                newPaths :+= path :+ "end"
              case smallCave if smallCave(0).isLower =>
                part match
                  case 1 =>
                    if !path.contains(smallCave) then
                      newPaths :+= path :+ smallCave
                      completed = false
                  case 2 =>
                    if path.contains(smallCave) then
                      val alreadyVisited = path
                        .filter(point => point(0).isLower && point != "end" && point != "start")
                        .groupBy(identity)
                        .filter(point => point._2.length > 1)
                        .size > 0
                      if !alreadyVisited then
                        newPaths :+= path :+ smallCave
                        completed = false
                    else
                      newPaths :+= path :+ smallCave
                      completed = false
              case largeCave =>
                newPaths :+= path :+ largeCave
                completed = false
      paths = newPaths
      newPaths = Array[Array[String]]()

    paths.foreach(path => println(path.mkString("-")))
    paths.filter(path => path.last == "end")
  }

}
