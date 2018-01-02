package labs;

import java.io.FileNotFoundException;

import exceptions.NotEnoughMonsterException;
import units.monster.*;
import utils.sort.BubbleSort;
/**
 * <h1>Lab 5 : Bubble/Selection Sort - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to sort and display monsters.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-20 
 */
public class Lab_5_Program 
{	
	public static void main(String[] args)
	{
		Monsters monsters = null;
		try {
			monsters = new Monsters("MONSTERLIST.txt");
		} catch (FileNotFoundException | NotEnoughMonsterException e) {
			//Nothing
		}
		
		monsters.sort(new BubbleSort<Monster>(), MonsterSort.BY_NAME);
		
		monsters.printList(MonsterPrint.BY_NAME);
	}
		
}