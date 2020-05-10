package kjmunz.hw4;

import algs.hw4.AVL;
import edu.princeton.cs.algs4.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Modify this class for problem 1 on HW5.
 */
public class WordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
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
	 * Main method to execute.
	 * 
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Use this to contain all four-letter words that you find in dictionary
		AVL<String> avl = new AVL<String>();

		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		int id = 0;
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {
				avl.insert(s);
				table.put(s, id);
				reverse.put(id, s);
				id++;
			}
		}
		sc.close();

		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter. Hint: use the
		// keys() method provided by the AVL tree to iterate over all keys in the graph

		Graph graph = new Graph(id);
		String min = avl.min();
		for (String w1: avl.keys()) {
			for (String w2: avl.keys(min, w1)) { // will do one more check than necessary...
				if(offByOne(w1, w2)){
					graph.addEdge(table.get(w1), table.get(w2));
				}
			}
		}


		StdOut.println("Enter word to start from (all in lower case):");
		String start = StdIn.readString().toLowerCase();
		StdOut.println("Enter word to end at (all in lower case):");
		String end = StdIn.readString().toLowerCase();

		// need to validate that these are both actual four-letter words in the dictionary
		if (!avl.contains(start)) {
			StdOut.println (start + " is not a valid word in the dictionary.");
			System.exit(-1);
		}
		if (!avl.contains(end)) {
			StdOut.println (end + " is not a valid word in the dictionary.");
			System.exit(-1);
		}

		// Once both words are known to exist in the dictionary, then create a search
		// that finds shortest distance (should it exist) between start and end.
		// be sure to output the words in the word ladder, IN ORDER, from the start to end.

		// conduct a BFS over entire graph
		BreadthFirstPaths bfs = new BreadthFirstPaths(graph, table.get(start));
		Iterable<Integer> path = bfs.pathTo(table.get(end));

		// Show path
		if(path != null) {
			for (Integer i : path) {
				StdOut.println(reverse.get(i));
			}
		} else {
			System.out.println("No Word Ladder Exists");
		}
	}
}
