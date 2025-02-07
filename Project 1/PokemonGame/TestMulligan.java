/*
 * Cachary Tolentino
 * This is a tester class for the Mulligan class
 * This class will test for the probabilities of a mulligan occuring in a 60 card deck.
 */

public class TestMulligan {
    public static void main(String[] args) {
        
        Mulligan mulligan = new Mulligan();

        System.out.println("Probabilities: ");

        double[] probabilities = mulligan.mulliganProbabilityPerPokemonCount(10000);

        for(double num : probabilities){
            System.out.println(num);
        }

    }
}
