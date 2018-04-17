package units.monster;

import java.util.function.Function;

import labs.Lab_6_Program;

/**
 * <h1>Lab 6 : Binary Search/Quick Sort Package</h1>
 * This Class contains constant Functions used in printing String value attributes of Monsters.
 * @author Alex Paul
 * @version CS161
 * @since 17-OCT-20 
 * @see Lab_6_Program 
 */
public enum MonsterPrint {

	BY_NAME(Monster::getName),
	BY_HEALTH((monster) -> String.valueOf(monster.getHealth())),
	BY_ATTACK((monster) -> String.valueOf(monster.getAttack())),
	BY_EXP((monster) -> String.valueOf(monster.getEXP())),
	BY_ALL_INFO(Monster::toString);
	
	private Function<Monster, String> printStrategy;
	
	private MonsterPrint(Function<Monster, String> printStrategy) {
		this.printStrategy = printStrategy;
	}

	public Function<Monster, String> get() {
		return printStrategy;
	}
	
}
