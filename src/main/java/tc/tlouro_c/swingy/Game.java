package tc.tlouro_c.swingy;

import tc.tlouro_c.swingy.controllers.GameplayController;
import tc.tlouro_c.swingy.controllers.HeroSelectController;
import tc.tlouro_c.swingy.utils.DBManager;
import tc.tlouro_c.swingy.utils.Frame;

public class Game {

	private Frame window;
	private HeroSelectController heroSelectController;
	private GameplayController gameplayController;
	private boolean cli;

	public Game() {
		this.cli = false;
		this.heroSelectController = new HeroSelectController(e -> gameplay());
		this.gameplayController = new GameplayController(this);
	}

	void run() {
		DBManager.getInstance().createTableIfNotExists();
		heroSelection();
	}

	public void heroSelection() {
		if (this.window != null)  {
			window.removeAll();
			window.dispose();
		}
		window = new Frame("Swingy - Hero Selection");
		heroSelectController.setFrame(window);
		heroSelectController.loadInitialScreen();
	}

	void gameplay() {
		if (window != null) {
			window.removeAll();
			window.dispose();
		}
		window = new Frame("Swingy");
		gameplayController.setFrame(window);
		gameplayController.setHero(heroSelectController.getSelectedHero());
		gameplayController.start();
	}

	public boolean isCli() {
		return cli;
	}

	public void setCli(boolean cli) {
		this.cli = cli;
	}

	
	
}
