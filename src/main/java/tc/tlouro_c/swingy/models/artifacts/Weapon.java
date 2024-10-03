package tc.tlouro_c.swingy.models.artifacts;

import tc.tlouro_c.swingy.models.Artifact;

public class Weapon extends Artifact {
	
	Weapon() {
		super(1.1 + (1.25 - 1.1) * Math.random(), 1.0, 1.0);
	}

	@Override
	public String toString() {
		return String.format("Weapon [ Bonus Attack: %.2f%% ]", this.getAttackMultiplier());
	}
}
