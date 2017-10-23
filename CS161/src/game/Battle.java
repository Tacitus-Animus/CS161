package game;

import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import utils.io.Input;

public class Battle {
	
	Monster monster;
	
	public Battle(Monster monster) {
		this.monster = monster;
		
	}
	
	public void fight(Fighter player) 
	{
		while(!player.isDead() && !monster.isDead())
		{
			System.out.println("1. Attack\n" +
							   "2. Escape");
			int input = Input.getIntRange("Choose option: ", 1, 2);
			
			if(input == 1) pre_emptive_Strike(player, false);
			else if(input == 2)
			{
				System.out.println("Running away");
				break;
			}
		}
	}

	public void fight(Brawler player) 
	{
		while(!player.isDead() && !monster.isDead())
		{
			System.out.println("1. Attack\n" +
							   "2. Escape");
			int input = Input.getIntRange("Choose option: ", 1, 2);
			
			if(input == 1) pre_emptive_Strike(player, true);
			else if(input == 2)
			{
				System.out.println("Running away");
				break;
			}
		}
	}
	
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
