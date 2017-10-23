package world;

import units.Fighter;

public class Portal extends Location {

	Map destination;
	
	public Portal(Map destination) {
		this.destination = destination;
		super.setSymbol('O');
		super.setSolid(false);
	}

	public void teleport(Fighter player) {
		System.out.println("Entering new Map.");
		player.enterMap(destination, 1, 1);
	}
	
	@Override
	public void setOnLocation(Fighter player) {
		teleport(player);
	}
	
}
