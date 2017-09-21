package lab_4_Package;
/**
 * <h1>Lab 4 : File IO, ArrayList - Monster Class</h1>
 * This Class is used in Lab_4_Program
 * <p>
 * Monster consists of a name, health, attack, and EXP.
 * @see Lab_4_Program 
 * @author Alex Paul
 * @version CS161
 * @since 9-SEP-20 
 */
public class Monster 
{
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
	public String toString()
	{		
		return "Monster: " + getStatus() +
			 "\nName: " + name +
     		 "\nHealth: " + health +
			 "\nAttack: " + attack +
			 "\nEXP: " + eXP;
	}
	/**
	 * @return
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
		}else{
			System.out.println("Damage dealt to " + name);
			health -= damage;
			if(isDead())
			{
				System.out.println(name + " was killed.");
				health = 0.0f;
			}
			return true;
		}
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
}

