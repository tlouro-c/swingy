package tc.tlouro_c.swingy.controllers;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.views.HeroSelectView;

public class HeroSelectController {

	private HeroSelectView view;

	public HeroSelectController(HeroSelectView view) {
		this.view = view;
	}

	public void previewHero(Hero hero) {

		view.updateHeroView(hero);
	}
	

}
