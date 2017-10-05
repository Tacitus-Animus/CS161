package lab_6_Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

import lab_5_package.Monster;
import utils.Input;

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
				case 3 : 
					break;
			}
			
		}
		
	}

	private static void quickSort(ArrayList<Monster> monsters, int front, int end, Comparator<Monster> compareStrategy) 
	{	
		int pivot_point = end;
		Monster pivot_monster = monsters.get(pivot_point);
		
		int left = front;
		Monster left_monster = monsters.get(left);
		
		int right =  pivot_point - 1;
		Monster right_monster = monsters.get(right);
		
		Outer:	
		while(left != right)
		{
			while(compareStrategy.compare(left_monster, pivot_monster) < 0)
			{
				if(left == pivot_point) break Outer;
				left++;
				left_monster = monsters.get(left);
			}
			
			while(compareStrategy.compare(right_monster, pivot_monster) >= 0) 
			{
				if(left == right) break Outer;
				right--;
				right_monster = monsters.get(right);
			}
			
			if(left >= right) break Outer;
			Monster temp = left_monster;
			monsters.set(left, right_monster);
			monsters.set(right, temp);
		}
		
		if(compareStrategy.compare(left_monster, pivot_monster) > 0)
		{
			Monster temp = pivot_monster;
			monsters.set(pivot_point, left_monster);
			monsters.set(left, temp);
		}
		
		if(0 > left - 1) quickSort(monsters, 0, left - 1, compareStrategy);
		if(left + 1 < monsters.size() - 1) quickSort(monsters, left + 1, monsters.size() - 1, compareStrategy);
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
	private static Function<Monster, String> getPrintStrategy() {
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
