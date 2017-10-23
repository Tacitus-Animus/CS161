package world;

import java.util.Optional;

import units.Fighter;
import units.monster.Monster;

public class Spawn extends Location {

	private Optional<Monster> monster;
	
	public Spawn(Monster monster) {
		this.monster = Optional.of(monster);
		super.setSymbol('M');
		super.setSolid(true);
	}

	public boolean hasMonster(){
		return monster.isPresent();
	}
	
	public Monster getMonster() {
		return monster.get();
	}
	
	@Override
	public void setOnLocation(Fighter player) 
	{
		if(monster.isPresent() && monster.get().isDead())
		{
			monster = Optional.empty();
			super.setSymbol(' ');
			super.setSolid(false);
		}
	}
}
