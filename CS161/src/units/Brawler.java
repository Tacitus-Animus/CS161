package units;

import game.Battle;
import units.monster.Monster;

public class Brawler extends Fighter {

	private String last_attacked_monster = null;

	public String getName() {
		return last_attacked_monster;
	}

	public void setName(String name) {
		this.last_attacked_monster = name;
	}
	

	@Override
	public void attack(LivingBeing object)
	{	
		super.attack(object); //Inflict initial damage and get initial exp.
		
		if(last_attacked_monster != null && last_attacked_monster.equals(((Monster)object).getName()))
		{
			object.takeDamage(attack * 0.5f); //Take more damage. (super + this = 50% more)
			if(object.isDead()) gainEXP(object.exp * 2); //Get's more exp. (super + this = 3x more)
		}
		
		if (object instanceof Monster) last_attacked_monster = ((Monster) object).getName();
		
	}
	
	@Override
	protected void levelUp() 
	{
		System.out.println("Level Up!");
		exp = 0;
		level++;
		maxEXP = 40 + (level * 10);
		maxHealth += (float) randomGenerator.nextInt(7) + 6;
		attack += randomGenerator.nextInt(5) + 4;
		defense += randomGenerator.nextInt(4) + 1;
		//if(exp > maxEXP) levelUp();
	}
	
	@Override
	public void accept(Battle battle) {
		battle.fight(this);
	}
	
}
