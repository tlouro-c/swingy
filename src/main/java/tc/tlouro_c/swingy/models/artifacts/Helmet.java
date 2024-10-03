package tc.tlouro_c.swingy.models.artifacts;

import tc.tlouro_c.swingy.models.Artifact;

public class Helmet extends Artifact {

	Helmet() {
		super(1.0, 1.0, 1.1 + (1.25 - 1.1) * Math.random());
	}
	
	@Override
	public String toString() {
		return String.format("Helmet [ Bonus Hit Points: %.2f%% ]", this.getHitPointsMultiplier());
	}
}
