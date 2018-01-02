package exceptions;

/**
 * <h1>Lab 9 : DeadFighterIsDeadException Class File</h1>
 * Thrown when fighter is dead.
 * @author Alex Paul
 * @version CS161
 * @since 06-NOV-2017 
 */

public class DeadFighterIsDeadException extends Exception {

	private static final long serialVersionUID = 1L;

	public DeadFighterIsDeadException() {
		super("Fighter is Dead!");
	}
	
}
