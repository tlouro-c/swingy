package tc.tlouro_c.swingy.models.villains;

import javax.swing.ImageIcon;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Level;
import tc.tlouro_c.swingy.models.Villain;


public class Terraphin extends Villain {

	Terraphin() {
		this.name = "Terraphin";
		this.sprite = new ImageIcon(getClass().getResource("/sprites/Terraphin.png"));
		this.characterClass = CharacterClass.TANK;
		this.level = new Level((int)(1 + (6.99 - 1) * Math.random()));
		this.assignStats();
	}
	
}
