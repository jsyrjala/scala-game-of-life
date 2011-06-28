package jsyrjala.gameoflife

import org.specs2.mutable._

class SparseMatrixSpec extends SpecificationWithJUnit {
  "isAlive" should {
    "return false if no cell live at location" in {
      SparseMatrix.blinker.isAlive(Location(1,1)) must beFalse
    }
    "return true if a cell lives at location" in {
      SparseMatrix.blinker.isAlive(Location(2,1)) must beTrue
    }
  }

  "aliveNeighbours" should {
    "return empty set when cell has no neighbours" in {
      SparseMatrix.blinker.aliveNeighbours(Location(10, 10)) must_== Set()
    }
    "return a set with one Location when cell has one neighbour" in {
      SparseMatrix.blinker.aliveNeighbours(Location(1,0)) must_== Set(Location(2,1))
      SparseMatrix.blinker.aliveNeighbours(Location(2,0)) must_== Set(Location(2,1))
      SparseMatrix.blinker.aliveNeighbours(Location(3,0)) must_== Set(Location(2,1))
    }
    "return a set with two Locations when cell has two neighbours" in {
      SparseMatrix.blinker.aliveNeighbours(Location(1,1)) must_== Set(Location(2,1), Location(2,2))
    }
    "return a set with three Locations when cell has three neighbours" in {
      SparseMatrix.block.aliveNeighbours(Location(2,2)) must_== Set(Location(2,1), Location(1,2), Location(1,1))
    }
  }

  "beacon.generateNext" should {
    "return beacon2" in {
      SparseMatrix.beacon.generateNext must_== SparseMatrix.beacon2
    }
  }

  "beacon2.generateNext" should {
    "return beacon" in {
      SparseMatrix.beacon2.generateNext must_== SparseMatrix.beacon
    }
  }

  "glider1.generateNext" should {
    "return glider2" in {
      SparseMatrix.glider1.generateNext must_== SparseMatrix.glider2
    }
  }

  "glider2.generateNext" should {
    "return glider2" in {
      SparseMatrix.glider2.generateNext must_== SparseMatrix.glider3
    }
  }

  "block.generateNext" should {
    "return block" in {
      SparseMatrix.block.generateNext must_== SparseMatrix.block
    }
  }

  "empty.generateNext" should {
    "return empty" in {
      SparseMatrix.empty.generateNext must_== SparseMatrix.empty
    }
  }

  "singleCell.generateNext" should {
    "return empty" in {
      SparseMatrix.singleCell.generateNext must_== SparseMatrix.empty
    }
  }

  "twoCell.generateNext" should {
    "return empty" in {
      SparseMatrix.twoCell.generateNext must_== SparseMatrix.empty
    }
  }

}
