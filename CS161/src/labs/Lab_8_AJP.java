package labs;

import java.io.FileNotFoundException;

import game.Battle;
import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import units.monster.Monsters;
import utils.io.Input;

/**
 * <h1>Lab 8 : Main Class File</h1>
 * This is the main program file which creates a monster and a fighter.
 * <p>
 * The fighter attack the monster and visa versa.
 * <p>
 * @see Monster 
 * @author Alex Paul
 * @version CS161
 * @since 06-NOV-2017 
 */
public class Lab_8_AJP {

	public static void main(String[] args) throws FileNotFoundException
	{
		Fighter fighter = getFighterType();
		
		Monsters monsters = new Monsters("MONSTERLIST.txt");
		
		Monster monster = monsters.search("Elites").get();
		
		
		
		while(!fighter.isDead() && !monster.isDead()) {
			fighter.print();
			monster.print();
			
				fighter.accept(new Battle(monster));
			
			Input.getStringln("Enter anything and press 'Enter' to continue fight... ");
		}
		
	}

	private static Fighter getFighterType() 
	{	
		System.out.println("1. Fighter\n" +
						   "2. Brawler");
		int result = Input.getIntRange("Choose type: (1 or 2) ", 1, 2);
		
		if(result == 1) return new Fighter();
						return new Brawler();
	}

}
