package items;

import units.Fighter;

public abstract class Item {

	protected char symbol;
	
	protected String itemName;

	protected Item(char symbol, String itemName) {
		this.symbol = symbol;
		this.itemName = itemName; 
	}
	
	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public abstract String printItem();

	public abstract boolean useby(Fighter fighter);
	
}
