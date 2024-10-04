package tc.tlouro_c.swingy.models;

public class HeroBuilder {

	private String name;
	private int spriteNumber;
	private CharacterClass characterClass;
	private int attack;
	private int defense;
	private int maxHP;

	public HeroBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HeroBuilder sprite(int spriteNumber) {
		this.spriteNumber = spriteNumber;
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
	public HeroBuilder maxHP(int maxHP) {
		this.maxHP = maxHP;
		return this;
	}
	public Hero build() {
		return new Hero(name, spriteNumber, characterClass, attack, defense, maxHP);
	}
	
}
