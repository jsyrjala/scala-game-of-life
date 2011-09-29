package jsyrjala.gameoflife.swingui

import swing._
import java.awt.{Graphics2D, Dimension, Color}
import jsyrjala.gameoflife.engine.{World, Location}

class CellCanvas(cellSizePixels: Int, prefSize: Dimension, world: World) extends Panel {

  preferredSize = prefSize
  background = Color.black

  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setColor(Color.white)
    drawCells(g, world)
  }


  private def drawCells(g: Graphics2D, world: World) {
    world.aliveLocations.foreach(loc => drawCell(g, loc))
  }

  private def drawCell(g: Graphics2D, loc: Location) {
    val xCorner = 1
    val yCorner = 1
    val xOffset = xCorner + loc.x * cellSizePixels
    val yOffset = yCorner + loc.y * cellSizePixels
    g.fillRect(xOffset, yOffset, cellSizePixels - 1, cellSizePixels - 1)
  }

}
