import java.util.Arrays;

/**
 * This class is used to represent a bag of items with a set
 * capacity that cannot be resized. The items in the bag
 * do not have any specified order. When an item is removed
 * from the bag, the highest index item in the bag gets moved
 * to its spot.
 * @author yeatesg, adamsc
 * @param <T> The type of item that is going to be stored in
 * this bag
 */
public class ArrayBag<T> implements BagInterface<T>
{	
	private int numEntries;
	private int capacity;
	private T[] bag;
	
	/**
	 * Constructs a new ArrayBag with the given capacity
	 * ArrayBags cannot be resized
	 * @param capacity the amount of entries this bag can hold
	 */
	@SuppressWarnings("unchecked")
	public ArrayBag(int capacity)
	{
		numEntries = 0;
		this.capacity = capacity;
		bag = (T[]) new Object[capacity];
	}

	/**
	 * Determines whether or not this ArrayBag is full.
	 * @return true if the number of entries in this bag is equal to
	 * the capacity of the bag
	 */
	public boolean isFull()
	{
		return numEntries == capacity;
	}
	
	/**
	 * Produces a copy of this ArrayBag
	 * @return an equivalent version of this ArrayBag that contains
	 * the same elements
	 */
	public ArrayBag<T> copy()
	{
		ArrayBag<T> copy = new ArrayBag<>(capacity);
		T[] arrCopy = toArray();
		copy.bag = arrCopy;
		copy.numEntries = numEntries;
		return copy;
	}
	
	/**
	 * Obtains the number of entries shared between this ArrayBag and
	 * another ArrayBag
	 * @param other the other ArrayBag that is being checked for similar entries
	 * @return the number of entries that would be in the intersection between this
	 * ArrayBag and another
	 */
	public int getNumMatching(ArrayBag<T> other)
	{
		int numMatching = 0;
		other = other.copy();
		for (T entry : bag) numMatching += other.remove(entry) ? 1 : 0;
		return numMatching;
	}
	
	@Override
	public int getCurrentSize()
	{
		return numEntries;
	}

	@Override
	public boolean isEmpty()
	{
		return numEntries == 0;
	}
	
	@Override
	public boolean add(T newEntry)
	{
		if (numEntries < capacity)
		{
			bag[numEntries++] = newEntry;
		}
		return true;
	}

	@Override
	public boolean remove(T anEntry)
	{
		for (int i = 0; i < numEntries; i++)
		{
			if (bag[i].equals(anEntry))
			{
				bag[i] = bag[numEntries - 1];
				bag[numEntries - 1] = null;
				numEntries--;
				return true;
			}
		}
		return false;
	}

	@Override
	public T remove()
	{
		for (int i = 0; i < numEntries; i++)
		{
			if (bag[i] != null)
			{
				T entry = bag[i];
				bag[i] = bag[numEntries - 1];
				bag[numEntries - 1] = null;
				numEntries--;
				return entry;
			}
		}
		return null;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < numEntries; i++)
		{
			bag[i] = null;
		}
		numEntries = 0;
	}

	@Override
	public boolean contains(T anEntry)
	{
		for (int i = 0; i < numEntries; i++)
		{
			if (bag[i].equals(anEntry))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int getFrequencyOf(T anEntry)
	{
		int count = 0;
		for (int i = 0; i < numEntries; i++)
		{
			if (bag[i].equals(anEntry))
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public T[] toArray()
	{
		return Arrays.copyOf(bag, bag.length);
	}

	@Override
	public T[] toArray(T[] a)
	{ 
		T[] arr = (T[]) Arrays.copyOf(a, bag.length);
		for (int i = 0; i < numEntries; i++) arr[i] = bag[i];
		return arr;
	}
}
