package labs;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.function.Consumer;

import exceptions.NotEnoughMonsterException;
import units.monster.*;
import utils.io.Input;
import utils.search.*;
import utils.sort.*;
/**
 * <h1>Lab 6 : Binary Search/Quick Sort - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to sort, search, save, load, attack, change and display monsters.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-2017 
 */
public class Lab_6_Program 
{
	public static void main(String[] args) throws NotEnoughMonsterException 
	{					
		Monsters monsters = null;
		try {
			monsters = new Monsters("MONSTERLIST.txt");
		} catch (FileNotFoundException e) {
			//Nothing
		}

		HashMap<Integer, Consumer<Monsters>> options = buildOptions();
				
		while(true)
		{	
			System.out.println("------------------------\n" +
							   "1. Change Monster Information\n" + 
							   "2. Attack The Monster\n" + 
							   "3. Display the Monster Array\n" + 
							   "4. Search Monster\n" + 
							   "5. Load a new file\n" + 
							   "6. Save to File\n" + 
							   "7. Sort the Array");
		
				options.get(Input.getIntRange("Enter option (1-7): ", 1, 7)).accept(monsters);
		}
	}

	/**
	 * @return - The HashMap is used to get a consumable operation for Monsters from the user using integers.
	 */
	private static HashMap<Integer, Consumer<Monsters>> buildOptions() 
	{
		HashMap<Integer, Consumer<Monsters>> options = new HashMap<>();
		
			options.put(1, monsters -> monsters.changeMonsterInfo(Input.getString("Enter which monster you would like to change? "),
																  Input.getString("Enter New Name: "),
																  Input.getFloat("Enter new Health: "),
																  Input.getInt("Enter new Attack: "),
																  Input.getInt("Enter new EXP: ")));
			
			options.put(2, monsters -> monsters.attackMonster(Input.getString("Enter which monster you would like to attack? "), 
															  Input.getFloat("Input attack damage: ")));
			
			options.put(3, monsters -> monsters.printList(getPrintStrategy()));
			options.put(4, monsters -> monsters.search(getSearchType(), 
													   getSearchStrategy(), 
													   Input.getString("Enter Search Criteria: ")));
			
			options.put(5, monsters -> {
				try {
					monsters.loadFromFile(Input.getString("Enter file to load monsters from: "));
				} catch (NotEnoughMonsterException | FileNotFoundException e) {
					//nothing
				}
			});
			options.put(6, monsters -> monsters.saveToFile(Input.getString("Enter file to save monsters to: ")));
			options.put(7, monsters -> monsters.sort(getSortType(), getSortStrategy()));
			
		return options;
	}

	/**
	 * 
	 * @return - The Search Object used to search for a specific monster.
	 */
	private static Search<Monster> getSearchType()
	{
		System.out.println("1. Binary Search\n" +
						   "2. Linear Search\n");

		int sortOption = Input.getIntRange("Which search method would you like to use? (1-2): ", 1, 2);	
		
		if(sortOption == 1) return new BinarySearch<Monster>();
							return new LinearSearch<Monster>();
	}

	/**
	 * @return - The Sort Object used to sort an ArrayList of Monsters.
	 */
	private static Sort<Monster> getSortType()
	{
		System.out.println("1. Quick Sort\n" +
		   		   		   "2. Bubble Sort\n" +
				           "3. Selection Sort");

		int sortOption = Input.getIntRange("Which Sort method would you like to use? (1-3): ", 1, 3);	
		
		if(sortOption == 1) return new Quicksort<Monster>();
		if(sortOption == 2) return new BubbleSort<Monster>();
							return new SelectionSort<Monster>();
	}

	/**
	 * @return - Function used to print out specific monster details.
	 */
	private static MonsterPrint getPrintStrategy() 	
	{
		System.out.println("-----------" +
						 "\n1. By Name" +
						 "\n2. By Health" +
						 "\n3. By EXP" +
						 "\n4. By Attack" +
						 "\n5. All Info");
		
		int input = Input.getIntRange("Display by? (1-5): ", 1, 5);
		
		if(input == 1) return MonsterPrint.BY_NAME;
		if(input == 2) return MonsterPrint.BY_HEALTH;
		if(input == 3) return MonsterPrint.BY_EXP;
		if(input == 4) return MonsterPrint.BY_ATTACK;
					   return MonsterPrint.BY_ALL_INFO;
	}
	
	/**
	 * @return - The comparator used to compare monsters.
	 */
	private static MonsterSort getSortStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp" +
						 "\n4. Attack");
		
		int input = Input.getIntRange("Sort by? (1-4): ", 1, 4);
		
		if(input == 1) return MonsterSort.BY_NAME;
		if(input == 2) return MonsterSort.BY_HEALTH;
		if(input == 3) return MonsterSort.BY_EXP;
					   return MonsterSort.BY_ATTACK;
	}
	/**
	 * @return - Bifunction used to used to search for a Monster based on specified attributes.
	 */
	private static MonsterSearch getSearchStrategy() {
		
		System.out.println("-----------" +
						 "\n1. By Name" +
						 "\n2. By Health" +
						 "\n3. By EXP" +
						 "\n4. By Attack");

		int input = Input.getIntRange("Search by? (1-4): ", 1, 4);
		
		if(input == 1) return MonsterSearch.BY_NAME;
		if(input == 2) return MonsterSearch.BY_HEALTH;
		if(input == 3) return MonsterSearch.BY_EXP;
					   return MonsterSearch.BY_ATTACK;
	}
	
}