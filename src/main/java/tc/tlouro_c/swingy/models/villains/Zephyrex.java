package tc.tlouro_c.swingy.models.villains;

import javax.swing.ImageIcon;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.models.Level;


public class Zephyrex extends Villain {

	Zephyrex() {
		this.name = "Zephyrex";
		this.sprite = new ImageIcon(getClass().getResource("/sprites/Zephyrex.png"));
		this.characterClass = CharacterClass.ASSASSIN;
		this.level = new Level((int)(1 + (6 - 1) * Math.random()));
		this.assignStats();
	}
	
}
