## Functionality Assessment

### Program Overview

In this game, the player controls a hero, with the option to either select an existing character or create a new one. The game proceeds with the following flow:

1. **Hero Selection:**
   - The player chooses between an existing Hero or creates a new one.
   
2. **Map Generation & Enemy Spawns:**
   - A map is generated based on the Hero's level.
   - Enemies (villains) spawn at random locations on the map.
   - The Hero spawns in the center of the map.

3. **Objective:**
   - The player's goal is to move the Hero to one of the borders of the map by navigating one square at a time in one of four directions: North, East, South, or West.
   
4. **Enemy Encounters:**
   - If the player moves to a square occupied by a villain, they have two options:
   
     - **Run:**
       - 50% chance of successfully escaping.
       - 50% chance of being forced to fight.

     - **Fight:**
       - The outcome is determined based on character stats and a bit of luck. Possible outcomes include:
         
         - **Hero Loses:**
           - The Hero loses the mission and is redirected to the Create/Select Character screen.
         
         - **Hero Wins:**
           - The Hero earns XP and may level up.
           - There's a chance the villain will drop an artifact, which the player can choose to pick up.

5. **Mission Completion:**
   - Upon successfully reaching the border of the map, the player is presented with three options:
   
     - **Leave the Game**
     - **Proceed to the Next Mission**
     - **Play with a Different Hero**
