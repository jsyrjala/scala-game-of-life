package jsyrjala.gameoflife.swingui

import swing._
import java.awt.Toolkit

object Ui extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Game of Life"

    iconImage = loadIconImage

    val runButton = new Button() {
      action = Action("Run") {
        println("Running as fast as possible ")
      }
    }
    val pauseButton = new Button() {
      action = Action("Pause") {
        println("Having a small break")
      }
    }
    val stepButton = new Button() {
      action = Action("Step") {
        println("Taking one step at time")
      }
    }
    val resetButton = new Button() {
      action = Action("Reset") {
        println("Resetting to the starting position")
      }
    }

    val populationCount = new Label("Population: 10")
    val generationCount = new Label("Generation: 77")
    val filename = new Label("File: foobar.txt")

    val statusPanel = new FlowPanel(populationCount, generationCount, filename)
    val buttonPanel = new FlowPanel(runButton, pauseButton, stepButton, resetButton)
    val canvas = new CellCanvas(10, new Dimension(200, 200))

    val mainPanel = new BorderPanel {
      add(buttonPanel, BorderPanel.Position.North)
      add(canvas, BorderPanel.Position.Center)
      add(statusPanel, BorderPanel.Position.South)
    }
    contents = mainPanel

  }

  private def loadIconImage = {
    Toolkit.getDefaultToolkit().getImage(this.getClass.getResource("/glider.png"))
  }

  override def shutdown() {
    println("Bye!")
  }

}