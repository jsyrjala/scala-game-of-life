package jsyrjala.gameoflife

/**
 * Runner
 */

object Run extends App {
  println(SparseMatrix.beacon(1).isAlive(Location(1,1)))
  println(SparseMatrix.beacon(1).isAlive(Location(1,2)))
  println(SparseMatrix.beacon(1).isAlive(Location(1,4)))

}
