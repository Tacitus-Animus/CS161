package units.monster;

import java.util.function.Function;

import units.LivingBeing;

/**
 * <h1>Monster Class</h1>
 * This Class is used in CS161
 * <p>
 * Monster consists of a name, health, attack, and EXP.
 * <p>
 * This is more or less than a data container.
 * @author Alex Paul
 * @version CS161
 * @since 4-OCT-2017
 */
public class Monster extends LivingBeing implements Cloneable
{
	private String name;
		
	public Monster(String name, float health, int attack, int eXP) {
		super(health, attack, eXP);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{		
		return SIMPLENAME + " " + getStatus() +
			 "\nName: " + name +
     		 "\nHealth: " + health + "/" + maxHealth +
			 "\nAttack: " + attack +
			 "\nEXP: " + exp;
	}
	
	/**
	 * This method returns true if this Monster was able to take damage, 
	 * <p>returns false if monster is dead or damage is less than one.
	 * @param damage value to be decreased from monster's health.
	 */
	@Override
	public boolean takeDamage(float damage) 
	{
		if(isDead()) {
			System.out.println(name + " is already dead; Can't take damage.");
			return false;
		}
		if(damage <= 0) {
			System.out.println("No damage inflicted to " + name + ".");
			return false;
		}
		
		System.out.println("Damage dealt to " + name + ".");
		
		health -= damage;
		
		if(isDead())
		{
			System.out.println(name + " was killed.");
			health = 0.0f;
		}
		return true;
	}
	
	/**
	 * 
	 * @param regex - The String used to split applied String by.
	 * @return Monster Object with parsed monster attributes from applied string.
	 */
	public static Function<String, Monster> stringToMonster(String regex) 
	{
		return data -> 
		{
			String[] parsed = data.split(regex);
			String name = parsed[0];
			float health = Float.parseFloat(parsed[1]);
			int attack = Integer.parseInt(parsed[2]);
			int exp = (int)((2*attack) + (health/5));	  //For Balance Sake.
			return new Monster(name, health, attack, exp);
		};
	}
	
	
	@Override 
	public Object clone()
	{
		return new Monster(name, health, attack, exp);
	}
	
}

