package tc.tlouro_c.swingy.models;

import tc.tlouro_c.swingy.utils.Sprite;

public class Hero extends Character {

	Hero(String name, int spriteNumber, CharacterClass characterClass, int attack, int defense, int maxHP) {
		this.level = new Level(1);
		this.name = name;
		this.sprite = new Sprite(true, spriteNumber);
		this.characterClass = characterClass;
		this.attack = (int)((attack + 50) * this.characterClass.getAttackMultiplier());
		this.defense = (int)((defense + 50) * this.characterClass.getDefenseMultiplier());
		this.maxHP = (int)((maxHP + 200) * this.characterClass.getMaxHPMultiplier());
		this.currentHP = this.maxHP;
		this.extraPointsUsed = maxHP + defense + attack;
	}

	Hero(int dbId, String name, int spriteNumber, Level level, CharacterClass characterClass, int attack, int defense, int maxHP, Artifact artifact) {
		this.dbId = dbId;
		this.level = level;
		this.name = name;
		this.sprite = new Sprite(true, spriteNumber);
		this.characterClass = characterClass;
		this.attack = attack;
		this.defense = defense;
		this.maxHP = maxHP;
		this.currentHP = this.maxHP;
		this.artifact = artifact;
	}



	public void move(Direction direction, Map map) {
	}
}
