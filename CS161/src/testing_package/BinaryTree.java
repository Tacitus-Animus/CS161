package testing_package;

import java.util.Comparator;
import java.util.Iterator;

import units.monster.Monster;
import units.monster.MonsterSort;
import units.monster.Monsters;
import world.RoomTree;

public class BinaryTree 
{
	final private Monsters monsters;
	
	final private Comparator<Monster> compareAttack = MonsterSort.BY_ATTACK.get();
	
	final private Iterator<Monster> iterator;
	
	private RoomTree tree;
	
	public BinaryTree(Monsters monsters) 
	{
		this.monsters = monsters;
		iterator = monsters.getIterator();
		
		tree = new RoomTree(iterator.next());
		
		while(iterator.hasNext())
		{
			tree.add(iterator.next());
		}
		
	}
	
}
