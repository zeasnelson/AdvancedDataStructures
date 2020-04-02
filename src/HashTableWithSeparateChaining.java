
import java.util.*;
import java.util.Map.Entry;

public class HashTableWithSeparateChaining<K, V> implements Iterable<K> {
	

public class Entry<K, V> {
	
	  int hash; //hash
	  K key;	//keys
	  V value;  //values

	  public Entry(K key, V value) {
	    this.key = key;
	    this.value = value;
	    this.hash = key.hashCode();
	  }

	  // We are not overriding the Object equals method
	  // No casting is required with this method.
	  
	  //equals method not being overidden 
	  public boolean equals(Entry<K, V> other) {
	    if (hash != other.hash) return false;
	    return key.equals(other.key);
	  }
	  
	  //print out the the link of key 
	  @Override
	  public String toString() {
	    return key + " => " + value;
	  }
}

	private static final int DefaultCapacity = 1;
	private static final double DefaultLoadFactor = 0.75;

	// load factor = (items in table)/(size of the table)
	private double LoadFactor; // we need a load factor which can't be zero
	private int capacity, threshold, size = 0; // threshold can't be zero
	private LinkedList<Entry<K, V>>[] table; // table to hold elements

	public HashTableWithSeparateChaining() {
		this(DefaultCapacity, DefaultLoadFactor);
	}

	public HashTableWithSeparateChaining(int capacity) {
		this(capacity, DefaultLoadFactor);
	}

	// constructor
	public HashTableWithSeparateChaining(int capacity, double LoadFactor) {
		// Capacity can't be less than zero
		if (capacity < 0)
			throw new IllegalArgumentException("must be a postive number for  capacity");
		// check if load factor is less than zero, if its not a number, and some huge
		// integer
		if (LoadFactor <= 0 || Double.isNaN(LoadFactor) || Double.isInfinite(LoadFactor))
			throw new IllegalArgumentException(" LoadFactor cannot be a negative value");
		this.LoadFactor = LoadFactor;
		this.capacity = Math.max(DefaultCapacity, capacity); // returns the greater of two int values. That is, the
																// result is the argument closer to positive infinity.
		threshold = (int) (this.capacity * LoadFactor); // cast the value of loadfactor plus capacity as an int
		table = new LinkedList[this.capacity]; // create our linkedlist with the said capacity
	}// end defualt constructor

	// Returns the size
	public int size() {
		return size;
	}// end size
		// returns true or false dependning whether the structure is empty or not

	public boolean isEmpty() {
		return size == 0;
	}// end is empty
		// Converts a hash value to an index. removes the negative sign.
		// places the hash value in the domain [0, capacity)

	private int normalizeIndex(int keyHash) {
		return (keyHash & 0x7FFFFFFF) % capacity;
	}// end normalize

