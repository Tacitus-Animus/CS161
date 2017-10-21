import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import units.monster.Monsters;
import utils.io.Input;

public class Lab_8_AJP {

	public static void main(String[] args) 
	{
		Fighter fighter = getFighterType();
		
		Monsters monsters = new Monsters("MONSTERLIST.txt");
				
	}

	private static Fighter getFighterType() 
	{	
		System.out.println("1. Fighter\n" +
						   "2. Brawler");
		int result = Input.getIntRange("Choose type: (1 or 2", 1, 2);
		
		if(result == 1) return new Fighter();
						return new Brawler();
	}

}
