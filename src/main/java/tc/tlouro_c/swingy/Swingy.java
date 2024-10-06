package tc.tlouro_c.swingy;

import java.awt.CardLayout;

import tc.tlouro_c.swingy.controllers.HeroSelectController;
import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;

public class Swingy 
{
    public static void main( String[] args )
    {
		HeroBuilder builder = new HeroBuilder();

		builder.name("Charizard")
				.sprite(1)
				.characterClass(CharacterClass.ASSASSIN)
				.attack(10)
				.defense(10)
				.maxHP(10);

		Hero hero = builder.build();
		hero.equipArtifact(ArtifactFactory.getInstance().newRandomArtifact());
		hero.updateExperience(5000);


		var frame = Frame.getInstance();
		frame.setLayout(new CardLayout(1, 1));

		var heroSelectController = new HeroSelectController(frame);

		heroSelectController.loadInitialScreen();

		//frame.getContentPane().removeAll();
		//frame.revalidate();
		//frame.repaint();
    }
		
}
