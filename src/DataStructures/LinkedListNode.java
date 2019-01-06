package DataStructures;

/**
 * This is a common interface for implementing nodes in linked lists (both single and doubly linked).
 * A LinkedListNode has a value that can be get and set.
 * 
 * @author eitan
 *
 * @param <T>
 */
public interface LinkedListNode<T> {
	
	
	/**
	 * Returns the value stored in the LinkedListNode.
	 * 
	 * @return the value stored in the LinkedListNode
	 */
	public T getValue();
	
	/**
	 * Sets the value of the node to the specified value.
	 * 
	 * @param value
	 */
	public void setValue(T value);
		
}
