package DataStructures;


public interface PriorityQueue <T extends Comparable<T>>{
	
	
	
	/**
	 * Inserts a new element
	 * @param T the element
	 */
	public void insert(T element);
	
	/**
	 * Returns the max element
	 * (like peek.)
	 * @return T the max element
	 */
	public T maximum();
	
	/**
	 * Removes and returns max
	 * element.
	 * @return T the max element
	 */
	public T extractMax();
	
	/**
	 * Sets the element at specified
	 * index to new elements. Fixes
	 * the Priority Queue (through swaps)
	 * to move element to the correct
	 * position.
	 */
	public void increaseKey(int index, T element);
}
