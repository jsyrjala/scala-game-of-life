# Game of Life implementation in Scala

http://en.wikipedia.org/wiki/Conway's_Game_of_Life

## Required software

- Maven 3.x http://maven.apache.org/
- git
- Some IDE, see below

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

IntelliJ IDEA 10.5 Community Edition works nicely

1. Download and install IDEA from http://www.jetbrains.com/idea/download/download_thanks.jsp
2. Install Scala plugin : _File -> Settings -> Plugins_
3. Right click file `src/man/scala/jsyrjala/gameoflife/swingui/Ui` and select `Compile` and `Run Ui.main()`

### Eclipse

TODO instructions
