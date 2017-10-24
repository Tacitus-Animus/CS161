package game;

import items.Bag;
import units.Fighter;
import utils.io.Input;
import world.Location;
import world.Map;
import world.Spawn;

public class GameState {

	private Fighter player;
		
	public GameState(Fighter player) {
		this.player = player;
	}

	public void getUserInput() 
	{
		System.out.println("1. Move up\n" + 
							"2. Move Down\n" + 
							"3. Move Right\n" + 
							"4. Move Left\n" + 
							"5. Atack Monster\n" +
							"6. Open Inventy");

		int input = Input.getIntRange("Enter Player Options: ", 1, 6);

		switch (input) {
		case 1:
			player.move(0, -1);
			break;
		case 2:
			player.move(0, 1);
			break;
		case 3:
			player.move(1, 0);
			break;
		case 4:
			player.move(-1, 0);
			break;
		case 5:
			proximityCheck();
			break;
		case 6:
			openInventory();
			
		}

	}

	private void openInventory() {
		
		Bag bag = player.getBag();
		
		if(bag.hasEmptyInventory()) System.out.println("Inventory is empty.");
		else while(true)
		{
			bag.printInventory();
			System.out.println(bag.getInventorySize() + 1 + ". Close Inventory");
			
			int item = Input.getIntRange("Choose Item: ", 1, bag.getInventorySize() + 1);
			
			if(item - 1 == bag.getInventorySize()) 
			{
				System.out.println("Inventory Closed."); 
				break;
			} else bag.useItem(player, item - 1);
		}
	}

	private void proximityCheck() 
	{
		boolean found = false;
		
		Map map = player.getMap();

		int centerX = player.getX();

		int centerY = player.getY();
		
		Outer: 
		for (int proxY = centerY - 1; proxY <= centerY + 1; proxY++) 
		{
			for (int proxX = centerX - 1; proxX <= centerX + 1; proxX++) 
			{
				Location location = map.getLocation(proxX, proxY);
				
				if (location instanceof Spawn && ((Spawn)location).hasMonster()) 
				{
					found = true;
					Spawn spawn = (Spawn)location;
					System.out.println("Found Monster Spawn");
					
					player.accept(new Battle(((Spawn)location).getMonster()));
					
					spawn.setOnLocation(player);
					break Outer;
				}			
			}
		}
		if(!found) System.out.println("No monster found.");
	}
	
}
