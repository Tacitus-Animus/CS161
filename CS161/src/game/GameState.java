package game;

import java.util.Optional;

import items.Armor;
import items.Bag;
import units.Fighter;
import utils.io.Input;
import world.Location;
import world.Map;
import world.Spawn;

/**
 * <h1>Lab 11 : GameState Class</h1>
 * This gets user input and controls game play.
 * @author Alex Paul
 * @version CS161
 * @since 01-DEC-2017 
 */
public class GameState {

	final Battle battle = new Battle();
	
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

		String input = Input.getString("Enter Player Options: ");

		switch (input.toLowerCase()) {
		case "1":
			player.move(0, -1);
			break;
		case "2":
			player.move(0, 1);
			break;
		case "3":
			player.move(1, 0);
			break;
		case "4":
			player.move(-1, 0);
			break;
		case "5":
			proximityCheck();
			break;
		case "6":
			openInventory();
		case "grace": System.out.println("Grace = poopy face and sticky mattermeron!");
		default: 
		}

	}

	private void openInventory() {
		
		Bag bag = player.getBag();
		
		if(bag.hasEmptyInventory()) System.out.println("Inventory is empty.");
		else while(true)
		{
			player.printInventory();
			System.out.println(bag.getInventorySize() + 1 + ". Close Inventory");
			
			int index = Input.getIntRange("Choose Item: ", 1, bag.getInventorySize() + 1);
			
			if(index - 1 == bag.getInventorySize()) 
			{
				System.out.println("Inventory Closed."); 
				break;
			}else if(bag.getItem(index - 1) instanceof Armor)
			{
				player.setArmor(Optional.of((Armor)bag.getItem(index - 1)));
			}else {					
				player.useItem(index - 1);
			}
		}
	}

	/**
	 * Checks for Monsters around player in a room and opens battle menu if found.
	 * @throws DeadFighterIsDeadException
	 */
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
					
					battle.setMonster(((Spawn)location).getMonster());
					player.accept(battle);
					
					spawn.setOnLocation(player);
					break Outer;
				}			
			}
		}
		if(!found) System.out.println("No monster found.");
	}

	public void run() 
	{
		while(!player.isDead()) 
	 	{
 		   player.print();
 		   player.printMap();

 		
			getUserInput();
			
	    }		
	}
}
