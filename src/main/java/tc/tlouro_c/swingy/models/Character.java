package tc.tlouro_c.swingy.models;

import javax.swing.ImageIcon;

public class Character extends MapEntity {

	protected String name;
	protected ImageIcon sprite;
	protected CharacterClass characterClass;
	protected Level level;
	protected int attack;
	protected int defense;
	protected int maxHP;
	protected int currentHP;
	protected Artifact artifact;

	protected Character() {}

	public boolean isAlive() {
		return this.currentHP > 0;
	}

	public int getLevel() {
		return level.getLevel();
	}

	public void updateExperience(int gainedExperience) {
		level.updateRealExperience(gainedExperience);
		while (level.getRealExperience() >= level.getLevelUpExperience() && getLevel() < 6) {
			levelUp();
		}
	}

	protected void levelUp() {
		level.levelUp();

		Artifact onHoldArtifact = this.unequipArtifact();
		
		this.attack += (Math.log(level.getLevel() + 1) * 20);
		this.defense += (Math.log(level.getLevel() + 1) * 20);
		int bonusMaxHP = (int)((Math.log(level.getLevel() + 1) * 30));
		setMaxHP(maxHP + bonusMaxHP);
		setCurrentHP(currentHP + bonusMaxHP);

		System.out.println("Level up! " + this.name + " is now level " + this.level.getLevel() + "!");
		System.out.println("bonus max hp earned = " + bonusMaxHP);

		if (onHoldArtifact != null) {
			this.equipArtifact(onHoldArtifact);
		}
	}

	public void equipArtifact(Artifact artifact) {
		
		int bonusAttack = (int)(this.attack * artifact.getAttackMultiplier()) - this.attack;
		int bonusDefense = (int)(this.defense * artifact.getDefenseMultiplier()) - this.defense;
		int bonusMaxHP = (int)(this.maxHP * artifact.getMaxHPMultiplier()) - this.maxHP;

		this.artifact = artifact;
		this.attack += bonusAttack;
		this.defense += bonusDefense;
		setMaxHP(maxHP + bonusMaxHP);
		setCurrentHP(this.currentHP + bonusMaxHP);
	}

	public Artifact unequipArtifact() {
		if (this.artifact == null)
			return null;

		int bonusAttack = (int)(this.attack * artifact.getAttackMultiplier()) - this.attack;
		int bonusDefense = (int)(this.defense * artifact.getDefenseMultiplier()) - this.defense;
		int bonusMaxHP = (int)(this.maxHP * artifact.getMaxHPMultiplier()) - this.maxHP;

		var artifactDrop = this.artifact;
		this.artifact = null;
		this.attack -= bonusAttack;
		this.defense -= bonusDefense;
		setMaxHP(maxHP - bonusMaxHP);
		return artifactDrop;
	}
	
	private void takeDamage(int damage) {

		//Dodging possibility
		boolean dodged = Math.random() > 0.85;
		if (dodged) {
			return;
		}

		//Apply diminishing returns formula to calculate the damage mitigated by the defense
		int trueDamage = (int)(damage / (1.0 + (this.defense / 100.0)));
		this.currentHP -= trueDamage;
	}

	void attack(Character other) {
		other.takeDamage(this.attack);
	}

	public String getName() {
		return name;
	}

	public ImageIcon getSprite() {
		return sprite;
	}

	public CharacterClass getCharacterClass() {
		return characterClass;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		if (this.currentHP > this.maxHP) {
			this.currentHP = this.maxHP;
		}
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = Math.min(this.maxHP, currentHP);
	}

	public Artifact getArtifact() {
		return artifact;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Character Details:\n");
		sb.append("===================\n");
		sb.append("Name: ").append(name).append("\n");
		sb.append("Class: ").append(characterClass != null ? characterClass.toString() : "None").append("\n");
		sb.append("Level: ").append(level).append("\n");
		sb.append("Attack: ").append(attack).append("\n");
		sb.append("Defense: ").append(defense).append("\n");
		sb.append("Hit Points: ").append(currentHP).append("/").append(maxHP).append("\n");
    	sb.append(generateHitPointsProgressBar(currentHP, maxHP, 20)).append("\n"); // 20 is the length of the progress bar
    
		sb.append("Equipped Artifact: ").append(artifact != null ? artifact.toString() : "None").append("\n");
		sb.append("===================\n");
    	return sb.toString();
	}

	// Helper method to generate a hit points progress bar
private String generateHitPointsProgressBar(int current, int max, int barLength) {
    StringBuilder progressBar = new StringBuilder();
    int filledLength = (int) ((double) current / max * barLength); // Calculate the filled portion of the bar
    
    // Append filled part of the bar
    for (int i = 0; i < filledLength; i++) {
        progressBar.append("█"); // Unicode block for filled part
    }
    
    // Append unfilled part of the bar
    for (int i = filledLength; i < barLength; i++) {
        progressBar.append("░"); // Unicode block for unfilled part
    }

    return progressBar.toString();
}
}
