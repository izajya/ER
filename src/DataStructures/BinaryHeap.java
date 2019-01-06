package DataStructures;

/**
 * Created by peter on 9/22/16.
 * 
 * This class implements and defines
 * the methods in interface Heap<T>.
 * This class builds a max heap and
 * defines methods to organize and add
 * to it.
 * This class was modified using
 * Peter's starter code.
 * 
 * @Yvonne
 */

import java.util.Arrays;

public class BinaryHeap< T extends Comparable<T>> implements Heap<T> {
	protected Comparable<T>[] internalArray; // array to hold the heap

	private int heapSize; 	// keeps track of the size of the heap
	private int capacity;	// the capacity of the array

	/**
	 * Constructor.
	 * @param Comparable<T>[] arr the array
	 */
	public BinaryHeap( Comparable<T>[] arr ) {
		// make the array a heap
		internalArray = arr;
		capacity = arr.length;
		heapSize = arr.length;
		
		buildMaxHeap();
	}

	/**
	 * Constructor.
	 * @param int n the capacity
	 * @param T[] arr the array
	 */
	public BinaryHeap(int n) {
		//creates a BinaryHeap of capacity n
		// Starting with the elements in arr
		internalArray = new Comparable[n];
		System.arraycopy(internalArray, 0, internalArray, 0, internalArray.length);
		// make the array a heap.

		capacity = n;
		buildMaxHeap();
	}

	@Override
	/**
	 * Finds the value of a place
	 * within the array given the 
	 * index.
	 * @return Comparable<T> the val
	 * @param int index the index
	 */
	public Comparable getElement(int index){
		// covers a corner case of
		// getting an element that is
		// not within the array by
		// returning null
		if (index > capacity - 1){
			return null;
		}

		// retrieve the element given
		// the index of the array
		return internalArray[index];
	}

	@Override
	/**
	 * Adds a value to the heap by
	 * adding a value to the end 
	 * of the valid heap.
	 * @param T value the value to add
	 */
	public void addElement(T value) {
		// if the user decides to add to
		// an array that has reached its
		// capacity, reset the capacity
		// size by calling setSize()
		if (size() > capacity - 1){
			setSize();
		}

		// set the end of the array to
		// the value in the param
		internalArray[size()] = value;

		// increase the size of the
		// heap because something has
		// been added.
		heapSize++;

		// if the array has more than 1
		// element, call heapIncreaseKey
		// to adjust the array
		if (size() > 1){
			heapIncreaseKey(size() - 1, value);


		}
	}

	@Override
	/**
	 * Returns the location
	 * of the index's left child
	 * @return int index of left child
	 * @param int index of the parent
	 */
	public int leftChild(int parentIndex) {
		// left child is found in the array
		// in relation to the parent as below:
		return 2 * parentIndex + 1;
	}

	@Override
	/**
	 * Returns the location
	 * of the index's right child
	 * @return int index of right child
	 * @param int index of parent
	 */
	public int rightChild(int parentIndex) {
		// right child is found in the
		// array in relation to the parent as below:
		return 2 * parentIndex + 2;
	}

	@Override
	/** 
	 * Returns the location
	 * of a index's parent.
	 * @return int the index of parent
	 * @param int childIndex of interest
	 */
	public int parent(int childIndex) {
		// parent of an element is found
		// in relation to any child as below:
		return (childIndex - 1 ) / 2;
	}

	/**
	 * Returns the capacity
	 * of the array.
	 * @return int the capacity
	 */
	public int capacity(){
		return capacity;
	}

	/**
	 * Returns the size of
	 * the valid heap.
	 * @return int the size
	 */
	public int size() {
		return heapSize;
	}

	/**
	 * Method to swap a parent
	 * and its child if the
	 * child is larger than the
	 * parent.
	 * @param int index the index
	 * @param int secondIdex to swap with
	 */
	public void swap(int index, int secondIndex){
		// a corner case where if the user
		// were to try to swap the value of
		// 2 indexes where 1 index is more
		// than the heap size.
		if (index > size() || secondIndex > size()){
			return;
		}
		// a temporary variable for the second index
		Comparable tempVal = internalArray[secondIndex];

		// switch the values of the smaller
		// and larger indexes
		internalArray[secondIndex] = internalArray[index];
		internalArray[index] = tempVal;
	}

