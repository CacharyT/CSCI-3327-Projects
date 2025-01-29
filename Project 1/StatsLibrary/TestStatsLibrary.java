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

        //Method testing and their results
        System.out.println("The array of numbers: " + Arrays.toString(someNumbers));
        System.out.println("The mean: " + tester.getMean(someNumbers));
        System.out.println("The median: " + tester.getMedian(someNumbers));
        System.out.println("The mode: " + tester.getMode(someNumbers));
        System.out.println("The standard deviation: " + tester.getStandardDeviation(someNumbers));
    }


}