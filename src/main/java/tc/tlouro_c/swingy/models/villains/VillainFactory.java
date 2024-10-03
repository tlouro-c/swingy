package tc.tlouro_c.swingy.models.villains;

import tc.tlouro_c.swingy.models.Villain;

public class VillainFactory {

	private static VillainFactory instance;

	public static VillainFactory getInstance() {
		if (instance == null) {
			instance = new VillainFactory();
		}
		return instance;
	}

	public Villain newRandomVillain() {
		int random = (int)(2.99 * Math.random());

		if (random == 0) {
			return new Zephyrex();
		} else if (random == 1) {
			return new Terraphin();
		} else {
			return new Lumetis();
		}
	}
}
