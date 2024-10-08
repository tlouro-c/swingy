package tc.tlouro_c.swingy.utils;

import tc.tlouro_c.swingy.models.Villain;

public class VillainAppearedException extends Exception {

	private Villain villain;

	public VillainAppearedException(Villain villain) {
		super("A " + villain.getName() + " has appeared!");
		this.villain = villain;
	}

	public Villain getVillain() {
		return villain;
	}
}
