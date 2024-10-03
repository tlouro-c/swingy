package tc.tlouro_c.swingy.entities;

public class Weapon extends Artifact {
	
	Weapon() {
		super(1.2 + (1.4 - 1.2) * Math.random(), 1.0, 1.0);
	}
}
