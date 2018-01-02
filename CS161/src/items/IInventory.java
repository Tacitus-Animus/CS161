package items;

public interface IInventory {
	
	public int getMaxSize();
	
	public boolean hasEmptyInventory();
	
	public int getInventorySize();
	
	public boolean hasFullInventory();
	
	public void printInventory();
			
	public void addItem(Item item);

	void removeItem(Item item);

	Item getItem(int index);

}
