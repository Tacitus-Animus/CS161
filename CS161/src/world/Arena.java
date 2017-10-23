package world;

import units.Brawler;
import units.Fighter;
import units.monster.Monster;

public class Arena {

	Fighter fighter;
	
	Monster monster;
	
	public Arena(Fighter fighter, Monster monster) {
		this.fighter = fighter;
		this.monster = monster;
	}
	
	public void fight() 
	{
		if(fighter instanceof Brawler) 
		{
			preemptiveStrike(true);
		}else {
			double result = Math.random();
		
			if(result < 0.5) 
			{
				preemptiveStrike(true);
			}else {
				preemptiveStrike(false);
			}
		}
	}
	
	private void preemptiveStrike(boolean attackFirst) {
		if(attackFirst) 
		{
			fighter.attack(monster);
			monster.attack(fighter);
		}else {
			monster.attack(fighter);
			fighter.attack(monster);
		}
	}
	
}
