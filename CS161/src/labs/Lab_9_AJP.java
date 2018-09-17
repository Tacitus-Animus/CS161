package labs;
import java.io.FileNotFoundException;

import game.Battle;
import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import units.monster.Monsters;
import utils.io.Input;

/**
 * <h1>Lab 9 : Main Class File</h1>
 * This is the main program file which creates a fighting game.
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 06-NOV-2017 
 */

public class Lab_9_AJP 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		final Battle battle = new Battle();

		final Monsters monsters = new Monsters(Input.getString("Enter Monster File: "));
		
		final Monster monster = monsters.search("Elites").get();
		
		Fighter fighter;		
						
		Game:
		while(true) 
		{
				fighter = getFighterType();
			
			
			battle.setMonster((Monster) monster.clone());
			
			Fight:
			while(true)
			{
				
					fighter.accept(battle); //Visitor pattern.
				
					if(fighter.isDead()) continue Game; //Try a new Game!
		
				System.out.println("Monster is Dead! Creating new Monster...");
				battle.setMonster((Monster) monster.clone());
				
				continue Fight; //Keep fighting!
			}
		}
	}

	private static Fighter getFighterType() 
	{	
		System.out.println("1. Fighter\n" +
						   "2. Brawler");
		int result = Input.getInt("Choose type (1 or 2): ");
		
		if(result == 1) return new Fighter();
			return new Brawler();
	}
	
}
