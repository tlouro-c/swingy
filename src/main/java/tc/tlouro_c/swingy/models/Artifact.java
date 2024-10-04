package tc.tlouro_c.swingy.models;

public class Artifact extends MapEntity {
	
	private double attackMultiplier;
	private double defenseMultiplier;
	private double maxHPMultiplier;

	protected Artifact(double attackMultiplier, double defenseMultiplier, double maxHPMultiplier) {
		this.attackMultiplier = Math.round(attackMultiplier * 100.0) / 100.0;
		this.defenseMultiplier = Math.round(defenseMultiplier * 100.0) / 100.0;
		this.maxHPMultiplier = Math.round(maxHPMultiplier * 100.0) / 100.0;
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
		return "Artifact [attackMultiplier=" + attackMultiplier + ", defenseMultiplier=" + defenseMultiplier
				+ ", maxHPMultiplier=" + maxHPMultiplier + "]";
	}

	
}
