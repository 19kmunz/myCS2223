package kjmunz.hw3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {
		System.out.println("N	MaxComp		MaxExch");
		Double[] a;
		for(int N = 16; N <= 512; N *= 2){
			a = new Double[N];
			double maxLessCount = 0;
			double maxExchCount = 0;
			for(int i = 0; i <= 100; i++) {
				for (int j = 0; j < N; j++) {
					a[j] = StdRandom.uniform(0.0, 1.0);
				}
				Heap.constructHeap(a);
				maxLessCount = Math.max(maxLessCount, Heap.lessCount);
				maxExchCount = Math.max(maxExchCount, Heap.exchCount);
			}
			System.out.println(N + "	" + maxLessCount + "		" + maxExchCount);
		}
			// for N in 16 .. 512

		//   for each N, do T=100 trials you want to keep track of
		//       what you believe to be the MOST number of exch invocations
		//       and most number of less invocations
		
		//       compute a random array of N uniform doubles
		
		//   Make sure you output for each N the maximum values you saw
		//   in a table like...
		//
		//       N	 MaxComp	MaxExch
		//       16  22         8
		//     .....
	}
}
