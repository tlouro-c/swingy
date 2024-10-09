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

	public Game(String ui) {
		this.cli = ui.toLowerCase().equals("cli");
		this.heroSelectController = new HeroSelectController(e -> gameplay());
		this.gameplayController = new GameplayController(this);
	}

	void run() {
		DBManager.getInstance().createTableIfNotExists();
		heroSelection();
	}

	public void heroSelection() {
		if (cli) {
			heroSelectController.loadInitialPrompt();
			gameplay();
		} else {
			if (this.window != null)  {
				window.removeAll();
				window.dispose();
			}
			window = new Frame("Swingy - Hero Selection");
			heroSelectController.setFrame(window);
			heroSelectController.loadInitialScreen();
		}
		
	}

	private void gameplay() {
		if (cli) {
			gameplayController.cliGameplay(heroSelectController.getSelectedHeroCli());
			heroSelection();
		} else {
			if (window != null) {
				window.removeAll();
				window.dispose();
			}
			window = new Frame("Swingy");
			gameplayController.setFrame(window);
			gameplayController.setHero(heroSelectController.getSelectedHero());
			gameplayController.start();
		}
		
	}

	public boolean isCli() {
		return cli;
	}

	
}
