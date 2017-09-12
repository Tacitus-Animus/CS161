package Lab_2_Package;

import utils.Input;

/**
 * <h1>Lab 2 : Objects - Main Class File</h1>
 * This Program makes use of Monster Class Object.
 * <p>
 * Asks user for input to interact with Monster Object.
 * @see Monster
 * @author Alex Paul
 * @version CS161
 * @since 9-SEP-17
 */

public class Lab_2_Program 
{	
	/**
	 * Main Method that makes use of Monster Class Object.
	 */
	public static void main(String[] args) 
	{
		Monster monster = new Monster();

		changeMonsterInfo(monster);

		monster.print();
		
		//main program loop
		while(!monster.isDead())
		{	
			System.out.println("1. Change Monster Info" + 
							 "\n2. Attack the Monster" +
							 "\n3. Display Monster Information");
			//control flow 
			switch(Input.getInt("Choose option(1-3): "))
			{
				case 1: changeMonsterInfo(monster);
					break;
				case 2: monster.dealDamage(Input.getFloat("Deal damage to monster: "));
					break;
				case 3: monster.print();
					break;
				default: System.out.println("Not an option."); 
			}
		}
	}
	
	/**
	 * This method gets user input to make changes to monsters Name, Health, and Attack. 
	 * @param m is the monster in which to make changes to.
	 */
	private static void changeMonsterInfo(Monster m) {
		m.setName(Input.getString("Enter Name of Monster: "));
		m.setHealth(Input.getFloat("Enter Health of Monster: "));
		m.setAttack(Input.getInt("Enter Attack of Monster: "));
	}

}
