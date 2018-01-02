package world;

import java.util.Optional;

import items.Item;
import units.Fighter;

public class Loot extends Location {

	Optional<Item> item;
	
	public Loot(Item item) {
		super.setSymbol(item.getSymbol());
		this.item = Optional.of(item);
	}

	@Override
	public void setOnLocation(Fighter player)
	{
		if(item.isPresent() && player.pickupItem(item.get())) 
		{
			super.setSymbol(' ');
			item = Optional.empty();
		}
	}
	
}
