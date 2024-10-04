package tc.tlouro_c.swingy.models;

public class FightSimulator {

	private static FightSimulator instance;

	public static FightSimulator getInstance() {
		if (instance == null) {
			instance = new FightSimulator();
		}
		return instance;
	}

	public Character runSimulation(Hero hero, Villain villain) {

		int heroInitialHP = hero.getCurrentHP();
		int villainInitialHP= villain.getCurrentHP();
		Character attacker = Math.random() > 0.5 ? hero : villain;
		Character attacked = attacker == hero ? villain : hero;

		while (hero.isAlive() && villain.isAlive()) {

			attacker.attack(attacked);

			Character tmp = attacker;
			attacker = attacked;
			attacked = tmp;
		}
		Character winner = hero.isAlive() ? hero : villain;

		System.out.println(hero);
		System.out.println(villain);

		//Winner restores half of the damage taken
		int damageTaken = winner == hero ?
			heroInitialHP - hero.getCurrentHP() : villainInitialHP - villain.getCurrentHP();
				
		winner.setCurrentHP(winner.getCurrentHP() + damageTaken / 2);
		
		return winner;
	}
}
