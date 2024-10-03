package tc.tlouro_c.swingy.entities;

import tc.tlouro_c.swingy.entities.artifacts.ArtifactFactory;

public class Villain extends Character {

		protected Villain() {
			boolean hasArtifact = Math.random() >= 0.5;
			if (hasArtifact) {
				this.artifact = ArtifactFactory.getInstance().newRandomArtifact();
			}
		}

		public Artifact dropArtifact() {
			return this.artifact;
		}
}
