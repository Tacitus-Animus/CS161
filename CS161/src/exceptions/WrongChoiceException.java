package exceptions;

/**
 * <h1>Lab 9 : WrongChoiceException Class File</h1>
 * Thrown when user puts in wrong input.
 * @author Alex Paul
 * @version CS161
 * @since 06-NOV-2017 
 */

public class WrongChoiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongChoiceException() {
		super("Wrong choice!");
	}
	
}
