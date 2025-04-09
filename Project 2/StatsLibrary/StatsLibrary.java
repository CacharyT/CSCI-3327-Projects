/*
 * Cachary Tolentino
 * StatsLibrary is a collection of statistical functions (Part 2 for Project 2)
 */

//Imports
import java.math.BigInteger;

public class StatsLibrary {

    /*
     * The function will retun the factorial value of the given number
     * @param num the number to find the factorial for
     * @return factorial value as a BigInteger type
     */
    public BigInteger factorial(int num){
        //Checks if the current num is 0, if so, can return (base case)
        if(num == 0){
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(num).multiply(factorial(num - 1)); //recursion case (decrease num each call)
    }

    /*
     * The function will return the calculation for the probability using poisson distribution
     * @param lambda an double value (lambda/average)
     * @param y an int value
     * @return a double value (poisson distribution)
     */
    public double poissonDistribution(double lambda, int y){
        double eValue = Math.exp(-lambda);
        double numerator = Math.pow(lambda, y);
        double denominator = factorial(y).doubleValue();
        return (eValue * numerator) / denominator;
    }

    /*
     * The function will return the expected value of the poisson distribution
     * @param lambda a double value
     * @return lambda a double value (expected)
     */
    public double poissonExpected(double lambda){
        return lambda;
    }

    /*
     * The function will return the expected variance of the poisson distribution
     * @param lambda a double value
     * @return lambda a double value (variance)
     */
    public double poissonVariance(double lambda){
        return lambda;
    }

    /*
     * The function will return the standard deviation of the poisson distribution
     * @param lambda a double value
     * @return square root of lambda a double value (variance)
     */
    public double poissonStandardDeviation(double lambda){
        return Math.sqrt(lambda);
    }

    /*
     * The function will return the calculate for the Tchebysheff's theorem
     * @param k an int value
     * @param greaterThanOrEqual to a boolean value
     * @return a double value
     */
    public double tchebysheff(int k, boolean greatThanOrEqualTo){
        if(greatThanOrEqualTo){
            return (1 - (1/(Math.pow(k,2))));
        } else{
            return 1/(Math.pow(k,2));
        }
    }
}