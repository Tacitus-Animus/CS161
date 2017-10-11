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
import utils.Visuals;
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
		Output.setDelay(10);
		
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
				case 2 : quickSort(monsters, 0, monsters.size() - 1, getSortStrategy());
					break;
				case 3 : binarySearch(monsters, 0, monsters.size() - 1, getSearchStrategy(), Input.getString("Enter your search criteria: "));
					break;
			}
			
		}
		
	}

	private static Optional<Monster> binarySearch(ArrayList<Monster> monsters, int front, int end, MComparator searchStrategy, String searchValue)
	{ 
		int mid = front + ((end - front) / 2); //interpolate to get middle of front and end bounds.
				
		int result = searchStrategy.compare(searchValue, monsters.get(mid));
						
		if(result == 0) return Optional.ofNullable(monsters.get(mid));
		
		if(result < 0 && mid > 0) return binarySearch(monsters, front, mid - 1, searchStrategy, searchValue);
		
		if(result > 0 && end > mid) return binarySearch(monsters, mid + 1, end, searchStrategy, searchValue);
		
		return Optional.empty();
	}

	/**
	 * This method asks user how 
	 * @return The strategy used to in binarySearch method.
	 */
	private static MComparator getSearchStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp" +
						 "\n4. Attack");
		
		int input = Input.getIntRange("Search by? (1-4): ", 1, 4);
		
		if(input == 1) return (search, monster) -> search.compareToIgnoreCase(monster.getName());
		if(input == 2) return (search, monster) -> Float.compare(Float.parseFloat(search), monster.getHealth());
		if(input == 3) return (search, monster) -> Integer.compare(Integer.parseInt(search), monster.getEXP());
					   return (search, monster) -> Integer.compare(Integer.parseInt(search), monster.getAttack());
	}
	
	/**
	 * This method recursively sorts the list within the front and end bounds.
	 * @param monsters - List to recursively sort.
	 * @param front - The front bounds of the list.
	 * @param end - The end bounds of the list.
	 * @param compareStrategy - The strategy used to sort the list.
	 */
	private static void quickSort(ArrayList<Monster> monsters, int front, int end, Comparator<Monster> compareStrategy) 
	{	
		Output.type("Sorting " + front + " to " + end);

		int pivot_marker = front + ((end - front) / 2);
		
		int left_marker = front;
		
		int right_marker =  end;
		
		
		while(left_marker != right_marker)
		{
			while(left_marker != end && compareStrategy.compare(monsters.get(left_marker), monsters.get(pivot_marker)) <= 0)
			{
				Visuals.see(monsters, pivot_marker, left_marker, right_marker);
				left_marker++;
			}
			
			while(left_marker < right_marker && compareStrategy.compare(monsters.get(right_marker), monsters.get(pivot_marker)) >= 0)
			{
				//Visuals.see(monsters, pivot_marker, left_marker, right_marker);
				right_marker--;
			}
			
			if(compareStrategy.compare(monsters.get(left_marker), monsters.get(right_marker)) > 0) swap(monsters, left_marker, right_marker);
		
		}
		
		if(compareStrategy.compare(monsters.get(left_marker), monsters.get(pivot_marker)) > 0) swap(monsters, left_marker, pivot_marker);
		
		if(left_marker - front > 1) quickSort(monsters, front , left_marker - 1, compareStrategy); 	
		else if(end - left_marker > 1) quickSort(monsters, left_marker + 1, end, compareStrategy); 
	}

	/**
	 * This method swaps 2 monsters with one another.
	 * @param monsters - List to swap 2 monsters in.
	 * @param indexA - The first monster index to swap.
	 * @param indexB - The second monster index to swap.
	 */
	private static void swap(ArrayList<Monster> monsters, int indexA, int indexB) 
	{
		
		Output.type("Swaping " + indexA + " : " + monsters.get(indexA).getHealth() + " with " + indexB + " : " + monsters.get(indexB).getHealth());
		Monster tempMonster = monsters.get(indexA);
		monsters.set(indexA, monsters.get(indexB));
		monsters.set(indexB, tempMonster);
	}
	
	/**
	 * This method asks user for compare strategy used in sorting/comparing monsters. 
	 * @return - The comparator used to compare monsters.
	 */
	private static Comparator<Monster> getSortStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp" +
						 "\n4. Attack");
		
		int input = Input.getIntRange("Sort by? (1-4): ", 1, 4);
		
		if(input == 1) return Monster.COMPARE_BY_NAME;
		if(input == 2) return Monster.COMPARE_BY_HEALTH;
		if(input == 3) return Monster.COMPARE_BY_EXP;
					   return Monster.COMPARE_BY_ATTACK;
	}
	
	/**
	 * This method iterates over loaded file to monster data that are added to Monster ArrayList.
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
	 * This method asks user for print strategy on what should be printed out by printList method.
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