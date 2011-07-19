package jsyrjala.gameoflife.swingui

import swing.Panel
import swing.UIElement._
import java.awt.{geom, Graphics2D, Dimension, Color}

class CellCanvas extends Panel {

  background = Color.white
  preferredSize = new Dimension(200,200)
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    g.setColor(new Color(100,100,100))
    g.drawString("Drawing some text.", 10, size.height-10)
    g.setColor(Color.black)
    val path = new geom.GeneralPath
    path.moveTo(1,20)
    path.lineTo(10, 10)
    path.lineTo(20, 20)
    path.lineTo(size.width,size.height)
    g.draw(path)

  }
}
