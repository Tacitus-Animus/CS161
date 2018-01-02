package items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import units.Fighter;

public class Bag implements IInventory {

	protected final Fighter PLAYER;
	protected final ArrayList<Item> BAG;
	protected final int MAX_SIZE;
	
	public Bag(Fighter PLAYER, int size) {
		this.PLAYER = PLAYER;
		BAG = new ArrayList<>();
		MAX_SIZE = size;
	}
	
	@Override
	public int getMaxSize() {
		return MAX_SIZE;
	}

	@Override
	public boolean hasEmptyInventory() {
		return BAG.isEmpty();
	}

	@Override
	public int getInventorySize() {
		return BAG.size();
	}

	@Override
	public boolean hasFullInventory() {
		return BAG.size() == MAX_SIZE;
	}

	@Override
	public void printInventory() {
		if(hasEmptyInventory()) 
		{
			System.out.println("Empty Inventory");
		}else {
			IntStream.range(0, getInventorySize()).forEach(index -> 
			{
				String equipt = BAG.get(index) instanceof Armor ? BAG.get(index) == PLAYER.getArmor().orElse(null) ? "[Equipted] ": "[Un-Equipted] " : "";
				System.out.printf("%d. %s\n", index + 1, equipt + BAG.get(index).printItem());
			});
		}
	}

	@Override
	public void addItem(Item item) 
	{
		if(hasFullInventory())
		{
			System.out.println("Inventory Full.");
		}else {
			BAG.add(item);
		}
	}

	@Override
	public void removeItem(Item item) {
		BAG.remove(item);
	}

	@Override
	public Item getItem(int index) {
		return BAG.get(index);
	}

	public List<Throw> getThrowables() {
		return BAG.stream().filter(item -> item instanceof Throw).map(item -> (Throw)item).collect(Collectors.toList());
	}

}
