package labs;
import units.Fighter;
import units.monster.Monster;

/**
 * <h1>Lab 7 : Inheritance - Main Class File</h1>
 * This is the main program file which creates a monster and a fighter.
 * <p>
 * The fighter attack the monster and visa versa.
 * <p>
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-2017 
 */
public class Lab_7_Program 
{	
	public static void main(String[] args) 
	{
		Monster monster = new Monster("Henry", 100.0f, 10, 10);
		//uses default values.
		Fighter fighter = new Fighter();
		
		fighter.print();
		monster.print();
		
		fighter.attack(monster);
		
		fighter.print();
		monster.print();
		
		monster.attack(fighter);
		
		fighter.print();
		monster.print();
		
	}

}
