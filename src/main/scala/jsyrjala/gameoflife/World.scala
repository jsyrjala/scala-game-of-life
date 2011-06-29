package jsyrjala.gameoflife

/**
 * TODO
 */
trait World {
  def isAlive(loc: Location): Boolean
  def aliveNeighbours(loc: Location): Set[Location]
  def deadNeighbours(loc: Location): Set[Location]
  def staysAliveAtNextGeneration(loc: Location) = {
    aliveNeighbours(loc).size match {
      case 2 => true
      case 3 => true
      case _ => false
    }
  }
  def becomesAliveAtNextGeneration(loc: Location) = {
    aliveNeighbours(loc).size == 3
  }
}

case class Location(x: Int, y: Int)

