package jsyrjala.gameoflife

import java.security.acl.Owner

/**
 * http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 */
object SparseMatrix{
  // TODO move these to spec

  def empty(generation: Int) = {
    new SparseMatrix(generation, Map())
  }

  def singleCell(generation: Int) = {
    /*
      1
    1 x
     */
    new SparseMatrix(generation, Map(1 -> Set(1)))
  }

  def twoCell(generation: Int) = {
    /*
      1 2
    1 x
    2 x
     */
    new SparseMatrix(generation, Map(1 -> Set(1), 2 -> Set(2)))
  }
  def blinker(generation: Int) = {
    /*
      1 2 3
    1
    2 x x x
    3
     */
    new SparseMatrix(generation, Map(2 -> Set(1,2,3)))
  }
    def blinker2(generation: Int) = {
    /*
      1 2 3
    1   x
    2   x
    3   x
     */
    new SparseMatrix(generation, Map(2 -> Set(1,2,3)))
  }
  def block(generation: Int) = {
    /*
       1 2 3
     1 x x
     2 x x
     3
      */
    new SparseMatrix(generation, Map(1 -> Set(1,2), 2 -> Set(1,2)))
  }

  def glider1(generation: Int) = {
    /*
       1 2 3
     1     x
     2 x   x
     3   x x
      */
    new SparseMatrix(generation, Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def glider2(generation: Int) = {
    /*
       1 2 3 4
     1     x
     2     x
     3   x x
     4
      */
    new SparseMatrix(generation, Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def glider3(generation: Int) = {
    /*
       1 2 3 4
     1     x
     2 x   x
     3   x x
     4
      */
    new SparseMatrix(generation, Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def beacon(generation: Int) = {
   /*
      1 2 3 4
    1 x x
    2 x x
    3     x x
    4     x x
     */
    new SparseMatrix(generation, Map(1 -> Set(1,2), 2 -> Set(1,2), 3 -> Set(3,4), 4 -> Set(3,4)))
  }

  def beacon2(generation: Int) = {
   /*
      1 2 3 4
    1 x x
    2 x
    3       x
    4     x x
     */
    new SparseMatrix(generation, Map(1 -> Set(1,2), 2 -> Set(1), 3 -> Set(4), 4 -> Set(3,4)))
  }
}

class SparseMatrix(val generation: Int, val data: Map[Int, Set[Int]]) extends World {

  def this(d: Map[Int, Set[Int]]) = this(1, d)

  def generateNext: SparseMatrix = {
    var unprocessedNeighbours = Set[Location]()
    var result = data.flatMap {rowEntry =>
      val x = rowEntry._1
      val aliveCells = rowEntry._2.filter{y =>
        val loc = Location(x, y)
        // store dead neighbours for later processing
        unprocessedNeighbours ++= deadNeighbours(loc)

        staysAliveAtNextGeneration(loc)
      }
      Map(x -> aliveCells)
    }.filter( rowEntry => {
      // remove empty Sets
      // otherwise result would contain values like 1 -> Set()
      !(rowEntry._2.isEmpty)
    })


    unprocessedNeighbours.foreach( loc => {
      if(becomesAliveAtNextGeneration(loc)) {

        val ySet = result.get(loc.x).getOrElse(Set())
        result += loc.x -> (ySet + loc.y)
      }
    })

    return new SparseMatrix(generation + 1, result)
  }
  def isAlive(loc: Location): Boolean = {
    data.get(loc.x).getOrElse(Set()).contains(loc.y)
  }
  def aliveNeighbourCount(loc: Location):Int = {
    aliveNeighbours(loc).size
  }
  def aliveNeighbours(loc: Location): Set[Location] = {
    this.neighbourLocations(loc).filter(l => isAlive(l))
  }
  def deadNeighbours(loc: Location): Set[Location] = {
    this.neighbourLocations(loc).filter(l => !isAlive(l))
  }
  private def neighbourLocations(loc: Location) = {
    val offsets = Set(-1, 0, 1)
    for {dx <- offsets
      dy <- offsets
      if (dx, dy) != (0, 0)}
      yield Location(loc.x + dx, loc.y + dy)
  }

  override def equals(obj: Any):Boolean = {
    obj match {
      case other: SparseMatrix => other.data == this.data && other.generation == this.generation
      case _ => false
    }
  }

  override def toString: String = {
    this.getClass.getSimpleName + ":gen" + generation + ":" + data
  }
}
