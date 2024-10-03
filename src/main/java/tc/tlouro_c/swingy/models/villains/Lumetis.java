package tc.tlouro_c.swingy.models.villains;

import javax.swing.ImageIcon;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.models.Level;


public class Lumetis extends Villain {

	Lumetis() {
		this.name = "Lumetis";
		this.sprite = new ImageIcon(getClass().getResource("/sprites/Lumetis.png"));
		this.characterClass = CharacterClass.BRUISER;
		this.level = new Level((int)(1 + (6 - 1) * Math.random()));
		this.assignStats();
	}
	
}
