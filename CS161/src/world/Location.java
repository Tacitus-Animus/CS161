package world;

import units.Fighter;

public class Location {

	private char symbol = ' ';
	
	private boolean isSolid = false;

	public char getSymbol() {
		return symbol;
	}

	public Location setSymbol(char symbol) {
		this.symbol = symbol;
		return this;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public Location setSolid(boolean isSolid) {
		this.isSolid = isSolid;
		return this;
	}
	
	public void setOnLocation(Fighter player) {
	}

}
