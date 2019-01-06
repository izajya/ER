package DataStructures;

/**
 * This class implements and defines
 * the methods in interface PriorityQueue<T>.
 * This class creates a priority queue
 * with the help of methods from the
 * BinaryHeap class.
 * 
 * @Yvonne
 */

public class PriorityQueueBH< T extends Comparable<T>> implements PriorityQueue<T> {

	BinaryHeap<T> heap;
	
	/**
	 * Constructor
	 * @param int the capacity
	 */
	public PriorityQueueBH(int capacity){
		// calls the constructor of BinaryHeap
		// which calls buildMaxHeap() immediately
		// so the priority queue will be organized
		// from the very start.
		heap = new BinaryHeap(capacity);
	}
	
	/**
	 * Constructor
	 * @param Comparable[] the array passed in.
	 */
	public PriorityQueueBH(Comparable[] arr){
		heap = new BinaryHeap(arr);
	}
	
	
	@Override
	/**
	 * Inserts an element into the
	 * priority queue.
	 * @param T element the added element
	 */
	public void insert(T element) {
		// Again, this method calls the
		// addElement() method created 
		// in the BinaryHeap class.
		heap.addElement(element);
	}

	@Override
	/**
	 * Reports the maximum value
	 * of the priority queue, which
	 * is also just the root.
	 * @return T the maximum value
	 */
	public T maximum() {
		// Reporting the value of
		// the root.
		return (T) heap.getElement(0);
	}

	@Override
	/**
	 * Extracts the max element
	 * (the root) of the priority 
	 * queue.
	 * @return T extracted element
	 */
	public T extractMax() {
		// First extract the max and store that
		// information in a local variable
		T max = maximum();
		
		// extract the first node. The heap
		// then decreases the heapSize and 
		// builds a max heap again.
		heap.extractFirst();
		
		// returns the original root
		return max;
	}

	@Override
	/**
	 * Increases the value of an element
	 * in the priority queue.
	 * @param int index of interest
	 * @param T increased value
	 */
	public void increaseKey(int index, T element) {
		heap.heapIncreaseKey(index, element);
	}
	
	public int getIndex(T key) {
		return heap.search(key);
	}
	
	/**
	 * Gets the element
	 * @param int index of interest
	 * @return Comparable the element value
	 */
	public Comparable getElement(int index){
		return heap.getElement(index);
	}
	
	public int getSize() {
		return heap.size();
	}
}
