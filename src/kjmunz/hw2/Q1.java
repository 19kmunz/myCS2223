package kjmunz.hw2;

import java.util.HashMap;
import java.util.HashSet;

/**
 * For this question, you are to process the book "The Tale of Two Cities" as included in the repository.   
 * 
 * There are 45 chapters in the book, which I have extracted into separate files. I will admit that the  
 * transcription is quite awkward. For example, everything has been converted to lower case, and all punctuation 
 * marks have been removed. Some words are subdivided improperly, but this is what we have to work with!
 * 
 * The questions you are to answer are:
 * 
 * 1. Which chapter contains the most # of words in total, as returned by the TaleOfTwoCitiesExtractor
 * 2. Which chapter (of the 45) contains the most # of unique words? and how many unique words occur in
 *    that chapter.
 * 3. Which two distinct chapters share the most words in common? And how many words is that?
 * 
 * The definition of a word is given to you by the TaleOfTwoCitiesExtractor class, which provides an
 * Iterable interface to a given chapter. This object will return the words in a chapter, one at a time,
 * in the order they appear in the chapter.
 * 
 * Your first responsibility is to ensure that TaleOfTwoCitiesExtractor works in your location.
 */
public class Q1 {
	
	/** Complete this implementation. */
	static void largestChapter() throws java.io.IOException {
		int chapter = 1;
		int max = 0;
		int currentChMax = 0;
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			currentChMax = 0;
			for(String word : extractor){
				currentChMax++;
			}
			if(currentChMax > max){
				chapter = ch;
				max = currentChMax;
			}
		}
		System.out.println(String.format("The chapter with the most number of words is %d with a total of %s", chapter, max));
	}
	
	/** Complete this implementation. */
	static void fewestUniqueWords() throws java.io.IOException {
		int chapter = -1;
		int minUnique = Integer.MAX_VALUE;
		WordList uniqueWords;
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			uniqueWords = new WordList();
			for(String word : extractor){
				uniqueWords.add(word);
			}
			if(uniqueWords.size() < minUnique){
				chapter = ch;
				minUnique = uniqueWords.size();
			}
		}
		System.out.println(String.format("The chapter with the fewest number of unique words is %d with a total of %s", chapter, minUnique));
	}
	
	/** Complete this implementation. */
	static void totalUniqueWords() throws java.io.IOException {
		int totalUnique = 0;
		WordList uniqueWords = new WordList();
		for(int ch = 1; ch <= 45; ch++) {
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(ch);
			for(String word : extractor){
				uniqueWords.add(word);
			}
		}
		totalUnique = uniqueWords.size();
		System.out.println(String.format("There are a total of %d unique words in the book.", totalUnique));
	}
	
	/** Complete this implementation. */
	static void twoChaptersShareMostInCommon() throws java.io.IOException {
		int chapter1 = -1;
		int chapter2 = -1;
		int maxShared = -1;
		WordList[] allChapWordLists = new WordList[45];
		WordList uniqueWordsCurrChap;
		int[] numberSharedWords; // at end of extractor for each loop: numberSharedWords[0] will have word in common between chap 1 and curr chap; numberSharedWords[1]: chap 2 and curr chap, etc.
		for(int currChap = 0; currChap <= 44; currChap++) {
			uniqueWordsCurrChap = new WordList();
			TaleOfTwoCitiesExtractor extractor = new TaleOfTwoCitiesExtractor(currChap+1);
			if(currChap != 0) { numberSharedWords = new int[currChap]; } else { numberSharedWords = new int[1]; }
			for(String word : extractor){
				boolean notInList = uniqueWordsCurrChap.add(word);
				for(int otherChap = 0; currChap != 0 && notInList && otherChap < numberSharedWords.length; otherChap++){
					if(allChapWordLists[otherChap].contains(word)){
						numberSharedWords[otherChap] = numberSharedWords[otherChap] + 1;
					}
				}
			}
			allChapWordLists[currChap] = uniqueWordsCurrChap;
			for(int otherChap = 0; otherChap < numberSharedWords.length; otherChap++){
				if(numberSharedWords[otherChap] > maxShared){
					maxShared = numberSharedWords[otherChap];
					chapter1 = currChap;
					chapter2 = otherChap;
				}
			}
		}
		System.out.println(String.format("The two chapters that share the most words in common are chapters %d and %d with a total of %s words", chapter1, chapter2, maxShared));
	}
	
	/** Leave this method alone. */
	public static void main(String[] args) throws java.io.IOException {
		largestChapter();
		fewestUniqueWords();
		totalUniqueWords();
		twoChaptersShareMostInCommon();
	}
}
