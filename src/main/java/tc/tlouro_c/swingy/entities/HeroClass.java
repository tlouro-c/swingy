package tc.tlouro_c.swingy.entities;

public enum HeroClass {
	BRUISER(1.0, 1.05, 1.1), TANK(0.8, 1.2, 1.1), ASSASSIN(1.20, 0.98, 0.95);

	private double attackMultiplier;
	private double defenseMultiplier;
	private double hitPointsMultiplier;

	private HeroClass(double attackMultiplier, double defenseMultiplier, double hitPointsMultiplier) {
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
