/*
 * Cachary Tolentino
 * TestStatsLibrary is a tester class containing various call operations for all methods in the StatsLibrary class.
 */

 //Imports
import java.util.Arrays;

public class TestStatsLibrary {


    public static void main(String[] args) {

        //StatsLibrary object 
        StatsLibrary tester = new StatsLibrary();

        //Example inputs
        int[] someNumbers = {1, 2, 5, 5, 5, 5};

        //Testing mean, median, mode, and standard deviation
        System.out.println("The array of numbers: " + Arrays.toString(someNumbers));
        System.out.println("The mean: " + tester.getMean(someNumbers));
        System.out.println("The median: " + tester.getMedian(someNumbers));
        System.out.println("The mode: " + tester.getMode(someNumbers));
        System.out.println("The standard deviation: " + tester.getStandardDeviation(someNumbers));

        //Testing using book examples (permutation and combination)
        System.out.println("Sample points in S: " + tester.permutation(30, 3)); //Example 2.8 - Correct
        System.out.println("Sample points in S: " + tester.combination(5, 2)); //Example 2.11 - Correct

        //Testing probability functions
        System.out.println("The mn value of 3 and 4 is: " + tester.getMNRule(3, 4));

        int[] partition = {2, 3, 4};
        System.out.println("The multinomial coefficient value of n as 9 and paritioned by 2, 3, and 4: " + tester.getMultinomialCoefficient(9, partition));

    }


}