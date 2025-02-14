package RareCandyStats;

/*
 * Cachary Tolentino
 * This is a tester class for the Bricked class
 * This class will test for the probabilities of a bricked deck based on a given 1, 2, 3, or 4 rare candies in the deck
 */

public class TestBricked {
    
    public static void main(String[] args) {
        
        Bricked bricked = new Bricked();

        System.out.println("Probabilities: ");

        double[] probabilities = bricked.brickedProbabilityPerRareCandy(10000);

        for(double num : probabilities){
            System.out.println(num);
        }
    }
}