package tc.tlouro_c.swingy.views;

import org.junit.Test;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.HeroBuilder;

public class GameplayCliViewTest {
	@Test
	public void testDrawMap() {

		var heroBuilder = new HeroBuilder();

		heroBuilder.name("Charizard")
					.attack(10)
					.defense(10)
					.maxHP(10)
					.characterClass(CharacterClass.ASSASSIN);

		var newHero = heroBuilder.build();
		newHero.updateExperience(30000);
	}
}
