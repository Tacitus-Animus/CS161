package lab_5_package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import utils.Input;
/**
 * <h1>Lab 5 : File IO, ArrayList - Main Class File</h1>
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
		//Used instead of a switch statement.
//		HashMap<Integer, Consumer<ArrayList<Monster>>> options = new HashMap<>();
//			options.put(1, (monsters) -> monsters = LoadMonsters("MONSTERLIST.txt"));
//			options.put(2, (monsters) -> bubbleSort(monsters));
//			options.put(3, (monsters) -> selectionSort(monsters));
//			options.put(4, (monsters) -> printList(monsters));
		
		ArrayList<Monster> monsters = LoadMonsters("MONSTERLIST.txt");
			
		
		while(true)
		{	
			System.out.println("------------------------" +
							 "\n1. Reset" + 
							 "\n2. Bubble Sort" +
							 "\n3. Selection Sort" +
							 "\n4. Display List");
			
			switch(Input.getIntRange("Select options 1-4: ", 1, 4))
			{
				case 1 : monsters = LoadMonsters("MONSTERLIST.txt");
					break;
				case 2 : bubbleSort(monsters);
					break;
				case 3 : selectionSort(monsters);
					break;
				case 4 : printList(monsters);
					break;
			}
			
			//int userChoice = Input.getIntRange("Select options 1-4: ", 1, 4);
			
			//options.get(userChoice).accept(monsters);  
		}	
	}
		
	/**
	 * This method turns a list of monsters into strings, connects all of them into one string, then prints out the new string.
	 * @param monsters - The list of monsters to print out.
	 */
	private static void printList(ArrayList<Monster> monsters) 
	{		
		System.out.println(monsters.stream()										        
		  		  			.map(getPrintStrategy())								
		  		  			.collect(Collectors.joining("\n----------\n")));
	}
	/**
	 * This method asks user for to specifics on what should be printed out by printList method.
	 * @return Function used to print out specified monster details
	 */
	private static Function<Monster, String> getPrintStrategy() {
		System.out.println("-----------" +
						 "\n1. By Name" +
						 "\n2. By Health" +
						 "\n3. By EXP" +
						 "\n4. All monster details");
		
		char input = Input.getDigitRange("Display by? (1-4): ", '1', '4');
		
		if(input == '1') return Monster::getName;
		if(input == '2') return (monster) -> String.valueOf(monster.getHealth());
		if(input == '3') return (monster) -> String.valueOf(monster.getEXP());
						 return Monster::toString;
	}
	/**
	 * This method loops through and compares monsters adjacent to one another.
	 * <p> Swaps if first monster is bigger/higher than second monster in relation to comparator.
	 * <p> Keeps moving larger monster to end of index; Iterates until sorted.
	 * @param monsters - List to be sorted.
	 */
	private static void bubbleSort(ArrayList<Monster> monsters) 
	{
		Comparator<Monster> compareStrategy = getCompareStrategy();	
		int end = monsters.size() - 1;
		
		for(int i = 0; i < monsters.size(); i++) 
		{
			for(int j = 0; j < end; j++)
			{
				Monster m1 = monsters.get(j);
				Monster m2 = monsters.get(j + 1);
				int result = compareStrategy.compare(m1, m2);
				if(result == 1)	//The bubble.
				{
					Monster temp = m1;
					monsters.set(j, m2);
					monsters.set(j + 1, temp);
				}
			}
			end--;
		}
	}
	/**
	 * This method sorts by first looping through to select the smallest/lowest Monster in relation to comparator.
	 * <p> Then swaps the first unsorted index with selected Monster; Repeats until sorted.
	 * @param monsters - The list to be sorted.
	 */
	private static void selectionSort(ArrayList<Monster> monsters)
	{
		Comparator<Monster> compareStrategy = getCompareStrategy();	
		
		for(int i = 0; i < monsters.size(); i++) 
		{	
			int minIndex = i;		
			Monster minMonster = monsters.get(minIndex);       
			
			for(int checkIndex = i + 1; checkIndex < monsters.size(); checkIndex++) 
			{																		
				Monster checkMonster = monsters.get(checkIndex);
				int result = compareStrategy.compare(checkMonster, minMonster);
				
				if(result == -1) //The selection.
				{
					minIndex = checkIndex;
					minMonster = checkMonster;
				}
			}
			
			if(i != minIndex) //If front index isn't the smallest/lowest monster, swap.
			{
				Monster temp = monsters.get(i);
				monsters.set(i, minMonster);
				monsters.set(minIndex, temp);
			}
		}
	}
	/**
	 * This method returns a compare strategy used in sorting/comparing monsters. 
	 * @return - The comparator used to compare monsters.
	 */
	private static Comparator<Monster> getCompareStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp");
		char input = Input.getDigitRange("Sort by? (1-3): ", '1', '3');
		if(input == '1') return Monster.COMPARE_BY_NAME;
		if(input == '2') return Monster.COMPARE_BY_HEALTH;
						 return Monster.COMPARE_BY_EXP;
	}
	/**
	 * This method iterates over loaded file to parse, segregate, and inject file data by line
	 * <p> into individual monster objects that are added to Monster ArrayList.
	 * @param file to load.
	 * @return ArrayList<Monster> from loaded file.
	 * @throws IOException if file can't be read.
	 */
	private static ArrayList<Monster> LoadMonsters(String file)
	{	
		ArrayList<Monster> monsters = new ArrayList<>();;
		try {
			monsters = (ArrayList<Monster>) Files.readAllLines(Paths.get(file))
					   .stream()
					   .map(Monster.stringToMonster("/"))
					   .collect(Collectors.toList());
		} catch (IOException e) {
			System.err.println("Load File ERROR!!!");
		}
		return monsters;
	}
}