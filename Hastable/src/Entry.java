
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
