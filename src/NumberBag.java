import java.util.Random;

public class NumberBag extends ArrayBag<Integer>
{
	private static final Random R = new Random();
	
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
				clear();
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
				catch (Exception e) { }
			}
		}
	}
	

}
