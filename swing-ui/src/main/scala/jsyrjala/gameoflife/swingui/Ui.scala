package jsyrjala.gameoflife.swingui

import scala.actors._
import Actor._
import swing._
import swing.Swing.onEDT
import swing.BorderPanel._
import java.awt.Toolkit
import scala.swing.FileChooser.Result._
import java.io.File
import io.Source
import javax.swing.ImageIcon
import jsyrjala.gameoflife.engine._

object Ui extends SimpleSwingApplication {
  val gliderIconName = "/glider.png"
  val pauseIconName = "/pause.png"
  val stepIconName = "/step.png"
  val runIconName = "/run.png"
  val resetIconName = "/reset.png"

  val populationCount = new Label("Population: 0")
  val generationCount = new Label("Generation: 0")
  val filename = new Label("File: -")
  var world: World = _

  updateWorld(new SparseMatrix(Map()), None)
  val updater = actor {
    eventloop {
      case world: World => onEDT(updateWorld(world))
    }
  }

  private val engine = new GameOfLifeEngine(world, updater)

  override def startup(args: Array[String]) {
    println("Starting.")

    if (args.length > 0) {
      val file = new File(args(0))
      loadFile(file)
    }
    engine.start()
    // call to super.startup() constructs ui and makes it visible
    super.startup(args)
  }

  def updateWorld(world: World, file: Option[File]) {
    updateWorld(world)
    file match {
      case None => filename.visible = false
      case Some(f) => filename.text = "File: %s".format(f.getName); filename.visible = true
    }
  }

  def updateWorld(world: World) {
    populationCount.text = "Population: %s".format(world.population)
    generationCount.text = "Generation: %s".format(world.generation)
    this.world = world
  }

  def loadFile(file: File) {
    if (!file.exists() || !file.canRead) {
      println("Unable to load file " + file.getPath)
      return
    }
    println("Reading file " + file.getPath)
    val data: SparseMatrix = Source.fromFile(file).getLines().mkString("\n")
    updateWorld(data, Some(file))
  }

  def top = new MainFrame {
    title = "Game of Life"

    iconImage = loadImage(gliderIconName)


    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Open") {
          val chooser = new FileChooser
          chooser.showOpenDialog(this) match {
            case Cancel => println("Cancel Open file")
            case Error => println("Error while Open file")
            case Approve => loadFile(chooser.selectedFile); updateWorld(world, Some(chooser.selectedFile))
          }
        })
        contents += new Separator
        contents += new MenuItem(Action("Exit") {
          quit()
        })
      }
    }

    val runButton = new Button() {
      action = Action("Run") {
        engine ! Run()
      }
      icon = loadIcon(runIconName)
    }

    val pauseButton = new Button() {
      action = Action("Pause") {
        engine ! Pause()
      }
      icon = loadIcon(pauseIconName)
    }
    pauseButton.enabled = false
    val stepButton = new Button() {
      action = Action("Step") {
        engine ! Step(1)
      }
      icon = loadIcon(stepIconName)
    }
    val resetButton = new Button() {
      action = Action("Reset") {
        engine ! Reset(world)
      }
      icon = loadIcon(resetIconName)
    }

    val statusPanel = new FlowPanel(populationCount, generationCount, filename)
    val buttonPanel = new FlowPanel(runButton, pauseButton, stepButton, resetButton)
    val canvas = new CellCanvas(10, new Dimension(200, 200), world)

    val mainPanel = new BorderPanel {
      add(buttonPanel, Position.North)
      add(canvas, Position.Center)
      add(statusPanel, Position.South)
    }
    contents = mainPanel

    override def closeOperation() {
      // let SimpleSwingApplication handle quit
      quit()
    }
  }

  private def loadImage(filename: String) = {
    Toolkit.getDefaultToolkit.getImage(this.getClass.getResource(filename))
  }

  private def loadIcon(filename: String) = {
    new ImageIcon(loadImage(filename))
  }

  override def shutdown() {
    println("Bye!")
  }

}