package Lab_2_Package;

import utils.Input;

public class Lab2_Objects_AJP 
{	
	public static void main(String[] args) 
	{
		//create default monster
		Monster monster = new Monster();
		//get user input to change monster data
		changeMonsterInfo(monster);
		//display monster data
		System.out.println(monster);
		//main program loop
		while(!monster.isDead())
		{	
			System.out.println("1. Change Monster Info" + 
				"\n2. Attack the Monster" +
				"\n3. Display Monster Information");
			//control flow using ints
			switch(Input.getInt("Choose option(1-3)"))
			{
				case 1: changeMonsterInfo(monster);
					break;
				case 2: monster.dealDamage(Input.getFloat("Deal damage to monster:"));
					break;
				case 3: System.out.println(monster);
			}
		}
	}
	//change  selected monsters data
	private static void changeMonsterInfo(Monster m) {
		m.setName(Input.getString("Enter Name of Monster: "));
		m.setHealth(Input.getFloat("Enter Health of Monster: "));
		m.setAttack(Input.getInt("Enter Attack of Monster: "));
	}

}
