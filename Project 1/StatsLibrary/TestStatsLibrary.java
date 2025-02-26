/*
 * Cachary Tolentino
 * TestStatsLibrary is a tester class containing various call operations for all methods in the StatsLibrary class.
 */

 //Imports
import java.util.ArrayList;
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


        //Testing Axiom Functions
        ArrayList<String> space = new ArrayList<>();
        ArrayList<String> subset = new ArrayList<>();
        double[] subsetProbabilities = new double[4];

        space.add("A");
        space.add("B");
        space.add("C");
        space.add("D");
        space.add("E");
        space.add("F");
        space.add("G");

        subset.add("A");
        subset.add("C");
        subset.add("D");
        subset.add("G");

        subsetProbabilities[0] = 0.1;
        subsetProbabilities[1] = 0.1;
        subsetProbabilities[2] = 0.1;
        subsetProbabilities[3] = 0.1;

        Boolean axiom1Result = tester.checkAxiomOne(space, subset, subsetProbabilities);

        System.out.println("Does the subset A fulfill Probability Axiom 1?: " + axiom1Result);

        double[] wrongSpace = new double[5];
        double[] correctSpace = new double[5];

        wrongSpace[0] = 0.1;
        wrongSpace[1] = 0.1;
        wrongSpace[2] = 0.1;
        wrongSpace[3] = 0.1;
        wrongSpace[4] = 0.1;

        correctSpace[0] = 0.2;
        correctSpace[1] = 0.2;
        correctSpace[2] = 0.2;
        correctSpace[3] = 0.2;
        correctSpace[4] = 0.2;

        Boolean axiom2ResultWrong = tester.checkAxiomTwo(wrongSpace);
        Boolean axiom2ResultCorrect = tester.checkAxiomTwo(correctSpace);

        System.out.println("Does the wrongSpace fulfill Probability Axiom 2? " + axiom2ResultWrong);
        System.out.println("Does the correctSpace fulfill Probability Axiom 2? " + axiom2ResultCorrect);


        double axiom3Result = tester.checkAxiomThree(space, subset, subsetProbabilities);

        System.out.println("Probability of subset A based on Probability Axiom 3?: " + axiom3Result);


        //Testing Independence and Dependence Functions

        //Book Example Values 2.72
        double eventA = (double) 40 / (double) 100;
        double eventM = (double) 60 / (double) 100;
        double eventAAndM = (double) 24 / (double) 100;
        System.out.println("Are Events A and M independent?: " + tester.checkIfIndependent(eventA, eventM, eventAAndM));

        double eventA1 = 0.4;
        double eventB1 = 0.5;
        double eventA1B1 = 0.3;
        System.out.println("Are Events C and D dependent?: " + tester.checkIfDependent(eventA1, eventB1, eventA1B1));
        
        double independentResult = tester.calculateInDependentOrDependentIntersection(eventA, eventM, eventAAndM);
        double dependentResult = tester.calculateInDependentOrDependentIntersection(eventA1, eventB1, eventA1B1);


        double exclusiveA = 0.4;
        double exclusiveB = 0.5;
        double exclusiveAAndB = 0;

        double exclusiveResult = tester.calculateExclusiveOrNotExclusiveunion(exclusiveA, exclusiveB, exclusiveAAndB);

        double notEA = 0.4;
        double notEB = 0.5;
        double notEAAndB = 0.2;

        double notExclusiveResult = tester.calculateExclusiveOrNotExclusiveunion(notEA, notEB, notEAAndB);


        //Checking binomial distribution (class example)
        System.out.println("The result is " + tester.binomialDistribution(0.8, 0.2, 10, 7));

    }


}