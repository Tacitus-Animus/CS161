package world;

import java.io.FileNotFoundException;
import java.util.Iterator;

import units.monster.Monster;
import units.monster.MonsterSort;
import units.monster.Monsters;
import utils.sort.Quicksort;

/**
 * <h1>Room Factory Class</h1>
 * This class creates Rooms linked together populated with monsters that get increasingly more lethal.
 * @author Alex Paul
 * @version CS161
 * @since 01-DEC-2017 
 */
public class LinkedRooms 
{			
	/**
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static Room getLinkedRooms() throws FileNotFoundException 
	{
		Monsters monsters = new Monsters("MONSTERLIST.txt"); //$NON-NLS-1$
		
		monsters.sort(new Quicksort<>(), MonsterSort.BY_ATTACK);
					
		Iterator<Monster> monsterIterator = monsters.getIterator();
				
		Room root = new Room(monsterIterator.next());
		
		Room selected = root;
		
		while(monsterIterator.hasNext()) 
		{
			selected.setNext(new Room(monsterIterator.next()));
			selected = selected.getNext();
		}
		return root;
	}
}
