package jsyrjala.gameoflife

/**
 * Runner
 */

object Run extends App {
  def printLog(gens: Int, start: Long, world: World) {
    val end = System.currentTimeMillis()
    val msec = end - start
    println("Generations " + gens + " in " + msec + " msec. " + (1.0* gens / msec) + " gen/msec. Pop: " + world.population )
    println(matrix)

  }
  println("game of life starting")
  val start = System.currentTimeMillis()
  val generations = 1000000
  //var matrix = new SparseMatrix(Map(1 -> Set(3), 2 -> Set(1,3), 3 -> Set(2,3)))
  var matrix = new SparseMatrix(Map(1 -> Set(2,3), 2 -> Set(1,2), 3 -> Set(2)))
  for (i <- 1 to generations) {
    matrix = matrix.generateNext
    if(i % 100 == 0) {
      printLog(i, start, matrix)
    }
  }
  println("End.")
  printLog(generations, start, matrix)

}
