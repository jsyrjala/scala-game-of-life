package jsyrjala.gameoflife.engine

import scala.actors._
import Actor._

trait Command

case class Dummy() extends Command

case class Run() extends Command

case class Pause() extends Command

case class Step(steps: Int) extends Command

case class Reset(world: World) extends Command

class GameOfLifeEngine(initialWorld: World, visuzalizer: Actor) extends Actor {

  private var currentWorld: World = initialWorld

  private var running = false

  def act() {
    println("GameOfLifeEngine started")
    eventloop {
      case Run() =>
        println("Run received")
        running = true
      case Pause() =>
        println("Pauser received")
        running = false
      case Step(x) =>
        println("Step " + x + " steps forward")
        running = false
        currentWorld = currentWorld.generateNext

        visuzalizer ! currentWorld
      case Reset(world: World) =>
        println("Reset to world " + world)
        running = false
    }
  }

}