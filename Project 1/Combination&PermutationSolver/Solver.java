/*
 * Cachary Tolentino
 * This class will:
 * 1. Allow for computing combinations
 * 2. Allow for computing permutations
 * 3. Allow for computing factorial
 */

 //Imports
 import java.math.BigInteger;

public class Solver {
    
    //Global Variable(s)
    
    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Solver(){
        //No global variables to initialize
    }

    /*
     * This function calculates the combination value of the given n and r values
     * @param n an int value
     * @param r an int value
     * @return BigInteger value of the combination
     */
    public BigInteger combination(int n, int r){

        BigInteger factN = factorial(n);
        BigInteger factR = factorial(r);
        BigInteger factNMinusR = factorial(n - r);
        BigInteger combinationValue = factN.divide(factR.multiply(factNMinusR));

        return combinationValue;

    }

    /*
     * This function will calculate the permutation value of the given n and r values
     * @param n an int value
     * @param r an int value
     * @return BigInteger value of the permutation
     */
    public BigInteger permutation(int n, int r){
        
        BigInteger factN = factorial(n);
        BigInteger factNMinusR = factorial(n - r);
        BigInteger permutationValue = factN.divide(factNMinusR);

        return permutationValue;

    }

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

}
