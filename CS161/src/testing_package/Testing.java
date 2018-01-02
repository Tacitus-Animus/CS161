package testing_package;

import java.io.FileNotFoundException;
import java.util.Iterator;

import exceptions.NotEnoughMonsterException;
import game.GameState;
import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import units.monster.MonsterSort;
import units.monster.Monsters;
import utils.sort.Quicksort;
import world.RoomTree;

//testing binary nodes...
public class Testing
{
   public static void main(String[] args) throws FileNotFoundException, NotEnoughMonsterException 
   {	 	  
	   //load monsters and sort...
	   Monsters monsters = new Monsters("MONSTERLIST.txt");
	   			monsters.sort(new Quicksort<>(), MonsterSort.BY_ATTACK);
	   
	   //create iterator...
	   Iterator<Monster> iterator = monsters.getIterator();
	   
	   //Create and set binary tree...
	   RoomTree root = new RoomTree(iterator.next());
	   			root.setLeft(new RoomTree(iterator.next()));
	   			root.setRight(new RoomTree(iterator.next()));
	   			root.getLeft().setLeft(new RoomTree(iterator.next()));
	   
	   
	   //create player and enter GUI representation of binary tree...
	   Fighter player = new Brawler();
	   		   player.enterMap(root, 2, 1);
	   
	   //create game and start...
	   GameState gameState = new GameState(player);
	   
	   gameState.run();
	   
   }
}     