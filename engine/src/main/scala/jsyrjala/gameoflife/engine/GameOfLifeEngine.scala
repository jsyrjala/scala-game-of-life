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

class GameOfLifeEngine(initialWorld: World, visuzalizer: Actor) extends Actor {
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
        logger.info("Step " + x + " steps forward")
        running = false
        currentWorld = currentWorld.generateNext

        visuzalizer ! currentWorld
      case Reset(world: World) =>
        logger.info("Reset to world " + world)
        running = false
    }
  }

}