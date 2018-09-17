package units.monster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import utils.search.LinearSearch;
import utils.search.Search;
import utils.sort.Sort;

/**
 * <h1>Lab 6 : Binary Search/Quick Sort - Helper Class File</h1>
 * This is the helper class file which acts as a facade for monster operations.
 * <p>
 * It contains the ArrayList of monsters in which to do operations on.
 * <p>
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-2017 
 */
public class Monsters
{
	private ArrayList<Monster> monsters;
	
	
	/**
	 * @param fileName - The name of the file to load Monster data from.
	 * @throws NotEnoughMonsterException 
	 * @throws FileNotFoundException 
	 */
	public Monsters(String fileName) throws FileNotFoundException
	{
			loadFromFile(fileName);
	}

	/**
	 * @param name - Name of monster to search and change info if found.
	 * @param exp 
	 * @param attack 
	 * @param health 
	 * @param newName 
	 */
	public void changeMonsterInfo(String name, String newName, float health, int attack, int exp) 
	{			
		search(name).ifPresent(foundMonster ->
		{
			foundMonster.print();
			foundMonster.setName(name);
			foundMonster.setHealth(health);
			foundMonster.setAttack(attack);
			foundMonster.setEXP(exp);
		});
	}
	
	/**
	 * @param name - Name of monster to search for and inflict damage to if found.
	 * @param damage - The damage inflicted to monster if found.
	 */
	public void attackMonster(String name, float damage) 
	{					
		search(name).ifPresent(foundMonster -> 
		{
			foundMonster.print();
			foundMonster.takeDamage(damage);
		});	
	}
	
	/**
	 * @param name - Name of the monster to search for and display if found.
	 */
	public void displayMonsterInfo(String name) 
	{				
		search(name).ifPresent(Monster::print);
	}
	
	/**
	 * @param fileName - Name of the file to load Monster data from.
	 * @return ArrayList<Monster> - The collection of monster objects loaded from the file.
	 * @throws FileNotFoundException 
	 * @throws IOException - Thrown if there is a file loading error.
	 */
	public void loadFromFile(String fileName) throws FileNotFoundException
	{	
		try {
			monsters = (ArrayList<Monster>) Files.readAllLines(Paths.get(fileName))
					   .stream()
					   .map(Monster.stringToMonster("/"))
					   .collect(Collectors.toList());
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
		 System.out.println("Monsters Loaded.");
	}
	
	/**
	 * @param fileName - Name of file to save monster data to.
	 * @throws IOException - Thrown if there is a file saving error.
	 */
	 public void saveToFile(String fileName)
	 {
		try {
			BufferedWriter writer = new BufferedWriter(
									new FileWriter(
									new File(
									fileName)));
			
			String[] monsterData = new String[4];
			
			for(int index = 0; index < monsters.size(); index++) 
			{	
				monsterData[0] = monsters.get(index).getName();
				monsterData[1] = String.valueOf((int)monsters.get(index).getHealth());
				monsterData[2] = String.valueOf(monsters.get(index).getAttack());
				monsterData[3] = String.valueOf(monsters.get(index).getEXP());
								
					writer.write(String.join("/", monsterData));
					writer.newLine();
			}
			writer.close();
		} catch (IOException e) { 
			System.err.println("File Save ERROR!!!"); 
		}
		System.out.println("File Saved.");
	}
	
	/**
	 * 
	 * @param printStrategy - Function used to print out monsters by specific attribute.
	 */
	public void printList(MonsterPrint printStrategy) 
	{		
		System.out.println(monsters.stream()										        
		  		  			.map(printStrategy.get())								
		  		  			.collect(Collectors.joining("\n----------\n")));
	}

	/**
	 * 
	 * @param searchType - The type of search used to search an ArrayList of monsters.
	 * @param searchStrategy - The BiFunction used to search by user input and specific monster attributes.
	 * @param searchCriteria - The user input used to search for monster with matching attribute.
	 */
	public Optional<Monster> search(Search<Monster, String> searchType, MonsterSearch searchStrategy, String searchCriteria) 
	{
		return searchType.search(monsters, searchStrategy.get(), searchCriteria);
	}

	/**
	 * @param searchCriteria - The user input used to linear search for monster by name with matching attribute.
	 */
	public Optional<Monster> search(String searchCriteria) 
	{
		return new LinearSearch<Monster, String>().search(monsters, MonsterSearch.BY_NAME.get(), searchCriteria);
	}
	
	/**
	 * 
	 * @param sorter - The type of sort used to sort an Arraylist of monsters.
	 * @param sortStrategy - The comparator used in sorting an Arraylist of monsters.
	 */
	public void sort(Sort<Monster> sorter, MonsterSort sortStrategy) 
	{
		sorter.sort(monsters, sortStrategy.get());
		System.out.println("Sorted.");
	}

	public Iterator<Monster> getIterator() 
	{
		return monsters.iterator();
	}

	/**
	 * {@code search(name)} will pass a monster through the method reference {@code alternator::eccept}. 
	 * The client's {@code Consumer<Monster>} will alter the monster as specified.
	 * Example: 
	 * <br>{@code monsters.changeMonsterInfo("Slime", (monster) -> {
			monster.setName("Avatar");
		});}
	 * @param name
	 * @param alternator
	 */
	public void changeMonsterInfo(String name, Consumer<Monster> alternator) {
		
		search(name).ifPresentOrElse(alternator::accept, () -> System.out.println(name + " doesn't exist"));
		
	}
	
}
