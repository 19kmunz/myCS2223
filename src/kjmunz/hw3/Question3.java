package kjmunz.hw3;

/**
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {
	
	public static void main(String[] args) throws java.io.IOException {
		
		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST bt = new BST();
		
		System.out.println("Top ten most frequent words");

		// TODO
		
		int n = bt.printUnique();
		System.out.println(n + " unique words.");
		 
	}
	
}
