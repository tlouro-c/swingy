package tc.tlouro_c.swingy.models;

import tc.tlouro_c.swingy.utils.Sprite;

public class Artifact extends MapEntity {
	
	private double attackMultiplier;
	private double defenseMultiplier;
	private double maxHPMultiplier;
	protected Sprite sprite;

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

	public Sprite getSprite() {
		return sprite;
	}
	
}
