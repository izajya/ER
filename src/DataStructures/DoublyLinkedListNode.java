package DataStructures;

/**
 * Creates a simple DoublyLinkedListNode by implementing
 * a SinglyLinkedListNode.
 * @author Yvonne Tran
 *
 * @param <T>
 */
public class DoublyLinkedListNode<T> implements LinkedListNode<T> {

	protected T value;
	protected DoublyLinkedListNode<T> next;
	protected DoublyLinkedListNode<T> previous;
	
	/**
	 * Constructs a DoublyLinkedListNode with the specified value.
	 * @param value
	 */
	public DoublyLinkedListNode(T value) {
		this.value = value;
	}
	
	/**
	 * Gets the value of the node.
	 * @return T the value
	 */
	@Override
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value for a node.
	 * @param T the value
	 */
	@Override
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Returns the next node.
	 * @return the next node.
	 */
	public DoublyLinkedListNode<T> getNext() {
		return next;
	}
	
	/**
	 * Returns the previous node.
	 * @return the previous node.
	 */
	public DoublyLinkedListNode<T> getPrevious(){
		return previous;
	}

	/**
	 * Sets next to the specified node.
	 * @param node
	 */
	public void setNext(DoublyLinkedListNode<T> node) {
		this.next =  node;
	}
	
	/**
	 * Sets next to the specified node.
	 * @param node
	 */
	public void setPrevious(DoublyLinkedListNode<T> node) {
		this.previous =  node;
	}
	
	/**
	 * Converts the value into a 
	 * String for easy comprehension
	 * and testing.
	 * @return String the String
	 */
	public String toString(){
		return value.toString();
	}
}
