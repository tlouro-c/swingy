package tc.tlouro_c.swingy.entities.villains;

import tc.tlouro_c.swingy.entities.Villain;

public class VillainFactory {

	private static VillainFactory instance;

	public static VillainFactory getInstance() {
		if (instance == null) {
			instance = new VillainFactory();
		}
		return instance;
	}

	public Villain newRandomVillain() {
		int random = (int)(3 * Math.random());

		if (random == 0) {
			return new Zephyrex();
		} else if (random == 1) {
			return new Terraphin();
		} else {
			return new Lumetis();
		}
	}
}
