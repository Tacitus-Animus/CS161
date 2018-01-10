package game;

import java.util.ArrayList;
import java.util.stream.IntStream;

import items.Throw;
import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import utils.io.Input;

/**
 * <h1>Battle Class</h1>
 * This Class Controls Fighting Behavior between Fighter/Brawler vs. Monsters.
 * This implements the visitor pattern. The fighter accepts this and this handles the battle logic.
 * @author Alex Paul
 * @version CS161
 * @since 6-NOV-2017
 */

public class Battle {
	
	Monster monster;
	
	public Battle(Monster monster) {
		this.monster = monster;
	}
	
	public Battle() {}

	/**
	 * This method loops through a battle between a fighter and monster.
	 * @param player - Fighter type that has a chance to attack first.
	 */
	public void fight(Fighter player) 
	{
		fightLoop(player, false);
	}
	/**
	 * This method loops through a battle between a fighter and monster.
	 * @param player - Brawler type that has a 100% chance to attack first.
	 */
	public void fight(Brawler player) 
	{
		fightLoop(player, true);
	}
	
	/**
	 * @param player
	 * @param preEmp 
	 */
	private void fightLoop(Fighter player, boolean preEmp) {
		while(!monster.isDead())
		{
			
			int input = getFightOption();
			
			if(input == 1) pre_emptive_Strike(player, preEmp);
			else if(input == 2)  {
				System.out.println("Run away!"); break;
			}else throwItem(player);
		}
	}

	private void throwItem(Fighter player) 
	{
		ArrayList<Throw> throwables = (ArrayList<Throw>) player.getBag().getThrowables();		
		
		if(!throwables.isEmpty()) {
			IntStream.range(0, throwables.size()).forEach(index -> 
			System.out.println(index + 1 + ": " + throwables.get(index).printItem()));	
			
			Throw item = throwables.get(Input.getIntRange("Choose Throwable: ", 1, throwables.size()) - 1);
			
			if(monster.takeDamage(item.damage))
			{
				player.getBag().removeItem(item);
			}
		}
	}

	private int getFightOption() 
	{
		monster.print();
		System.out.println("1. Attack" + 
				         "\n2. Escape" +
						 "\n3. Throw");
		return Input.getIntRange("Choose: ", 1, 3);
	}

	/**
	 * @param monster - Monster to fight Fighter Type.
	 */
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	
	/**
	 * @param player - The fighter that attacks monster and visa versa.
	 * @param hitsFirst - Determines if fighter attacks first else 50/50 chance.
	 */
	private void pre_emptive_Strike(Fighter player, boolean hitsFirst)
	{
		if(hitsFirst)
		{
			player.attack(monster);
			monster.attack(player);
		}else {
			double result = Math.random();
			
			if(result < 0.5) 
			{
				player.attack(monster);
				monster.attack(player);
			}else {
				monster.attack(player);
				player.attack(monster);
			}		
		}
		
		player.print();
		monster.print();
		
	}
	
}
