/**
 * 
 */
package items;

import units.Fighter;
import world.Room;

/**
 * @author AJP
 *
 */
public class Crystal extends Item {

	Room room;
	
	public Crystal(String name, Room room) {
		super('C', name);
		this.room = room;
	}

	@Override
	public String printItem() {
		return this.itemName + ": " + room.peek();
	}

	@Override
	public boolean useby(Fighter fighter) {
		System.out.println("Teleporting!");
		fighter.enterMap(room, 1, 2);
		fighter.getBag().removeItem(this);
		return true;
	}

}
