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
        System.out.println("Given Lambda: 1 and Y: 0 the expected of the poisson distribution is " + tester.poissonExpected(1));
        System.out.println("Given Lambda: 1 and Y: 0 the variance of the poisson distribution is " + tester.poissonVariance(1));
        System.out.println("Given Lambda: 1 and Y: 0 the standard deviation of the poisson distribution is " + tester.poissonStandardDeviation(1));

        //Testing Tchebysheff
        System.out.println("Given k: 2 and greater than or equal to, the Tchebysheff value is " + tester.tchebysheff(2, true));
        System.out.println("Given k: 2 and less than or equal to, the Tchebysheff value is " + tester.tchebysheff(2, false));

        //Testing Uniform Distribution
        System.out.println("Given a:0 and b:30 the uniform probability distribution is " + tester.uniformDistribution(0, 30));
        System.out.println("Given a:0, b:40, c:0 and d:15 the uniform probability distribution is " + tester.uniformDistributionAlt(0, 40, 0, 15));
        System.out.println("Given a:0 and b:30 the expected value of the uniform probability distribution is " + tester.uniformExpected(0, 30));
        System.out.println("Given a:0 and b:30 the variance value of the uniform probability distribution is " + tester.uniformVariance(0, 30));

        //Testing Gamma Expected and Variance
        System.out.println("Given alpha:2 and beta:3, the expected value is " + tester.gammaExpected(2, 3));
        System.out.println("Given alpha:2 and beta:3, the variance value is " + tester.gammaVariance(2, 3));

        //Testing Chi-square Expected and Variance
        System.out.println("Given v:2, the expected value is " + tester.chiSquareExpected(2));
        System.out.println("Given v:2, the variance value is " + tester.chiSquareVariance(2));

        //Testing Exponential Distribution for a Gamma Function Expected and Variance
        System.out.println("Given beta:3, the expected value is " + tester.exponentialExpected(3));
        System.out.println("Given beta:3, the variance value is " + tester.exponentialVariance(3));
    }
}