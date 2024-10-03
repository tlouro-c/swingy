package tc.tlouro_c.swingy.entities;

public class Artifact extends MapEntity {
	
	private double attackMultiplier;
	private double defenseMultiplier;
	private double hitPointsMultiplier;

	protected Artifact(double attackMultiplier, double defenseMultiplier, double hitPointsMultiplier) {
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
