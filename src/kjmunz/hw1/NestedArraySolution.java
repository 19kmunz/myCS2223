package kjmunz.hw1;

import algs.hw1.arraysearch.NestedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class NestedArraySolution extends NestedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public NestedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		int k = 1 + ((n-1)/3);
		int low = 0;
		int high = k-1;

		while (low <= high) {
			int mid = (low+high)/2;

			int rc = inspect(mid * 2, mid) - target;
			if (rc < 0) {
				low = mid+1;
			} else if (rc > 0) {
				high = mid-1;
			} else {
				return new int[] {mid * 2, mid};
			}
		}

		if(high == -1){
			return null;
		}

		int t = high;
		//int numInd = 3 * (n - ((3*t)+1));
		int size = (n - ((3*t)+1))+1;

		int vertexOne = inspect((t * 2) + (size - 1), t + (size - 1)) - target; // location of diagonal corner
		if(vertexOne > 0){ // in diagonal
			int r = high;
			low = 1;
			high = size - 2;
			while (low <= high) {
				int mid = (low+high)/2;

				int rc = inspect(mid + (t * 2), mid + t) - target;
				if (rc < 0) {
					low = mid+1;
				} else if (rc > 0) {
					high = mid-1;
				} else {
					return new int[] {mid + (t * 2), mid + t};
				}
			}
		} else if(vertexOne < 0) {
			int vertexTwo = inspect((t * 2) + (size - 1), t) - target;
			if(vertexTwo > 0){ // in horizontal
				int r = t * 2 + (size - 1);
				low = t + (size - 2);
				high = t + 1;
				while (low >= high) {
					int mid = (low+high)/2;

					int rc = inspect(r, mid) - target;
					if (rc < 0) {
						low = mid-1;
					} else if (rc > 0) {
						high = mid+1;
					} else {
						return new int[] {r,mid};
					}
				}
			} else if(vertexTwo < 0){ // in vertical
				int c = t;
				low = (t * 2) + (size - 2);
				high = (t * 2) + 1;
				while (low >= high) {
					int mid = (low+high)/2;

					int rc = inspect(mid, c) - target;
					if (rc < 0) {
						low = mid-1;
					} else if (rc > 0) {
						high = mid+1;
					} else {
						return new int[] {mid,c};
					}
				}
			} else {
				return new int[] {(t * 2) + (size - 1), t};
			}
		} else {
			return new int[] {(t * 2) + (size - 1), t + (size - 1)};
		}

		return null; // not found
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = NestedArraySearch.create(13);
		new NestedArraySolution(ar).trial();
	}
}
