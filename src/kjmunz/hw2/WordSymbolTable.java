package kjmunz.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 *
 * This class will be used by Question Q2 on Homework2.
 */
public class WordSymbolTable {

	/**
	 * Leave this Node class as is. While you don't need to make changes to this class,
	 * it is acceptable if you would like to add methods to this class.
	 */
	static class Node {
		String     word;
		int        count;
		Node       next;

		Node(String w, int count) {
			this.word = w;
			this.count = count;
		}
	}
	private Node first = null;
	private Node last = null;
	private int size = 0;
	private int totalCounts = 0;
	private Node mostFreq;

	/**
	 * Increase the count for given word.
	 *
	 * Note: this might need to add the word in the first place.
	 *
	 * Returns TRUE if the word was newly added, otherwise FALSE
	 *
	 * @param elt      element whose count has increased by 1.
	 */
	public boolean increment(String elt) {
		if(size == 0){
			size++;
			totalCounts++;
			first = new Node(elt, 1);
			last = first;
			return true;
		}
		Node currNode = first;
		while(currNode != null){
			if(currNode.word.equals(elt)){
				currNode.count++;
				totalCounts++;
				return false;
			}
			currNode = currNode.next;
		}
		// did not find word in list, add it to the end
		last.next = new Node(elt, 1);
		last = last.next;
		size++;
		totalCounts++;
		return true;
	}

	/**
	 * Decrease the count for given word.
	 *
	 * Note: this might need to remove the word once the count reaches zero.
	 * Returns TRUE if the word was removed, otherwise FALSE
	 *
	 * @param elt      element whose count is to decrease by 1.
	 */
	public boolean decrement(String elt) {
		if(size == 0){
			return false;
		}
		if(size == 1) {
			if (first.word.equals(elt)) {
				if(totalCounts == 1) {
					first = null;
					last = null;
					size--;
					totalCounts--;
				} else {
					first = first.next;
					last.count--;
					totalCounts--;
				}
				return true;
			}
			return false;
		}
		Node previousNode = first;
		Node currNode = first.next;
		while(currNode != null){
			if(currNode.word.equals(elt)){
				currNode.count--;
				totalCounts--;
				if(currNode.count == 0){
					previousNode.next = currNode.next;
					size--;
				}
				return true;
			}
			previousNode = currNode;
			currNode = currNode.next;
		}
		return false;
	}

	/** Return number of words in the symbol table. */
	public int size() {
		return size;
	}

	/** Return the accumulated counts of all words in the word table. */
	public int totalCounts() {
		return totalCounts;
	}

	/** Remove entire word from the word table. */
	public boolean remove (String elt) {
		if(size == 0){
			return false;
		}
		if(first.word.equals(elt)){
			totalCounts -= first.count;
			first = first.next;
			size--;
			return true;
		}
		Node previousNode = first;
		Node currNode = first.next;
		while(currNode != null){
			if(currNode.word.equals(elt)){
				totalCounts -= currNode.count;
				previousNode.next = currNode.next;
				if(currNode == last){
					last = previousNode;
				}
				size--;
			}
			previousNode = currNode;
			currNode = currNode.next;
		}
		return false;
	}

	/**
	 * Returns true if word exists in the WordSymbolTable; false otherwise.
	 *
	 * @param elt      target element to seek.
	 */
	public boolean contains(String elt) {
		Node currNode = first;
		if(size() == 0){
			return false;
		}
		while(currNode.next != null){
			if(currNode.word.equals(elt)){
				return true;
			}
			currNode=currNode.next;
		}
		return currNode.word.equals(elt);
	}

	/**
	 * Returns the count for the word (or 0 if the word doesn't exist in the symbol table).
	 *
	 * @param elt      target element to seek.
	 */
	public int count(String elt) {
		if(mostFreq != null && mostFreq.word.equals(elt)){ return mostFreq.count; }
		Node currNode = first;
		while(currNode != null){
			if(currNode.word.equals(elt)){
				return currNode.count;
			}
			currNode = currNode.next;
		}
		return 0;
	}

	/**
	 * Return a word whose repetition count is equal to the maximum in the Symbol table.
	 *
	 * Note that there may be multiple words that have the maximum count, so this method
	 * only needs to return one of them.
	 */
	public String mostFrequent() {
		Node mostFrequent = first;
		Node currNode = first.next;
		while(currNode != null){
			if(currNode.count > mostFrequent.count ||
					(currNode.count == mostFrequent.count && currNode.word.length() > mostFrequent.word.length())){
				mostFrequent = currNode;
			}
			currNode = currNode.next;
		}
		this.mostFreq = mostFrequent;
		return mostFrequent.word;
	}


	/** For debugging, return semicolon-separated string of (word,count) pairs. */
	public String elements() {
		StringBuilder elements = new StringBuilder();
		if(size() == 0){
			return "";
		}
		Node currNode = first;
		while(currNode.next != null){
			elements.append("(").append(currNode.word).append(",").append(currNode.count).append("); ");
			currNode = currNode.next;
		}
		elements.append("(").append(currNode.word).append(",").append(currNode.count).append(")");
		return elements.toString();
	}


	// you should not have to modify anything below. These are testing routines for you to check your work.
	// ----------------------------------------------------------------------------------------------------
	static void validate(Object o1, Object o2) {
		if (o1.equals(o2)) { return; }
		throw new RuntimeException(o1 + " doesn't equal " + o2);
	}

	// Once you have completed the implementation, you should be able to run this method and have
	// it complete without any runtime exceptions. While not an exhaustive test, this should be
	// sufficient to help you uncover many of the boundary cases.
	public static void main(String[] args) {

		WordSymbolTable wl = new WordSymbolTable();
		validate(0, wl.size());
		validate("", wl.elements());           // empty word list must return ""
		validate(0, wl.count("nothing"));
		validate(false, wl.contains("this"));
		validate(true, wl.increment("test"));
		validate("(test,1)", wl.elements());       // no trailing or pre comma.
		validate(1, wl.count("test"));
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(1, wl.count("test"));
		validate(false, wl.increment("test"));       // when add TWICE, false is returned
		validate(2, wl.count("test"));

		validate(true, wl.remove("test"));     // can remove first element
		validate(false, wl.remove("test"));    // can't remove first empty
		validate(true, wl.increment("test"));
		validate(true, wl.increment("that"));
		validate(1, wl.count("test"));
		validate(false, wl.increment("that"));
		validate(2, wl.count("that"));
		validate(3, wl.totalCounts());
		validate(2, wl.size());
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("(that,2)", wl.elements());       // no trailing or pre comma.
		validate(true, wl.remove("that"));
	}
}
