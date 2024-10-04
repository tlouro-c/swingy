package tc.tlouro_c.swingy.models.villains;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Level;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.Sprite;


public class Gyarados extends Villain {

	Gyarados() {
		this.name = "Gyarados";
		this.sprite = new Sprite(false, 2);
		this.characterClass = CharacterClass.TANK;
		this.level = new Level((int)(1 + (6.99 - 1) * Math.random()));
		this.assignStats();
	}
	
}
