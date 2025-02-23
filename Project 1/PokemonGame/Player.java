/*
 * 
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
     * 
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

            if(!checkIfPokemonWinCondition(player1, player2)){
                return false;
            }

            Card[] currentHand = player1.getHand();

            System.out.print("This is your current hand: [");
            for(Card card : currentHand){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");

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
                case 1:
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

                        if(continueOrNot.equals("y") || continueOrNot.equals("yes")){
                            System.out.print("Pick an energy card to place on the current active pokemon (0 - N; position in array): ");
                            int arrayPositionEnergy = scan.nextInt();
                            doneEnergy = player1.addEnergyToPokemon(arrayPositionEnergy);
                        }

                        if(doneEnergy){
                            Card activePokemon = player1.getActiveField();
                            System.out.print("\nYour active pokemon " + activePokemon.getName() + " now has [");
                            Energy[] pokemonEnergies = activePokemon.getEnergies();
                            for(Energy energy: pokemonEnergies){
                                System.out.print(energy.getName() + " ");
                            }
                            System.out.println("]");
                        } else{
                            System.out.println("Adding energy failed.");
                        }

                    } else{
                        System.out.println("You can not add anymore energy this turn.");
                    }
                    
                    
                    break;
                case 2:
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

                            player1.useTrainerCard(player1, arrayPosition);

                        }
                    }

                    break;
                case 3:

                    beginningPokemonBench(player1);

                    break;
                case 4:

                    if(!doneRetreat){
                        doneRetreat = player1.allowRetreatPokemon();
                    } else{
                        System.out.println("You can not perform a retreat again for this turn.");
                    }

                    break;
                case 5:
                    if(!turnOne){
                        Boolean state = player1.allowPokemonAttack(player2);
                        if(state){
                            System.out.println("Current player has ended their turn!");
                            endTurn = true;
                        } 
                    } else{
                        System.out.println("You can not attack during the first turn.");
                    }
                    break;
                case 6:
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
     * 
     */
    public void beginningPokemonBench(Player player){ //NOTE: MIGHT NEED TO MOVE TO PLAYER

        Scanner scan = new Scanner(System.in);
        Card[] currentHand = player.getHand();

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

                while(!benchDone){
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
                //Does nothing
            } else{
                System.out.println("Invalid option. Retry.");
                beginningPokemonBench(player);
            }
        } else{
            System.out.println("Your bench is full!");
        }

    }


    /*
     * 
     */
    public void benchPokemonFromHand(Player player, int arrayPosition){

        //Get the pokemon from the hand, but check first if of type pokemon, otherwise not allowed
        Card[] currentHand = player.getHand();
        Card pokemonCard = currentHand[arrayPosition];

        if(pokemonCard.getCardType().equals("Pokemon")){

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
            Card[] currentBench = player.getbench();
            Card[] newBench = new Card[currentBench.length];
            Boolean benchHasSpace = false;

            //Check first if the bench is already full
            for(Card card : currentBench){
                if(card == null){
                    benchHasSpace = true;
                }
            }

            if(benchHasSpace){

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
     * 
     */
    public Card[] createDeck(){

        Random random = new Random();

        int totalCards = 60;
        Card[] newDeck = new Card[totalCards];

        //Default deck to 16 16 28
        int pokemonCount = 16;
        int trainerCount = 16;
        int energyCount = 28;

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
     * Uses Fisher-Yates Algorithm CITE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
    public Boolean addEnergyToPokemon(int arrayPosition){

        Card[] currentHand = getHand();
        Card activePokemon = getActiveField();

        //Add energy card to pokemon and update pokemon energy
        Energy[] currentEnergies = activePokemon.getEnergies();
        Energy[] newEnergies = new Energy[currentEnergies.length + 1];

        for(int i =0; i < currentEnergies.length; i++){
            newEnergies[i] = currentEnergies[i];
        }

        if(currentHand[arrayPosition].getCardType().equals("Energy")){

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
     * 
     */
    public void useTrainerCard(Player player, int arrayPosition){

        Card[] currentHand = getHand();

        //Get trainer card
        Trainer trainerCard = (Trainer) currentHand[arrayPosition];

        //Activate effect
        trainerCard.activateEffect(player);

        if(trainerCard instanceof ProfessorOak){

            //Do nothing, ProfessorOak handles the updating
            
        } else{

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

        }

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

                //Checks if active pokemon has enough energy
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

                    System.out.print("Choose a pokemon to swap with the active pokemon (0 - N; position in array; if done then enter -1): ");
                    int arrayPosition = scan.nextInt();


                    //Get chosen pokemon to swap with
                    Card newActivePokemon = currentBench[arrayPosition];

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
     * 
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
        int arrayPosition = scan.nextInt();

        if(arrayPosition == -1){
            return false;
        } else{

            String abilityChosen = abilityList[arrayPosition];

            if (currentActivePokemon instanceof Pokemon) {
                Pokemon activePokemon = (Pokemon) currentActivePokemon;

                try {
                    // Find the method for the chosen ability dynamically
                    Method method = activePokemon.getClass().getMethod(abilityChosen, Energy[].class);
                    Object result = method.invoke(activePokemon, (Object) activeEnergies); //Need casting for correct resolution

                    if (result instanceof Boolean) {
                        Boolean collected = (Boolean) result;
                        if (collected) {
                            System.out.println("\nYour " + currentActivePokemon.getName() + " performs " + abilityChosen);
                            System.out.println("You draw 1 extra card!");
                            Card drawnCard = drawCard();
                            System.out.println("You got a " + drawnCard.getName() + "!");
                        }
                    } else if (result instanceof Integer) {
                        //Check element types and give damage multiplier if applicable
                        Pokemon opponentActivePokemon = (Pokemon) opponentPlayer.getActiveField();

                        String activeElement = activePokemon.getElementType();
                        String opponentWeakness = opponentActivePokemon.getWeakness();
                        if(activeElement.equals(opponentWeakness)){

                            int damageDone = (int) result * 2;
                            if(damageDone > 0){

                                System.out.println("\n" + opponentActivePokemon.getName() + " is weak against " + opponentWeakness + "! 2X damage applied!");
                                System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");
    
                            } else{
    
                                System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");
    
                            }
                            
                            // Update health based on damage dealt
                            int currentOpponentHP = opponentActivePokemon.getHP();
                            int newOpponentHp = currentOpponentHP - damageDone;
                            opponentActivePokemon.setHP(newOpponentHp);
    
                            System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());
    
                            if(newOpponentHp <= 0){
    
                                //Allow current player to draw from the prize pile
                                System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                                getPrizeCard();
    
                            }
    
                        } else {
    
                            int damageDone = (int) result;

                            if(damageDone > 0){

                                System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");
    
                            } else{
    
                                System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");
    
                            }
                            
                            // Update health based on damage dealt
                            int currentOpponentHP = opponentActivePokemon.getHP();
                            int newOpponentHp = currentOpponentHP - damageDone;
                            opponentActivePokemon.setHP(newOpponentHp);
    
                            System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());
    
                            if(newOpponentHp <= 0){
    
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
     * 
     */
    public Boolean allowPokemonAttackAI(Player opponentPlayer){

        Random random = new Random();

        Card currentActivePokemon = getActiveField();
        Energy[] activeEnergies = currentActivePokemon.getEnergies();

        //Randomly choose an ability to perform (with our without knowing if enough energy)
        String[] abilityList = currentActivePokemon.getAbilityDescriptions();
        String abilityChosen = abilityList[random.nextInt(2)];

        if (currentActivePokemon instanceof Pokemon) {
            Pokemon activePokemon = (Pokemon) currentActivePokemon;

            try {
                // Find the method for the chosen ability dynamically
                Method method = activePokemon.getClass().getMethod(abilityChosen, Energy[].class);
                Object result = method.invoke(activePokemon, (Object) activeEnergies); //Need casting for correct resolution

                if (result instanceof Boolean) {
                    Boolean collected = (Boolean) result;
                    if (collected) {
                        System.out.println("\nYour " + currentActivePokemon.getName() + " performs " + abilityChosen);
                        System.out.println("You draw 1 extra card!");
                        Card drawnCard = drawCard();
                        System.out.println("You got a " + drawnCard.getName() + "!");
                    }
                } else if (result instanceof Integer) {

                    //Check element types and give damage multiplier if applicable
                    Pokemon opponentActivePokemon = (Pokemon) opponentPlayer.getActiveField();

                    String activeElement = activePokemon.getElementType();
                    String opponentWeakness = opponentActivePokemon.getWeakness();

                    if(activeElement.equals(opponentWeakness)){

                        int damageDone = (int) result * 2;

                        if(damageDone > 0){

                            System.out.println("\n" + opponentActivePokemon.getName() + " is weak against " + opponentWeakness + "! 2X damage applied!");
                            System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");

                        } else{

                            System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");

                        }
                        
                        
                        // Update health based on damage dealt
                        int currentOpponentHP = opponentActivePokemon.getHP();
                        int newOpponentHp = currentOpponentHP - damageDone;
                        opponentActivePokemon.setHP(newOpponentHp);

                        System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());

                        if(newOpponentHp <= 0){

                            //Allow current player to draw from the prize pile
                            System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                            getPrizeCard();

                        }

                    } else {

                        int damageDone = (int) result;

                        if(damageDone > 0){

                            System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");

                        } else{

                            System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");

                        }


                        // Update health based on damage dealt
                        int currentOpponentHP = opponentActivePokemon.getHP();
                        int newOpponentHp = currentOpponentHP - damageDone;
                        opponentActivePokemon.setHP(newOpponentHp);

                        System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());

                        if(newOpponentHp <= 0){

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

        return true;
    }

    /*
     * 
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
                Card[] newDiscardPile = new Card[fallenPokemonEnergies.length + 1];

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
