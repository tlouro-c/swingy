package tc.tlouro_c.swingy.models;

import javax.swing.ImageIcon;

public class Hero extends Character {

	Hero(String name, ImageIcon sprite, CharacterClass characterClass, int attack, int defense, int maxHP) {
		this.level = new Level(1);
		this.name = name;
		this.sprite = sprite;
		this.characterClass = characterClass;
		this.attack = (int)((attack + 50) * this.characterClass.getAttackMultiplier());
		this.defense = (int)((defense + 50) * this.characterClass.getDefenseMultiplier());
		this.maxHP = (int)((maxHP + 200) * this.characterClass.getMaxHPMultiplier());
		this.currentHP = this.maxHP;
	}

	public void move(Direction direction, Map map) {
	}
}
