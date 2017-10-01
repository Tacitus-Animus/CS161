package lab_5_package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import utils.Input;
/**
 * <h1>Lab 5 : File IO, ArrayList - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to sort and diplay monsters.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-20 
 */
public class Lab_5_Program 
{
	public static void main(String[] args) throws IOException 
	{
		ArrayList<Monster> monsters = LoadMonsters("MONSTERLIST.txt");
		while(true)
		{	
			System.out.println("------------------------" +
							 "\n1. Reset" + 
							 "\n2. Bubble Sort" +
							 "\n3. Selection Sort" +
							 "\n4. Display List");
			switch(Input.getDigitRange("Choose option(1-4): ", '1', '4'))
			{
				case '1': monsters = LoadMonsters("MONSTERLIST.txt");
					break;
				case '2': bubbleSort(monsters);
					break;
				case '3': selectionSort(monsters);
					break;
				case '4': monsters.forEach(Monster::print);
					break;
				default: System.out.println("Not an option."); 
			}
		}	
	}
	/**
	 * This method loops through and compares monsters adjacent to one another.
	 * <p> Swaps if first monster is bigger/higher than second monster in relation to comparator.
	 * <p> Iterates until sorted.
	 * @param monsters List to be sorted.
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
				if(result == 1)
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
	 * This method sorts by first looping through to find the smallest/lowest Monster in relation to comparator.
	 * <p> Then swaps the first unsorted index with said Monster; Repeats until sorted.
	 * @param monsters The list to be sorted.
	 */
	private static void selectionSort(ArrayList<Monster> monsters)
	{
		Comparator<Monster> compareStrategy = getCompareStrategy();		
		for(int i = 0; i < monsters.size(); i++) 
		{	
			int minIndex = i;
			for(int checkIndex = i + 1; checkIndex < monsters.size(); checkIndex++) 
			{
				int result = compareStrategy.compare(monsters.get(checkIndex), monsters.get(minIndex));
				if(result == -1) minIndex = checkIndex;
			}
			if(i != minIndex)
			{
				Monster temp = monsters.get(i);
				monsters.set(i, monsters.get(minIndex));
				monsters.set(minIndex, temp);
			}
		}
	}
	/**
	 * This method returns a compare strategy used in sorting/comparing monsters. 
	 * @return the comparator used to compare monsters.
	 */
	private static Comparator<Monster> getCompareStrategy()
	{
		System.out.println("------------" +
						 "\n1. Name" +
						 "\n2. Health" +
						 "\n3. Exp");
		int input = Input.getDigitRange("Sort by? (1-3): ", '1', '3');
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
		List<String> monsterData = null;
		try {
			monsterData = Files.readAllLines(Paths.get(file));
		} catch (IOException e) {
			System.err.println("Load File ERROR!!!");
		}
		ArrayList<Monster> monsters = new ArrayList<>();
		for(int index = 0; index < monsterData.size(); index++) 
		{
			String[] data = monsterData.get(index).split("/");
			Monster newMonster = new Monster()	
									   .setName(data[0])
									   .setHealth(Float.parseFloat(data[1]))
									   .setAttack(Integer.parseInt(data[2]))
									   .setEXP(Integer.parseInt(data[3]));
			monsters.add(newMonster);
			System.out.println((index + 1) + ". " + 
								newMonster.getName() +" : " + 
								newMonster.getStatus());
		}
		return monsters;
	}
}