package tc.tlouro_c.swingy.entities;

public class Level {
	
	private int level;
	private int levelUpExperience;
	private int realExperience;

	private void levelUp() {
		level++;
		realExperience = realExperience - levelUpExperience;
		levelUpExperience = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
	}

	public Level(int level) {
		this.level = level;
		this.levelUpExperience = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
	}

	public int getLevel() {
		return level;
	}
	public int getLevelUpExperience() {
		return levelUpExperience;
	}
	public int getRealExperience() {
		return realExperience;
	}
	public void updateExperience(int gainedExperience) {
		realExperience += gainedExperience;
		if (realExperience >= levelUpExperience) {
			levelUp();
		}
	}
}
