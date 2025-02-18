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
        discardPile = new Card[0];
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

        if(trainerCount > 16 || pokemonCount > 16 || (trainerCount + pokemonCount + energyCount) < 60 || (trainerCount + pokemonCount + energyCount) > 60){ //To ensure that the player does not exceed 4 cards per card type
            System.out.println("Invalid allocation sizes. Please try again.");
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

        Card[] updatedDeck = new Card[getDeck().length + getHand().length];

        //Re adding hand cards to deck
        for(int i = 0; i < getDeck().length; i++){
            
            updatedDeck[i] = getDeck()[i];

        }

        for(int i = deck.length; i < updatedDeck.length; i++){

            updatedDeck[i] = getDeck()[i - deck.length];

        }

        //Update the deck
        setDeck(updatedDeck);

        //Update hand (empty)
        setHand(new Card[7]);

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
        Card[] newHand = new Card[getHand().length + 1];
        for(int i = 0; i < newHand.length - 1; i++){
            newHand[i] = getHand()[i];
        }
        newHand[newHand.length - 1] = newCard;

        setHand(newHand);

    }

    /*
     * 
     */
    public Card[] fillHand(){
        Card[] newHand = new Card[7];

        //Get cards from the deck
        for(int i = 0; i < newHand.length; i++){
            newHand[i] = drawCard();
        }

        //Update hand
        setHand(newHand);

        return newHand;
    }


    /*
     * 
     */
    public Card[] fillPrize(){
        Card[] newPrizePile = new Card[6];

        for(int i = 0; i < newPrizePile.length; i++){
            newPrizePile[i] = drawCard();
        }

        setPrizePile(newPrizePile);

        return newPrizePile;

    }


    /*
     * 
     */
    public void getPrizeCard(){

        Card[] currentPrizePile = getPrizePile();
        Card[] currentHand = getHand();

        //Get card from the prize pile
        Card wonCard = currentPrizePile[currentPrizePile.length - 1];

        //Notify Player of Card won
        System.out.println("You won a card from the prize pile! It is a " + wonCard.getName());

        //Remove card from prize pile, update prize pile
        Card[] newPrizePile = new Card[currentPrizePile.length - 1];
        for(int i = 0; i < currentPrizePile.length - 1; i++){ //skips the last card (removed card)
            newPrizePile[i] = currentPrizePile[i];
        }
        setPrizePile(newPrizePile);

        //Add the card to the hand, update hand
        Card[] newHand = new Card[currentHand.length + 1];
        for(int i = 0; i < currentHand.length; i++){
            newHand[i] = currentHand[i];
        }
        newHand[newHand.length - 1] = wonCard;
        setHand(newHand);

    }


    /*
     * Uses Fisher-Yates Algorithm
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
    public void addEnergyToPokemon(int arrayPosition){

        Card[] currentHand = getHand();
        Card activePokemon = getActiveField();

        //Add energy card to pokemon and update pokemon energy
        Energy[] currentEnergies = activePokemon.getEnergies();
        Energy[] newEnergies = new Energy[currentEnergies.length + 1];
        newEnergies[newEnergies.length - 1] = (Energy) currentHand[arrayPosition];
        activePokemon.setEnergies(newEnergies);


        //Remove energy card from hand and update hand
        Card[] newHand = new Card[currentHand.length - 1];
        int newIndex = 0;
        for(int i = 0; i < currentHand.length; i++){
            if(i != arrayPosition){
                newHand[newIndex++] = currentHand[i];
            }
        }
        setHand(newHand);

    }

    /*
     * 
     */
    public void useTrainerCard(Player player, int arrayPosition){

        Card[] currentHand = getHand();

        //Get trainer card
        Card trainerCard = currentHand[arrayPosition];

        //Activate effect
        trainerCard.activateEffect(player);

        //Remove card from hand (add to discard pile)
        Card[] updatedHand = getHand();
        Card[] newHand = new Card[updatedHand.length - 1];
        int newIndex = 0;
        for(int i = 0; i < updatedHand.length; i++){
            if(i != arrayPosition){
                newHand[newIndex++] = updatedHand[i];
            }
        }
        setHand(newHand);

        Card[] currentDiscardPile = getDiscardPile();
        Card[] newDiDiscardPile = new Card[currentDiscardPile.length + 1];
        for(int i = 0; i < currentDiscardPile.length; i++){
            newDiDiscardPile[i] = currentDiscardPile[i];
        }
        newDiDiscardPile[newDiDiscardPile.length - 1] = trainerCard;
        setDiscardPile(newDiDiscardPile);

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
