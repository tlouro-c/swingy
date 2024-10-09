package tc.tlouro_c.swingy.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DebugTools {

	private static boolean ON = false;

	public static boolean ON() {
		return ON;
	}

	public static void log(String message) {
		if (ON) {
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       		LocalTime currentTime = LocalTime.now();
			System.out.println("\n==========DEBUG MESSAGE==========");
			System.out.println("Timestamp: " + timeFormatter.format(currentTime));
			System.out.println("Message: " + message);
			System.out.println("=================================");
		}
	}
}
