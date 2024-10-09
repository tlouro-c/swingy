package tc.tlouro_c.swingy.views;

import java.util.ArrayList;

import tc.tlouro_c.swingy.models.Artifact;
import tc.tlouro_c.swingy.models.Direction;
import tc.tlouro_c.swingy.models.Hero;
import tc.tlouro_c.swingy.models.Map;
import tc.tlouro_c.swingy.models.MapEntity;
import tc.tlouro_c.swingy.models.Villain;
import tc.tlouro_c.swingy.utils.InputReader;

public class GameplayCliView {

	private InputReader ir;

	public GameplayCliView() {
		this.ir = InputReader.getInstance();
	}

	public void drawMap(Map map) {
		var grid = map.getMapGrid();

		for (MapEntity[] row : grid) {

			for (MapEntity square : row) {
				if (square instanceof Hero) {
					System.out.print("\033[33mP \033[0m");
				} else if (square instanceof Villain) {
					System.out.print("\033[31mE \033[0m");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

	public Direction directionInput() {
		var options = new ArrayList<String>();
		options.add("w");
		options.add("a");
		options.add("s");
		options.add("d");
        System.out.print("\nW (North)  |  ");
        System.out.print("A (West)  |  ");
        System.out.print("S (South)  |  ");
        System.out.println("D (East)\n");
		System.out.print("Next move direction: ");
		String input = ir.optionsBasedInputString(options).toLowerCase();
		switch(input) {
			case "w": return Direction.NORTH;
			case "a": return Direction.WEST;
			case "s": return Direction.SOUTH;
			case "d": return Direction.EAST;
		}
		return null;
	}

	public void waitForEnter(String message) {
		System.out.println(message);
		System.out.print("Press Enter to continue...");
		ir.pressEnterToContinue();
		ir.pressEnterToContinue();
	}

	public void waitForEnter2(String message) {
		System.out.println(message);
		System.out.print("Press Enter to continue...");
		ir.pressEnterToContinue();
	}

	public int villainFound(Villain villain) {
		System.out.println(String.format("====== %s Lv.%d found! ======",
										villain.getName(), villain.getLevel()));
		var options = new ArrayList<Integer>();
		options.add(1);
		options.add(2);
		System.out.println("[ 1 ]  Run");
		System.out.println("[ 2 ]  Fight");
		return (int)ir.optionsBasedInput(options);
	}

	public int pickUpArtifactPrompt(Artifact artifact) {
		System.out.println(String.format("====== A %s was dropped! ======", artifact));
		var options = new ArrayList<Integer>();
		options.add(1);
		options.add(2);
		System.out.println("[ 1 ]  Pick it up");
		System.out.println("[ 2 ]  Drop it");
		return (int)ir.optionsBasedInput(options);
	}

	public void printStats(Hero hero) {
		System.out.println(hero);
	}
}
