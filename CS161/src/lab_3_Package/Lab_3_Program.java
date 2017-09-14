package lab_3_Package;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

import lab_2_Package.Monster;
import utils.Input;

public class Lab_3_Program 
{
	public static void main(String[] args) throws IOException 
	{
		List<String> monsterData = Files.readAllLines(Paths.get("MONSTERLIST.txt"));
		
		Monster[] monsters = new Monster[monsterData.size()];
		
		IntStream.range(0, monsters.length).forEach(index -> 
		{
			String[] data = monsterData.get(index).split("/");
			
			monsters[index] = new Monster().setName(data[0])
										   .setHealth(Float.parseFloat(data[1]))
										   .setAttack(Integer.parseInt(data[2]))
										   .setEXP(Integer.parseInt(data[3]));
		});
		
		while(true)
		{	
			System.out.println("1. Change Monster Info" + 
							 "\n2. Attack Monster" +
							 "\n3. Display Monster Info" +
							 "\n4. Quit");
			//control flow 
			switch(Input.getDigitRange("Choose option(1-3): ", '1', '4'))
			{
				case '1': changeMonsterInfo(monsters);
					break;
				case '2': AttackMonster(monsters);
					break;
				case '3': DisplayMonsterInfo(monsters);
					break;
				case '4': System.exit(0);

				default: System.out.println("Not an option."); 
			}
		}		
	}

	private static void printList(Monster[] monsters) 
	{
		IntStream.range(0,monsters.length)
		.forEach(index -> System.out.println((index + 1) + ". " + monsters[index].getName()));
	}
	
	private static Monster getMonster(Monster[] monsters) 
	{
		return monsters[Input.getIntRange("Enter index: ", 1, monsters.length) - 1];
	}
	
	private static void DisplayMonsterInfo(Monster[] monsters) 
	{
		System.out.println("Which monster would you like to Display?");
		
		printList(monsters);

		getMonster(monsters).print();
		
	}

	private static void AttackMonster(Monster[] monsters) 
	{
		System.out.println("Which monster would you like to Attack? ");
		
		printList(monsters);

		getMonster(monsters).dealDamage(Input.getFloat("Attack: "));
	}

	private static void changeMonsterInfo(Monster[] monsters) 
	{	
		System.out.println("Which monster would you like to Change? ");
		
		printList(monsters);
		
		getMonster(monsters)
				.setName(Input.getString("Enter Name: "))
				.setHealth(Input.getFloat("Enter Health: "))
				.setAttack(Input.getInt("Enter Attack: "))
				.setEXP(Input.getInt("Enter EXP: "));
	}
}
