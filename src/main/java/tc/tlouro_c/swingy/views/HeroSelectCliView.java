package tc.tlouro_c.swingy.views;

import java.util.ArrayList;

import tc.tlouro_c.swingy.controllers.HeroSelectController;
import tc.tlouro_c.swingy.models.Character;
import tc.tlouro_c.swingy.models.CharacterClass;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.utils.DBManager;
import tc.tlouro_c.swingy.utils.InputReader;

public class HeroSelectCliView {

	private HeroSelectController controller;
	private InputReader ir;
	private String name;
	private CharacterClass heroClass;
	private int attack;
	private int defense;
	private int hitPoints;
	private Hero selectedHero;

	public HeroSelectCliView(HeroSelectController controller) {
		this.controller = controller;
		this.ir = InputReader.getInstance();
	}

	public void initialPrompt() {
		System.out.println("\n====== Welcome to Swingy ======");

		ArrayList<Integer> options = new ArrayList<>();
		options.add(1);
		options.add(2);
		options.add(3);

		System.out.println("[ 1 ]  Create a new hero");
		System.out.println("[ 2 ]  Select an existing hero");
		System.out.println("[ 3 ]  Close the game");
		System.out.println("=======================");
		int optionChosen = ir.optionsBasedInput(options);

		if (optionChosen == 1) {
			createHero();
		} else if (optionChosen == 2) {
			selectHero();
		} else {
			System.exit(0);
		}
	}

	private void selectHero() {
		System.out.println("====== Existing heroes ======");

		ArrayList<Integer> options = new ArrayList<>();
		ArrayList<Character> existingHeroes = DBManager.getInstance().fetchHeroes();

		int i = 1;
		for (Character hero : existingHeroes) {
			System.out.println(String.format(
				"[ %d ]  %s Lv.%d Attack: %d Defense: %d Hit Points:%d Artifact: %s",
				i, hero.getName(), hero.getLevel(), hero.getAttack(), hero.getDefense(), hero.getMaxHP(),
				(hero.getArtifact() != null ? hero.getArtifact().getClass().getSimpleName() : "None")));
			options.add(i);
			i++;
		}
		int goBackOption = i;
		System.out.println(String.format("[ %d ]  Go back", i));
		options.add(i);
		System.out.println("=======================");

		int optionChosen = ir.optionsBasedInput(options);

		if (optionChosen == goBackOption) {
			initialPrompt();
		} else {
			selectedHero = (Hero)existingHeroes.get(optionChosen - 1);
		}
	}


	private void createHero() {
		System.out.println("====== Create a hero ======");

		printInstructions();
		System.out.print("Hero name: ");
		name = ir.getString();
		System.out.println();
		heroClass = heroClassInput();
		int remainingPoints = 30;
		System.out.print(String.format("(%d remaining points) Attack: ", remainingPoints));
		attack = ir.getIntBetween(0, remainingPoints, String.format("The number must be between %d and %d", 0, remainingPoints));
		remainingPoints -= attack;
		System.out.print(String.format("(%d remaining points) Defense: ", remainingPoints));
		defense = ir.getIntBetween(0, remainingPoints, String.format("The number must be between %d and %d", 0, remainingPoints));
		remainingPoints -= defense;
		System.out.print(String.format("(%d remaining points) Hit Points: ", remainingPoints));
		hitPoints = ir.getIntBetween(0, remainingPoints, String.format("The number must be between %d and %d", 0, remainingPoints));
		System.out.println();
		System.out.println("=======================");
		controller.createHeroCli();
	}


	private CharacterClass heroClassInput() {
		System.out.println("-> Choose your hero class <-");
		System.out.println("[ 1 ]  Bruiser | [ 2 ]  Assassin | [ 3 ]  Tank");

		CharacterClass classChosen = null;
		ArrayList<Integer> options = new ArrayList<>();
		options.add(1);
		options.add(2);
		options.add(3);
		int optionChosen = ir.optionsBasedInput(options);
		switch(optionChosen) {
			case 1: 
				classChosen = CharacterClass.BRUISER;
				break;
			case 2: 
				classChosen = CharacterClass.ASSASSIN;
				break;
			case 3: 
				classChosen = CharacterClass.TANK;
				break;
		}
		System.out.println();
		return classChosen;
	} 

	private void printInstructions() {
		System.out.println("Each hero begins with the following base stats:");
		System.out.println("  - 50 Attack");
		System.out.println("  - 50 Defense");
		System.out.println("  - 200 Hit Points");
		System.out.println();
		System.out.println("You have an additional 30 points to allocate as you choose.");
		System.out.println("Your class selection will also influence the overall stats.");
		System.out.println();
		System.out.print("Press Enter to continue...");
		ir.pressEnterToContinue();
		ir.pressEnterToContinue();
		System.out.println();
	}

	public void invalidCreateHeroForm(String errorMessage) {

		System.out.println("====== Error creating hero ======");
		System.out.println(errorMessage);
		System.out.println("Please correct your name.");
		System.out.print("Hero name: ");
		name = ir.getString();
		controller.createHeroCli();
	}

	public void successCreatingHero() {
		System.out.println("====== Hero created successfully ======");
		System.out.print("Press Enter to continue...");
		ir.pressEnterToContinue();
		ir.pressEnterToContinue();
		initialPrompt();
	}

	public String getName() {
		return name;
	}

	public CharacterClass getHeroClass() {
		return heroClass;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public Hero getSelectedHero() {
		return selectedHero;
	}

	
}
