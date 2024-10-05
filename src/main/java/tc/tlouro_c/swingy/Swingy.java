package tc.tlouro_c.swingy;

import java.awt.CardLayout;

import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.FightSimulator;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.models.artifacts.ArtifactFactory;
import tc.tlouro_c.swingy.models.villains.VillainFactory;
import tc.tlouro_c.swingy.views.HeroSelectView;

public class Swingy 
{
    public static void main( String[] args )
    {
        // Frame frame = new Frame();
		// frame.setLayout(null);


		// JPanel panel = new JPanel();
		// panel.setBounds(0, 0, 250, 250);

		// JLabel label = new JLabel("Mewtoo");
		// Sprite sprite = new Sprite(false, 2);
		// ImageIcon spriteImg = new ImageIcon(sprite.getPreviewSprite().getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		// label.setIcon(spriteImg);

		// panel.add(label);
		// frame.add(panel);
		// frame.setVisible(true);

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

		var villain = VillainFactory.getInstance().newRandomVillain();


		var frame = Frame.getInstance();
		frame.setLayout(new CardLayout(1, 1));

		//var statsView = new CharacterView(614, 200).newStatsPanel(hero);

		var heroSelectView = new HeroSelectView();
		heroSelectView.updateHeroView(hero);

		frame.add(heroSelectView);
		frame.setVisible(true);

		//FightSimulator.getInstance().runSimulation(hero, villain);


		// for (int i = 0; i < 100; i++) {

		// 	try {
        //     	// Sleep for 2 seconds (2000 milliseconds)
        //     	Thread.sleep(100);
		// 	} catch (InterruptedException e) {
		// 		e.printStackTrace();
		// 	}
		// 	hero.updateExperience(50);
		// 	heroSelectView.updateHeroView(hero);
		//	// heroSelectView.updateHeroView(VillainFactory.getInstance().newRandomVillain());
		// }
		
		//frame.getContentPane().removeAll();
		//frame.revalidate();
		//frame.repaint();
    }
		
}
