# Swingy - A Java-based RPG Game

## Overview
Swingy is a simple text-based RPG game, developed in Java, where the player can create and manage heroes, navigate a map, and battle enemies. The game follows the Model-View-Controller (MVC) architecture and can be run in both console and GUI modes. This project was part of the 42 school's Java track, focusing on building graphical user interfaces with Java's Swing framework.

## Features
- Create and manage multiple heroes of different types.
- Heroes can navigate through maps, battle enemies, and level up.
- Two game modes:
  - **Console mode** for a pure text-based interface.
  - **GUI mode** using Java Swing for graphical interaction.
- Hero data is saved in a relational database and loaded when the game starts.
- Implemented using the **MVC architecture** for a clean separation of logic, view, and controller layers.
  
## Game Mechanics
- **Hero Attributes**: Each hero has unique stats, including Attack, Defense, and Hit Points.
- **Artifacts**: Heroes can equip weapons, armor, and helms that increase their stats.
- **Battles**: Heroes fight monsters when they encounter them on the map. Victory depends on hero and monster stats, with a bit of luck.
- **Leveling System**: Experience points are awarded after each battle, allowing heroes to level up and grow stronger.
- **Map Navigation**: The map size increases with the hero's level. Heroes win the game by reaching the map's border.


### GUI Mode
<img src="screenshots/Screenshot%202024-10-09%20at%2003.38.54.png" alt="Create a hero screen" width="500"/>
<img src="screenshots/Screenshot 2024-10-09 at 03.39.46.png" alt="Map Screen GUI mode" width="500"/>

### Console Mode
<img src="screenshots/Screenshot 2024-10-09 at 03.41.40.png" alt="Map Screen CLI mode" width="500"/>


## Installation
1. Clone the repository:  
   ```bash
   git clone https://github.com/tlouro-c/swingy.git
   cd swingy
   ```
2. Build the project using Maven:
   ```bash
   mvn clean package
   ```
3. Run the game in console mode:
   ```bash
   java -jar swingy.jar cli
   ```
4. Run the game in GUI mode:
   ```bash
   java -jar swingy.jar gui
   ```
## Architecture
This project follows the Model-View-Controller (MVC) design pattern:

- **Model**: Manages the game's data (hero stats, map generation, battles).
- **View**: Provides the user interface. Implemented as both console output and GUI using Swing.
- **Controller**: Handles user input and updates the model and view accordingly.

## Challenges
- **Implementing MVC**: Initially, I struggled with the separation of concerns between the model, view, and controller layers. While I believe the models were well-implemented, I had difficulty managing the interaction between views and controllers effectively. Future projects will benefit from this experience.


## Useful Links

[https://www.visual-paradigm.com/guide/uml-unified-modeling-language/uml-class-diagram-tutorial/]

[https://dewitters.com/model-view-controller-for-games/]

[https://www.youtube.com/watch?v=MaY_MDdWkQw]

[https://www.youtube.com/watch?v=dTVVa2gfht8]
