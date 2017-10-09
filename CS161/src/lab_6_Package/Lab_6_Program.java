package lab_6_Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lab_6_Package.Monster.MComparator;
import utils.Input;
import utils.Output;
/**
 * <h1>Lab 6 : Binary Search/Quick Sort - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to sort, search and display monsters.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 4-OCT-2017 
 */
public class Lab_6_Program 
{
	public static void main(String[] args) 
	{		
		Output.setDelay(5);
		
		ArrayList<Monster> monsters = LoadMonsters("MONSTERLIST.txt");
		
		//Doesn't work; Reset function has no effect.
		//HashMap<Integer, Consumer<ArrayList<Monster>>> options = buildOptions();
				
		while(true)
		{	
			System.out.println("------------------------" +
							 "\n1. Display List" +
							 "\n2. QuickSort" +
							 "\n3. Binary Search");
			
			switch(Input.getIntRange("Select options 1-4: ", 1, 4))
			{
				case 1 : printList(monsters);
					break;
				case 2 : quickSort(monsters, 0, monsters.size() - 1, getCompareStrategy());
					break;
				case 3 : binarySearch(monsters, 0, monsters.size() - 1, getSearchStrategy(), Input.getString("Enter your search criteria: "));
					break;
			}
			
		}
		
	}

	private static Optional<Monster> binarySearch(ArrayList<Monster> monsters, int front, int end, MComparator searchStrategy, String searchValue)
	{ 
		int mid = (end - front) / 2;
				
		int result = searchStrategy.compare(searchValue, monsters.get(mid));
				
		Output.type("Comparing " + mid + " : " + searchValue + " with " + monsters.get(mid).getName());
		
		if(result == 0) 
		{
			System.out.println("Found Monster");
			return Optional.ofNullable(monsters.get(mid));
		}
		
		if(result < 0 && mid > 1)
		{
			System.out.println("Less");
			return binarySearch(monsters, 0, mid - 1, searchStrategy, searchValue);
		}
		
		if(result > 0 && monsters.size() > mid) 
		{
			System.out.println("More");
			return binarySearch(monsters, mid + 1, end, searchStrategy, searchValue);
		}
		
		System.out.println("Monster not found");
		return Optional.empty();
	}

	private static MComparator getSearchStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp" +
						 "\n4. Attack");
		int input = Input.getIntRange("Sort by? (1-4): ", 1, 4);
		if(input == 1) return (search, monster) -> search.compareToIgnoreCase(monster.getName());
		if(input == 2) return (search, monster) -> Float.compare(Float.parseFloat(search), monster.getHealth());
		if(input == 3) return (search, monster) -> Integer.compare(Integer.parseInt(search), monster.getEXP());
					   return (search, monster) -> Integer.compare(Integer.parseInt(search), monster.getAttack());
	}
	
	private static void quickSort(ArrayList<Monster> monsters, int front, int end, Comparator<Monster> compareStrategy) 
	{	
		int pivot_marker = end;
		
		int left_marker = front;
		
		int right_marker =  pivot_marker - 1;
		
		Outer:
		while(true)
		{
			while(compareStrategy.compare(monsters.get(left_marker), monsters.get(pivot_marker)) <= 0)
			{
				if(left_marker == pivot_marker) break Outer;
				left_marker++;
			}
			
			while(compareStrategy.compare(monsters.get(right_marker), monsters.get(pivot_marker)) > 0)
			{
				if(right_marker == left_marker) break Outer;
				right_marker--;
			}

			swap(monsters, left_marker, right_marker);
			
		}
		
		swap(monsters, left_marker, pivot_marker);
		
		if(left_marker > 1) quickSort(monsters, 0, left_marker - 1, compareStrategy); 	
		if(pivot_marker > left_marker + 1) quickSort(monsters, left_marker + 1, pivot_marker, compareStrategy); 
	
	}

	/**
	 * @param monsters
	 * @param indexA
	 * @param indexB
	 */
	private static void swap(ArrayList<Monster> monsters, int indexA, int indexB) 
	{
		Monster tempMonster = monsters.get(indexA);
		monsters.set(indexA, monsters.get(indexB));
		monsters.set(indexB, tempMonster);
	}
	
	/**
	 * This method asks user for compare strategy used in sorting/comparing monsters. 
	 * @return - The comparator used to compare monsters.
	 */
	private static Comparator<Monster> getCompareStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp");
		int input = Input.getIntRange("Sort by? (1-3): ", 1, 3);
		if(input == 1) return Monster.COMPARE_BY_NAME;
		if(input == 2) return Monster.COMPARE_BY_HEALTH;
					   return Monster.COMPARE_BY_EXP;
	}
	
	/**
	 * This method iterates over loaded file to parse, segregate, and inject file data by line
	 * <p> into individual monster objects that are added to Monster ArrayList.
	 * @param monsters 
	 * @param file to load.
	 * @return ArrayList<Monster> from loaded file.
	 * @throws IOException if file can't be read.
	 */
	private static ArrayList<Monster> LoadMonsters(String file)
	{	
		ArrayList<Monster> monsters = new ArrayList<>();
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
	private static Function<Monster, String> getPrintStrategy() 	
	{
		System.out.println("-----------" +
						 "\n1. By Name" +
						 "\n2. By Health" +
						 "\n3. By EXP" +
						 "\n4. All monster details");
		
		int input = Input.getIntRange("Display by? (1-4): ", 1, 4);
		
		if(input == 1) return Monster::getName;
		if(input == 2) return (monster) -> String.valueOf(monster.getHealth());
		if(input == 3) return (monster) -> String.valueOf(monster.getEXP());
					   return Monster::toString;
	}
	
}
