/*
 * 
 */

//Imports
import java.util.Random;

public class Player {
    
    //Global Variable(s)
    private Card[] hand;
    private Card[] deck;
    private Card[] bench;
    private Card[] prizePile;
    private Card[] discardPile;
    private Card activeField;


    /*
     * Default Constructor (random mix of card types)
     * @param none
     * @return none
     */
    public Player(){
        hand = new Card[7];
        deck = new Card[60];
        bench = new Card[6];
        prizePile = new Card[6];
        discardPile = new Card[60];
    }


    /*
     * 
     */
    public Card[] createDeck(){

        Random random = new Random();

        int totalCards = 60;
        Card[] newDeck = new Card[totalCards];

        //Minimum of 1 Pokemon must be in the deck
        int pokemonCount = random.nextInt(totalCards);

        while(pokemonCount == 0){
            pokemonCount = random.nextInt(totalCards);
        }

        totalCards -= pokemonCount;

        //maximum of 4 trainer cards per trainer type
        int trainerCount = 16; //only 4 different trainer types (currently)
        totalCards -= trainerCount;

        int energyCount = totalCards;


        for(int i = 0; i < pokemonCount; i++){
            int pokemonType = random.nextInt(4);
            switch(pokemonType){
                case 0:
                    newDeck[i] = new Charmander();
                    break;
                case 1:
                    newDeck[i] = new Pikachu();
                    break;
                case 2:
                    newDeck[i] = new Bulbasaur();
                    break;
                case 3:
                    newDeck[i] = new Squirtle();
                    break;
            }
        }

        for(int i = pokemonCount; i < energyCount + pokemonCount; i++){
            int energyType = random.nextInt(5);
            switch(energyType){
                case 0:
                    newDeck[i] = new Fire();
                    break;
                case 1:
                    newDeck[i] = new Lightning();
                    break;
                case 2:
                    newDeck[i] = new Grass();
                    break;
                case 3:
                    newDeck[i] = new Water();
                    break;
                case 4:
                    newDeck[i] = new Basic();
                    break;
            }
        }

        for(int i = pokemonCount + energyCount; i < energyCount + pokemonCount + trainerCount; i++){
            int trainerType = random.nextInt(4);
            switch(trainerType){
                case 0:
                    newDeck[i] = new ProfessorOak();
                    break;
                case 1:
                    newDeck[i] = new Bill();
                    break;
                case 2:
                    newDeck[i] = new Recylce();
                    break;
                case 3:
                    newDeck[i] = new MrFuji();
                    break;
            }
        }

        //Update the deck
        setDeck(newDeck);

        return newDeck;

    }


    /*
     * 
     */
    public Card[] createDeck(int pokemonCount, int trainerCount, int energyCount){
        
        Random random = new Random();

        if(trainerCount > 16 || pokemonCount > 16){ //To ensure that the player does not exceed 4 cards per card type
            return null;
        }

        int totalCards = 60;
        Card[] newDeck = new Card[totalCards];

        for(int i = 0; i < pokemonCount; i++){
            int pokemonType = random.nextInt(4);
            switch(pokemonType){
                case 0:
                    newDeck[i] = new Charmander();
                    break;
                case 1:
                    newDeck[i] = new Pikachu();
                    break;
                case 2:
                    newDeck[i] = new Bulbasaur();
                    break;
                case 3:
                    newDeck[i] = new Squirtle();
                    break;
            }
        }

        for(int i = pokemonCount; i < energyCount + pokemonCount; i++){
            int energyType = random.nextInt(5);
            switch(energyType){
                case 0:
                    newDeck[i] = new Fire();
                    break;
                case 1:
                    newDeck[i] = new Lightning();
                    break;
                case 2:
                    newDeck[i] = new Grass();
                    break;
                case 3:
                    newDeck[i] = new Water();
                    break;
                case 4:
                    newDeck[i] = new Basic();
                    break;
            }
        }

        // NEEDS TO BE CAPPED AT 4 PER SAME TRAINER CARD (SO 4 DIFFERENT CARDS = 16 TOTAL TRAINER OF 4 TYPES)
        for(int i = pokemonCount + energyCount; i < energyCount + pokemonCount + trainerCount; i++){
            int trainerType = random.nextInt(4);
            switch(trainerType){
                case 0:
                    newDeck[i] = new ProfessorOak();
                    break;
                case 1:
                    newDeck[i] = new Bill();
                    break;
                case 2:
                    newDeck[i] = new Recylce();
                    break;
                case 3:
                    newDeck[i] = new MrFuji();
                    break;
            }
        }

        //Update the deck
        setDeck(newDeck);

        return newDeck;

    }


