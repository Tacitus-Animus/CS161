package lab_6_Package;

import java.util.Comparator;
import java.util.function.Function;

/**
 * <h1>Lab 6 : Binary Search/Quick Sort Package</h1>
 * This Class is used in Lab_6_Program
 * <p>
 * Monster consists of a name, health, attack, and EXP.
 * @author Alex Paul
 * @version CS161
 * @since 4-OCT-20 
 * @see Lab_6_Program 
 */
class Monster 
{
	/**
	 * Comparator used to compared monsters by String values of their names.
	 */
	public static final Comparator<Monster> COMPARE_BY_NAME = (m1,m2) -> m1.getName().compareToIgnoreCase(m2.getName());
	/**
	 * Comparator used to compared monsters by Float values of their health.
	 */
	public static final Comparator<Monster> COMPARE_BY_HEALTH = (m1,m2) -> Float.compare(m1.getHealth(), m2.getHealth());
	/**
	 * Comparator used to compared monsters by Integer values of their EXP.
	 */
	public static final Comparator<Monster> COMPARE_BY_EXP = (m1,m2) -> Integer.compare(m1.getEXP(), m2.getEXP());

	private String name;
	
	private float health;
	
	private int attack; //Not used
	
	private int eXP; //Not used
	/**
	 * Default constructor call
	 */
	public Monster()
	{
		name = "monster";
		health = 100.0f;
		attack = 10;
		eXP = 0;
	}
	public static int alphabetCompare(String m1, String m2, int index) {
		char ma = Character.toLowerCase(m1.charAt(index));
		char mb = Character.toLowerCase(m2.charAt(index));
		int result = Character.compare(ma, mb);
		try {
			if(result == 0) return alphabetCompare(m1, m2, index + 1);
		}catch(StringIndexOutOfBoundsException e) {
			return 0;
		}
		if(result == -1) return 1;
		return -1;
	}
	/*
	 * Basic getters and setters
	 */
	public String getName() {
		return name;
	}

	public Monster setName(String name) {
		this.name = name;
		return this;
	}

	public float getHealth() {
		return health;
	}

	public Monster setHealth(float health) {
		this.health = health;
		if(this.health < 0.0f) this.health = 0.0f;
		return this;
	}

	public int getAttack() {
		return attack;
	}

	public Monster setAttack(int attack) {
		this.attack = attack;
		if(this.attack < 0) this.attack = 0;
		return this;
	}
	
	public int getEXP() {
		return eXP;
	}

	public Monster setEXP(int eXP) {
		this.eXP = eXP;
		return this;
	}
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof Monster && this == (Monster)other) 
				|| 
			   (other instanceof String && this.name.equals((String)other));
	}
	
	@Override
	public String toString()
	{		
		return "Monster: " + getStatus() +
			 "\nName: " + name +
     		 "\nHealth: " + health +
			 "\nAttack: " + attack +
			 "\nEXP: " + eXP;
	}
	/**
	 * @return [DEAD] or [ALIVE]
	 */
	public String getStatus() {
		return isDead() ? "[DEAD]" : "[ALIVE]";
	}
	/**
	 * This method returns true if user was able to deal damage, else returns false if monster is dead.
	 * <p> The monster can't take damage if it's dead.
	 * @param damage value to be decreased from monster's health.
	 */
	public boolean dealDamage(float damage) 
	{
		if(isDead()) 
		{
			System.out.println(name + " is already dead.");
			return false;
		}
		if(damage <= 0)
		{
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
	 * @return true if monster's health is <= 0.
	 */
	public boolean isDead() 
	{
		return health <= 0.0f;
	}
	/**
	 * Prints out monster description.
	 */
	public void print() {
		System.out.println(this);
	}
	public static Function<String, Monster> stringToMonster(String regex) 
	{
		return data -> 
		{
			String[] parsed = data.split(regex);
			return new Monster().setName(parsed[0])
							    .setHealth(Float.parseFloat(parsed[1]))
							    .setAttack(Integer.parseInt(parsed[2]))
							    .setEXP(Integer.parseInt(parsed[3]));
		};
	}
}

