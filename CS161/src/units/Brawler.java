package units;

public class Brawler extends Fighter {

	private String monsterName;

	public String getName() {
		return monsterName;
	}

	public void setName(String name) {
		this.monsterName = name;
	}
	
	private void levelUp() {
		level++;
		health += (float) randomGenerator.nextInt(7) + 6;
		attack += randomGenerator.nextInt(5) + 4;
		defense += randomGenerator.nextInt(4) + 1;
		maxEXP = 40 + (level * 10);
		if(eXP > maxEXP) levelUp();
	}
	
}
