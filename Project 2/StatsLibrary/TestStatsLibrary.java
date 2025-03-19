/*
 * Cachary Tolentino
 * TestStatsLibrary is a tester class containing various call operations for all methods in the StatsLibrary class.
 */

public class TestStatsLibrary {
    public static void main(String[] args) {

        //StatsLibrary object 
        StatsLibrary tester = new StatsLibrary();

        //Testing Poisson distribution
        System.out.println("Given Lambda: 1 and Y: 0 the poisson distribution is " + tester.poissonDistribution(1, 0));
        System.out.println("Given Lambda: 1 and Y: 0 the variance of the poisson distribution is " + tester.poissonVariance(1));
        System.out.println("Given Lambda: 1 and Y: 0 the standard deviation of the poisson distribution is " + tester.poissonStandardDeviation(1));

    }
}