    /*
     * 
     */
    public void reAddHandToDeck(){

        Card[] updatedDeck = new Card[deck.length + hand.length];

        //Re adding hand cards to deck
        for(int i = 0; i < deck.length; i++){
            
            updatedDeck[i] = deck[i];

        }

        for(int i = deck.length; i < updatedDeck.length; i++){

            updatedDeck[i] = hand[i - deck.length];

        }

        //Update the deck
        setDeck(updatedDeck);

    }



    /*
     * 
     */
    public Card drawCard(){
        Card drawnCard = deck[deck.length - 1];

        //update deck
        Card[] newDeck = new Card[deck.length - 1];
        for(int i = 0; i < newDeck.length; i++){
            newDeck[i] = deck[i];
        }
        setDeck(newDeck);

        return drawnCard;
    }

    /*
     * 
     */
    public void addCardToHand(Card newCard){

        //Make new hand with new card and update hand
        Card[] newHand = new Card[hand.length + 1];
        for(int i = 1; i < newHand.length; i++){
            newHand[i] = hand[i];
        }
        newHand[newHand.length] = newCard;

        setHand(newHand);

    }

    /*
     * 
     */
    public Card[] fillHand(){
        Card[] newHand = new Card[7];

        for(int i = 0; i < newHand.length; i++){
            newHand[i] = drawCard();
        }

        setHand(newHand);

        return newHand;
    }


    /*
     * 
     */
    public void fillPrize(){
        Card[] newPrizePile = new Card[6];

        for(int i = 0; i < newPrizePile.length; i++){
            newPrizePile[i] = drawCard();
        }

        setPrizePile(newPrizePile);

    }


    /*
     * 
     */
    public void getPrizeCard(){
        int count = 0;
        for(Card card : hand){
            count++;
        }

        if(count < hand.length){
            hand[count + 1] = prizePile[0];

            //Remove the card from the prize pile
            Card[] newPrizePile = new Card[prizePile.length - 1];
            int newIndex = 0;
            for(int i = 0; i < newPrizePile.length; i++){
                newPrizePile[newIndex++] = prizePile[i + 1];
            }
            setPrizePile(newPrizePile);
        }
        else{
            Card[] newHand = new Card[hand.length + 10];
            for(int i = 0; i < newHand.length; i++){
                newHand[i] = hand[i];
            }

            hand[count + 1] = prizePile[0];

            //Remove the card from the prize pile
            Card[] newPrizePile = new Card[prizePile.length - 1];
            int newIndex = 0;
            for(int i = 0; i < newPrizePile.length; i++){
                newPrizePile[newIndex++] = prizePile[i + 1];
            }
            setPrizePile(newPrizePile);
        }
    }


    /*
     * Uses Fisher-Yates Algorith,
     */
    public void shuffleDeck(){
        Random random = new Random();

        for(int i = deck.length - 1; i > 0; i--){
            int j = random.nextInt(i + 1);
            Card tempCard = deck[i];
            deck[i] = deck[j];
            deck[j] = tempCard;
        }
    }



    /*
     * 
     */
    public void setHand(Card[] newHand){
        hand = newHand;
    }


    /*
     * 
     */
    public void setDeck(Card[] newDeck){
        deck = newDeck;
    }


    /*
     * 
     */
    public void setBench(Card[] newBench){
        bench = newBench;
    }


    /*
     * 
     */
    public void setPrizePile(Card[] newPrizePile){
        prizePile = newPrizePile;
    }


    /*
     * 
     */
    public void setDiscardPile(Card[] newDiscardPile){
        discardPile = newDiscardPile;
    }

    /*
     * 
     */
    public void setActiveField(Card newPokemonCard){
        activeField = newPokemonCard;
    }


    /*
     * 
     */
    public Card[] getHand(){
        return hand;
    }


    /*
     * 
     */
    public Card[] getDeck(){
        return deck;
    }


    /*
     * 
     */
    public Card[] getbench(){
        return bench;
    }


    /*
     * 
     */
    public Card[] getPrizePile(){
        return prizePile;
    }


    /*
     * 
     */
    public Card[] getDiscardPile(){
        return discardPile;
    }

    /*
     * 
     */
    public Card getActiveField(){
        return activeField;
    }


}
