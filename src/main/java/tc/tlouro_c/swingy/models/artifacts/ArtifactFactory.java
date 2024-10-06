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
			return new Weapon();
		} else if (random == 1) {
			return new Armor();
		} else {
			return new Helmet();
		}
	}

	public Artifact newArtifact(String type) {
		if (type == null) {
			return null;
		}
		switch (type.toLowerCase()) {
			case "weapon":
				return new Weapon();
			case "armor":
				return new Armor();
			case "helmet":
				return new Helmet();
			default:
				return null;
		}
	}
}
