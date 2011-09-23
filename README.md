# Game of Life implementation in Scala

http://en.wikipedia.org/wiki/Conway's_Game_of_Life

## Required software

- Maven 3.x http://maven.apache.org/
- Some IDE, see below
- git http://git-scm.com/ _(optional)_

## Source

Source is stored in GitHub: https://github.com/jsyrjala/scala-game-of-life

## Building software

1. Download source tarball: https://github.com/jsyrjala/scala-game-of-life/tarball/master

```
    $ wget https://github.com/jsyrjala/scala-game-of-life/tarball/master
    $ tar -zxf master
```

2. Or make a git clone

```
    $ git clone git://github.com/jsyrjala/scala-game-of-life.git
```

3. Build software:

```
    $ cd scala-game-of-life
    $ mvn clean install
```

## Development environment

### IntelliJ IDEA

IntelliJ IDEA 10.5 Community Edition is recommended.

1. Download and install IDEA from http://www.jetbrains.com/idea/download/download_thanks.jsp
2. Install Scala plugin : _File -> Settings -> Plugins_
3. Create project from checkout files
4. Right click file `src/man/scala/jsyrjala/gameoflife/swingui/Ui` and select `Compile` and `Run Ui.main()`


### Eclipse

Maybe it works, maybe not.

Version, either Eclipse 3.6 Helios, or Eclipse 3.7 Indigo

Install Scala plugin for Eclipse: http://www.scala-ide.org/

Generate Eclipse project files and configure maven repository to Eclipse
```
mvn eclipse:eclipse
mvn -Declipse.workspace=/path/to/workspace eclipse:add-maven-repo
```
Import checkouted in Eclipse.

