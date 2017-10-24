package items;

import units.Fighter;

public class Potion extends Item{
	
	int uses, maxUses;
	
	float heals;
	
	public Potion(String itemName, float heals, int uses) {
		super('P', itemName);
		this.heals = heals;
		this.uses = this.maxUses = uses;
	}

	@Override
	public boolean useby(Fighter fighter) {
		if(fighter.hasFullHealth()) {
			System.out.println(fighter.simpleName + " is at full health.");
			return false;
		}else {
			System.out.println(fighter.simpleName + " is healed.");
			uses--;
			fighter.heal(heals);
			
			if(uses <= 0) fighter.getBag().removeItem(this);
			
			return true;
		}
	}

	@Override
	public String printItem() {
		return itemName + " : " + uses + "/" + maxUses;		
	}

}
