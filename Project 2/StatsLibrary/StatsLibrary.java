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

    /*
     * The function will return the value for the uniform probability distribution
     * @param a an int value
     * @param b an int value
     * @return a double value or zero
     */
    public double uniformDistribution(int a, int b){
        if(a <= b){
            return (1 / (double) (b - a));
        } else{
            return 0;
        }
    }

    /*
     * The function will return the value for the uniform probability distribution (alternate shortcut)
     * @param a an int value
     * @param b an int value
     * @param c an int value
     * @param d an int value
     * @return a double value
     */
    public double uniformDistributionAlt(int a, int b, int c, int d){
        return (double) (d-c) / (double) (b-a);
    }

    /*
     * The function will return the expected value for the uniform probability distribution 
     * @param theta1 an int value
     * @param theta2 an int value
     * @return a double value
     */
    public double uniformExpected(int theta1, int theta2){
        return (double) (theta1 + theta2) / 2;
    }

    /*
     * The function will return the variance value for the uniform probability distribution
     * @param theta1 an int value
     * @param theta2 an int value
     * @return a double value
     */
    public double uniformVariance(int theta1, int theta2){
        return (double) (Math.pow((theta2 - theta1),2)) / (double) 12;
    }

    /*
     * The function will return the expected value for a gamma probability distribution
     * @param alpha - an int value
     * @param beta - an int value
     * @return a double value
     */
    public double gammaExpected(int alpha, int beta){
        return alpha * beta;
    }

    /*
     * The function will return the variance value for a gamma probability distribution
     * @param alpha - an int value
     * @param beta - an int value
     * @return a double value
     */
    public double gammaVariance(int alpha, int beta){
        return alpha * Math.pow(beta, 2);
    }

    /*
     * The function will return the expected value for a chi-square
     * @param v - an int value
     * @return a int value
     */
    public int chiSquareExpected(int v){
        return v;
    }

    /*
     * The function will return the variance value for a chi-square
     * @param v - an int value
     * @return a int value
     */
    public int chiSquareVariance(int v){
        return 2 * v;
    }

    /*
     * The function will return the expected value for a exponential distribution of a gamma function
     * @param beta - a int value
     * @return a int value
     */
    public int exponentialExpected(int beta){
        return beta;
    }

    /*
     * The function will return the variance value for a exponential distribution of a gamma function
     * @param beta - a int value
     * @return a double value
     */
    public double exponentialVariance(int beta){
        return Math.pow(beta, 2);
    }

    /*
     * The function will return the expected value for a normal distribution
     * @param mu - a int value
     * @return a int value
     */
    public int normalExpected(int mu){
        return mu;
    }

    /*
     * The function will return the variance value for a normal distribution
     * @param sigma - a int value
     * @return a double value
     */
    public double normalVariance(int sigma){
        return Math.pow(sigma, 2);
    }

    /*
     * The function will return the expected value for a beta distribution
     * @param alpha - a double value
     * @param beta - a double value
     * @return a double value
     */
    public double betaExpected(double alpha, double beta){
        return alpha / (alpha + beta);
    }

    /*
     * The function will return the variance value for a beta distribution
     * @param alpha - a double value
     * @param beta - a double value
     * @return a double value
     */
    public double betaVariance(double alpha, double beta){
        return (alpha * beta) / (Math.pow((alpha + beta), 2) * (alpha + beta + 1));
    }
}