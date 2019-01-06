package DataStructures;

/**
 * Created by peter on 9/22/16.
 * 
 * This class has methods that will
 * be further defined in BinaryHeap.
 * 
 * This class was modified from Peter's
 * starter code.
 * 
 * @Yvonne
 */
public interface Heap<T extends Comparable<T>> {

	/**
	 * Gets an element in the array
	 * @param int index of interest
	 * @return Comparable<T> the value
	 */
	public Comparable<T> getElement(int index);
	
	/**
	 * Adds an element to the heap
	 * @param T value the value
	 */
	public void addElement(T value);
	
	/**
	 * Returns the index of an index's
	 * left child
	 * @param int index of interest (the parent)
	 * @return int left child index
	 */
	public int leftChild(int parentIndex);
	
	/**
	 * Returns the index of an index's
	 * right child
	 * @param int index of interest (the parent)
	 * @return int right child index
	 */
	public int rightChild(int parentIndex);
	
	/**
	 * Returns the index of an index's
	 * parent
	 * @param int index of interest (a child)
	 * @return int the parent index
	 */
	public int parent(int childIndex);
	
	/**
	 * Produces a max-heap from an
	 * unordered input array.
	 */
	public void buildMaxHeap();
	
	/**
	 * Organizes a subtree so that
	 * the parent is greater than
	 * the children.
	 * @param int the parent index
	 */
	public void maxHeapify(int index);
	
	/**
	 * Reports if the heap is empty.
	 * @return boolean if heap is empty
	 */
	public boolean isEmpty();

}
