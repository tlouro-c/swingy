package tc.tlouro_c.swingy.models;

import javax.swing.ImageIcon;

public class Character extends MapEntity {

	protected String name;
	protected ImageIcon sprite;
	protected CharacterClass characterClass;
	protected Level level;
	protected int attack;
	protected int defense;
	protected int hitPoints;
	protected Artifact artifact;

	protected Character() {}

	protected Character(String name, ImageIcon sprite, CharacterClass characterClass, int attack, int defense, int hitPoints) {
		this.name = name;
		this.sprite = sprite;
		this.characterClass = characterClass;
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
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
			
		this.attack += level.getLevel() * 10 * Math.pow(1.1, level.getLevel());
		this.defense += level.getLevel() * 10 * Math.pow(1.2, level.getLevel());
		this.hitPoints += level.getLevel() * 10 * Math.pow(1.5, level.getLevel());

		if (onHoldArtifact != null) {
			this.equipArtifact(onHoldArtifact);
		}
	}

	public void equipArtifact(Artifact artifact) {
		
		int bonusAttack = (int)(this.attack * artifact.getAttackMultiplier()) - this.attack;
		int bonusDefense = (int)(this.defense * artifact.getDefenseMultiplier()) - this.defense;
		int bonusHitPoints = (int)(this.hitPoints * artifact.getHitPointsMultiplier()) - this.hitPoints;

		this.artifact = artifact;
		this.attack += bonusAttack;
		this.defense += bonusDefense;
		this.hitPoints += bonusHitPoints;
	}

	public Artifact unequipArtifact() {
		if (this.artifact == null)
			return null;

		int bonusAttack = (int)(this.attack * artifact.getAttackMultiplier()) - this.attack;
		int bonusDefense = (int)(this.defense * artifact.getDefenseMultiplier()) - this.defense;
		int bonusHitPoints = (int)(this.hitPoints * artifact.getHitPointsMultiplier()) - this.hitPoints;

		var artifactDrop = this.artifact;
		this.artifact = null;
		this.attack -= bonusAttack;
		this.defense -= bonusDefense;
		this.hitPoints -= bonusHitPoints;
		return artifactDrop;
	}
	
	private void takeDamage(int damage) {

		//Apply diminishing returns formula to calculate the damage mitigated by the defense
		int trueDamage = (int)(damage / (1.0 + (this.defense / 100.0)));
		this.hitPoints -= trueDamage;
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

	public CharacterClass getcharacterClass() {
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

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
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
		sb.append("Hit Points: ").append(hitPoints).append("\n");
		sb.append("Equipped Artifact: ").append(artifact != null ? artifact.toString() : "None").append("\n");
		sb.append("===================\n");
    	return sb.toString();
	}
}
