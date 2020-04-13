package kjmunz.hw2;

import algs.days.day05.FixedCapacityQueue;

import javax.swing.*;

/**
 * N people go to a Casino and everyone wins a little bit of money. You suggest it would be 
 * better if just one person collected all the winnings and you propose the following strategy.
 * 
 * 1. You tell everyone to stand in a circle (yourself included)
 * 2. You ask someone to volunteer to be the start. That is person #1. In clockwise fashion,
 *    everyone in the circle is assigned a number from 1 to N.
 * 3. You pick a number 0 < k < N.
 * 
 * Starting with the starting person, count k people clockwise. The kth person leaves the circle
 * which shrinks by one in size; starting with the next person, again count k people clockwise
 * and that person leaves the circle.
 * 
 * The last one remaining collects all winnings.
 * 
 * Your program must produce a FixedCapacityQueue<Integer> reflecting the order in which people
 * are eliminated. The final item in the queue is the person who collects all winnings.
 * 
 * Your implementation MUST create a Linked List using the Node class.
 * 
 * For new Selection(5, 3).countOff(), the resulting queue should be [3 1 5 2 4] where last person to receive
 * winnings is person #4.
 * 
 * For new Selection(17, 7).countOff(), the resulting queue should be [7 14 4 12 3 13 6 17 11 9 8 10 16 5 15 1 2] where
 * last person to receive winnings is person #2.
 */
public class Selection {
	final int N;      /* Number of people. */
	final int k;      /* Delta to counting. */
	Node firstPerson;
	
	/** Construct an instance of the problem with N people choosing by k. */
	public Selection(int N, int k) {
		this.N = N;
		this.k = k;

		firstPerson = new Node(1);
		Node currNode = firstPerson;
		for(int i = 2; i <= N; i++){
			currNode.next = new Node(i);
			currNode = currNode.next;
		}
	}
	
	/** Use this node to form the linked list. */
	class Node {
		int person;
		Node next;
		
		public Node(int person) {
			this.person = person;
		}
	}
	
	/**
	 * Method consumes the elements of the queue and outputs them with spaces between elements.
	 * 
	 * No need to worry about a trailing space.
	 * @param result
	 */
	static void output(FixedCapacityQueue<Integer> result) {
		StringBuilder queueBuild = new StringBuilder();
		while(!result.isEmpty()){
			queueBuild.append(result.dequeue()).append(" ");
		}
		System.out.println(queueBuild.toString());
	}
	
	/**
	 * Key implementation for this assignment is to return a queue that contains the person identifiers (integers)
	 * in the order that they were removed. The very last value is the "last man standing" who claims the full lottery winnings.
	 */
	FixedCapacityQueue<Integer> countOff() {
		FixedCapacityQueue<Integer> result = new FixedCapacityQueue<Integer>(N);

		Node previousNode = null;
		Node currNode = firstPerson;
		while(firstPerson.next != null) {
			for (int i = 1; i < k; i++) {
				previousNode = currNode;
				if (currNode.next == null) {
					currNode = firstPerson;
				} else {
					currNode = currNode.next;
				}
			}
			result.enqueue(currNode.person);
			/*System.out.print("BEFORE: ");
			Node printingNode = firstPerson;
			while(printingNode != null){
				System.out.print(printingNode.person + " ");
				printingNode = printingNode.next;
			}
			System.out.println();*/
			if(currNode == firstPerson){
				firstPerson = firstPerson.next;
			} else {
				previousNode.next = currNode.next;
			}
			if (currNode.next == null) {
				currNode = firstPerson;
			} else {
				currNode = currNode.next;
			}
			/*
			System.out.print("AFTER: ");
			printingNode = firstPerson;
			while(printingNode != null){
				System.out.print(printingNode.person + " ");
				printingNode = printingNode.next;
			}
			System.out.println();*/
		}
		result.enqueue(firstPerson.person);
		
		return result;
	}

	/** Launch the small examples to demonstrate success. */
	public static void main(String[] args) {
		FixedCapacityQueue<Integer> result = new Selection(5, 3).countOff();
		System.out.println("N=5, k=3 should be [3 1 5 2 4]");
		output(result);
		System.out.println();
		result = new Selection(17, 7).countOff();
		System.out.println("N=17, k=7 should be [7 14 4 12 3 13 6 17 11 9 8 10 16 5 15 1 2]");
		output(result);
	}
}
