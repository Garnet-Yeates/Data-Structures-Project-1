import java.util.Random;

/**
 * Represents an ArrayBag whose element type is {@link Integer}
 * This class is very similar to a regular ArrayBag, but it is
 * specially designed for the Guessing Game. This class's constructor
 * allows for it to either be automatically filled with random integers,
 * or for it to be filled through the console by the person playing this game.
 * @author yeatesg, adamsc
 */
public class NumberBag extends ArrayBag<Integer>
{
	private static final Random R = new Random();
	
	/**
	 * Constructs a new NumberBag. If the auto-fill parameter is set to true, then
	 * this NumberBag will be filled with randomly generated numbers. If it is set
	 * to false, the program will instruct the user to enter a series of numbers
	 * equal to the capacity of the bag, which is also a parameter in this
	 * constructor
	 * @param message the message sent to the user upon requesting for them to enter numbers
	 * @param capacity the capacity of this bag
	 * @param autoFill determines whether or not this bag is filled automatically
	 */
	public NumberBag(String message, int capacity, boolean autoFill)
	{
		super(capacity);
		
		if (autoFill)
		{
			while (!isFull()) add(R.nextInt(10));
		}
		else
		{
			while (true)
			{
				String nextLine = GuessingGame.nextLine(message);
				message = "Please enter a valid series of " + capacity + " numbers, separated by spaces";
				try
				{
					String[] split = nextLine.split(" ");
					if (split.length == capacity)
					{
						for (String s : split)
						{
							add(Integer.parseInt(s));
						}
						break;
					}
				}
				catch (Exception e)
				{ 
					clear();
				}
			}
		}
	}
}
