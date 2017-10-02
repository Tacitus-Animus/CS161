import lab_5_package.Monster;
import utils.Print;

public class StreamTest 
{
	public static void main(String[] args)
	{
		Print.out(Monster.stringToMonster(" ").apply("Henry 100 50 25").toString());
	}
}
