package tc.tlouro_c.swingy.entities.villains;

import javax.swing.ImageIcon;

import tc.tlouro_c.swingy.entities.HeroClass;
import tc.tlouro_c.swingy.entities.Villain;
import tc.tlouro_c.swingy.entities.Level;


public class Lumetis extends Villain {

	Lumetis() {
		this.name = "Lumetis";
		this.sprite = new ImageIcon(getClass().getResource("/sprites/Lumetis.png"));
		this.heroClass = HeroClass.BRUISER;
		this.level = new Level((int)(1 + (6 - 1) * Math.random()));
		var min = level.getLevel() * 10;
		var max = min + (level.getLevel() * 3);
		this.attack = (int)((min + (max - min) * Math.random()) * this.heroClass.getAttackMultiplier());
		this.defense = (int)((min + (max - min) * Math.random()) * this.heroClass.getDefenseMultiplier());
		this.hitPoints = (int)((min + (max - min) * Math.random()) * this.heroClass.getHitPointsMultiplier());
	}
	
}
