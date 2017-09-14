package lab_2_Package;

/**
 * <h1>Lab 2 : Objects - Monster Class</h1>
 * This Class is used in Lab_2_Program
 * <p>
 * Monster consists of a name, health, and attack.
 * @see Lab_2_Program 
 * @author Alex Paul
 * @version CS161
 * @since 9-SEP-17 
 */

public class Monster 
{
	private String name;
	
	private float health;
	
	private int attack; //Not used
	
	private int eXP;
	
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
	
	/**
	 * Custom constructor call
	 * @param name set name
	 * @param health set health
	 * @param attack set attack
	 */
	public Monster(String name, float health, int attack, int eXP)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.eXP = eXP;
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
		return this;
	}

	public int getAttack() {
		return attack;
	}

	public Monster setAttack(int attack) {
		this.attack = attack;
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
	public String toString()
	{
		return !isDead() ? 
			 "Monster: [ALIVE]" +
			 "\nName: " + name +
			 "\nHealth: " + health +
			 "\nAttack: " + attack +
			 "\nEXP: " + eXP 
			 :
			 "Monster: [DEAD]";
	}
	
	/**
	 * This method decreases monsters health and notifies user if the monster is dead.
	 * @param flt value to be decreased from monster's health.
	 */
	public boolean dealDamage(float flt) 
	{
		if(isDead()) 
		{
			System.out.println(name + " is already dead.");
			return false;
		}
		else
		{
			health -= flt;
			return true;
		}
	}
	
	/**
	 * @return true if monster's health is <= 0.
	 */
	public boolean isDead() 
	{
		return health <= 0;
	}
	
	/**
	 * Prints out monster description.
	 */
	public void print() {
		System.out.println(this);
	}
}
