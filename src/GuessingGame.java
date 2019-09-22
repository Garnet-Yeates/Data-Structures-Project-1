import java.util.Scanner;

public class GuessingGame
{
	private static final Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		new GuessingGame(false);
	}
	
	public GuessingGame(boolean debug)
	{
		game: while (true)
		{
			int numDigits = nextInt("Enter the amount of digits to be randomly generated");
			if (numDigits < 1) continue game;
			
			NumberBag botBag = new NumberBag(null, numDigits, true);
			
			if (debug)
			{
				Integer[] arr = botBag.toArray(new Integer[0]);
				System.out.print("[DEBUG] ");
				for (Integer i : arr) System.out.print(i + " ");			
				System.out.println();
			}

			NumberBag playerBag = null;
			String message = "Enter " + numDigits + " integer" + (numDigits == 1 ? "" : "s") + " in the range from 0 to 9. Entries may be duplicate";
			guessing: while (true)
			{
				playerBag = new NumberBag(message, numDigits, false);
				int numMatching = botBag.getNumMatching(playerBag);
				if (numMatching == numDigits)
				{
					String answer = GuessingGame.nextLine("You are correct! Would you like to play again? ");
					if (arrContains(new String[] { "y", "yes" }, answer))
					{
						continue game;
					}
					else
					{
						System.out.println("Good bye!");
						System.exit(0);
					}		
				}
				message = numMatching + " of your guesses are correct. Guess again";
				continue guessing;
			}

		}
	}
	
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
	
	public static String nextLine(String message)
	{
		if (message != null) System.out.print(message + ":\n -> ");
		String nextLine = input.next() + input.nextLine();
		return nextLine;
	}
	
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
