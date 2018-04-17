package units.monster;

import java.util.function.BiFunction;

import labs.Lab_6_Program;

/**
 * <h1>Lab 6 : Binary Search/Quick Sort Package</h1>
 * This Class contains constant BiFunctions used in searching Arraylists of Monsters
 * <p> using value from user compared to attribute of monster.
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-20 
 * @see Lab_6_Program 
 */
public enum MonsterSearch 
{
	BY_NAME((search, monster) -> search.compareToIgnoreCase(monster.getName())),
	BY_HEALTH((search, monster) -> Float.valueOf(search).compareTo(monster.getHealth())),
	BY_ATTACK((search, monster) -> Integer.valueOf(search).compareTo(monster.getEXP())),
	BY_EXP((search, monster) -> Integer.valueOf(search).compareTo(monster.getEXP()));
	
	private BiFunction<String, Monster, Integer> searchStrategy;
	
	private MonsterSearch(BiFunction<String, Monster, Integer> searchStrategy) {
		this.searchStrategy = searchStrategy;
	}
	
	public BiFunction<String, Monster, Integer> get() {
		return searchStrategy;
	}
}
