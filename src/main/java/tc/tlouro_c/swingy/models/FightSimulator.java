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

		return hero;
	}
}
