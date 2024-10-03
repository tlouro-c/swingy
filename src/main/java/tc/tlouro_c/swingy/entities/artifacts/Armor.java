package tc.tlouro_c.swingy.entities.artifacts;

import tc.tlouro_c.swingy.entities.Artifact;

public class Armor extends Artifact {
	
	Armor() {
		super(1.0, 1.2 + (1.4 - 1.2) * Math.random(), 1.0);
	}
}
