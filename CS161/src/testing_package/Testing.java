package testing_package;

import game.GameState;
import items.Potion;
import units.Brawler;
import units.monster.Monster;
import world.Map;
import world.Portal;
import world.Spawn;

public class Testing
{
   public static void main(String[] args)
   {	 
	   Map start = new Map(5,5); 
	   	start.setLocation(new Spawn(new Monster("Henry", 30, 10, 10)), 2, 2);
	   
	   Map end = new Map(10, 5);
	   
	   	end.setLocation(new Loot(new Potion("Potion", 10, 3)), 7, 2);
	   
	   start.setLocation(new Portal(end), 2, 4);
 	   
 	   Brawler player = new Brawler();
 	   
 	   player.enterMap(start, 1, 1);

 	   player.takeDamage(50);
 	   
 	   GameState gameState = new GameState(player);
 	   
 	   while(!player.isDead()) 
 	   {
 		   player.print();
 		   
 		   player.getMap().print();

 		   gameState.getUserInput();
 	   }
	 	   
   }
}     