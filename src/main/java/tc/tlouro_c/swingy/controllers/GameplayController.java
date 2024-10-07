package tc.tlouro_c.swingy.controllers;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.utils.Frame;
import tc.tlouro_c.swingy.views.GameplayView;

public class GameplayController {

	private Frame frame;
	private GameplayView gameplayView;
	private Hero hero;


	public GameplayController() {
		this.gameplayView = new GameplayView();
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public void start() {
		gameplayView.setHero(hero);
		var gameplayScreen = gameplayView.loadScreen();
		frame.add(gameplayScreen);
		frame.setVisible(true);
	}

	
}
