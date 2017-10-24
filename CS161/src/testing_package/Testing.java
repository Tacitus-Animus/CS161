package testing_package;

import game.GameState;
import items.Potion;
import units.Brawler;
import units.monster.Monster;
import world.*;

public class Testing
{
   public static void main(String[] args)
   {	 	  
	   Map startingPoint = new Map(5,5); 
	   	
	   startingPoint.setLocation(new Spawn(new Monster("Henry", 30, 10, 10)), 2, 2);
	   
	   Map end = new Map(10, 5);
	   
	   end.setLocation(new Loot(new Potion("Potion", 10, 3)), 7, 2);
	   
	   startingPoint.setLocation(new Portal(end), 2, 4);
 	   
 	   Brawler player = new Brawler();
 	   
 	   player.enterMap(startingPoint, 1, 1);
 	   
 	   GameState gameState = new GameState(player);
 	   
 	   while(!player.isDead()) 
 	   {
 		   player.print();
 		   
 		   player.printMap();

 		   gameState.getUserInput();
 	   }
	 	   
   }
}     