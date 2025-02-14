package RareCandyStats;

/*
 * Cachary Tolentino
 * This class will check the probability of the deck bricking based on 1, 2, 3, or 4 rare candies within the deck
 */

//Imports
import java.util.Random;

public class Bricked {
    

    //Global Variable()
    Card[] deck;
    Card[] hand;
    Card[] prizePile;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Bricked(){
        deck = null;
        hand = null;
        prizePile = null;
    }

    /*
     * Constructor with parameters
     * @param newDeck an array of Card objects
     * @param newHand an array of Card objects
     * @param newPrizePile an array of Card objects
     * @return none
     */
    public Bricked(Card[] newDeck, Card[] newHand, Card[] newPrizePile){
        deck = newDeck;
        hand = newHand;
        prizePile = newPrizePile;
    }

    /*
     * This function will find the porbabilities of bricking given 1, 2, 3, or 4 rare candies in the deck
     * @param trialNum an int value indicating the amount of trials to perform 
     * @return brickedProbabilities an array of double values containing the porbability for each amount of candy
     */
    public double[] brickedProbabilityPerRareCandy(int trialNum){

        double[] brickedProbabilities = new double[4];


        for(int rareCandyCount = 1; rareCandyCount <= 4; rareCandyCount++){

            int totalBricked = 0;

            for(int trial = 0; trial < trialNum; trial++){

                deck = createBrickedDeck(rareCandyCount);
                hand = createHand(deck);

                //Ensure hand contains a pokemon, then check if hand contains pokemon (it should)
                while(!checkContainsPokemon(hand)){
                    hand = createHand(deck);
                }

                //then make and check prize pile, if contains all rare candies, then bricked
                prizePile = createPrizePile(deck);
                if(checkBricked(prizePile, rareCandyCount)){
                    totalBricked++;
                }
            }

            brickedProbabilities[rareCandyCount - 1] = (double) totalBricked/ (double) trialNum;
        }

        return brickedProbabilities;
    }

    /*
     * This function will check whether the prize pile contains all rare candies, thus causing a bricked deck
     * @param prizePile an array of card objects
     * @param rareCandyCount the amount of rare candies that was added in the deck
     * @return boolean decides if the deck is bricked
     */
    public Boolean checkBricked(Card[] prizePile, int rareCandyCount){

        int candyCount = 0;

        for(Card card : prizePile){
            if(card.getCardType().equals("Rare Candy")){
                candyCount++;
            }
        }

        if(candyCount == rareCandyCount){
            return true;
        }
        
        return false;
    }


    /*
     * This function will check whether the current hand contains atleast one pokemon, otherwise bricked
     * @param hand an array of card objects
     * @return boolean decides if the deck is bricked
     */
    public Boolean checkContainsPokemon(Card[] hand){

        for(Card card : hand){
            if(card.getCardType().equals("Pokemon")){
                return true;
            }
        }
        
        return false;
    }



    /*
     * This function will fill the hand with 7 card at random to emulate a shuffled deck
     * @param deck an array of card objects
     * @return currentHand an array of card objects
     */
    public Card[] createHand(Card[] deck){

        Random random = new Random();
        Card[] currentHand = new Card[7];

        for(int currentCount = 0; currentCount < 7; currentCount++){

            currentHand[currentCount] = deck[random.nextInt(deck.length)];

        }

        return currentHand;
    }



    /*
     * This function will fill the prize pile with 6 cards
     * @param deck an array of card objects
     * @return currentPrizePile an array of card objects
     */
    public Card[] createPrizePile(Card[] deck){

        Random random = new Random();
        Card[] currentPrizePile = new Card[6];

        for(int currentCount = 0; currentCount < 6; currentCount++){

            currentPrizePile[currentCount] = deck[random.nextInt(deck.length)];

        }

        return currentPrizePile;
    }



    /*
     * This function fills a deck with a set amount of rare candies and filled the rest with energy cards or pokemon
     * @param rareCandyCount the amount of rare candies to add
     * @return brickedDeck an array of card objects
     */
    public Card[] createBrickedDeck(int rareCandyCount){

        Random random = new Random();
        Card[] brickedDeck = new Card[60];

        for(int currentCount = 0; currentCount < rareCandyCount; currentCount++){

            brickedDeck[currentCount] = new Card("Rare Candy");

        }

        for(int currentCount = rareCandyCount; currentCount < 60; currentCount++){

            int choice = random.nextInt(2);

            if(choice == 0){
                brickedDeck[currentCount] = new Card("Energy");
            } else{
                brickedDeck[currentCount] = new Card("Pokemon");
            }

        }

        return brickedDeck;
    }

}
