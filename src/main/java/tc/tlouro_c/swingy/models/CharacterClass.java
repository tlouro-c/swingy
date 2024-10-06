package tc.tlouro_c.swingy.models;

public enum CharacterClass {
	BRUISER(1.5, 1.0, 1.0), TANK(0.8, 1.5, 1.5), ASSASSIN(2.0, 0.7, 0.7);

	private double attackMultiplier;
	private double defenseMultiplier;
	private double maxHPMultiplier;

	private CharacterClass(double attackMultiplier, double defenseMultiplier, double maxHPMultiplier) {
		this.attackMultiplier = attackMultiplier;
		this.defenseMultiplier = defenseMultiplier;
		this.maxHPMultiplier = maxHPMultiplier;
	}

	public static CharacterClass getCorrespondingClass(String type) {
		switch (type.toLowerCase()) {
			case "bruiser":
				return CharacterClass.BRUISER;
			case "assassin":
				return CharacterClass.ASSASSIN;
			case "tank":
				return CharacterClass.TANK;
			default:
				return null;
		}
	}

	public double getAttackMultiplier() {
		return attackMultiplier;
	}

	public double getDefenseMultiplier() {
		return defenseMultiplier;
	}

	public double getMaxHPMultiplier() {
		return maxHPMultiplier;
	}

	@Override
	public String toString() {
		StringBuilder test = new StringBuilder(name().toString().toLowerCase());
		test.setCharAt(0, (char)(test.charAt(0) - 32));
		return new String(test);
	}

}
