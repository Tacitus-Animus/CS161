package testing_package;
import lab_5_package.Monster;

public class Testing
{
   public static void main(String[] args)
   {	   
	   Monster monster = Monster.stringToMonster(" ").apply("Henry 100 50 25");
	   
	   monster.print();
	   
	   String myHealth = monster.getName();
	   
	   myHealth = "Jerry";
	   
	   monster.print();
	   
   }
}     