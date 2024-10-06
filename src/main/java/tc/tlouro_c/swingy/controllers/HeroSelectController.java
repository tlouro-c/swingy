package tc.tlouro_c.swingy.controllers;

import java.awt.Frame;

import javax.validation.ConstraintViolationException;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.views.HeroSelectView;

public class HeroSelectController {

	private final Frame frame;
	private HeroSelectView view;

	public HeroSelectController(Frame frame) {
		this.frame = frame;
		this.view = new HeroSelectView();
	}

	public void loadInitialScreen() {
		var startScreen = view.startScreen(e -> createHero());

		frame.add(startScreen);
		frame.setVisible(true);
	}

	private void createHero() {
		var heroBuilder = new HeroBuilder();
		Hero newHero;

		heroBuilder.name(view.getHeroName())
					.characterClass(view.getHeroClass())
					.sprite(view.getSelectedSprite())
					.attack(view.getAttackPoints())
					.defense(view.getDefensePoints())
					.maxHP(view.getHitPoints());
		
		try {
			newHero = heroBuilder.build();
			System.out.println("Hero successfully created!");
			System.out.println(newHero);
		} catch (ConstraintViolationException e) {
			view.displayErrorPopUp(e.getConstraintViolations().iterator().next().getMessage(), null);
		}
	}
}
