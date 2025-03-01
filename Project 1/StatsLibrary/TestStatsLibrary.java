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
        System.out.println("The variance: " + tester.getVariance(someNumbers));
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

        //Testing mutually exclusive probability
        String[] A = new String[3];
        String[] B = new String[3];
        A[0] = "A";
        A[1] = "A";
        A[2] = "A";
        B[0] = "B";
        B[1] = "B";
        B[2] = "B";
        double probA = 0.5;
        double probB = 0.3;
        System.out.println("Mutually exclusive probability of subset A and B: " + tester.probMutualExclusive(A, B, probA, probB));

        //Testing conditional probability
        double probAB = 0.1;
        System.out.println("Conditional Probability of A given B: " + tester.conditionalProbabilityAB(probAB, probB));
        System.out.println("Conditional Probability of B given A: " + tester.conditionalProbabilityBA(probAB, probA));

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

        //Test if parition of space
        ArrayList<String> space2 = new ArrayList<>();
        ArrayList<ArrayList<String>> setsOfValues = new ArrayList<>();
        ArrayList<String> b1 = new ArrayList<>();
        ArrayList<String> b2 = new ArrayList<>();
        b1.add("Hello");
        b1.add("Hi");
        b2.add("Morning");
        b2.add("Night");
        setsOfValues.add(b1);
        setsOfValues.add(b2);
        space2.add("Hello");
        space2.add("Hi");
        space2.add("Morning");
        space2.add("Night");
        System.out.println("Is B1 and B2 a parition of space? " + tester.partitionOfSpace(space2, setsOfValues));

        //Test for total probability
        double[] AB = {0.8, 0.2, 0.7};
        double[] B3 = {0.1, 0.5, 0.4};
        System.out.println("Total probability of AB and B3: " + tester.theoremOfTotalProbability(AB, B3));

        //Testing Baye's theorem
        double probC = 0.8;
        double probD = 0.1;
        System.out.println("Probability of C given D (Bayes theorem): " + tester.bayesTheorem(probC, probD));

        //Testing Random variable expected, variance, and standard deviation
        double[] die = {1,2,3,4,5,6};
        double[] dieProb = {0.17, 0.17, 0.17, 0.17, 0.17, 0.17};
        System.out.println("Expected Value for Dice: " + tester.expectedRandom(die, dieProb));
        System.out.println("Variance Value for Dice: " + tester.varianceRandom(die, dieProb));
        System.out.println("Standard deviation Value for Dice: " + tester.standardDeviationRandom(die, dieProb));

        //Testing binomial distribution expected, variance, and standard deviation
        double p = 0.8;
        double q = 0.2;
        int n = 10;
        int y = 7;
        System.out.println("Binomial Distribution: " + tester.binomialDistribution(p, q, n, y));
        System.out.println("Binomial Distribution Expected: " + tester.expectedBinomial(n, p));
        System.out.println("Binomial Distribution Variance: " + tester.varianceBinomial(n, p, q));
        System.out.println("Binomial Distribution Standard Deviation: " + tester.standardDeviationBinomial(n, p, q));

        //Testing geometric distribution, variance, and standard deviation
        double p2 = 0.2;
        double q2 = 0.8;
        double y2 = 3;
        System.out.println("Geometric Distribution: " + tester.geometricDistribution(p2, y2, q2));
        System.out.println("Geometric Distribution Expected: " + tester.geometricExpected(p));
        System.out.println("Geometric Distribution Variance: " + tester.geometricVariance(p));
        System.out.println("Geometric Distribution Standard Deviation: " + tester.geometricStandardDeviation(p));

    }
    
}