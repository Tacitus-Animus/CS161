/*

  This program calculates the distance and speed of an
   object falling in a vacuum.

    v = gt
    h = 1.2 g t ^ 2

*/

public class FreeFall
{
   public static void main(String[] args)
   {
      final int LOCATIONS = 10;           // Number of array elements
      final double accelGrav = 9.8;          // Acceleration due to gravity
      double[] speed = new double[LOCATIONS];  // Speed for each element
      double[] distance = new double[LOCATIONS];  // Fall distance for each element
      
      for (int t = 0; t < LOCATIONS; t++) {
        speed[t] = accelGrav * t;
        distance[t] = 0.5 * accelGrav * t * t;
      }
      
      System.out.println("Time (seconds)  Speed  Distance fallen\n");

      for (int t = 0; t < LOCATIONS; t++) {
       if(speed[t] == distance[t]){ // Print the time where the speed is the same as distance once
         System.out.println("The speed and distance are the same number at: " + t + " seconds");
       }else
       System.out.printf("     %d          %2.2f         %3.2f\n", t, speed[t], distance[t]);
       }
   }
}     