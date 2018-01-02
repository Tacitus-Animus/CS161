package labs;

import game.GameState;
import items.Armor;
import items.Crystal;
import items.Potion;
import items.Throw;
import units.Brawler;
import units.Fighter;
import utils.io.Input;
import world.LinkedRooms;
import world.Loot;
import world.Room;

/**
 * <h1>Lab 11 : Main Class File</h1>
 * This is the main program file which creates a Dungeon Crawling game.
 * @author Alex Paul
 * @version CS161
 * @since 01-DEC-2017 
 */
public class Lab_11_AJP 
{
	public static void main(String[] args) 
	{	
		final Room world = LinkedRooms.getLinkedRooms();
				
		world.add(9, new Room(null));
		
		Room bonusRoom = world.getRoom(10);
			bonusRoom.setLocation(new Loot(new Potion("Elixer", 100, 5)), 1, 1);
			bonusRoom.setLocation(new Loot(new Armor("Prinny's Cloak", 10000, 5)), 1, 3);
			bonusRoom.setLocation(new Loot(new Throw("Frieza's Bane", 10000)), 3, 1);
			bonusRoom.setLocation(new Loot(new Crystal("Teleport Crystal", world.getRoom(29))), 3, 3);
		
		Fighter player = getFighterType();
	 	  		
		System.out.println("\nCan you beat the BOSS!?\n");
		
		Room selected = world;
		
		Menu:
		while(true)
		{
			System.out.println("1. Start game" +
							 "\n2. View Dungeon" +
							 "\n3. Select Room" +
							 "\n4. Remove Room" +
							 "\n5. Add Room");
			
			switch(Input.getIntRange("Choose option: (1-5) ", 1, 5)) 
			{
			case 1: player.enterMap(selected, 1, 2);
				break Menu;
			case 2: System.out.println(world.peekAll());
				break;
			case 3: selected = world.getRoom(Input.getIntRange("Choose room index to start in: ", 1, world.getSize()) - 1);
				break;
			case 4: world.remove(Input.getIntRange("Remove Room at index: ", 1, world.getSize()) - 1);
				break;
			case 5: world.add(Input.getIntRange("Add Room at index: ", 1, world.getSize()) - 1, new Room(null));
				break;
			}
		}

		GameState gameState = new GameState(player);
		
			gameState.run();		
	
	}
		
	private static Fighter getFighterType()
	{	
		System.out.println("1. Fighter\n" +
						   "2. Brawler");
		int result = Input.getIntRange("Choose type (1 or 2): ", 1, 2);
		
		if(result == 1) return new Fighter();
						return new Brawler();
	}

}
