package tc.tlouro_c.swingy.models;

public enum CharacterClass {
	BRUISER(1.5, 1.0, 1.0), TANK(0.8, 1.5, 1.5), ASSASSIN(2.0, 0.7, 0.7);

	private double attackMultiplier;
	private double defenseMultiplier;
	private double hitPointsMultiplier;

	private CharacterClass(double attackMultiplier, double defenseMultiplier, double hitPointsMultiplier) {
		this.attackMultiplier = attackMultiplier;
		this.defenseMultiplier = defenseMultiplier;
		this.hitPointsMultiplier = hitPointsMultiplier;
	}

	public double getAttackMultiplier() {
		return attackMultiplier;
	}

	public double getDefenseMultiplier() {
		return defenseMultiplier;
	}

	public double getHitPointsMultiplier() {
		return hitPointsMultiplier;
	}



}
