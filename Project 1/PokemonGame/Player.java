/*
 * Cachary Tolentino
 * The Player class emulates what a player would contain and be able to do in Pokemon TCG
 */

//Imports
import java.lang.reflect.Method;
import java.util.*;

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
     * The function emulates a turn for a player (gives the player options)
     * @param player1 a player object
     * @param player2 a player object
     * @param turnOne a boolean value
     * @return boolean value indicating if the player has no more pokemon available to play (defeat)
     */
    public Boolean playerTurn(Player player1, Player player2, Boolean turnOne){

        Scanner scan = new Scanner(System.in);

        System.out.println("Current player draws a card!");
        Card drawnCard = player1.drawCard();
        player1.addCardToHand(drawnCard);
        System.out.println("You have drawn a " + drawnCard.getName() + "!");

        //Turn restrictors
        Boolean endTurn = false;
        Boolean doneEnergy = false;
        Boolean doneRetreat = false;

        while(!endTurn){

            //Checks if the player has any playable pokemon
            if(!checkIfPokemonWinCondition(player1, player2)){
                return false;
            }

            Card[] currentHand = player1.getHand();

            System.out.print("This is your current hand: [");
            for(Card card : currentHand){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");

            //Player options
            System.out.println("What would you like to do (enter the #): ");
            System.out.println("1) Play 1 energy for the turn (put the energy on a pokemon)");
            System.out.println("2) Play a trainer card");
            System.out.println("3) Bench a pokemon");
            System.out.println("4) Retreat active pokemon/swap with a benched pokemon");
            System.out.println("5) Attack with active pokemon (ends turn)");
            System.out.println("6) End turn");
            System.out.print("Player choice: ");
            int decision = scan.nextInt();
            
            switch(decision){
                case 1: //player can player a single energy per turn (add to the active pokemon)
                    doneEnergy = playerPlayEnergy(doneEnergy, player1);
                    break;
                case 2: //allow player to play a trainer card (unlimited)
                    playerPlayTrainer(player1);
                    break;
                case 3: //allows the player to bench a pokemon from the hand (unlimited)
                    beginningPokemonBench(player1);
                    break;
                case 4: //allows the player to retreat their active pokemon once per turn
                    doneRetreat = playerPlayRetreat(doneRetreat, player1);
                    break;
                case 5: //allows the player to attack with active pokemon
                    endTurn = playerPlayAttack(turnOne, player1, player2, endTurn);
                    break;
                case 6: //ends turn
                    System.out.println("\nCurrent player has ended their turn!");
                    endTurn = true;
                    break;
                default:
                    System.out.println("Invalid option. Retry");
                    break;
            }
        }
        return false;
    }

    /*
     * The function will allow a player to attach an energy to the active pokemon
     * @param
     */
    public Boolean playerPlayEnergy(Boolean doneEnergy, Player player1){
        Scanner scan = new Scanner(System.in);
        if(!doneEnergy){

            Card[] newHandEnergy = player1.getHand();
            System.out.print("This is your current hand: [");
            for(Card card : newHandEnergy){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");

            System.out.print("Would you like to continue to add an energy? (Y or N): ");
            String continueOrNot = "";
            try {
                continueOrNot = scan.next().toLowerCase();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            //Adds energy to the active pokemon
            if(continueOrNot.equals("y") || continueOrNot.equals("yes")){
                System.out.print("Pick an energy card to place on the current active pokemon (0 - N; position in array): ");
                int arrayPositionEnergy = scan.nextInt();
                doneEnergy = player1.addEnergyToPokemon(arrayPositionEnergy); //after adding energy, returned value signifies true to ensure can not add anymore during the current turn
            }

            if(doneEnergy){
                Card activePokemon = player1.getActiveField();
                System.out.print("\nYour active pokemon " + activePokemon.getName() + " now has [");
                Energy[] pokemonEnergies = activePokemon.getEnergies();
                for(Energy energy: pokemonEnergies){
                    System.out.print(energy.getName() + " ");
                }
                System.out.println("]");
                return doneEnergy;
            } else{
                System.out.println("Adding energy failed.");
            }

        } else{
            System.out.println("You can not add anymore energy this turn.");
        }
        return false;
    }

    /*
     * The function will allow the player to activate a trainer card effect
     * @param lplayer1 a player object
     * @return none
     */
    public void playerPlayTrainer(Player player1){
        Scanner scan = new Scanner(System.in);
        Boolean trainerDone = false;
        while(!trainerDone){

            Card[] newHand = player1.getHand();
            System.out.print("This is your current hand: [");
            for(Card card : newHand){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");
            System.out.print("Pick a trainer card to play (0 - N; position in array; if done then enter -1): ");
            int arrayPosition = scan.nextInt();

            if(arrayPosition == -1){
                trainerDone = true;
            } else{
                player1.useTrainerCard(player1, arrayPosition); //activates the chosen card's effect
            }
        }
    }

    /*
     * The function allow  the player top retreat their active pokemon
     * @param doneRetreat a boolean value
     * @param player1 a player object
     * @return a boolean value
     */
    public Boolean playerPlayRetreat(Boolean doneRetreat, Player player1){
        if(!doneRetreat){
            doneRetreat = player1.allowRetreatPokemon();
            return doneRetreat;
        } else{
            System.out.println("You can not perform a retreat again for this turn.");
        }
        return false;
    }

    /*
     * The function allow the player to attack with their active pokemon
     * @param turnOne a boolean value
     * @param player1 a player object
     * @param player2 a player object
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean playerPlayAttack(Boolean turnOne, Player player1, Player player2, Boolean endTurn){
        if(!turnOne){
            Boolean state = player1.allowPokemonAttack(player2);
            if(state){
                System.out.println("Current player has ended their turn!");
                endTurn = true;
                return endTurn;
            } 
        } else{
            System.out.println("You can not attack during the first turn.");
        }
        return false;
    }

    /*
     * The function allows the current player to bench any number of pokemon
     * @param player a player object
     * @return none
     */
    public void beginningPokemonBench(Player player){

        Scanner scan = new Scanner(System.in);
        Card[] currentHand = player.getHand();

        //Count the number of filled spots
        int filledSpots = 0;
        for(Card card : currentHand){
            if(card != null){
                filledSpots++;
            }
        }

        //Check first if there are enough space (can not bench more than 6)
        if(filledSpots != 6){
            System.out.print("This is your current hand: ");
            System.out.print("[");
            for(Card card : currentHand){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");

            Boolean benchDone = false;

            System.out.print("Would you like to bench any pokemon? (if applicable; Y or N): ");
            String decision = "";
            try {
                decision = scan.next().toLowerCase();
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            if(decision.equals("y")){

                while(!benchDone){ // allow player to bench as many pokemon as they want
                    System.out.print("Which pokemon would you like to bench? (0 - N; position in array; if done then enter -1): ");
                    int position = scan.nextInt();

                    if(position == -1){
                        break;
                    } else{

                        benchPokemonFromHand(player, position);
                        Card[] newHand = player.getHand();

                        System.out.print("This is your new current hand: ");
                        System.out.print("[");
                        for(Card card : newHand){
                            System.out.print(card.getName() + " ");
                        }
                        System.out.print("]\n");

                    }
                }

            } else if(decision.equals("n")){
                //Does nothing (denies benching)
            } else{ //restarts the function
                System.out.println("Invalid option. Retry.");
                beginningPokemonBench(player);
            }
        } else{
            System.out.println("Your bench is full!");
        }

    }

    /*
     * The function is a helper function for the beginningPokemonBench function which allows it bench any pokemon from the current player hand
     * @param player a player object
     * @param arrayposition an int value 
     * @return none
     */
    public void benchPokemonFromHand(Player player, int arrayPosition){

        //Get the pokemon from the hand, 
        Card[] currentHand = player.getHand();
        Card pokemonCard = currentHand[arrayPosition];

        if(pokemonCard.getCardType().equals("Pokemon")){ //check first if of type pokemon, otherwise not allowed

            //Check first if the bench is already full
            Card[] currentBench = player.getbench();
            Boolean benchHasSpace = false;
            for(Card card : currentBench){
                if(card == null){
                    benchHasSpace = true;
                }
            }
            if(benchHasSpace){

                //Remove from the hand and update hand
                Card[] newHand = new Card[currentHand.length - 1];
                int newIndex = 0; //Allows for shifting the skipped values down
                for(int i = 0; i < currentHand.length; i++){
                    if(i != arrayPosition){
                        newHand[newIndex++] = currentHand[i];
                    }
                }
                player.setHand(newHand);

                //Add on the newBench and update the bench
                Card[] newBench = new Card[currentBench.length];
                for(int i = 0; i < currentBench.length; i++){
                    if(currentBench[i] != null){
                        newBench[i] = currentBench[i];
                    } else{
                        newBench[i] = pokemonCard;
                        break;
                    }
                }

                player.setBench(newBench);
            } else{
                System.out.println("There are no more space left in the bench.");
            }
        } else{
            System.out.println("Chosen card is not a pokemon. Pick again.");
        }
    }


    /*
     * The function creates a default allocation deck (16 pokemon - 16 trainer - 28 energy)
     * @param none
     * @return an array of card objects
     */
    public Card[] createDeck(){

        Random random = new Random();

        int totalCards = 60;
        Card[] newDeck = new Card[totalCards];

        //Default deck to 16 16 28
        int pokemonCount = 16;
        int trainerCount = 16;
        int energyCount = 28;

        //Randomly chooses which pokemon type to create
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

        //Randomly chooses which energy type to create
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

        //Randomly chooses which trainer card to make
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
     * The function creates the player deck based on a given allocation of each card
     * @param pokemonCount an int value
     * @param trainerCount an int value
     * @param energyCount an int value
     * @return an array of card objects
     */
    public Card[] createDeck(int pokemonCount, int trainerCount, int energyCount){
        
        Random random = new Random();

        //To ensure that the player does not exceed 4 cards per card type
        if(trainerCount > 16 || pokemonCount > 16 || (trainerCount + pokemonCount + energyCount) < 60 || (trainerCount + pokemonCount + energyCount) > 60){
            System.out.println("Invalid allocation sizes. Please try again.");
            return null;
        }

        int totalCards = 60;
        Card[] newDeck = new Card[totalCards];

        //Randomly chooses which pokemon type to create
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

        //Randomly chooses which energy type to create
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

        //Randomly chooses which trainer card to make
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
     * The function moves the hand card to the deck
     * @parma none
     * @return none
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
        
        setDeck(updatedDeck); //Update the deck
        setHand(new Card[7]); //Update hand (empty)

    }

    /*
     * The function draws a card from the deck and returns it
     * @param none
     * @return drawnCard a card object
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
     * The function moves a given card to the player hand
     * @param newCard a card object
     * @return none
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
     * The function draws seven cards (a hand) and updates the current player's hand
     * @param none
     * @return an array of card objects
     */
    public Card[] fillHand(){
        Card[] newHand = new Card[7];

        //Get cards from the deck
        for(int i = 0; i < newHand.length; i++){
            newHand[i] = drawCard();
        }
        setHand(newHand);

        return newHand;
    }

    /*
     * The function fills the prize pile of the current player with cards from the deck
     * @param none
     * @return an array of car dobjects
     */
    public Card[] fillPrize(){

        Card[] newPrizePile = new Card[6];
        for(int i = 0; i < newPrizePile.length; i++){ //get cards then update prize pile
            newPrizePile[i] = drawCard();
        }
        setPrizePile(newPrizePile);

        return newPrizePile;
    }

    /*
     * The function checks the current hand if it contains any pokemon
     * @param none
     * @return a boolean value
     */
    public Boolean checkPokemonInHand(){
        Card[] currentHand = getHand();
        for(Card card :currentHand){
            if(card.getCardType().equals("Pokemon")){
                return true;
            }
        }
        return false;
    }

    /*
     * The function takes a card from the prize pile and add it to the current player's hand
     * @param none
     * @return none
     */
    public void getPrizeCard(){

        Card[] currentPrizePile = getPrizePile();
        Card[] currentHand = getHand();
        Card wonCard = currentPrizePile[currentPrizePile.length - 1]; //Get card from the prize pile
        System.out.println("You won a card from the prize pile! It is a " + wonCard.getName());//Notify Player of Card won

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
     * Uses Fisher-Yates Algorithm to shuffle an array of items
     * The function uses the Fisher-Yates shuffle algorithm to shuffle th current player deck 
     * @Source: https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
     * @param none
     * @return none
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
     * The function adds an energy card to the active pokemon
     * @param none
     * @return a boolean value
     */
    public Boolean addEnergyToPokemon(int arrayPosition){

        Card[] currentHand = getHand();
        Card activePokemon = getActiveField();

        //Add energy card to pokemon and update pokemon energy
        Energy[] currentEnergies = activePokemon.getEnergies();
        Energy[] newEnergies = new Energy[currentEnergies.length + 1];

        for(int i =0; i < currentEnergies.length; i++){
            newEnergies[i] = currentEnergies[i];
        }

        if(currentHand[arrayPosition].getCardType().equals("Energy")){ //ensures card chose n is an energy card

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

            return true;
            
        } else{
            System.out.println("Chosen card is not an energy card.");
            return false;
        }
    }

    /*
     * The function activates the trainer card's effect
     * @param player a player object
     * @parm arrayPosition an int value
     * @return none
     */
    public void useTrainerCard(Player player, int arrayPosition){

        Card[] currentHand = getHand();
        Trainer trainerCard = (Trainer) currentHand[arrayPosition]; //Get trainer card
        trainerCard.activateEffect(player); //Activate effect

        if(trainerCard instanceof ProfessorOak){
            //Do nothing; the trainer card handles the effect
        } else{

            //Remove card from hand 
            Card[] updatedHand = getHand();
            Card[] newHand = new Card[updatedHand.length - 1];
            int newIndex = 0;
            for(int i = 0; i < updatedHand.length; i++){
                if(i != arrayPosition){
                    newHand[newIndex++] = updatedHand[i];
                }
            }
            setHand(newHand);

        }

        //Rmoved card gets moved to the discard pile
        Card[] currentDiscardPile = getDiscardPile();
        Card[] newDiDiscardPile = new Card[currentDiscardPile.length + 1];
        for(int i = 0; i < currentDiscardPile.length; i++){
            newDiDiscardPile[i] = currentDiscardPile[i];
        }
        newDiDiscardPile[newDiDiscardPile.length - 1] = trainerCard;
        setDiscardPile(newDiDiscardPile);
    }

    /*
     * The function allows the current player to retreat a pokemon
     * @param none
     * @return a boolean value
     */
    public Boolean allowRetreatPokemon(){

        Scanner scan = new Scanner(System.in);

        Boolean doneSwap = false;
        while(!doneSwap){

            Card currentActivePokemon = getActiveField();
            Energy[] activeEnergies = currentActivePokemon.getEnergies();

            System.out.print("Your " + currentActivePokemon.getName() + " has [");
            for(Energy energy : activeEnergies){
                if(energy != null){
                    System.out.print(energy.getName() + " ");
                }
            }
            System.out.print("]\n");

            System.out.print("Would you like to retreat your " + currentActivePokemon.getName() + "? (Yes or No): ");
            String swapOrNot = scan.next().toLowerCase();

            if(swapOrNot.equals("y") || swapOrNot.equals("yes")){

                //Check first if pokemon has enough energy and correct types of energy
                Energy[] currentRetreatCost = currentActivePokemon.getRetreatCost();
                int correctCounter = currentRetreatCost.length;
                for(Energy retreatEnergy : currentRetreatCost){
                    for(Energy activeEnergy : activeEnergies){
                        if(activeEnergy.getName().equals(retreatEnergy.getName())){
                            correctCounter--;
                        }
                    }
                }

                if(correctCounter <= 0){

                    System.out.print("Your " + currentActivePokemon.getName() + " has enough energy to retreat!");
                    Card[] currentBench = getbench();
                    Card[] currentDiscardPile = getDiscardPile();

                    System.out.print("\nThis is your bench: [");
                    for(Card card : currentBench){
                        if(card != null){
                            System.out.print(card.getName() + " ");
                        }
                    }
                    System.out.print("]\n");

                    System.out.print("Choose a pokemon to swap with the active pokemon (0 - N; position in array): ");
                    int arrayPosition = 0;
                    try {
                        arrayPosition = scan.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid option");
                    }
                    Card newActivePokemon = currentBench[arrayPosition]; //Get chosen pokemon to swap with

                    //Swap pokemon
                    Card[] newBench = new Card[currentBench.length];
                    for(int i = 0; i < currentBench.length; i++){

                        if(i == arrayPosition){
                            newBench[i] = currentActivePokemon;
                        } else{
                            newBench[i] = currentBench[i];
                        }

                    }
                    setBench(newBench);
                    setActiveField(newActivePokemon);

                    //Move the used active energy to the discard pile
                    Card[] newDiscardPile = new Card[currentDiscardPile.length + activeEnergies.length];
                    for(int i = 0; i < currentDiscardPile.length; i++){
                        newDiscardPile[i] = currentDiscardPile[i];
                    }
                    for(int i = activeEnergies.length; i > 0; i--){
                        newDiscardPile[newDiscardPile.length - i] = activeEnergies[i - 1];
                    }
                    setDiscardPile(newDiscardPile);

                } else{
                    System.out.println("Your " + currentActivePokemon.getName() + " does not have enough energy to retreat!");
                    break;
                }

            } else{
                doneSwap = true;
                return doneSwap;
            }
        }
        return doneSwap;
    }


    /*
     * The function allows the current player to attack with their active pokemon
     * @param opponentPlayer a player object
     * @return a boolean value
     */
    public Boolean allowPokemonAttack(Player opponentPlayer){

        Scanner scan = new Scanner(System.in);
        System.out.println("Player has chosen to attack!");

        Card currentActivePokemon = getActiveField();
        Energy[] activeEnergies = currentActivePokemon.getEnergies();

        System.out.print("Your " + currentActivePokemon.getName() + " has [");
        for(Energy energy : activeEnergies){
            if(energy != null){
                System.out.print(energy.getName() + " ");
            }
        }
        System.out.print("] energy \n");

        String[] abilityList = currentActivePokemon.getAbilityDescriptions();
        System.out.print("Your " + currentActivePokemon.getName() + " has [");
        for(String ability : abilityList){
            System.out.print(ability + " ");
        }
        System.out.print("] abilities \n");

        System.out.print("Choose an ability to perform (0 - N; position in array; if done then enter -1): ");
        int arrayPosition = 0;
        try {
            arrayPosition = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid option");
        }

        if(arrayPosition == -1){ //cancel attack
            return false;
        } else{

            String abilityChosen = abilityList[arrayPosition]; //get the ability chosen from the list
            if (currentActivePokemon instanceof Pokemon) { //check first that the current card (active zone) is a pokemon card
                Pokemon activePokemon = (Pokemon) currentActivePokemon;

                try {
                    //Find the method for the chosen ability dynamically using the given string name with Java's reflect method
                    Method method = activePokemon.getClass().getMethod(abilityChosen, Energy[].class);
                    Object result = method.invoke(activePokemon, (Object) activeEnergies); //Need casting for correct resolution

                    if (result instanceof Boolean) { //specified output for charmander Collect ability
                        Boolean collected = (Boolean) result;
                        if (collected) {
                            System.out.println("\nYour " + currentActivePokemon.getName() + " performs " + abilityChosen);
                            System.out.println("You draw 1 extra card!");
                            Card drawnCard = drawCard();
                            System.out.println("You got a " + drawnCard.getName() + "!");
                        }
                    } else if (result instanceof Integer) { //general output for other pokemon abilities

                        Pokemon opponentActivePokemon = (Pokemon) opponentPlayer.getActiveField();
                        String activeElement = activePokemon.getElementType();
                        String opponentWeakness = opponentActivePokemon.getWeakness();
                        if(activeElement.equals(opponentWeakness)){ //Check element types and give damage multiplier if applicable
                            int damageDone = (int) result * 2;
                            if(damageDone > 0){
                                System.out.println("\n" + opponentActivePokemon.getName() + " is weak against " + opponentWeakness + "! 2X damage applied!");
                                System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");
                            } else{
                                System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");
                            }
                            
                            // Update health based on damage dealt
                            opponentActivePokemon.reduceHealth(damageDone);

                            System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());
    
                            if(opponentActivePokemon.knockedOut()){ //if fell enemy pokemon, then award a card from the prize pile
    
                                //Allow current player to draw from the prize pile
                                System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                                getPrizeCard();
                            }
    
                        } else { //dealt no damage output
    
                            int damageDone = (int) result;
                            if(damageDone > 0){
                                System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");
                            } else{
                                System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");
                            }
                            
                            // Update health based on damage dealt (interface utilized)
                            opponentActivePokemon.reduceHealth(damageDone);
    
                            System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());
    
                            //(interface utilized)
                            if(opponentActivePokemon.knockedOut()){
    
                                //Allow current player to draw from the prize pile
                                System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                                getPrizeCard();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error: Active Pokemon is not a valid Pokemon instance.");
            }
        }
        return true;
    }

    /*
     * The function checks the current player whether they have an active pokemon, if not then pokemon in bench, if not then pokemon in hand, else the game is over
     * @param player1 a player object
     * @param player2 a player object
     * @return a boolean value (win or lose)
     */
    public Boolean checkIfPokemonWinCondition(Player player1, Player player2){

        //At the start of each turn, check if active pokemon still alive, otherwise make the player add one to the active zone
        //if no pokemon available, end game
        Pokemon activePokemon1 = (Pokemon) player1.getActiveField();
        if(activePokemon1.getHP() <= 0){

            //Check if bench contains a pokemon, if so, get the arrayposition for auto switch
            Card[] currentBenchPokemon = player1.getHand();
            int arrayPosition = 0;
            for(int i = 0; i < currentBenchPokemon.length; i++){ 
                if(currentBenchPokemon[i].getCardType().equals("Pokemon")){
                    arrayPosition = i;
                    break;
                }   
            }

            //check bench if any pokemon, if not then game over
            if(arrayPosition == 0){ 
                return false;
            } else{

                //move benched pokemon to the active field
                Card benchPokemon = currentBenchPokemon[arrayPosition];
                player1.setActiveField(benchPokemon);

                //move the dead pokemon to the discard pile and its any energy attached to it
                Card fallenPokemon = player1.getActiveField();
                Card[] currentDiscardPile = player1.getDiscardPile();
                Energy[] fallenPokemonEnergies = fallenPokemon.getEnergies();
                Card[] newDiscardPile = new Card[currentDiscardPile.length + fallenPokemonEnergies.length + 1];

                for(int i = 0; i < currentDiscardPile.length; i++){
                    newDiscardPile[i] = currentDiscardPile[i];
                }

                int index = 0;
                for(int i = currentDiscardPile.length; i < newDiscardPile.length - 1; i++){
                    newDiscardPile[i] = fallenPokemonEnergies[index++];
                }
                newDiscardPile[newDiscardPile.length - 1] = fallenPokemon;
                player1.setDiscardPile(newDiscardPile);
            }
        }
        return true;
    }

    /*
     * Changes the hand value
     * @param newHand an array of cards
     * @return none
     */
    public void setHand(Card[] newHand){
        hand = newHand;
    }

    /*
     * Changes the deck value
     * @param newDeck an array of cards
     * @return none
     */
    public void setDeck(Card[] newDeck){
        deck = newDeck;
    }

    /*
     * Changes the bench value
     * @param newBench an array of cards
     * @return none
     */
    public void setBench(Card[] newBench){
        bench = newBench;
    }

    /*
     * Changes the prize pile value
     * @param newPrizePile an array of cards
     * @return none
     */
    public void setPrizePile(Card[] newPrizePile){
        prizePile = newPrizePile;
    }

    /*
     * Changes the discard pile value
     * @param newDiscardPile an array of cards
     * @return none
     */
    public void setDiscardPile(Card[] newDiscardPile){
        discardPile = newDiscardPile;
    }

    /*
     * Changes the hand value
     * @param newPokemonCard a card object
     * @return none
     */
    public void setActiveField(Card newPokemonCard){
        activeField = newPokemonCard;
    }

    /*
     * Returns the hand value
     * @param none
     * @return hand an array of card objects
     */
    public Card[] getHand(){
        return hand;
    }

    /*
     * Returns the deck value
     * @param none
     * @return deck an array of card objects
     */
    public Card[] getDeck(){
        return deck;
    }

    /*
     * Returns the bench value
     * @param none
     * @return bench an array of card objects
     */
    public Card[] getbench(){
        return bench;
    }

    /*
     * Returns the prize pile value
     * @param none
     * @return prizePile an array of card objects
     */
    public Card[] getPrizePile(){
        return prizePile;
    }

    /*
     * Returns the discard pile value
     * @param none
     * @return discardPile an array of card objects
     */
    public Card[] getDiscardPile(){
        return discardPile;
    }

    /*
     * Returns the active field value
     * @param none
     * @return activeFiled a card object
     */
    public Card getActiveField(){
        return activeField;
    }
}
