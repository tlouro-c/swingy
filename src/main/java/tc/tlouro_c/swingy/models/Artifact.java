package tc.tlouro_c.swingy.models;

public class Artifact extends MapEntity {
	
	private double attackMultiplier;
	private double defenseMultiplier;
	private double hitPointsMultiplier;

	protected Artifact(double attackMultiplier, double defenseMultiplier, double hitPointsMultiplier) {
		this.attackMultiplier = Math.round(attackMultiplier * 100.0) / 100.0;
		this.defenseMultiplier = Math.round(defenseMultiplier * 100.0) / 100.0;
		this.hitPointsMultiplier = Math.round(hitPointsMultiplier * 100.0) / 100.0;
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

	@Override
	public String toString() {
		return "Artifact [attackMultiplier=" + attackMultiplier + ", defenseMultiplier=" + defenseMultiplier
				+ ", hitPointsMultiplier=" + hitPointsMultiplier + "]";
	}

	
}
