package kjmunz.hw4;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.File;
import java.util.Scanner;

public class LongestWordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();

	public static void main(String[] args) throws java.io.IOException {

		// Use this to contain all four-letter words that you find in dictionary
		AVL<String> avl = new AVL<String>();

		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {

				// fill in here...
			}
		}
		sc.close();

		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter. Use EdgeWeightedGraph
		// Because you can run DijkstraUndirectedSP on it. Hint: use the
		// keys() method provided by the AVL tree to iterate over all keys in the graph
		
		EdgeWeightedGraph graph = new EdgeWeightedGraph(table.size());

		// fill in here...
		
		double max = 0;
		int maxu = 0;
		int maxv = 0;

		// Find largest non-infinite distance between any two vertices.
		
		System.out.println("longest WordLadder from " + reverse.get(maxu) + " to " + reverse.get(maxv) + " in " + max + " steps.");
		System.out.println("No go back and use WordLadder to recover this.");
	}
}
