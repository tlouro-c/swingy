package tc.tlouro_c.swingy.entities;

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
	
}
