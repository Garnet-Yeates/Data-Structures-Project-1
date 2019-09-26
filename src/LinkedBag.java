import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
public class LinkedBag<T> implements BagInterface<T>, Iterable<T>
{	
	private int numEntries;
	private Node first;
	
	private class Node
	{
		private T value;
		private Node next;
	}
	
	/**
	 * Constructs a new ArrayBag with the given capacity
	 * ArrayBags cannot be resized
	 * @param capacity the amount of entries this bag can hold
	 */
	public LinkedBag()
	{
		first = null;
		numEntries = 0;
	}
	
	public LinkedBag(LinkedBag<T> copying)
	{
		for (T entry : copying)
		{
			add(entry);
		}
	}
	
	public LinkedBag<T> intersection(LinkedBag<T> other)
	{
		LinkedBag<T> intersection = new LinkedBag<T>();
		other = new LinkedBag<>(other);
		for (T entry : this)
		{
			if (other.remove(entry))
			{
				intersection.add(entry);
			}
		}
		return intersection;
	}
	
	public LinkedBag<T> union(LinkedBag<T> other)
	{
		other = new LinkedBag<>(other);
		for (T entry : this) other.add(entry);
		return other;
	}

	/**
	 * Produces a copy of this ArrayBag
	 * @return an equivalent version of this ArrayBag that contains
	 * the same elements
	 */
	public LinkedBag<T> copy()
	{
		LinkedBag<T> copy = new LinkedBag<>();
		for (T entry : this) copy.add(entry);
		return copy;
	}
	
	/**
	 * Obtains the number of entries shared between this ArrayBag and
	 * another ArrayBag
	 * @param other the other ArrayBag that is being checked for similar entries
	 * @return the number of entries that would be in the intersection between this
	 * ArrayBag and another
	 */
	public int getNumMatching(LinkedBag<T> other)
	{
		return intersection(other).numEntries;
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
		Node oldFirst = first;
		first = new Node();
		first.value = newEntry;
		first.next = oldFirst;
		numEntries++;
		return true;
	}

	@Override
	public boolean remove(T anEntry)
	{
		Node current = first;
		Node previous = null;
		while (current != null)
		{
			if (current.value.equals(anEntry))
			{
				if (current == first)
				{
					remove();
					return true;
				}
				else
				{
					previous.next = current.next;
					numEntries--;
					return true;
				}
			}
			current = current.next;
			previous = current;
		}
		return false;
	}

	@Override
	public T remove()
	{
		Node oldFirst = first;
		first = first.next;
		numEntries--;
		return oldFirst.value;
	}

	@Override
	public void clear()
	{
		first = null;
		numEntries = 0;
	}

	@Override
	public boolean contains(T anEntry)
	{
		for (T entry : this)
		{
			if (entry.equals(anEntry))
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
		for (T entry : this)
		{
			if (entry.equals(anEntry))
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public T[] toArray()
	{
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) new Object[numEntries];
		int i = 0;
		for (T entry : this)
		{
			arr[i] = entry;
			i++;
		}
		return arr;
	}

	@Override
	public T[] toArray(T[] a)
	{ 
		T[] arr = (T[]) Arrays.copyOf(a, numEntries);
		int i = 0;
		for (T entry : this)
		{
			arr[i] = entry;
			i++;
		}
		return arr;
	}
	
	private class LinkedListIterator implements Iterator<T>
	{
        private Node current;

        // creates a new iterator
        public LinkedListIterator() 
        {
            current = first;
        }

        // is there a next item in the iterator?
        public boolean hasNext()
        {
            return current != null;
        }

        // returns the next item in the iterator (and advances the iterator)
        public T next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.value;
            current = current.next; 
            return item;
        }
    }

	@Override
	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}
}
