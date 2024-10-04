package tc.tlouro_c.swingy.models.artifacts;

import tc.tlouro_c.swingy.models.Artifact;

public class ArtifactFactory {

	private static ArtifactFactory instance;

	public static ArtifactFactory getInstance() {
		if (instance == null) {
			instance = new ArtifactFactory();
		}
		return instance;
	}

	public Artifact newRandomArtifact() {
		int random = (int)(3 * Math.random());

		if (random == 0) {
			System.out.println("Weapon");
			return new Weapon();
		} else if (random == 1) {
			System.out.println("Armor");
			return new Armor();
		} else {
			System.out.println("Helmet");
			return new Helmet();
		}
	}
	
}
