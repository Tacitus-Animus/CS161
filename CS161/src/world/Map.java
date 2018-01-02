package world;

import units.Fighter;

public class Map {

	private Fighter fighter;
	
	private Location[][] map;
	
	public Map(int length, int width) {
		map = new Location[width][length];
		create();
	}
	
	private void create() 
	{
		for(int width = 0;  width < map.length; width++)
		{
			for(int length = 0; length < map[0].length; length++)
			{
				if(width == 0 || width == map.length - 1 || length == 0 || length == map[0].length - 1)
				{
					map[width][length] = new Location().setSolid(true)
									  				   .setSymbol('W');
				}else {
					map[width][length] = new Location();
				}
			}
		}
	}
	
	public void print() {
		
		String layout = "";
		
		for(int width = 0;  width < map.length; width++)
		{
			for(int length = 0; length < map[0].length; length++)
			{
				if(fighter.getY() == width && fighter.getX() == length)
				{
					layout += "F" + " ";
				}else {
				layout += map[width][length].getSymbol() + " ";
				}
			}
			layout += "\n";
		}
		System.out.println(layout);
	}
	
	public void setLocation(Location spawn, int length, int width) {
		map[width][length] = spawn;
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}
	
	public Location getLocation(int x, int y) {
		return map[y][x];
	}
	
}
