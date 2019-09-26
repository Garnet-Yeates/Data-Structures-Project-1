import java.util.Scanner;

/**
 * 
 * @author yeatesg adamsc
 * Main class for the GuessingGame. This class contains methods for getting user input
 * and it also contains the main loops that run the game
 *
 */
public class GuessingGame
{
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		new GuessingGame(true);
	}
	
	/**
	 * Constructs a new GuessingGame instance. This will first request for the user to enter the number
	 * of digits they want to guess. After they choose, the game will begin and the game will request for
	 * the user to start guessing. The game will continue to run until the player successfully guesses
	 * all of the numbers (order doesn't matter)
	 * @param debug set to true to show the answer at the beginning for testing
	 */
	public GuessingGame(boolean debug)
	{
		game: while (true) // This loop continues until the player quits the game
		{
			int numDigits = nextInt("Enter the amount of digits to be randomly generated");
			if (numDigits < 1) continue game;
			
			NumberBag botBag = new NumberBag(null, numDigits, true);
			
			if (debug) // For debugging the game, the answer will be printed
			{
				Integer[] arr = botBag.toArray(new Integer[0]);
				System.out.print("[DEBUG] ");
				for (Integer i : arr) System.out.print(i + " ");			
				System.out.println();
			}

			NumberBag playerBag = null;
			String message = "Enter " + numDigits + " integer" + (numDigits == 1 ? "" : "s") + " in the range from 0 to 9. Entries may be duplicate";
			guessLoop: while (true) // This loop continues until the player guesses the numbers
			{
				playerBag = new NumberBag(message, numDigits, false);
				int numMatching = botBag.getNumMatching(playerBag);
				if (numMatching == numDigits)
				{
					String answer = GuessingGame.nextLine("You are correct! Would you like to play again? ");
					if (arrContains(new String[] { "y", "yes" }, answer))
					{
						continue game; // If they typed yes then the game will restart
					}
					else
					{
						System.out.println("Good bye!");
						break game;
					}		
				}
				message = numMatching + " of your guesses are correct. Guess again";
				continue guessLoop; // If they didn't match all of the numbers, do the guessing loop again
			}

		}
	}
	
	/**
	 * Obtains the next integer input from the user
	 * @param message the message that is printed before obtaining the input
	 * @return the next integer that the user types into the console
	 */
	public static int nextInt(String message)
	{
		if (message != null) System.out.print(message + ":\n -> ");
		message = "This input should be a number: ";
		while (true)
		{
			String next = input.next() + input.nextLine();
			try
			{
				int num = Integer.parseInt(next);
				return num;
			}
			catch (Exception e)
			{
				if (message != null) System.out.print(message);
			}
		}
	}
	
	/**
	 * Gets the next line of text that the user enters into the console, and
	 * prints a message to tell them what they should be entering
	 * @param message set to null for no message
	 * @return the next line of text that the user types
	 */
	public static String nextLine(String message)
	{
		if (message != null) System.out.print(message + ":\n -> ");
		String nextLine = input.next() + input.nextLine();
		return nextLine;
	}
	
	/**
	 * Determines whether or not the given array contains the given element.
	 * If the elements are strings, then {@link String#equalsIgnoreCase(String)}
	 * is used
	 * @param arr the array whose contents are being checked
	 * @param entry the entry that is being searched for
	 * @return true if the array contains the given entry
	 */
	public static <T> boolean arrContains(T[] arr, T entry)
	{
		for (T t : arr)
		{
			if (t.equals(entry) || (t instanceof String && entry instanceof String && ((String) t).equalsIgnoreCase((String) entry)))
			{
				return true;
			}
		}
		return false;	
	}
}