	@Override
	/**
	 * Creates a valid heap and
	 * maxHeapifies it.
	 */
	public void buildMaxHeap() {
		// maxheapify an array by
		// maxheaifying n times where
		// n is 1 less than the size 
		// of the heap.
		for (int i = (int)(size() - 1) / 2; i >= 0; i--){
			maxHeapify(i);
		}
	}

	@Override
	/**
	 * Checks a parent, left child, and 
	 * right child and swaps a child with
	 * its parent if the child is larger than
	 * the parent.
	 * @param int the index of interest
	 */
	public void maxHeapify(int index) {
		// corner case if the user
		// tries to maxheaify an index
		// that is not within the heap size.
		if (index > size()){
			return;
		}
		// while the index is one of the parents
		while (index <= (size() - 1 ) / 2){
			int l = leftChild(index); // the left child of the index
			int r = rightChild(index); 	// have a variable for the right child
			int largest; // some index for the largest

			// if the left child is larger than the index then
			// the left child is the largest value
			if ( l < size() &&
					0 < internalArray[l].compareTo((T) internalArray[index])){
				largest = l;
			} else {
				
				// else the index is largest than its
				// left child, the index is the largest
				largest = index;
			}

			// now compare the largest value to the
			// index's right child:
			if ( r < size() &&
					0 < internalArray[r].compareTo((T) internalArray[largest])){
				largest = r;
			}

			// if the largest is either the left
			// or the right child, swap the child
			// with the (smaller) parent
			if (largest != index){
				swap(index, largest);
				index = largest;
			} else {
				break;
			}
		}

	}

	/**
	 * This method works its way
	 * up the heap to organize the 
	 * elements.
	 * @param Comparable arr the array
	 * @param int index the of interest
	 * @param Comparable key 
	 */
	public void heapIncreaseKey(int index, Comparable key) {
		// if the user tries to use this
		// method on an index that is not
		// within the size of the heap, 
		// return.
		if ( index > size()){
			return;
		}
		
		internalArray[index] = key;		
		maxHeapify(0);
	}

	public int search(Comparable key) {
		for(int i = 0; i < heapSize; i++) {
			if(internalArray[i].equals(key)) {
				return i;
			}			
		}
		return -1;
	}
	
	/**
	 * Removes the root
	 * by switching it with
	 * the last element in the
	 * array.
	 */
	public void extractFirst(){
		// there is no need to extract
		// the root if the array has
		// less than or equal to 1 element
		if (size() < 1){
			return;
		}
		
		// build a valid, organized
		// heap and swap the first
		// and last elements.
		swap(0, size() - 1);
		heapSize--;
		buildMaxHeap();
	}

	@Override
	/**
	 * Reports if the heap is
	 * empty.
	 * @return boolean if heap is empty
	 */
	public boolean isEmpty(){
		// the heap is empty if the
		// heapsize is 0
		return (size() == 0);
	}

	/**
	 * Resets the size of the array
	 * if the user wants to add to an
	 * array that has reached its 
	 * capacity.
	 */
	public void setSize() {
		
		// copy the array but double the capacity
		internalArray = Arrays.copyOf(internalArray, capacity * 2);
		capacity = capacity * 2;
	}

	/**
	 * Sorts the array.
	 */
	public void heapSort(){
		
		// no need to sort if the heapsize
		// is less than or equal to 1.
		if (size() < 1){
			return;
		}

		// keep track of the original size
		// of the array
		int originalSize = size();
		
		// for 1 less the size of the heap,
		// continue to swap the first and last
		// nodes while subtracting from the heapsize.
		// this will order the array.
		for (int i = size() - 1; i > 0; i--){
			extractFirst();
			heapSize--;
			maxHeapify(0);
		}
		
		// reset the heapsize to the
		// original size because the for loop
		// above set the heapsize down to 0.
		heapSize = originalSize;
	}
}
