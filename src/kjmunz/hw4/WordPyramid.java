package kjmunz.hw4;

import algs.hw4.AVL;
import edu.princeton.cs.algs4.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Create 10 AVL trees, one for each size.
 
processing 1
processing 2
processing 3
processing 4
processing 5
processing 6
processing 7
Total Time:266.861
Enter word to start from (or press return to stop program.):
relapse
relapse
serape
spear
spar
spa
pa
a
 
 */
public class WordPyramid {
	static int MAX = 7;
	
	static Digraph graph;
	
	@SuppressWarnings("unchecked")
	static AVL<String>[] wordTrees = (AVL<String>[]) new AVL[MAX+1]; // 1 .. 10 and burn 0
	
	/**
	 * Represent the mapping of (uniqueID,word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();
	
	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		boolean foundOff = false;
		for(int i = 0; i < w1.length(); i++) {
			if(w1.charAt(i) != w2.charAt(i)){
				if(foundOff){
					return false;
				}
				foundOff = true;
			}
		}
		return foundOff;
	}
	
	/** 
	 * For bonus question.
	 * 
	 * Given a string of four-letters, determine if the five letter words contains
	 * each of these four letters distinctly in any arrangement.
	 * 
	 * Thus anagramPlusOne ("this", "shirt") is true, and anagramPlusOne("seas", "tasse") is true, but 
	 * anagramPlusOne ("meet", "tempt") is not because "tempt" has only one "e" while "meet" had two 
	 */
	static boolean anagramPlusOne(String word, String wordPlus) {
		if(Math.abs(word.length() - wordPlus.length()) == 1){
			// Map all the letters
			HashMap<Character,Integer> wordMap = new HashMap<Character,Integer>();
			HashMap<Character,Integer> wordPlusMap = new HashMap<Character,Integer>();
			for(int i = 0; i < word.length(); i++){
				wordMap.put(word.charAt(i), (wordMap.get(word.charAt(i)) != null) ? wordMap.get(word.charAt(i)) + 1 : 1);
			}
			for(int i = 0; i < wordPlus.length(); i++){
				wordPlusMap.put(wordPlus.charAt(i), (wordPlusMap.get(wordPlus.charAt(i)) != null) ? wordPlusMap.get(wordPlus.charAt(i)) + 1 : 1);
			}
			// check if the maps are the same except for one
			boolean foundOff = false;
			for(Character c : wordPlusMap.keySet()){ // for all the unique letters in the larger word
				if(!wordPlusMap.get(c).equals(wordMap.get(c))){ // if they are are not equal to the letters in the shorter word
					if(!foundOff && Math.abs(
							((wordPlusMap.get(c) == null) ? 0 : wordPlusMap.get(c)) -
							((wordMap.get(c) == null) ? 0 : wordMap.get(c)))
							== 1){ // make sure the difference between the two is one
						if(foundOff){ // if youve already found one with one off
							return false; // return false
						}
						foundOff = true; // if you havent, now you have
					} else {
						return false;
					}
				}
			}
			return foundOff; // if foundOff is false it was the same word so return false. otherwise foundOff should be true so return true
		}
		return false;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println ("STARTING! Takes around 400s to process on kjmunz's machine");
		// Create AVL trees
		for (int i = 1; i <= MAX; i++) {
			wordTrees[i] = new AVL<String>();
		}
		
		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		Stopwatch sw = new Stopwatch();
		Scanner sc = new Scanner(new File ("words.english.txt"));
		int total = 1;
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() <= MAX && s.length() >= 1) {
				wordTrees[s.length()].insert(s);
				table.put(s, total);
				reverse.put(total, s);
				total++;
			}
		}
		sc.close();
		
		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter.
		//total = total + 1; // reserve space for terminal one.
		graph = new Digraph(total);
		for(int i = 2; i <= MAX; i++){
			for(String longWord : wordTrees[i].keys()){
				for(String shortWord : wordTrees[i-1].keys()){
					if(anagramPlusOne(shortWord, longWord)){
						graph.addEdge(table.get(longWord), table.get(shortWord));
					}
				}
			}
		}
		
		// all back to special one. 
		for (String w1: wordTrees[1].keys()) {
			graph.addEdge( table.get(w1), 0);
		}
		table.put("", 0);
		reverse.put(0, "");
		
		System.out.printf("Total Time:%.3f\n", sw.elapsedTime());
		
		while (true) {
			StdOut.println("Enter word to start from (or press return to stop program):");
			String start = StdIn.readLine().toLowerCase();
			
			if (start.length() == 0) {
				break;
			}
			
			int len = start.length();
			if (!wordTrees[len].contains(start)) {
				System.out.println (start + "is an invalid word to start from.");
				continue;
			}

			// conduct a BFS over entire graph
			BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(graph, table.get(start));
			Iterable<Integer> path = bfs.pathTo(table.get(""));

			// Show path
			if(path != null) {
				for (Integer i : path) {
					StdOut.println(reverse.get(i));
				}
			} else {
				System.out.println("No Word Pyramid Exists");
			}

		}
	}
}
