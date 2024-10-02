# Functionality assessment

## Program Overall Logic

	A user starts the game by being able to chose between an existing Hero or creating a new hero.
	After choosing his hero, a map is generated based on his level and enemies spawn on different random locations, and the user character spawns in the middle of the map.
	The user's objectives is to reach one of the borders of the map, by moving one square at a time in one of the 4 directions: North, East, South, West.
	If a user moves to a position occupied by a villain, he has 2 options:
		- Run:
			He has 50% chance of being able to run and 50% of having to fight.
		- Fight:
			A simulation based on the stats of each character and a bit of luck, with the following possible outcomes:
				- Hero loses:
					- He loses the mission and is redirected to the Create/Select Character Screen.
				- Hero wins:
					- Hero wins XP and levels up if possible.
					- The possibility of a Villain dropping an artifact, which the Hero can pick or not.
	If a user passes the mission he gets 3 options:
		- Leave the game.
		- Go to next mission.
		- Play with a different Hero.
