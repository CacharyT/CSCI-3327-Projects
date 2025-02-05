/*
 * Cachary Tolentino
 * This class will test the functions of Solver Class:
 * 1. factorial
 * 2. combination
 * 3. 
 */

public class TestSolver {
    public static void main(String[] args) {
        Solver solver = new Solver();

        //Testing factorial
        System.out.println("Factorial of 0: " + solver.factorial(0));
        System.out.println("Factorial of 1: " + solver.factorial(0));
        System.out.println("Factorial of 5: " + solver.factorial(5));
        System.out.println("Factorial of 100: " + solver.factorial(100));

        //Testing using book examples
        System.out.println("Sample points in S: " + solver.permutation(30, 3)); //Example 2.8 - Correct
        System.out.println("Sample points in S: " + solver.combination(5, 2)); //Example 2.11 - Correct


    }
}
