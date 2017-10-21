package units.monster;

import java.util.Comparator;

/**
 * <h1>Lab 6 : Binary Search/Quick Sort Package</h1>
 * This Class contains constant comparators used in sorting Arraylists of Monsters.
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-20 
 * @see Lab_6_Program 
 */
public enum MonsterSort
{	
	BY_NAME((m1,m2) -> m1.getName().compareToIgnoreCase(m2.getName())),
	BY_HEALTH((m1,m2) -> Float.compare(m1.getHealth(), m2.getHealth())),
	BY_EXP((m1,m2) -> Integer.compare(m1.getEXP(), m2.getEXP())),
	BY_ATTACK((m1,m2) -> Integer.compare(m1.getAttack(), m2.getAttack()));
	
	Comparator<Monster> strategy;
	
	private MonsterSort(Comparator<Monster> strategy){
		this.strategy = strategy;
	}

	public Comparator<Monster> get() {
		return strategy;
	}
}
