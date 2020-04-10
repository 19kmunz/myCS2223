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
		WordSymbolTable elements = new WordSymbolTable();
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			for(String word : extractor){
				elements.increment(word);
			}
		}
		int totalCounts = elements.totalCounts();
		String mostFreq = elements.mostFrequent();
		int mostFreqCount = elements.count(mostFreq);
		double percentOfOne = (((double) mostFreqCount/ (double) totalCounts) * 10.0);
		int totalOfTen = mostFreqCount;
		System.out.println(
				String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
						mostFreq, mostFreqCount, totalCounts, percentOfOne));

		System.out.println("The Top Ten words by frequency are:");
		for(int i = 1; i <= 10; i++) {
			System.out.println(String.format("%2d. %s (%d)", i, mostFreq, mostFreqCount));
			elements.remove(mostFreq);
			mostFreq = elements.mostFrequent();
			mostFreqCount = elements.count(mostFreq);
			totalOfTen += mostFreqCount;
		}


		System.out.println(String.format("These ten words represent %.2f%% of the total words in the book", (((double) totalOfTen/ (double) totalCounts)) * 10.0));

	}

	static void wordsUsedOnce() throws java.io.IOException {
		int numSingle = -1;
		String longest = "";
		WordSymbolTable firstTime = new WordSymbolTable();
		WordSymbolTable elements = new WordSymbolTable();
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			for(String word : extractor){
				boolean notAlreadyIn = elements.increment(word);
				if(notAlreadyIn){
					firstTime.increment(word);
				} else {
					firstTime.remove(word);
				}
			}
		}
		numSingle = firstTime.size();
		longest = firstTime.mostFrequent(); // i made it so if the frequency is the same, prioritize by word length! Line 195 of WordSymbolTable
		
		System.out.println(String.format("%d words are used exactly once (longest is \"%s\")", numSingle, longest));
	}

	public static void main(String[] args) throws java.io.IOException {
		mostFrequent();
		wordsUsedOnce();
	}
}
