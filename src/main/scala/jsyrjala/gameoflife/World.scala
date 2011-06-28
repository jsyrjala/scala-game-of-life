package jsyrjala.gameoflife

/**
 * TODO
 */

trait World {
  def isAlive(loc: Location): Boolean
  def aliveNeighbours(loc: Location): Set[Location]
  def deadNeighbours(loc: Location): Set[Location]
}

case class Location(x: Int, y: Int)

