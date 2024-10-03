package tc.tlouro_c.swingy.models.artifacts;

import tc.tlouro_c.swingy.models.Artifact;

public class Armor extends Artifact {
	
	Armor() {
		super(1.0, 1.1 + (1.25 - 1.1) * Math.random(), 1.0);
	}

	@Override
	public String toString() {
		return String.format("Armor [ Bonus Defense: %.2f%% ]", this.getDefenseMultiplier());
	}
}
