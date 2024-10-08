package tc.tlouro_c.swingy.models;

import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;

public class Villain extends Character {

		protected Villain() {
		}

		public Artifact dropArtifact() {
			return this.artifact;
		}

		protected void assignStats() {
			this.attack = (int)((55) * this.characterClass.getAttackMultiplier());
			this.defense = (int)((55) * this.characterClass.getDefenseMultiplier());
			this.maxHP = (int)((20) * this.characterClass.getMaxHPMultiplier());
			this.currentHP = this.maxHP;

			this.level = new Level(1);
			int randomLevel = (int)(1 + (6.99 - 1) * Math.random()); //! FOR DEVELOPMENT ONLY
			while (this.getLevel() < randomLevel) {
				this.levelUp();
			}

			boolean hasArtifact = Math.random() >= 0.5; // 50% chance of having an artifact
			if (hasArtifact && randomLevel > 2) {
				this.equipArtifact(ArtifactFactory.getInstance().newRandomArtifact());
			}
		}
}
