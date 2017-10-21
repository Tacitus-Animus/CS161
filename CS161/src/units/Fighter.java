package units;

import java.util.Random;

/**
 * <h1>Fighter Class</h1>
 * This Class is used in CS161
 * <p>
 * Fighter consists of level, health, attack, MaxEXP and EXP.
 * @author Alex Paul
 * @version CS161
 * @since 18-OCT-2017 
 */
public class Fighter extends LivingBeing 
{
	Random randomGenerator = new Random();
	
	protected int level = 1;
	
	protected int maxEXP = 40 + (level * 10);
	
	protected int defense = 1;
	
	public Fighter() {
		super(100.0f, 5, 0);
	}

	public int getMaxEXP() {
		return maxEXP;
	}

	@Override
	public String toString() {
		 return "Fighter: " + getStatus() +
	     		 "\nHealth: " + health +
				 "\nAttack: " + attack +
				 "\nEXP: " + eXP;
	}
	
	@Override
	public void attack(LivingBeing object) 
	{
		if(isDead()) 
		{
			System.out.println(this.getClass().getSimpleName() + " is Dead");
		}else {
			object.takeDamage(attack);
			this.gainEXP(object.eXP);
		}
	}

	/**
	 * This method increases Fighters eXP up to MaxEXP;
	 * @param eXP
	 */
	private void gainEXP(int eXP) 
	{
		this.eXP += eXP;
		if(this.eXP > maxEXP) levelUp();
	}
	
	private void levelUp() 
	{
		level++;
		health += (float) randomGenerator.nextInt(6) + 5;
		attack += randomGenerator.nextInt(4) + 4;
		defense += randomGenerator.nextInt(4) + 1;
		maxEXP = 40 + (level * 10);
		if(eXP > maxEXP) levelUp();
	}

	/**
	 * This method returns true if this Fighter was able to take damage, 
	 * <p>returns false if monster is dead or damage less than one.
	 * @param damage value to be decreased from monster's health.
	 */

	@Override
	public boolean takeDamage(float damage) 
	{
		float resultantDamage = damage - defense;
		
		if(isDead()) 
		{
			System.out.println("Fighter is already dead.");
			return false;
		}
		
		if(resultantDamage <= 0)
		{
			System.out.println("No damage inflicted to fighter");
			return false;
		}else{
			System.out.println("Damage dealt to Fighter.");
			health -= resultantDamage;
		}
		
		if(isDead())
		{
			System.out.println("Fighter was killed.");
			health = 0.0f;
		}
		return true;
	}
}
