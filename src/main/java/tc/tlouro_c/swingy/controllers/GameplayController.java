package tc.tlouro_c.swingy.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tc.tlouro_c.swingy.Game;
import tc.tlouro_c.swingy.models.Artifact;
import tc.tlouro_c.swingy.models.Direction;
import tc.tlouro_c.swingy.models.FightSimulator;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.Map;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.DBManager;
import tc.tlouro_c.swingy.utils.Frame;
import tc.tlouro_c.swingy.utils.MapFinishedException;
import tc.tlouro_c.swingy.utils.VillainAppearedException;
import tc.tlouro_c.swingy.views.GameplayCliView;
import tc.tlouro_c.swingy.views.GameplayView;

public class GameplayController {

	private Game game;
	private Frame frame;
	private GameplayView gameplayView;
	private GameplayCliView gameplayCliView;
	private Hero hero;
	private Map map;
	private DBManager db;
	private ControlsKeyListener controlsKeyListener;


	public GameplayController(Game game) {
		this.game = game;
		this.db = DBManager.getInstance();
		this.controlsKeyListener = new ControlsKeyListener();
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
		this.map = new Map(hero);
	}

	public void start() {
		this.gameplayView = new GameplayView(this);
		gameplayView.setHero(hero);
		gameplayView.setMap(map);
		var gameplayScreen = gameplayView.loadScreen();
		frame.add(gameplayScreen);
		frame.setVisible(true);
		enableControls();
	}

	public void movePlayer(Direction direction) {

		try {
			hero.move(direction, this.map);
			gameplayView.updateMapView();
		} catch (VillainAppearedException e) {
			gameplayView.handleConflict(e.getVillain());
		} catch (MapFinishedException e) {
			disableControls();
			db.updateHero(hero);
			gameplayView.endScreen("You finished this map!", ev -> game.heroSelection());
		}
	}

	public void run(Villain villain) {
		var random = Math.random();

		if (random < 0.5) {
			gameplayView.freeHero("You were able to run!");
		} else {
			fight(villain, "You can't run, you must fight!");
		}
	}

	public void fight(Villain villain, String message) {
		gameplayView.startFightPrompt(villain, message);
	}

	public void simulateFight(Villain villain) {
		var fightSimulator = FightSimulator.getInstance();
		var winner = fightSimulator.runSimulation(hero, villain);

		if (winner == villain) {
			gameplayView.endScreen("Your hero died!", ev -> game.heroSelection());
		} else {
			map.getMapGrid()[villain.getY()][villain.getX()] = null;
			movePlayer(hero.getDirection());
			gameplayView.fightWon(villain);
			gameplayView.updateDashboard();
			gameplayView.updatePreview();
		}
	}

	public void enableControls() {		
		frame.addKeyListener(this.controlsKeyListener);
		frame.requestFocusInWindow(); // Important
	}

	public void disableControls() {
		frame.removeKeyListener(this.controlsKeyListener);
	}

	public void pickUpArtifact(Artifact artifact) {
		hero.equipArtifact(artifact);
		gameplayView.updateDashboard();
		gameplayView.freeHero("");
	}

	private class ControlsKeyListener implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
			switch (charToLower(e.getKeyChar())) {
				case 'w': movePlayer(Direction.NORTH); break;
				case 'a': movePlayer(Direction.WEST); break;
				case 's': movePlayer(Direction.SOUTH); break;
				case 'd': movePlayer(Direction.EAST); break;
			}
		}
		private char charToLower(char c) {
			return c > 'A' && c < 'Z' ? (char)(c + 32) : c;
		}
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}


	public void cliGameplay(Hero hero) {
		this.gameplayCliView = new GameplayCliView();
		this.hero = hero;
		map = new Map(hero);

		gameplayCliView.waitForEnter("The game will start!");
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
		while (hero.isAlive()) {
			gameplayCliView.printStats(hero);
			gameplayCliView.drawMap(map);
			var direction = gameplayCliView.directionInput();
			try {
				movePlayerCli(direction, map);
			} catch (MapFinishedException e) {
				db.updateHero(hero);
				break;
			}
		}
		String message = hero.isAlive() ? "You finished this map!": "Your hero died...";
		gameplayCliView.waitForEnter2(message);
	}

	private void movePlayerCli(Direction direction, Map map) throws MapFinishedException {
		try {
			hero.move(direction, this.map);
		} catch (VillainAppearedException e) {
			var villain = e.getVillain();
			int decision = gameplayCliView.villainFound(villain);
			if (decision == 1 && Math.random() > 0.5) { // run
				gameplayCliView.waitForEnter("You were able to run!");
			} else { // fight
				gameplayCliView.waitForEnter((decision == 1 ? "You can't run! " : "") + "The fight will start!");
				var winner = FightSimulator.getInstance().runSimulation(hero, villain);
				if (winner == hero) {
					map.getMapGrid()[villain.getY()][villain.getX()] = null;
					gameplayCliView.waitForEnter2(String.format("You won!\n+%s experience!",
																		villain.getLevel() * 500));
					var artifact = villain.getArtifact();
					if (artifact != null) {
						var choice = gameplayCliView.pickUpArtifactPrompt(artifact);
						if (choice == 1) {
							hero.equipArtifact(artifact);
						}
						var message = choice == 1 ? ("You picked up a " + artifact) : "You dropped it";
						gameplayCliView.waitForEnter(message);
					}
					try {
						hero.move(hero.getDirection(), map);
					} catch (VillainAppearedException ex) {
						// ignore, impossible to happen
					}
				}
			}
		}
	}
}
