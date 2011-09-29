package jsyrjala.gameoflife.engine

import scala.actors._
import Actor._
import org.slf4j.LoggerFactory

trait Command

case class Dummy() extends Command

case class Run() extends Command

case class Pause() extends Command

case class Step(steps: Int) extends Command

case class Reset(world: World) extends Command

class GameOfLifeEngine(initialWorld: World, visualizer: Actor) extends Actor {
  lazy val logger = LoggerFactory.getLogger(this.getClass)

  private var currentWorld: World = initialWorld

  private var running = false

  def act() {
    logger.info("GameOfLifeEngine started")
    eventloop {
      case Run() =>
        logger.info("Run received")
        running = true
      case Pause() =>
        logger.info("Pause received")
        running = false
      case Step(x) =>
        running = false
        currentWorld = currentWorld.generateNext
        logger.info("Stepped " + x + " steps forward. Generation: " +
          currentWorld.generation + " population: " + currentWorld.population)

        visualizer ! currentWorld
      case Reset(world: World) =>
        logger.info("Reset to world " + world)
        currentWorld = world
        running = false
        visualizer ! currentWorld
    }
  }

}