package units;

import java.util.Random;
import game.Battle;
import items.Bag;
import items.Item;
import world.Location;
import world.Map;

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
	private Bag bag = new Bag(10);
	
	private Map map;
	
	protected int locationX;
	
	protected int locationY;
	
	protected Random randomGenerator = new Random();
	
	protected int level = 1;
	
	protected int maxEXP = 40 + (level * 10);
	
	protected int defense = 1;
	
	public Fighter() {
		super(100.0f, 5, 0);
	}

	@Override
	public String toString() {
		 return simpleName + " " + getStatus() +
	     		 "\nHealth: " + health + "/" + maxHealth +
				 "\nAttack: " + attack +
				 "\nEXP: " + exp + "/" + maxEXP +
				 "\nDefense: " + defense +
				 "\nLevel: " + level;
	}
	
	@Override
	public void attack(LivingBeing object) 
	{
		super.attack(object);
		this.gainEXP(object.exp);
	}

	/**
	 * This method increases Fighters eXP up to MaxEXP;
	 * @param eXP
	 */
	protected void gainEXP(int eXP) 
	{
		this.exp += eXP;
		if(this.exp >= maxEXP) levelUp();
	}
	
	protected void levelUp() 
	{
		System.out.println("Level Up!");
		exp = 0;
		maxEXP = 40 + (level * 10);
		level++;
		maxHealth += (float) randomGenerator.nextInt(6) + 5;
		attack += randomGenerator.nextInt(4) + 4;
		defense += randomGenerator.nextInt(4) + 1;
		//if(eXP > maxEXP) levelUp();
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
			System.out.println(simpleName + " is already dead.");
			return false;
		}
		
		if(resultantDamage <= 0)
		{
			System.out.println("No damage inflicted to fighter");
			return false;
		}else{
			System.out.println("Damage dealt to " + simpleName);
			health -= resultantDamage;
		}
		
		if(isDead())
		{
			System.out.println(simpleName + " was killed.");
			health = 0.0f;
		}
		return true;
	}

	
	
	public void printMap() {
		map.print();
	}

	public void enterMap(Map map, int x, int y) {
		this.map = map;
		locationX = x;
		locationY = y;
		map.setFighter(this);
	}

	public int getY() {
		return locationY;
	}
	public int getX() {
		return locationX;
	}
	
	public void move(int x, int y) 
	{
		Location location = map.getLocation(locationX + x, locationY + y);
		
		if(location.isSolid()) 
		{
			System.out.println("Can't move here.");
		}else {
			locationX += x;
			locationY += y;
			
			location.setOnLocation(this);
		}
	}

	public void accept(Battle battle) {
		battle.fight(this);
	}
	
	public boolean hasFullHealth() {
		return health == maxHealth;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public boolean pickupItem(Item item) {
		if(bag.hasFullInventory())
		{
			System.out.println("Couldn't pick up " + item.getItemName());
			return false;
		}else {
			System.out.println("Picked up " + item.getItemName());
			bag.addItem(item);
			return true;
		}
	}
	
	public void useItem(int index) 
	{
		if(bag[index] == null) 
		{
			System.out.println("Can't use.");
		}else {
			bag[index].useby(player);
		}
	}
	
}
