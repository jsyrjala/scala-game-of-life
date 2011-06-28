package jsyrjala.gameoflife

import java.security.acl.Owner

/**
 * http://en.wikipedia.org/wiki/Conway's_Game_of_Life
 */
object SparseMatrix{
  // TODO move these to spec

  def empty = {
    new SparseMatrix(Map())
  }

  def singleCell = {
    /*
      1
    1 x
     */
    new SparseMatrix(Map(1 -> Set(1)))
  }

  def twoCell = {
    /*
      1 2
    1 x
    2 x
     */
    new SparseMatrix(Map(1 -> Set(1), 2 -> Set(2)))
  }
  def blinker = {
    /*
      1 2 3
    1
    2 x x x
    3
     */
    new SparseMatrix(Map(2 -> Set(1,2,3)))
  }
    def blinker2 = {
    /*
      1 2 3
    1   x
    2   x
    3   x
     */
    new SparseMatrix(Map(2 -> Set(1,2,3)))
  }
  def block = {
    /*
       1 2 3
     1 x x
     2 x x
     3
      */
    new SparseMatrix(Map(1 -> Set(1,2), 2 -> Set(1,2)))
  }

  def glider1 = {
    /*
       1 2 3
     1     x
     2 x   x
     3   x x
      */
    new SparseMatrix(Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def glider2 = {
    /*
       1 2 3 4
     1     x
     2     x
     3   x x
     4
      */
    new SparseMatrix(Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def glider3 = {
    /*
       1 2 3 4
     1     x
     2 x   x
     3   x x
     4
      */
    new SparseMatrix(Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  }

  def beacon = {
   /*
      1 2 3 4
    1 x x
    2 x x
    3     x x
    4     x x
     */
    new SparseMatrix(Map(1 -> Set(1,2), 2 -> Set(1,2), 3 -> Set(3,4), 4 -> Set(3,4)))
  }

  def beacon2 = {
   /*
      1 2 3 4
    1 x x
    2 x
    3       x
    4     x x
     */
    new SparseMatrix(Map(1 -> Set(1,2), 2 -> Set(1), 3 -> Set(4), 4 -> Set(3,4)))
  }
}

class SparseMatrix(val generation: Int, val data: Map[Int, Set[Int]]) {

  def this(d: Map[Int, Set[Int]]) = this(1, d)

  def generateNext: SparseMatrix = {
    var unprocessedNeighbours = Set[Location]()
    var result = data.flatMap {rowEntry =>
      val x = rowEntry._1
      val aliveCells = rowEntry._2.filter{y =>
        val loc = Location(x, y)
        // store dead neighbours for later processing
        unprocessedNeighbours ++= deadNeighbours(loc)

        aliveNeighbours(loc).size match {
          case 2 => true
          case 3 => true
          case _ => false
        }

      }
      Map(x -> aliveCells)
    }.filter( x => {
      // remove empty Sets
      // otherwise result would contain values like 1 -> Set()
      !(x._2.isEmpty)
    })

    unprocessedNeighbours.foreach( loc => {
      if(aliveNeighbours(loc).size == 3) {
        // XXX
        val ySet = result.get(loc.x).getOrElse(Set())
        result += loc.x -> (ySet + loc.y)
      }
    })

    return new SparseMatrix(result)
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
      case other: SparseMatrix => other.data == data
      case _ => false
    }
  }

  override def toString: String = {
    this.getClass.getSimpleName + ":" + data
  }
}
