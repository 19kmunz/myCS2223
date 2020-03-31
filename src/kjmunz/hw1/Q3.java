package kjmunz.hw1;

import algs.hw1.arraysearch.UnknownArraySearch;
import algs.hw1.arraysolution.ImprovedUnknownArraySolution;
import algs.hw1.arraysolution.UnknownArraySolution;

/**
 * You do not need to copy this class or modify this method for this assignment.
 */
public class Q3 {
	
	static long f(int n) {
		// YOUR computation here
		return 0;
	}
	
	static long g(int n) {
		// YOUR computation here
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println("Results from UnknownArraySolution");
		System.out.println("n\tf(n)\tResults");
		for (int n = 3; n <= 13; n++) {
			System.out.print(n + "\t" + f(n) + "\t");
			int[][] ar = UnknownArraySearch.create(n);
			new UnknownArraySolution(ar).trial();
		}

		System.out.println();
		System.out.println("Results from ImprovedUnknownArraySolution");
		System.out.println("n\tg(n)\tResults");
		for (int n = 3; n <= 13; n++) {
			int[][] ar = UnknownArraySearch.create(n);
			System.out.print(n + "\t" + g(n) + "\t");
			ImprovedUnknownArraySolution imp = new ImprovedUnknownArraySolution(ar);
			imp.trial();
			System.out.println("                                                                                       note min=" + 
			                   imp.min + "\tmax = " + imp.max);
		}

	}
}
