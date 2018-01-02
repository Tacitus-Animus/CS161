package world;

import units.monster.Monster;

/**
 * <h1>Lab 11 : Room Class</h1>
 * <p>
 * This is the node which can be doubly linked with other room nodes that contain monsters.
 * The Fighter can move about the room and attack the Monster and teleport to other adjacent rooms.
 * </p>
 * <a href="https://www.cs.cmu.edu/~adamchik/15-121/lectures/Linked%20Lists/linked%20lists.html"> LinkedList</a>
 * @author Alex Paul
 * @version CS161
 * @since 01-DEC-2017 
 */
public class Room extends Map {

	private Room prev, next;
	
	private Monster monster;
		
	public Room(Monster monster) {
		super(5, 5);
		this.monster = monster;
		if(monster != null) this.setLocation(new Spawn(monster), 3, 2);
	}

	public Room getPrev() {
		return prev;
	}

	private void setPrev(Room prev) 
	{
		this.prev = prev;
		this.setLocation(new Portal((Map)prev, 3, 2), 0, 2);
	}

	public Room getNext() {
		return next;
	}

	public void setNext(Room next) 
	{
		this.next = next;
		this.setLocation(new Portal((Map)next, 1, 2), 4, 2);
		next.setPrev(this);
	}

	public Monster getMonster() {
		return monster;
	}

	public boolean hasPrev(){
		return prev != null;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public int getIndex() {
		return hasPrev() ? 1 + prev.getIndex() : 0;
	}
	
	public int getSize() {
		return 1 + getIndex() + forwardCount();
	}

	private int forwardCount() 
	{
		return hasNext() ? 1 + next.forwardCount() : 0;
	}
	
	public Room getRoom(int index) 
	{
		int result = index - getIndex();
		if(result == 0) return this;
		if(result < 0)  return prev.getRoom(index);
		if(result > 0)  return next.getRoom(index);
						return null;
	}

	public void remove(int index)
	{
		getRoom(index).remove();
	}
	//Can't remove head and tail nodes.
	private void remove() 
	{
		if(!hasPrev()) 
		{
			monster = next.monster;
			next = next.next;
			next.prev = this;
		}else if(!hasNext())
		{
			monster = prev.monster;
			prev = prev.prev;
			prev.next = this;
		}else {
			next.setPrev(prev);
			prev.setNext(next);
			next = prev = null;
			monster = null;
		}
	}
	
	public String peek() 
	{
		String index = String.valueOf(getIndex() + 1) + ". ";
		String name = monster == null ? "Empty" : monster.getName();
		return "Room " + index + name;
	}
	
	
	private String backwardPeek()
	{
		return !hasPrev() ? "" : prev.backwardPeek() + 
								   prev.peek() + "\n";
	}
	
	private String forwardPeek() 
	{
		return !hasNext() ? "" : next.peek() + "\n" + 
								    next.forwardPeek();
	}
	
	/**
	 * @return List form of monster names from all the linked Rooms.
	 */
	public String peekAll() 
	{
		return backwardPeek() +
			   peek() + "\n" + 
			   forwardPeek();
	}

	public void add(int index, Room newRoom) 
	{
		Room prevRoom = getRoom(index);
		
		Room nextRoom = prevRoom.next;
		
		prevRoom.setNext(newRoom);
		
		newRoom.setNext(nextRoom);
	}

}
