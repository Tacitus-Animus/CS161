package Lab_2_Package;

/**
 * <h1>Lab 2 : Objects - Monster Class</h1>
 * This Class is used in Lab_2_Program
 * @see Lab_2_Program 
 * @author Alex Paul
 * @version CS161
 * @since 9-SEP-17 
 */

public class Monster {

	private String name;
	
	private float health;
	
	private int attack; //Not used
	
	/**
	 * Default constructor call
	 */
	public Monster(){
		name = "monster";
		health = 100.0f;
		attack = 10;
	}
	
	/**
	 * Custom constructor call
	 * @param name set name
	 * @param health set health
	 * @param attack set attack
	 */
	public Monster(String name, float health, int attack) {
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	/**
	 * Basic getters and setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	@Override
	public String toString(){
		return "Monster:" +
			 "\nName: " + name +
			 "\nHealth: " + health +
			 "\nAttack: " + attack;
	}
	
	/**
	 * This method decreases monsters health and notifies user if the monster is Dead
	 * @param flt value to be decreased from monster's health.
	 */
	public void dealDamage(float flt) {
		health -= flt;
		if(isDead()) {
			System.out.println(name + " died...");
		}
	}
	
	/**
	 * @return true if monster's health is <= 0.
	 */
	public boolean isDead() {
		return health <= 0;
	}
	
	/**
	 * Prints out monster description.
	 */
	public void print() {
		System.out.println(this);
	}
	
}
