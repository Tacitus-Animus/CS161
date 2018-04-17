package labs;

import java.io.FileNotFoundException;

import units.monster.Monster;
import units.monster.Monsters;
/**
 * <h1>Lab 3 : File IO, Arrays - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses that data to create a Monster[] with instantiated Monster Objects using said data.
 * <p>
 * Then, loops to get user input to change, attack, or display specific Monster Object Info in the Monster[].
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-17 
 */
public class Lab_3_Program 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		Monsters monsters = null;
		
			monsters = new Monsters("MONSTERLIST.txt");
		
		
		monsters.changeMonsterInfo("Slime", "Avatar", 1000.5f, 500, 7777);

		monsters.attackMonster("Avatar", 500.3f);
		
		monsters.displayMonsterInfo("Avatar");
	}
}