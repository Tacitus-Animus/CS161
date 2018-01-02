package items;

import java.util.Optional;

import units.Fighter;

public class Armor extends Item {

	private int defence, durability;
	
	public Armor(String itemName, int defence, int durability) {
		super('A', itemName);
		this.defence = defence;
		this.durability = durability;
	}

	public int getDefence() {
		return defence;
	}
	
	public int getDurability() {
		return durability;
	}

	@Override
	public String printItem() {
		return itemName + ": def: " + defence + " dur: " + durability;
	}
	
	@Override
	public boolean useby(Fighter fighter) {
		durability--;
		if(durability == 0) {
			fighter.setArmor(Optional.empty());
			fighter.getBag().removeItem(this);
			return true;
		}
		return false;
	}

}
