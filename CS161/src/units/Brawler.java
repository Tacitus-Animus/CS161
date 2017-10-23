package units;

import game.Battle;
import units.monster.Monster;

public class Brawler extends Fighter {

	private String last_attacked_monster;

	public String getName() {
		return last_attacked_monster;
	}

	public void setName(String name) {
		this.last_attacked_monster = name;
	}
	

	@Override
	public void attack(LivingBeing object) 
	{
		super.attack(object);
		if (object instanceof Monster) {
			last_attacked_monster = ((Monster) object).getName();
		}
	}
	
	@Override
	protected void levelUp() 
	{
		System.out.println("Level Up!");
		level++;
		health += (float) randomGenerator.nextInt(7) + 6;
		attack += randomGenerator.nextInt(5) + 4;
		defense += randomGenerator.nextInt(4) + 1;
		maxEXP = 40 + (level * 10);
		if(exp > maxEXP) levelUp();
	}
	
	@Override
	public void accept(Battle battle) {
		battle.fight(this);
	}
	
}
