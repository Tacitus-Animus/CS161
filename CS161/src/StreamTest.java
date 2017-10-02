import lab_5_package.Monster;

public class StreamTest 
{
	public static void main(String[] args)
	{
		Monster.stringToMonster(" ").apply("Henry 100 50 25").print();
	}
}
