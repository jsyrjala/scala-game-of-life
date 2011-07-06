package jsyrjala.gameoflife

import org.specs2.mutable._


object SparseMatrixSpec {
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
     1   x
     2     x x
     3   x x
     4
      */
    new SparseMatrix(Map(1 -> Set(2), 2 -> Set(3,4), 3 -> Set(2,3)))
  }

  def glider3 = {
    /*
       1 2 3 4
     1     x
     2       x
     3   x x x
     4
      */
    new SparseMatrix(Map(1 -> Set(3), 2 -> Set(4), 3 -> Set(2,3,4)))
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

  def r_pentamino = {
    /*
      1 2 3
    1   x x
    2 x x
    3   x
     */
    new SparseMatrix(Map(1 -> Set(2,3), 2 -> Set(1,2), 3 -> Set(2)))
  }
  def r_pentamino2 = {
    /*
      1 2 3
    1 x x x
    2 x
    3 x x
     */
    new SparseMatrix(2, Map(1 -> Set(1,2,3), 2 -> Set(1), 3 -> Set(1,2)))
  }
}

class SparseMatrixSpec extends SpecificationWithJUnit {
  "isAlive" should {
    "return false if no cell live at location" in {
      SparseMatrixSpec.blinker.isAlive(Location(1,1)) must beFalse
    }
    "return true if a cell lives at location" in {
      SparseMatrixSpec.blinker.isAlive(Location(2,1)) must beTrue
    }
  }

  "aliveNeighbours" should {
    "return empty set when cell has no neighbours" in {
      SparseMatrixSpec.blinker.aliveNeighbours(Location(10, 10)) must_== Set()
    }
    "return a set with one Location when cell has one neighbour" in {
      SparseMatrixSpec.blinker.aliveNeighbours(Location(1,0)) must_== Set(Location(2,1))
      SparseMatrixSpec.blinker.aliveNeighbours(Location(2,0)) must_== Set(Location(2,1))
      SparseMatrixSpec.blinker.aliveNeighbours(Location(3,0)) must_== Set(Location(2,1))
    }
    "return a set with two Locations when cell has two neighbours" in {
      SparseMatrixSpec.blinker.aliveNeighbours(Location(1,1)) must_== Set(Location(2,1), Location(2,2))
    }
    "return a set with three Locations when cell has three neighbours" in {
      SparseMatrixSpec.block.aliveNeighbours(Location(2,2)) must_== Set(Location(2,1), Location(1,2), Location(1,1))
    }
  }

  "r_pentamino.generateNext" should {
    "return r_pentamino2" in {
      SparseMatrixSpec.r_pentamino.generateNext must_== SparseMatrixSpec.r_pentamino2
    }
  }
  "r_pentamino.population" should {
    "be 5" in {
      SparseMatrixSpec.r_pentamino.population must_== 5
    }
  }


  "beacon.generateNext" should {
    "return beacon2" in {
      SparseMatrixSpec.beacon.generateNext must_== SparseMatrixSpec.beacon2
    }
  }

  "beacon2.generateNext" should {
    "return beacon" in {
      SparseMatrixSpec.beacon2.generateNext must_== SparseMatrixSpec.beacon
    }
  }

  "glider1.generateNext" should {
    "return glider2" in {
      SparseMatrixSpec.glider1.generateNext must_== SparseMatrixSpec.glider2
    }
  }

  "glider2.generateNext" should {
    "return glider3" in {
      SparseMatrixSpec.glider2.generateNext must_== SparseMatrixSpec.glider3
    }
  }

  "block.generateNext" should {
    "return block" in {
      SparseMatrixSpec.block.generateNext must_== SparseMatrixSpec.block
    }
  }
  "block.population" should {
    "be 4" in {
      SparseMatrixSpec.block.population must_== 4
    }
  }

  "empty.generateNext" should {
    "return empty" in {
      SparseMatrixSpec.empty.generateNext must_== SparseMatrixSpec.empty
    }
  }
  "empty.population" should {
    "be 0" in {
      SparseMatrixSpec.empty.population must_== 0
    }
  }

  "singleCell.generateNext" should {
    "return empty" in {
      SparseMatrixSpec.singleCell.generateNext must_== SparseMatrixSpec.empty
    }
  }
  "singleCell.population" should {
      "be 1" in {
        SparseMatrixSpec.singleCell.population must_== 1
      }
    }

  "twoCell.generateNext" should {
    "return empty" in {
      SparseMatrixSpec.twoCell.generateNext must_== SparseMatrixSpec.empty
    }
  }

  "twoCell.population" should {
    "be 2" in {
      SparseMatrixSpec.twoCell.population must_== 2
    }
  }

}
