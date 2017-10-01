package lab_3_Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import utils.Input;
/**
 * <h1>Lab 3 : File IO, Arrays - Main Class File</h1>
 * This is the main program file which gets monster data from a file.
 * <p>
 * It parses that data to create a Monster[] with instantiated Monster Objects using said data.
 * <p>
 * Then, loops to get user input to change, attack, or display specific Monster Object Info in the Monster[].
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 12-SEP-17 
 */
public class Lab_3_Program 
{
	/**
	 * Main Method to be executed.
	 * @throws IOException thrown if MONSTERLIST.txt file cannot be found.
	 */
	public static void main(String[] args) throws IOException 
	{
		//Retrieve monster data.
		List<String> monsterData = Files.readAllLines(Paths.get("MONSTERLIST.txt"));
		//Instantiate Monster[] to specific size.
		Monster[] monsters = new Monster[monsterData.size()];
		//Populate Monster[] with monsterData List in respect to each others' index.
		IntStream.range(0, monsters.length).forEach(index -> 
		{
			//Segregate data.
			String[] data = monsterData.get(index).split("/");
			//Create monster via method chaining using segregated data.
			monsters[index] = new Monster()	
							   .setName(data[0])
							   .setHealth(Float.parseFloat(data[1]))
							   .setAttack(Integer.parseInt(data[2]))
							   .setEXP(Integer.parseInt(data[3]));
		});
		//Main loop to get user input.
		while(true)
		{	
			System.out.println("1. Change Monster Info" + 
							 "\n2. Attack Monster" +
							 "\n3. Display Monster Info" +
							 "\n4. Quit");
			//control flow 
			switch(Input.getDigitRange("Choose option(1-4): ", '1', '4'))
			{
				case '1': changeMonsterInfo(monsters);
					break;
				case '2': AttackMonster(monsters);
					break;
				case '3': DisplayMonsterInfo(monsters);
					break;
				case '4': System.exit(0);
					break;
				default: System.out.println("Not an option."); 
			}
		}		
	}
	/**
	 * @param monsters array to get monster from.
	 * @return monster at index specified by user.
	 */
	private static Monster getMonster(Monster[] monsters) 
	{
		String prompt = "Enter index (1-"+ monsters.length +"): ";
		int index = Input.getIntRange(prompt, 1, monsters.length);
		return monsters[index-1];
	}
	/**
	 * @param monsters array to get specified monster to display.
	 */
	private static void DisplayMonsterInfo(Monster[] monsters) 
	{
		System.out.println("Which monster would you like to Display?");
		//prints out monsters in numbered list format.
		for(int i = monsters.length - 1; i >= 0; i--) {
			System.out.println(monsters[i].getName());
		}
	}
	/**
	 * @param monsters array to get specified monster to attack.
	 */
	private static void AttackMonster(Monster[] monsters) 
	{
		System.out.println("Which monster would you like to Attack? ");
		getMonster(monsters).dealDamage(Input.getFloat("Attack: "));
	}
	/**
	 * @param monsters array to get specified monster to change.
	 */
	private static void changeMonsterInfo(Monster[] monsters) 
	{	
		System.out.println("Which monster would you like to Change? ");
		getMonster(monsters)
			.setName(Input.getString("Enter Name: "))
			.setHealth(Input.getFloat("Enter Health: "))
			.setAttack(Input.getInt("Enter Attack: "))
			.setEXP(Input.getInt("Enter EXP: "));
	}
}