package tc.tlouro_c.swingy.models;

import javax.swing.ImageIcon;

public class HeroBuilder {

	private String name;
	private ImageIcon sprite;
	private CharacterClass characterClass;
	private int attack;
	private int defense;
	private int hitPoints;

	public HeroBuilder name(String name) {
		this.name = name;
		return this;
	}
	public HeroBuilder sprite(ImageIcon sprite) {
		this.sprite = sprite;
		return this;
	}
	public HeroBuilder characterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
		return this;
	}
	public HeroBuilder attack(int attack) {
		this.attack = attack;
		return this;
	}
	public HeroBuilder defense(int defense) {
		this.defense = defense;
		return this;
	}
	public HeroBuilder hitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
		return this;
	}
	public Hero build() {
		return new Hero(name, sprite, characterClass, attack, defense, hitPoints);
	}
	
}
