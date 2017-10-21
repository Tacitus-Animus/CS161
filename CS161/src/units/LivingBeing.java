package units;

public abstract class LivingBeing
{
	protected float health;
	protected int attack;
	protected int eXP;
	
	protected LivingBeing(float health, int attack, int eXP) {
		this.health = health;
		this.attack = attack;
		this.eXP = eXP;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
		if(this.health < 0.0f) this.health = 0.0f;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if(this.attack < 0) this.attack = 0;
	}
	
	public int getEXP() {
		return eXP;
	}

	public void setEXP(int eXP) {
		this.eXP = eXP;
	}
	
	/**
	 * @return true if monster's health is <= 0.
	 */
	public boolean isDead() 
	{
		return health <= 0.0f;
	}
	
	/**
	 * @return [DEAD] or [ALIVE]
	 */
	public String getStatus() {
		return isDead() ? "[DEAD]" : "[ALIVE]";
	}
	
	/**
	 * Prints out monster description.
	 */
	public void print() {
		System.out.println(this);
	}
	
	public abstract void attack(LivingBeing object);
	
	public abstract boolean takeDamage(float damage);
	
}
