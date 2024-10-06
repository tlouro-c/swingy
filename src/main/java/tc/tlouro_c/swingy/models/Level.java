package tc.tlouro_c.swingy.models;

public class Level {
	
	private int level;
	private int levelUpExperience;
	private int realExperience;
	private boolean up;

	public void levelUp() {
		level++;
		realExperience = Math.max(realExperience - levelUpExperience, 0);
		levelUpExperience = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		up = true;
	}

	public Level(int level) {
		this.level = level;
		this.levelUpExperience = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		this.up = false;
	}

	public Level(int level, int currentXP) {
		this.level = level;
		this.levelUpExperience = level * 1000 + (int)Math.pow(level - 1, 2) * 450;
		this.realExperience = currentXP;
		this.up = false;
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
	public boolean up() {
		return up;
	}
	public void updateRealExperience(int gainedExperience) {
		this.realExperience += gainedExperience;
		up = false;
	}

	@Override
	public String toString() {
		 // Calculate the percentage of progress
		 double progressPercentage = (double) realExperience / levelUpExperience;
		 // Clamp the percentage between 0 and 1
		 progressPercentage = Math.min(1.0, Math.max(0.0, progressPercentage));
		 
		 // Define the total width of the progress bar
		 int barWidth = 20;
		 // Calculate the number of filled blocks and empty blocks
		 int filledBlocks = (int) (progressPercentage * barWidth);
		 int emptyBlocks = barWidth - filledBlocks;
	 
		 // Create the progress bar string
		 StringBuilder progressBar = new StringBuilder();
		 for (int i = 0; i < filledBlocks; i++) {
			 progressBar.append("█"); // Filled block
		 }
		 for (int i = 0; i < emptyBlocks; i++) {
			 progressBar.append("-"); // Empty block
		 }
	 
		 // Construct the final string representation
		 return String.format(
			 "%d, Level-Up Experience: %d, Real Experience: %d, Progress: [%s]",
			 level, levelUpExperience, realExperience, progressBar.toString()
		 );
	}

	
}
