package world;

import units.Fighter;

public class Portal extends Location {

	Map destination;

	int locX, locY;
	
	public Portal(Map destination, int locX, int locY) {
		super.setSymbol('O');
		super.setSolid(false);
		this.destination = destination;
		this.locX = locX;
		this.locY = locY;
	}

	public void teleport(Fighter player) {
		System.out.println("Entering new Map.");
		player.enterMap(destination, locX, locY);
	}
	
	@Override
	public void setOnLocation(Fighter player) {
		teleport(player);
	}
	
}
