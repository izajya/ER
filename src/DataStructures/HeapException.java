package DataStructures;

/**
 * Handle runtime exception for binary heap
 * 
 * @author Ching Ching Huang
 *
 */
public class HeapException extends RuntimeException {

	/**
	 * Constructor takes in a string to pop up when error appears
	 * 
	 * @param error
	 */
	public HeapException(String error) {
		super(error);
	}
}
