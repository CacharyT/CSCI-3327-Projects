package RareCandyStats;

/*
 * Cachary Tolentino
 * This class will check the probability of the deck bricking based on 1, 2, 3, or 4 rare candies within the deck
 */

//Imports
import java.util.Random;

public class Bricked {
    

    //Global Variable()
    private Card[] deck;
    private Card[] hand;
    private Card[] prizePile;

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

                setDeck(createBrickedDeck(rareCandyCount));
                setHand(createHand(this.deck));

                //Ensure hand contains a pokemon, then check if hand contains pokemon (it should)
                while(checkContainsPokemon(this.hand) == false){
                    setHand(createHand(this.deck));
                }

                //then make and check prize pile, if contains all rare candies, then bricked
                setPrizePile(createPrizePile(this.deck));
                if(checkBricked(this.prizePile, rareCandyCount)){
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

        //Draw 7 cards
        Card[] currentHand = new Card[7];

        for(int i = 0; i < 7; i++){
            currentHand[i] = drawCard(this.deck);
        }

        //Return hand
        return currentHand;

    }


    /*
     * This function will fill the prize pile with 6 cards
     * @param deck an array of card objects
     * @return currentPrizePile an array of card objects
     */
    public Card[] createPrizePile(Card[] deck){

        //Draw 6 cards
        Card[] currentPrizePile = new Card[6];
        for(int i = 0; i < 6; i++){
            currentPrizePile[i] = drawCard(this.deck);
        }

        //Return prize pile
        return currentPrizePile;

    }


    /*
     * This is a helper function that check if an array contains a certain number
     * @param indexes an array of int values
     * @param currentIndex an int value
     * @return boolean decides if the array contains the number
     */
    public Boolean checkIndex(int[] indexes, int currentIndex){
        for(int num : indexes){
            if(num == currentIndex){
                return true;
            }
        }
        return false;
    }


    /*
     * This function will draw a card from the deck and remove it from the deck
     * @param deck an array of card objects
     * @return drawnCard a card object
     */
    public Card drawCard(Card[] deck){
        
        //Draw a card randomly
        Random random = new Random();
        int num = random.nextInt(deck.length);
        Card drawnCard = deck[num];


        //Remove card from the deck
        Card[] updatedDeck = new Card[deck.length - 1];
        int newIndex = 0;

        for(int i = 0; i < deck.length; i++){
            if(i != num){
                updatedDeck[newIndex++] = deck[i];
            }
        }

        //Update deck
        this.deck = updatedDeck;

        return drawnCard;
        
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


    /*
     * This function will set the deck
     * @param newDeck an array of card objects
     * @return none
     */
    public void setDeck(Card[] newDeck){
        deck = newDeck;
    }

    /*
     * This function will set the hand
     * @param newHand an array of card objects
     * @return none
     */
    public void setHand(Card[] newHand){
        hand = newHand;
    }

    /*
     * This function will set the prize pile
     * @param newPrizePile an array of card objects
     * @return none
     */
    public void setPrizePile(Card[] newPrizePile){
        prizePile = newPrizePile;
    }

    /*
     * This function will return the deck
     * @param none
     * @return deck an array of card objects
     */
    public Card[] getDeck(){
        return deck;
    }

    /*
     * This function will return the hand
     * @param none
     * @return hand an array of card objects
     */
    public Card[] getHand(){
        return hand;
    }

    /*
     * This function will return the prize pile
     * @param none
     * @return prizePile an array of card objects
     */
    public Card[] getPrizePile(){
        return prizePile;
    }

}
