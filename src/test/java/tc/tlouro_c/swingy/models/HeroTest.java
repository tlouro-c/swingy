package tc.tlouro_c.swingy.models;

import org.junit.Test;

import tc.tlouro_c.swingy.models.villains.VillainFactory;

public class HeroTest {

	@Test
	public void testHero() {

		HeroBuilder heroBuilder = new HeroBuilder();

		heroBuilder.name("Charizard")
					.sprite(null)
					.characterClass(CharacterClass.ASSASSIN)
					.attack(10)
					.defense(10)
					.maxHP(10);

		Hero myHero = heroBuilder.build();
		myHero.updateExperience(4000);

		Villain myVillain = VillainFactory.getInstance().newRandomVillain();

		System.out.println(myHero);

		System.out.println(myVillain);

		Character winner =  FightSimulator.getInstance().runSimulation(myHero, myVillain);


	}
}
