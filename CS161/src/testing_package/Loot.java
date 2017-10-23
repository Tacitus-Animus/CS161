package testing_package;

import java.util.Optional;

import items.Item;
import units.Fighter;
import world.Location;

public class Loot extends Location {

	Optional<Item> item;
	
	public Loot(Item item) {
		super.setSymbol('L');
		this.item = Optional.of(item);
	}

	@Override
	public void setOnLocation(Fighter player)
	{
		if(item.isPresent() && player.pickupItem(item)) 
		{
			super.setSymbol(' ');
			item = Optional.empty();
		}
	}
	
}
