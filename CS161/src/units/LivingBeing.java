package units;

public abstract class LivingBeing
{
	public final String SIMPLENAME = this.getClass().getSimpleName();
	
	protected float maxHealth, health;
	protected int attack;
	protected int exp;
	
	protected LivingBeing(float health, int attack, int eXP) {
		this.maxHealth = this.health = health;
		this.attack = attack;
		this.exp = eXP;
	}
	
	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
		if(this.health < 0.0f) this.health = 0.0f;
	}
	
	public void heal(float heals) {
		if(heals > 0) health += heals;
		if(health > maxHealth) health = maxHealth;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if(this.attack < 0) this.attack = 0;
	}
	
	public int getEXP() {
		return exp;
	}

	public void setEXP(int eXP) {
		this.exp = eXP;
	}
	
	public boolean isDead() 
	{
		return health <= 0.0f;
	}
	
	public String getStatus() {
		return isDead() ? "[DEAD]" : "[ALIVE]";
	}
	
	
	public void print() {
		System.out.println(this);
	}
	
	public void attack(LivingBeing object)
	{
		if(isDead()) 
		{
			System.out.println(this.SIMPLENAME + " is Dead; Can't attack.");
		}else {
			object.takeDamage(attack);
		}
	}
	
	public abstract boolean takeDamage(float damage);
	
}
