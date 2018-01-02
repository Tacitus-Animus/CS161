package world;

import units.monster.Monster;

public class RoomTree extends Map {

	private Monster monster;
	
	private RoomTree prev, left, right;
	
	public RoomTree(Monster monster) {
		super(5, 5);
		this.setMonster(monster);
		if(monster!=null)this.setLocation(new Spawn(monster), 2, 2);
		this.getLocation(1, 2).setSolid(true).setSymbol('W');
		this.getLocation(3, 2).setSolid(true).setSymbol('W');
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public RoomTree getPrev() {
		return prev;
	}

	public void setPrev(RoomTree prev) {
		this.prev = prev;
	}

	public RoomTree getLeft() {
		return left;
	}

	public void setLeft(RoomTree left) {
		this.left = left;
		this.setLocation(new Portal(left, 2, 1), 1, 4);
		left.setPrev(this);
		left.setLocation(new Portal(this, 1, 3), 2, 0);
	}

	public RoomTree getRight() {
		return right;
	}

	public void setRight(RoomTree right) {
		this.right = right;
		this.setLocation(new Portal(right, 2, 1), 3, 4);
		right.setPrev(this);
		right.setLocation(new Portal(this, 3, 3), 2, 0);
	}

	public void add(Monster newMonster) 
	{
		int thisAttack = monster.getAttack();
		int nextAttack = newMonster.getAttack();
		
		
		
	}

	
	
}
