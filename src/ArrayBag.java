import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T>
{	
	private int numEntries;
	private int capacity;
	private T[] bag;
	
	@SuppressWarnings("unchecked")
	public ArrayBag(int capacity)
	{
		numEntries = 0;
		this.capacity = capacity;
		bag = (T[]) new Object[capacity];
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
	
	public boolean isFull()
	{
		return numEntries == capacity;
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
	
	public ArrayBag<T> copy()
	{
		ArrayBag<T> copy = new ArrayBag<>(capacity);
		T[] arrCopy = toArray();
		copy.bag = arrCopy;
		copy.numEntries = numEntries;
		return copy;
	}
	
	public int getNumMatching(ArrayBag<T> other)
	{
		int numMatching = 0;
		other = other.copy();
		for (T entry : bag) numMatching += other.remove(entry) ? 1 : 0;
		return numMatching;
	}
}
