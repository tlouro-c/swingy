package tc.tlouro_c.swingy.utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {

	private static InputReader instance;
	private Scanner scanner;

	private InputReader() {
		scanner = new Scanner(System.in);
	}

	public static InputReader getInstance() {
		if (instance == null) {
			instance = new InputReader();
		}
		return instance;
	}

	public Integer optionsBasedInput(ArrayList<Integer> listOfOptions) {

		Integer input = 0;
		int i = 0;

		while (!listOfOptions.contains(input)) {
			if (i != 0) {
				System.out.println("Invalid Option");
			}
			input = scanner.nextInt();
			i++;
		}
		return input;
	}

	public String optionsBasedInputString(ArrayList<String> listOfOptions) {

		String input = "";
		int i = 0;

		while (!listOfOptions.contains(input.toLowerCase())) {
			if (i != 0) {
				System.out.println("Invalid Option");
			}
			input = scanner.next();
			i++;
		}
		return input;
	}

	public int getIntBetween(int min, int max, String errorMessage) {

		Integer input = -1;
		int i = 0;

		while (input < min || input > max) {
			if (i != 0) {
				System.out.println(errorMessage);
			}
			try {
				input = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid number format");
				scanner.next();
				input = -2;
			}
			i++;
		}
		return input;
	}
	

	public String getString() {
		return scanner.next();
	}

	public void pressEnterToContinue() {
		scanner.nextLine();
	}

	
}
