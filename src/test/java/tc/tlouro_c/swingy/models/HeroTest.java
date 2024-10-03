package tc.tlouro_c.swingy.models;

import org.junit.Test;

import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;
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
					.hitPoints(10);

		Hero myHero = heroBuilder.build();
		myHero.updateExperience(2000);

		Villain myVillain = VillainFactory.getInstance().newRandomVillain();
		
		myHero.attack(myVillain);

		//myHero.equipArtifact(ArtifactFactory.getInstance().newRandomArtifact());

		System.out.println(myHero);

		System.out.println(myVillain);


	}
}
