
import java.util.*;
public class testing {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		HashTableWithSeperateChaining one = new HashTableWithSeperateChaining();
		
		one.toString();
		
		
		//entries works 
		Entry entries = new Entry("alex",23);
		
		//System.out.println(entries.toString());
		
		
		one.put("al", 1);
		one.insert("bl", 2);
		one.insert("dl", 3);
		one.insert("fl", 1);
		one.insert("gl", 1);
		
		
		
		
		System.out.println(one.toString());
		
	}//end main
	
}//end class
