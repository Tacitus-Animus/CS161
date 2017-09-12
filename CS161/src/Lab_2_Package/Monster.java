package Lab_2_Package;

public class Monster {

	private String name;
	
	private float health;
	
	private int attack;//how much damage it does; not used
	
	//default
	public Monster(){
		name = "monster";
		health = 100;
		attack = 10;
	}
	//custom
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
	public void dealDamage(float flt) {
		health -= flt;
		if(isDead()) {
			System.out.println(name + " died...");
		}
	}
	public boolean isDead() {
		return health <= 0;
	}
	
}
