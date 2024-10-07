package tc.tlouro_c.swingy;

import tc.tlouro_c.swingy.controllers.GameplayController;
import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.utils.Frame;

public class Swingy 
{
    public static void main( String[] args )
    {
		//var game = new Game();

		//game.run();

		var heroBuilder = new HeroBuilder();

		heroBuilder.name("Test")
					.characterClass(CharacterClass.BRUISER)
					.sprite(1)
					.attack(1)
					.defense(1)
					.maxHP(1);
		
		var hero = heroBuilder.build();

		var gameplayController = new GameplayController();
		gameplayController.setHero(hero);
		gameplayController.setFrame(new Frame("Swing - In Development"));
		gameplayController.start();
    }
		
}
