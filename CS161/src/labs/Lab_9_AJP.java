package labs;
import java.io.FileNotFoundException;
import exceptions.DeadFighterIsDeadException;
import exceptions.NotEnoughMonsterException;
import exceptions.WrongChoiceException;
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
	public static void main(String[] args) 
	{
		final Battle battle = new Battle();

		final Monsters monsters = loadMonsters();
		
		final Monster monster = monsters.search("Elites").get();
		
		Fighter fighter;		
						
		Game:
		while(true) 
		{
			try {
				fighter = getFighterType();
			} catch (WrongChoiceException e1) {
				System.out.println("Wrong Choice! Needs to be a 1 or 2");
				continue Game; //try again!
			}
			
			battle.setMonster((Monster) monster.clone());
			
			Fight:
			while(true)
			{
				try {
					fighter.accept(battle); //Visitor pattern.
				} catch (DeadFighterIsDeadException e) {
					System.out.println("Fighter is Dead! Creating new Game...");
					continue Game; //Try a new Game!
				}
		
				System.out.println("Monster is Dead! Creating new Monster...");
				battle.setMonster((Monster) monster.clone());
				
				continue Fight; //Keep fighting!
			}
		}
	}

	private static Monsters loadMonsters() 
	{
		Monsters monsters;
		while(true) 
		{
			try {
				monsters = new Monsters(Input.getString("Enter Monster File: "));
			} catch (NotEnoughMonsterException | FileNotFoundException e) {
				System.out.println("File needs atleast 10 monsters or file isn't found!");
				continue; //Try loading again!
			}
			break;
		}
		return monsters;
	}

	private static Fighter getFighterType() throws WrongChoiceException 
	{	
		System.out.println("1. Fighter\n" +
						   "2. Brawler");
		int result = Input.getInt("Choose type (1 or 2): ");
		
		if(result == 1) return new Fighter();
		if(result == 2)	return new Brawler();
		else throw new WrongChoiceException();
	}
	
}
