package exceptions;

/**
 * <h1>Lab 9 : NotEnoughMonsterException Class File</h1>
 * Thrown when monster data file has less than 10 monster data lines.
 * @author Alex Paul
 * @version CS161
 * @since 06-NOV-2017 
 */

public class NotEnoughMonsterException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughMonsterException() {
		super("Not enough Monsters in Data File!");	}
	
}
