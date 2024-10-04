package tc.tlouro_c.swingy.models;

import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;

public class Villain extends Character {

		protected Villain() {
		}

		public Artifact dropArtifact() {
			return this.artifact;
		}

		protected void assignStats() {
			this.attack = (int)((60) * this.characterClass.getAttackMultiplier());
			this.defense = (int)((60) * this.characterClass.getDefenseMultiplier());
			this.maxHP = (int)((210) * this.characterClass.getMaxHPMultiplier());
			this.currentHP = this.maxHP;

			this.level = new Level(1);
			int randomLevel = (int)(1 + (6.99 - 1) * Math.random()); // Generate a random level for the Villain
			while (this.getLevel() < randomLevel) {
				this.levelUp();
			}

			boolean hasArtifact = Math.random() >= 0.5; // 50% chance of having an artifact
			if (hasArtifact) {
				this.equipArtifact(ArtifactFactory.getInstance().newRandomArtifact());
			}
		}
}
