package labs;

import java.io.FileNotFoundException;

import units.monster.Monster;
import units.monster.MonsterPrint;
import units.monster.MonsterSearch;
import units.monster.MonsterSort;
import units.monster.Monsters;
import utils.search.BinarySearch;
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
	public static void main(String[] args) throws FileNotFoundException
	{
		Monsters monsters = new Monsters("MONSTERLIST.txt");
		
		monsters.sort(new BubbleSort<>(), MonsterSort.BY_NAME);
		
		monsters.printList(MonsterPrint.BY_NAME);
		
		monsters.search(new BinarySearch<>(), MonsterSearch.BY_NAME, "Poop")
		.ifPresentOrElse(
				(m) -> System.out.println("YES"), 
				() -> System.out.println("NO"));
		
	}
		
}