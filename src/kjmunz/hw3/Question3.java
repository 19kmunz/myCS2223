package kjmunz.hw3;

import kjmunz.hw2.TaleOfTwoCitiesExtractor;

/**
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {
	
	public static void main(String[] args) throws java.io.IOException {
		
		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST bt = new BST();
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			for(String word : extractor){
				Integer wCount = bt.get(word);
				int count = (wCount == null) ? 1 : wCount + 1;
				bt.put(word, count);
			}
		}
		
		System.out.println("Top ten most frequent words");

		// TODO
		
		int n = bt.printUnique();
		System.out.println(n + " unique words.");
		 
	}
	
}
