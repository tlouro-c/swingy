package tc.tlouro_c.swingy;

public class Swingy 
{
    public static void main( String[] args )
    {
		if (args.length != 1
			|| (!args[0].equalsIgnoreCase("gui") && !args[0].equalsIgnoreCase("cli"))) {
			System.err.println("Please specify the type of user interface as an argument! [GUI / CLI]");
			return;
		}
		Game game = new Game(args[0]);
		game.run();
    }
		
}
