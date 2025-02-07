/*
 * Cachary Tolentino
 * This class will:
 * 1. Check for the probability of a mulligan appearinng in a 60 card deck given x amount of trials
 */

//Imports
import java.util.Random;

public class Mulligan {
    
    //Global Variable()
    Card[] deck;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Mulligan(){
        deck = null;
    }

    /*
     * Constructor with parameters
     * @param newDeck an array of Card objects
     * @return none
     */
    public Mulligan(Card[] newDeck){
        deck = newDeck;
    }

    /*
     * This function will find the porbabilities of a mulligan appearing given each varying amount of pokemon in a 60 card deck
     * @param trialNum an int value indicating the amount of trials to perform per pokemon count size
     * @return mulliganProbabilities an array of double values containing the porbability for each size pokemon count
     */
    public double[] mulliganProbabilityPerPokemonCount(int trialNum){

        double[] mulliganProbabilities = new double[60];


        for(int pokemonCount = 0; pokemonCount < 60; pokemonCount++){

            int totalMulligans = 0;

            for(int trial = 0; trial < trialNum; trial++){

                Card[] currentDeck = createMulliganDeck(pokemonCount);

                if(checkMulligan(currentDeck)){
                    totalMulligans++;
                }

            }

            mulliganProbabilities[pokemonCount] = (double) totalMulligans/ (double) trialNum;

        }

        return mulliganProbabilities;
    }

    /*
     * This function will check whether a mulligan appears during the first seven cards drawn
     * @param deck an array of card objects
     * @return boolean decides if a mulligan appears or not
     */
    public Boolean checkMulligan(Card[] deck){

        Random random = new Random();
        Card[] firsthand = new Card[7];

        for(int currentCount = 0; currentCount < 7; currentCount++){

            firsthand[currentCount] = deck[random.nextInt(60)];

        }

        for(Card currentCard : firsthand){
            if(currentCard.getCardType().equals("Pokemon")){
                return false;
            }
        }
        
        return true;
    }

    /*
     * This function fills a deck with a set amount of pokemons and filled the rest with energy cards
     * @param pokemonCount the amount of pokemon cards to add
     * @return mulliganDeck an array of card objects
     */
    public Card[] createMulliganDeck(int pokemonCount){

        Card[] mulliganDeck = new Card[60];

        for(int currentCount = 0; currentCount < pokemonCount; currentCount++){

            mulliganDeck[currentCount] = new Card("Pokemon");

        }

        for(int currentCount = pokemonCount; currentCount < 60; currentCount++){

            mulliganDeck[currentCount] = new Card("Energy");

        }

        return mulliganDeck;
    }

}