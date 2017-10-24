package items;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Bag implements IInventory {

	private final Item[] bag;
	
	public Bag(int size) {
		bag = new Item[size];
	}
	
	@Override
	public int getMaxSize() {
		return bag.length;
	}

	@Override
	public boolean hasEmptyInventory() {
		// TODO Auto-generated method stub
		return Arrays.asList(bag).stream().allMatch(null);
	}

	@Override
	public int getInventorySize() {
		return Arrays.asList(bag).stream()
				.reduce(0, (quantity, item) -> item == null ? 0 : 1, (initial, result) -> initial + result);
	}

	@Override
	public boolean hasFullInventory() {
		return Arrays.asList(bag).stream().noneMatch(null);
	}

	@Override
	public void printInventory() {
		if(hasEmptyInventory()) 
		{
			System.out.println("Empty Inventory");
		}else {
			IntStream.range(0, getInventorySize()).forEach(index -> 
			{
				System.out.printf("%d. %s\n", index + 1, bag[index] == null ? "Nothing." : bag[index].printItem());
			});
		}
	}

	@Override
	public void addItem(Item item) 
	{
		if(hasFullInventory())
		{
			Arrays.asList(bag).stream().filter(null).findFirst().map(nullItem -> nullItem = item);
		}else {
			System.out.println("Inventory Full.");
		}
	}

	@Override
	public void removeItem(Item index) {
		Arrays.asList(bag).stream().filter(item -> item == index).findFirst().map(item -> item == null);
	}
	
	public boolean pickupItem(Item item) 
	{
		if(hasFullInventory())
		{
			System.out.println("Couldn't pick up " + item.getItemName());
			return false;
		}else {
			System.out.println("Picked up " + item.getItemName());
			addItem(item);
			return true;
		}
	}

}
