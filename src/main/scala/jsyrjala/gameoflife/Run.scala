package jsyrjala.gameoflife

/**
 * Runner
 */

object Run extends App {
  println("game of life starting")
  val start = System.currentTimeMillis()
  val generations = 10000
  var matrix = new SparseMatrix(Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  for (i <- 1 to generations) {
    matrix = matrix.generateNext
  }
  val end = System.currentTimeMillis()
  val msec = end - start
  println("Generations " + generations + " in " + msec + " msec. " + (1.0* generations / msec) + " gen/msec" )
  println(matrix)
}
