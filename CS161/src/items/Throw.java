package items;

import units.Fighter;

public class Throw extends Item {

	public final int damage;
	
	public Throw(String itemName, int damage) {
		super('T', itemName);
		this.damage = damage;
	}

	@Override
	public String printItem() {
		return itemName + ": Damage - " + damage;
	}

	@Override
	public boolean useby(Fighter fighter) {
		System.out.println("Can't use.");
		return false;
	}

}
