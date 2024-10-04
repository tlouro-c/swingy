package tc.tlouro_c.swingy.models.villains;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.models.Level;


public class Arcanine extends Villain {

	Arcanine() {
		this.name = "Arcanine";
		this.sprite = new Sprite(false, 1);
		this.characterClass = CharacterClass.ASSASSIN;
		this.level = new Level((int)(1 + (6 - 1) * Math.random()));
		this.assignStats();
	}
	
}
