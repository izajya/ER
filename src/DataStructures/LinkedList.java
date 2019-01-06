package DataStructures;

/**
 *  This is a common interface for linked lists (both single and doubly linked).
 * 
 * @author eitan
 * @param <T>
 */
public interface LinkedList<T> {

	
	/**
	 * Returns the first element in this list.
	 * 
	 * @return the first element in this list.
	 */
	public T getFirst();
	
	
	/**
	 * Returns the last element in this list.
	 * 
	 * @return the last element in this list.
	 */
	public T getLast();
	
	
	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param element
	 */
	public void add(T element);

	
	/**
	 * Inserts the specified element at the specified position in this list. 
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) ;

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 * @return the element at the specified position in this list.
	 */
	public T get(int index) ;
	
	
	/**
	 * Removes the element at the specified position in this list. 
	 * Shifts any subsequent elements to the left (subtracts one from their indices).
	 * 
	 * @param index
	 * @return
	 */
	public T remove(int index) ;
	
	/**
	 * Returns true if this list contains no elements.
	 * 
	 * @return true if this list contains no elements.
	 */
	public boolean isEmpty();

	
	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list.
	 */	
	public int size();
	
	
	/**
	 * Removes all of the elements from this list. The list will be empty after this call returns.
	 * 
	 */
	public void clear();
	
	
	/**
	 * Returns a String representation of this node.
	 */
	public String toString();

}
