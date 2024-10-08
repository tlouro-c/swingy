package tc.tlouro_c.swingy.models.villains;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.Sprite;
import tc.tlouro_c.swingy.models.Level;


public class Machamp extends Villain {

	Machamp() {
		this.name = "Machamp";
		this.sprite = new Sprite(false, 3);
		this.characterClass = CharacterClass.BRUISER;
		this.assignStats();
	}
	
}
