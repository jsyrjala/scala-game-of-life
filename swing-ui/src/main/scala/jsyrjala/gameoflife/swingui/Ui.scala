package jsyrjala.gameoflife.swingui

import swing._
import java.awt.Toolkit
import scala.swing.FileChooser.Result._
object Ui extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Game of Life"

    iconImage = loadIconImage

    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Open") {
          val chooser = new FileChooser
          chooser.showOpenDialog(this) match {
            case Cancel => println("Cancel Open file")
            case Error => println("Error while Open file")
            case Approve => println("Opening file " + chooser.selectedFile.getPath)
          }
        })
        contents += new Separator
        contents += new MenuItem(Action("Exit") {
          quit()
        })
      }
    }

    val runButton = new Button(Action("Run") {
      println("Running as fast as possible ")
    })
    val pauseButton = new Button(Action("Pause") {
      println("Having a small break")
    })
    val stepButton = new Button(Action("Step") {
      println("Taking one step at time")
    })
    val resetButton = new Button(Action("Reset") {
      println("Resetting to the starting position")
    })

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

    override def closeOperation() {
      // let SimpleSwingApplication handle quit
      quit()
    }
  }

  private def loadIconImage = {
    Toolkit.getDefaultToolkit.getImage(this.getClass.getResource("/glider.png"))
  }

  override def shutdown() {
    println("Bye!")
  }

}