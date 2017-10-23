
import units.Brawler;
import units.Fighter;
import units.monster.Monster;
import units.monster.Monsters;
import utils.io.Input;
import world.Arena;

public class Lab_8_AJP {

	public static void main(String[] args) throws InterruptedException 
	{
		Fighter fighter = getFighterType();
		
		Monsters monsters = new Monsters("MONSTERLIST.txt");
		
		Monster monster = monsters.search("Elites").get();
		
		Arena arena = new Arena(fighter, monster);
		
		while(!fighter.isDead() && !monster.isDead()) {
			fighter.print();
			monster.print();
			arena.fight();
			Input.getStringln("Enter anything and press 'Enter' to continue fight");
		}
		
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
