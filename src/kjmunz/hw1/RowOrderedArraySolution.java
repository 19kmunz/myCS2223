package kjmunz.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public RowOrderedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		// FIX ME: complete this method. Currently this causes an exception to be thrown
		int n = this.length();
		int low = 0;
		int high = n-1;

		while (low <= high) {
			int mid = (low+high)/2;

			int rc = inspect(mid, 0) - target;
			if (rc < 0) {
				low = mid+1;
			} else if (rc > 0) {
				high = mid-1;
			} else {
				return new int[] {mid,0};
			}
		}

		if(high == -1){
			return null;
		}

		int r = high;
		low = 1;
		high = r;
		while (low <= high) {
			int mid = (low+high)/2;

			int rc = inspect(r, mid) - target;
			if (rc < 0) {
				low = mid+1;
			} else if (rc > 0) {
				high = mid-1;
			} else {
				return new int[] {r,mid};
			}
		}
		return null; // not found
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
