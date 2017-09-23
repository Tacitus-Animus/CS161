package lab_4_Package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import utils.Input;
/**
 * <h1>Lab 4 : File IO, ArrayList - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses and injects that data to create a Monster List.
 * <p>
 * Loops to get user input to save/load monsters to/from file...
 * <p>
 * and display and attack specific Monster Object Info in the Monster List.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-20 
 */
public class Lab_4_Program 
{
	public static void main(String[] args) throws IOException 
	{
		//Load default monster file.
		ArrayList<Monster> monsters = LoadMonsters("MONSTERLIST.txt");
		
		while(true)
		{	
			System.out.println("------------------------" +
							 "\n1. Attack Monster" + 
							 "\n2. Save to Monster File" +
							 "\n3. Load Monster File" +
							 "\n4. Display Monster:");
			//control flow 
			switch(Input.getDigitRange("Choose option(1-4): ", '1', '4'))
			{
				case '1': attackMonster(monsters);
					break;
				case '2': saveToFile(monsters);
					break;
				case '3': monsters = LoadMonsters(Input.getString("Enter name of file to load: "));
					break;
				case '4': displayMonsterInfo(monsters);
					break;
				default: System.out.println("Not an option."); 
			}
		}	
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
		} catch (IOException e) {System.err.println("Load File ERROR!!!");}

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
	/**
	 * This method iterates over a monster ArrayList to parse, congregate, and print by line
	 * <p> monster object data to file.
	 * @param monsters list to save to file.
	 * @throws IOException 
	 */
	private static void saveToFile(ArrayList<Monster> monsters) throws IOException 
	{
			BufferedWriter writer = new BufferedWriter(
									new FileWriter(
									new File(
									Input.getString("Enter file name to save monsters to: "))));
			
			String[] monsterData = new String[4];
			
			for(int index = 0; index < monsters.size(); index++) 
			{	
				monsterData[0] = monsters.get(index).getName();
				monsterData[1] = String.valueOf((int)monsters.get(index).getHealth());
				monsterData[2] = String.valueOf(monsters.get(index).getAttack());
				monsterData[3] = String.valueOf(monsters.get(index).getEXP());
								
				try {
					writer.write(String.join("/", monsterData));
					writer.newLine();
				} catch (IOException e) { System.err.println("File Save ERROR!!!");};
			};
			writer.close();
			System.out.println("File Saved...");
	}
	/**
	 * This method searches for user specified monster by name.
	 * <p> The search could come up empty returning an empty Optional of Monster; 
	 * <p>Notifies user if the monster is found/not found.
	 * @param monsters array to get monster from.
	 * @param prompt is shown to the user.
	 * @return Optional of Monster.
	 */
	private static Optional<Monster> searchMonster(ArrayList<Monster> monsters, String prompt) 
	{
		String userInput = Input.getString(prompt);
		
		@SuppressWarnings("unlikely-arg-type")
		Optional<Monster> search = monsters.stream()
			.filter(monster -> monster.equals(userInput)).findFirst();
		
		System.out.println(search.isPresent() ? "Monster found." : "Monster not found.");
		
		return search;
	}
	/**
	 * This method makes use of getMonster() method and performs user specified damage
	 * <p> to monster if found.
	 * @param monsters array to get specified monster to attack.
	 */
	private static void attackMonster(ArrayList<Monster> monsters) 
	{
		searchMonster(monsters, "Which monster would you like to Attack: ")
			.ifPresent(monster -> 
			{
				monster.dealDamage(Input.getFloat("Input attack damage: "));
			});
	}
	/**
	 * This method makes use of getMonster() method and prints out
	 * <p> monster info if found.
	 * @param monsters array to get specified monster to display.
	 */
	private static void displayMonsterInfo(ArrayList<Monster> monsters) 
	{		
		searchMonster(monsters, "Which monster would you like to Display? ")
			.ifPresent(Monster::print);
	}
}