	// delete contents of the table, fill the table with null values and reset the
	// size to 0
	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}// end clear

	// check for key, returns true or false
	public boolean containsKey(K key) {
		return hasKey(key);
	}// end contains key
		// iterator functions and list functions

	// Returns the list of keys
	public List<K> keys() {

		List<K> keys = new ArrayList<>(size());
		for (LinkedList<Entry<K, V>> bucket : table)
			if (bucket != null)
				for (Entry<K, V> entry : bucket)
					keys.add(entry.key);
		return keys;
	}// end return keys

	// Returns the list of values
	public List<V> values() {

		List<V> values = new ArrayList<>(size());
		for (LinkedList<Entry<K, V>> bucket : table)
			if (bucket != null)
				for (Entry<K, V> entry : bucket)
					values.add(entry.value);
		return values;
	}// end return values

	// Return an iterator to iterate over all the keys in the map
	@Override
	public Iterator<K> iterator() {
		final int elementCount = size();
		return new Iterator<K>() {

			int bucketIndex = 0;
			Iterator<Entry<K, V>> bucketIter = (table[0] == null) ? null : table[0].iterator();

			@Override
			public boolean hasNext() {

				// An item was added or removed while iterating
				if (elementCount != size)
					throw new ConcurrentModificationException();

				// No iterator or the current iterator is empty
				if (bucketIter == null || !bucketIter.hasNext()) {

					// Search next buckets until a valid iterator is found
					while (++bucketIndex < capacity) {
						if (table[bucketIndex] != null) {

							Iterator<Entry<K, V>> nextIter = table[bucketIndex].iterator();
							if (nextIter.hasNext()) {
								bucketIter = nextIter;
								break;
							}
						}
					}
				}
				return bucketIndex < capacity;
			}// end has next

			@Override
			public K next() {
				return bucketIter.next().key;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}// end public iterator

	/*
	 * Everytime we seek something within the bucket be it a function such as hasKey
	 * we need create a bucket index to look at the values
	 *
	 * A hash table that uses buckets is actually a combination of an array and a
	 * linked list. Each element in the array (the hash table) is a header for a
	 * linked list. All elements that hash into the same location will be stored in
	 * the list. The hash function assigns each record to the first slot within one
	 * of the buckets. In case the slot is occupied, then the bucket slots will be
	 * searched sequentially until an open slot is found. In case a bucket is
	 * completely full, the record will get stored in an overflow bucket of infinite
	 * capacity at the end of the table. All buckets share the same overflow bucket.
	 * However, a good implementation will use a hash function that distributes the
	 * records evenly among the buckets so that as few records as possible go into
	 * the overflow bucket.
	 * 
	 */

	// Returns true/false depending on whether a key is in the hash table
	public boolean hasKey(K key) {
		int bucketIndex = normalizeIndex(key.hashCode()); // normalize the index with the nomalize index function
		return bucketSeekEntry(bucketIndex, key) != null;
	}// end haskey

	// The next 3 function can all insert, and put values in the hash table
	public V put(K key, V value) {
		return insert(key, value);
	}// end insert

	public V add(K key, V value) {
		return insert(key, value);
	}// end add method

	public V insert(K key, V value) {
		if (key == null)
			throw new IllegalArgumentException("Keys cannot be null."); // if the key is null return error message
		Entry<K, V> NEntry = new Entry<>(key, value); // otherwise create a new entry with the key and value
		int bucketIndex = normalizeIndex(NEntry.hash);// normalize the index with a hash
		return bucketInsertEntry(bucketIndex, NEntry);// return to the bucket
	}// end insert

	// keys must exist otherwise returns null
	public V get(K key) {

		if (key == null)
			return null; // if the key is null return null to the user
		int bucketIndex = normalizeIndex(key.hashCode()); // otherwise check the bucket
		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
		if (entry != null)
			return entry.value;// if the value is not equal to null return that values
		return null;// otherwise return null as in the value wasent found
	}// end get

	// Removes a key from the map and returns the value.
	public Tracker remove(K key) {

		// tracker variable
		Tracker tracker = new Tracker("remove");
		tracker.setParameters(key.toString());// save param
		tracker.setStartTime();

		if (key == null)
			return null;

		int bucketIndex = normalizeIndex(key.hashCode());
		V output = bucketRemoveEntry(bucketIndex, key, tracker); // save ouput
		tracker.setEndTime();
		tracker.setFuncOutput(output.toString());
		return tracker;

	}// end remove

	/*
	 * Bucket remove, bucket seek and bucket insert all work with a bucket index, we
	 * need to first find the place of the value within the buckets
	 */

	// Removes an entry from a given bucket if it exists
	private V bucketRemoveEntry(int bucketIndex, K key, Tracker tracker ) {

		Entry<K, V> entry = bucketSeekEntry(bucketIndex, key); // look in the bucket
		if (entry != null) {
			// create node
			LinkedList<Entry<K, V>> links = table[bucketIndex];
			int index = links.indexOf(entry);

			index++;
			
			tracker.setComparisons((long) index);
			// return index
			
			links.remove(entry);// find the entry
			//System.out.println("index postion: " + index);
			--size;// decrement the size
			return entry.value;// return the value that was deleted
		} else
			return null;
	}// end bucket remove entry

	// bucket inserts, find a place for an element within the bucket
	private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {

		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		if (bucket == null) // If the buckets are empty a new list
			table[bucketIndex] = bucket = new LinkedList<>();

		Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
		if (existentEntry == null) {
			bucket.add(entry);
			if (++size > threshold)
				resizeTable();// if the table needs to be resied call the reizeTable function
			return null;
		} else {
			V oldVal = existentEntry.value;
			existentEntry.value = entry.value;
			return oldVal;
		}
	}// end bucket insert entry

	// Finds and returns a particular entry in a given bucket if it exists
	private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {

		if (key == null)
			return null;
		LinkedList<Entry<K, V>> bucket = table[bucketIndex];
		if (bucket == null)
			return null;
		for (Entry<K, V> entry : bucket)
			if (entry.key.equals(key))
				return entry;
		return null;
	}// end bucketseek entry

	// Resizes the internal table holding buckets of entries
	// when the size is greater than the threshold the table size is made to grow
	// capacity is doubled for more spaces of keys
	private void resizeTable() {
		capacity *= 2; // double size
		threshold = (int) (capacity * LoadFactor); // create a threshhold that is > than capacity

		LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {

				for (Entry<K, V> entry : table[i]) {
					int bucketIndex = normalizeIndex(entry.hash);
					LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
					if (bucket == null)
						newTable[bucketIndex] = bucket = new LinkedList<>();
					bucket.add(entry);
				}

				// to avoid memory leak
				table[i].clear();
				table[i] = null;
			}
		}

		table = newTable;
	}// end resize table
		// return a string rep of the table

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null)
				continue;
			for (Entry<K, V> entry : table[i])
				sb.append(entry + ", ");
		}
		sb.append("}");
		return sb.toString();
	}// end to string

	/*public static void main(String[] args) {

		HashTableWithSeparateChaining map = new HashTableWithSeparateChaining();

		map.insert("1", 1);
		map.insert("2", 2);


		map.insert("3", 3);

		map.insert("4", 4);
		map.insert("5", 5);
		map.insert("6", 6);
		map.insert("7", 7);
		map.insert("8", 8);
		map.insert("9", 9);



		System.out.println(map.remove("3"));


	}*/

}// end class
