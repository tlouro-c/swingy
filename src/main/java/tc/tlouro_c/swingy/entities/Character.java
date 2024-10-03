package tc.tlouro_c.swingy.entities;

import javax.swing.ImageIcon;

public class Character extends MapEntity {

	protected String name;
	protected ImageIcon sprite;
	protected HeroClass heroClass;
	protected Level level;
	protected int attack;
	protected int defense;
	protected int hitPoints;
	protected Artifact artifact;

	protected Character() {}

	protected Character(String name, ImageIcon sprite, HeroClass heroClass, int attack, int defense, int hitPoints) {
		this.name = name;
		this.sprite = sprite;
		this.heroClass = heroClass;
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
	}

	public int getLevel() {
		return level.getLevel();
	}

	public void updateExperience(int gainedExperience) {
		this.level.updateExperience(gainedExperience);
	}
}
