package kjmunz.hw2;

/**
 * Building from Question 1, there are different questions to answer.
 * 
 * 1. What is the most frequently used word in the entire book?
 * 2. What are the top-ten most frequently used words in the entire book?
 * 3. How many words occur exactly once in the book?
 */
public class Q2 {

	static void mostFrequent() throws java.io.IOException {
		// TODO CHANGE TO MAKE THIS WORK...
		
		System.out.println(
				String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
						"NOTHING", 1, 1000, .001));

		System.out.println("The Top Ten words by frequency are:");
		System.out.println(String.format("%2d. %s (%d)", 1, "NOTHING", 1));
		

		System.out.println(String.format("These ten words represent %.2f%% of the total words in the book", 99.99));

	}

	static void wordsUsedOnce() throws java.io.IOException {
		int numSingle = -1;
		int longest = 999;
		
		// TODO
		
		System.out.println(String.format("%d words are used exactly once (longest is \"%s\")", numSingle, longest));
	}

	public static void main(String[] args) throws java.io.IOException {
		mostFrequent();
		wordsUsedOnce();
	}
}
