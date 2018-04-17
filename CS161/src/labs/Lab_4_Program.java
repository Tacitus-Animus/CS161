package labs;

import java.io.FileNotFoundException;

import units.monster.Monster;
import units.monster.Monsters;
/**
 * <h1>Lab 4 : File IO, ArrayList - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to save/load monsters to/from file...
 * <p>
 * and display and attack specific Monster Object Info in the Monster List.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-20 
 */
public class Lab_4_Program 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Monsters monsters = null;
		
			monsters = new Monsters("MONSTERLIST.txt");
		

		monsters.displayMonsterInfo("Slime");
		
		monsters.attackMonster("Slime", 10);
		
		monsters.saveToFile("SAVED.txt");
		
	
			monsters.loadFromFile("SAVED.txt");
		
		
		monsters.displayMonsterInfo("Slime");
		
	}
	
}
