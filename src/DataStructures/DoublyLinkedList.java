package DataStructures;


/**
 * Creates a simple DoublyLinkedList by implementing
 * a SinglyLinkedList.
 * 
 * @author eitan
 *
 * @param <T>
 */

public class DoublyLinkedList<T> implements LinkedList<T> {
	protected int count = 0;
	protected DoublyLinkedListNode<T> head;

	/**
	 * Returns the value of the first
	 * node.
	 * @return T value of first node
	 */
	@Override
	public T getFirst() {
		return head.getValue();
	}

	public DoublyLinkedListNode<T> getFirstNode(){
		return head;
	}

	/**
	 * Returns the last node in the list.
	 * @return the last node in the list.
	 */
	protected DoublyLinkedListNode<T> getLastNode() {
		DoublyLinkedListNode<T> curNode = head;
		while(curNode.getNext() != null) {
			curNode = curNode.getNext();
		}
		return curNode;
	}

	/**
	 * Returns the node at the specified 
	 * index in this list.
	 * @param index
	 * @return the node at the specified index
	 */
	protected DoublyLinkedListNode<T> getNode(int index)  {
		DoublyLinkedListNode<T> curNode = head;
		while(index > 0) { 
			curNode = curNode.getNext();
			index--;
		}
		return curNode;
	}


	/**
	 * Returns the value of the last
	 * node in the doubly linked list.
	 * @return T last node value
	 */
	@Override
	public T getLast() {
		return getLastNode().getValue();
	}

	/**
	 * Adds a single node into
	 * the doubly linked list
	 * from the end.
	 * @param T the value
	 */
	@Override
	public void add(T value) {

		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(value);

		if (size() == 0) {		/* if the doubly linked list is empty */
			head = newNode;
		} else { 				/* if the doubly linked list is not empty */
			DoublyLinkedListNode<T> oldLastNode = getLastNode();
			oldLastNode.setNext(newNode);
			newNode.setNext(null);
			newNode.setPrevious(oldLastNode);
		}

		count++;
	}

	/**
	 * Adds a single node into
	 * the doubly linked list
	 * in at a specific index.
	 * @param int the desited index
	 * @param T the value
	 */
	@Override
	public void add(int index, T value) {
		if (isEmpty()){
			add(value);
		} else {
			if(index == 0) {
				DoublyLinkedListNode<T> oldHead = head;
				head = new DoublyLinkedListNode<T>(value);
				head.setNext(oldHead);
				oldHead.setPrevious(head);
			} else {
				DoublyLinkedListNode<T> prevNode = getNode(index - 1);
				DoublyLinkedListNode<T> nextNode = prevNode.getNext();
				DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(value);

				prevNode.setNext(newNode);
				newNode.setNext(nextNode);
				newNode.setPrevious(prevNode);
				nextNode.setPrevious(newNode);
			}

			/* increasing the number of elements in the doubly linked list */
			count++;
		}
	}

	/**
	 * Get the index of the data in the doubly linked list
	 * @param data
	 * @return
	 */
	public int getIndex(T data) {
		DoublyLinkedListNode<T> node = head;
		for(int i = 0; i < size(); i++) {
			if(node.getValue().equals(data)) {
				return i;
			}else {
				node = node.getNext();
			}
		}
		return -1;
	}
	
	/**
	 * Removing the element from
	 * the doubly linked list depending
	 * on the specified index.
	 * @return T the value
	 * @param int the index
	 */
	@Override
	public T remove(int index) {
		DoublyLinkedListNode<T> removedNode = null;

		/* The first if condition and the next 2 
		 * else-if conditions focus on removing
		 * the head of the doubly linked list
		 * depending on the size of the
		 * dll (doubly linked list)
		 * */
		if((index == 0) && (size() > 1)) {	/* removing head in dll with more than 1 element*/
			removedNode = head;
			head = head.getNext();
			head.setPrevious(null);
			count--;
		} else if ((index == 0) && (size() == 1)) {
			clear();
			return null;
		} else if (isEmpty()) { 			/* removing from empty dll */
			System.out.print("Cannot remove from empty list.");
		} else {
			DoublyLinkedListNode<T> prevNode = getNode(index - 1);
			removedNode = prevNode.getNext();

			if (removedNode.getNext() == null) { /*if removing the last element*/
				prevNode.setNext(null);
			} else {
				DoublyLinkedListNode<T> nextNode = removedNode.getNext(); /*removes node*/
				prevNode.setNext(nextNode);
				nextNode.setPrevious(prevNode);
			}
			count--; /* decreases count because we've removed a node*/
		}		
		return removedNode.getValue();
	}

	/**
	 * Reports the size of
	 * the dll.
	 * @return int the size
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Clears the dll.
	 */
	@Override
	public void clear() {
		head = null;
		count = 0;
	}

	/**
	 * Gets the value of a node
	 * given the index.
	 * 
	 * @return T the value
	 * @param int the index
	 */
	@Override
	public T get(int i) {
		return getNode(i).getValue();
	}

	/**
	 * Reports if the dll is
	 * empty.
	 * @return boolean if empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}


	/**
	 * Converts the value into a 
	 * String for easy comprehension
	 * and testing.
	 * @return String the String
	 */
	@Override
	public String toString() {
		String s = "";

		if (isEmpty()){
			return "";
		} else if (size() == 1) {
			return (String)(head.getValue());
		} else {
			DoublyLinkedListNode<T> currentNode = new DoublyLinkedListNode<T>(null);
			currentNode = head;

			while (currentNode.getNext() != null) {
				s += currentNode.toString() + " -> ";
				currentNode = currentNode.getNext();
			}

			s+= currentNode.toString();
			return s;
		}
	}
}
