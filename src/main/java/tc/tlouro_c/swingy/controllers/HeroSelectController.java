package tc.tlouro_c.swingy.controllers;

import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.validation.ConstraintViolationException;

import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.HeroBuilder;
import tc.tlouro_c.swingy.utils.DBManager;
import tc.tlouro_c.swingy.views.HeroSelectCliView;
import tc.tlouro_c.swingy.views.HeroSelectView;

public class HeroSelectController {

	private Frame frame;
	private HeroSelectView view;
	private HeroSelectCliView cliView;
	private DBManager db;
	ActionListener listenerForGameStart;

	public HeroSelectController(ActionListener listenerForGameStart) {
		this.listenerForGameStart = listenerForGameStart;
		this.db = DBManager.getInstance();
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void loadInitialScreen() {
		this.view = new HeroSelectView(e -> createHero(), listenerForGameStart);
		var startScreen = view.startScreen();
		frame.add(startScreen);
		frame.setVisible(true);
	}

	public Hero getSelectedHero() {
		return view.getSelectedHero();
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
			db.createHero(newHero);
			view.loadInitialButtons();
		} catch (ConstraintViolationException e) {
			view.displayErrorPopUp(e.getConstraintViolations().iterator().next().getMessage(), null);
		}
	}

	// CLI 

	public void loadInitialPrompt() {
		this.cliView = new HeroSelectCliView(this);
		cliView.initialPrompt();
	}

	public void createHeroCli() {
		var heroBuilder = new HeroBuilder();
		Hero newHero;
		heroBuilder.name(cliView.getName())
					.characterClass(cliView.getHeroClass())
					.attack(cliView.getAttack())
					.defense(cliView.getDefense())
					.maxHP(cliView.getHitPoints());
		try {
			newHero = heroBuilder.build();
			db.createHero(newHero);
			cliView.successCreatingHero();
		} catch (ConstraintViolationException e) {
			cliView.invalidCreateHeroForm(e.getConstraintViolations().iterator().next().getMessage());
		}
	}

	public Hero getSelectedHeroCli() {
		return cliView.getSelectedHero();
	}


}
