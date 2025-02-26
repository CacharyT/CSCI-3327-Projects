/*
 * Cachary Tolentino
 * This class is responsible for handling all actions that the AI would make, especially during turns.
 */

//Imports
import java.lang.reflect.Method;
import java.util.Random;

public class AIPlayer extends Player{

    //Global Variables
    //None

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public AIPlayer(){
        //no global variables to initialize
    }

    /*
     * The function allows the AI to automatically set its active field with a pokemon, any leftover pokemon in hand is placed in the bench
     * @param player the player object
     * @return none
     */
    public void aiAutoActiveFieldAndBenchPokemon(Player player){

        Card[] currentHand = player.getHand();

        //Get first pokemon in hand, place in active field
        Card firstPoke = null;
        int firstPokePosition = 0;
        for(int i = 0; i < currentHand.length; i++){
            if(currentHand[i].getCardType().equals("Pokemon")){
                firstPoke = currentHand[i];
                firstPokePosition = i;
                break;
            }
        }
        player.setActiveField(firstPoke);

        //Remove pokemon from hand
        Card[] newHand = new Card[currentHand.length - 1];
        int newIndex = 0; //Allows for shifting the skipped values down
        for(int i = 0; i < currentHand.length; i++){
            if(i != firstPokePosition){
                newHand[newIndex++] = currentHand[i];
            }
        }
        player.setHand(newHand);

        //All other pokemon in hand, place in bench
        Card[] updatedHand = player.getHand();
        Card[] newBench = new Card[player.getbench().length];
        int index = 0;
        for(int i = 0; i < updatedHand.length; i++){
            if(updatedHand[i] == null){
                break;
            } else if(updatedHand[i].getCardType().equals("Pokemon")){
                newBench[index++] = updatedHand[i];
            }
        }
        player.setBench(newBench);

        //Find how many non-pokemon in hand
        int nonPokemonCounter = 0;
        for(Card card : updatedHand){
            if(!card.getCardType().equals("Pokemon")){
                nonPokemonCounter++;
            }
        }

        //Remove pokemons from hand
        Card[] newerHand = new Card[nonPokemonCounter];
        int newerIndex = 0;
        for(int i = 0; i < updatedHand.length; i++){
            if(!updatedHand[i].getCardType().equals("Pokemon")){
                newerHand[newerIndex++] = updatedHand[i];
            }
        }
        player.setHand(newerHand);

    }

    /*
     * The function emulates an AI that chooses their option based on available resources
     * @param player1 a player object
     * @param player2 a player object
     * @param turnOne a boolean value
     * @return boolean value indicating if the AI has no more pokemon available to play (defeat)
     */
    public Boolean aiTurn(AIPlayer player1, Player player2, Boolean turnOne){

        System.out.println("Current player draws a card!");
        Card drawnCard = player1.drawCard();
        player1.addCardToHand(drawnCard);

        //Turn restrictors
        Boolean endTurn = false;
        Boolean doneEnergy = false;
        Boolean doneRetreat = false;

        while(!endTurn){

            //Checks if the Ai has any playable pokemon
            if(!checkIfPokemonWinCondition(player1, player2)){
                return false;
            }

            int decision = aiDecisionMaker(player1);

            switch(decision){
                case 1:
                    endTurn = aiAddEnergy(player1, doneEnergy, endTurn); //adds energy for the AI
                    break;
                case 2:
                    endTurn = aiPlayTrainer(player1, endTurn); //activates the trainer for the AI
                    break;
                case 3:
                    endTurn = aiAddToBench(player1, endTurn); //add a pokemon to the AI bench
                    break;
                case 4:
                    endTurn = aiRetreatPokemon(doneRetreat, player1, endTurn); //retreat the active pokemon
                    break;
                case 5:
                    endTurn = aiPokemonAttack(player1, turnOne, player2, endTurn); //allow pokemon to attack and end turn
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
     * The function will determine the possible decision that an AI can perform for a turn
     * @param player a player object
     * @return decision an int value
     */
    public int aiDecisionMaker(Player player){
        //Random object
        Random random = new Random();

        Card[] currentHand = player.getHand();
        Card[] currentBench = player.getbench();

        //Check for which options the AI can perform
        Boolean hasEnergy = false;
        Boolean hasTrainer = false;
        Boolean hasPokemonForAttack = true;
        Boolean hasPokemonForBench = false;
        Boolean canEndTurn = true;

        for(Card card : currentHand){
            if(card.getCardType().equals("Energy")){
                hasEnergy = true;
                break;
            }
        }
        for(Card card : currentHand){
            if(card.getCardType().equals("Trainer")){
                hasTrainer = true;
                break;
            }
        }

        for(Card card : currentBench){
            if(card != null){
                hasPokemonForBench = true;
            }
        }

        int[] decisions = {1, 2, 3, 4, 5, 6};
        int[] noEnergy = {2, 3, 4, 5, 6};
        int[] noTrainer = {1, 3, 4, 5, 6};
        int[] noBench = {1, 2, 5, 6};
        int[] noEnergyAndTrainer = {3, 4, 5, 6};
        int[] noEnergyAndBench = {2, 5, 6};
        int[] notrainerAndBench = {1, 5, 6};
        int[] noneAll = {5,6};
        int decision = 0;

        //Chooses options based on found resources
        if(hasEnergy && hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(decisions.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(!hasEnergy && !hasTrainer && !hasPokemonForAttack && !hasPokemonForBench && !canEndTurn){

            int randomDecision = random.nextInt(noneAll.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(!hasEnergy && hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(noEnergy.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(hasEnergy && !hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(noTrainer.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(hasEnergy && hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(noBench.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(!hasEnergy && !hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(noEnergyAndTrainer.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(!hasEnergy && hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(noEnergyAndBench.length);
            decision = decisions[randomDecision];
            return decision;

        } else if(hasEnergy && !hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

            int randomDecision = random.nextInt(notrainerAndBench.length);
            decision = decisions[randomDecision];
            return decision;

        } else{ //case not covered, still allow for them to exit turn or attack

            int randomDecision = random.nextInt(noneAll.length);
            decision = decisions[randomDecision];
            return decision;

        }
    }


    /*
     * The function will allow the AI to add an energy into their active pokemon
     * @param player1 a player object
     * @param doneEnergy a boolean
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean aiAddEnergy(Player player1, Boolean doneEnergy, Boolean endTurn){

        //Allow the pokemon to have no restriction for how mnay energy (balancing to give the Ai a chance to attack)
        if(!doneEnergy){

            //Find the first energy and add it to the active pokemon
            Card[] playerHand = player1.getHand();
            int energyPosition = 0;
            Boolean fulfilled = false;
            for(int i = 0; i < playerHand.length; i++){
                if(playerHand[i].getCardType().equals("Energy")){
                    energyPosition = i;
                    fulfilled = true;
                    break;
                }
            }

            //Adds the energy, otherwise ends turn 
            if(fulfilled){

                player1.addEnergyToPokemon(energyPosition);
                Card activePokemon = player1.getActiveField();
                System.out.print("\nYour active pokemon " + activePokemon.getName() + " now has [");
                Energy[] pokemonEnergies = activePokemon.getEnergies();
                for(Energy energy: pokemonEnergies){
                    System.out.print(energy.getName() + " ");
                }
                System.out.println("]");

            } else{

                System.out.println("Found no energy.");
                System.out.println("Current player has ended their turn!");
                endTurn = true;
                return endTurn;

            }

        } else{
            System.out.println("You can not add anymore energy this turn.");
        }
        return false;
    }


    /*
     * The function will activate the trainer card for the AI
     * @param player1 a player object
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean aiPlayTrainer(Player player1, Boolean endTurn){
        //Find the first trainer card in hand, play it
        Card[] trainerHand = player1.getHand();
        int arrayPosition = 0;
        Boolean foundValue = false;
        for(int i = 0; i < trainerHand.length; i++){

            if(trainerHand[i].getCardType().equals("Trainer")){
                arrayPosition = i;
                foundValue = true;
                break;
            }

        }

        //Restrictions: AI can not play Mr.Fuji & Recycle (due to input limitation)
        //If trainer card is Recyle or Mr.Fuji; then redo turn (will lose the card)
        //ohterwise play the card
        if(foundValue){
            Card trainerCard = trainerHand[arrayPosition];
            if(trainerCard instanceof Bill || trainerCard instanceof ProfessorOak){
                player1.useTrainerCard(player1, arrayPosition);
            }
        } else{
            System.out.println("Found no trainer card.");
            System.out.println("Current player has ended their turn!");
            endTurn = true;
            return endTurn;
        }
        return false;
    }

    /*
     * The function will add a pokemon to the bench for the Ai
     * @param player1 a player object
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean aiAddToBench(Player player1, Boolean endTurn){
        //Get first pokemon from hand and bench it
        Card[] benchHand = player1.getHand();
        int arrayPositionBench = 0;
        Boolean found = false;
        for(int i = 0; i < benchHand.length; i++){

            if(benchHand[i].getCardType().equals("Pokemon")){
                arrayPositionBench = i;
                found = true;
                break;
            }

        }

        //Benches pokemon if found, otherwise end turn
        if(found){
            benchPokemonFromHand(player1, arrayPositionBench);
        } else{
            System.out.println("Found no pokemon.");
            System.out.println("Current player has ended their turn!");
            endTurn = true;
            return endTurn;
        }
        return false;
    }

    /*
     * The function will allow the Ai to retreat their active pokemon
     * @param doneRetreat a boolean value
     * @param player1 a player object
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean aiRetreatPokemon(Boolean doneRetreat, Player player1, Boolean endTurn){
        //Ensures only retreatable once per turn
        if(!doneRetreat){

            //Get Active Pokemon
            Card currentPokemon = player1.getActiveField();
            Energy[] activeEnergies = currentPokemon.getEnergies();

            //Check first if pokemon has enough energy and correct types of energy
            Energy[] currentRetreatCost = currentPokemon.getRetreatCost();

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

                Card[] bench = player1.getbench();
                Card[] currentDiscardPile = player1.getDiscardPile();

                //Find the first pokemon in the bench
                Card newActivePokemon = bench[0];

                //Swap pokemon
                Card[] newBench = new Card[bench.length];
                for(int i = 0; i < bench.length; i++){

                    if(i == 0){
                        newBench[i] = currentPokemon;
                    } else{
                        newBench[i] = bench[i];
                    }

                }
                player1.setBench(newBench);
                player1.setActiveField(newActivePokemon);

                //Move the used active energy to the discard pile
                Card[] newDiscardPile = new Card[currentDiscardPile.length + activeEnergies.length];
                for(int i = 0; i < currentDiscardPile.length; i++){
                    newDiscardPile[i] = currentDiscardPile[i];
                }
                for(int i = activeEnergies.length; i > 0; i--){
                    newDiscardPile[newDiscardPile.length - i] = activeEnergies[i - 1];
                }
                player1.setDiscardPile(newDiscardPile);

                doneRetreat = true;

            } else{
                System.out.print("Your " + currentPokemon.getName() + " does not have enough energy to retreat!");
                System.out.println("\nCurrent player has ended their turn!");
                endTurn = true;
                return endTurn;
            }

        } else{
            System.out.println("You can not perform a retreat again for this turn.");
        }
        return false;
    }

    /*
     * The function will allow the AI to perform an attack with their active pokemon
     * @param player1 a player object
     * @param turnone a boolean value
     * @param player2 a player object
     * @param endTurn a boolean value
     * @return a boolean value
     */
    public Boolean aiPokemonAttack(Player player1, Boolean turnOne, Player player2, Boolean endTurn){
        if(!turnOne){

            //Check if active pokemon is still alive then allow to attack, otherwise end turn
            Pokemon activePoke = (Pokemon) player1.getActiveField();
            if(activePoke.getHP() > 0){

                Boolean state = false;
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
                            state = true; //end turn
                        } else if (result instanceof Integer) {

                            //Check element types and give damage multiplier if applicable
                            Pokemon opponentActivePokemon = (Pokemon) player2.getActiveField();
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
                                
                                // Update health based on damage dealt (interface utilized)
                                opponentActivePokemon.reduceHealth(damageDone);

                                System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());

                                if(opponentActivePokemon.knockedOut()){

                                    //Allow current player to draw from the prize pile
                                    System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                                    getPrizeCard();
                                }
                                state = true; //end turn
                            } else {

                                int damageDone = (int) result;
                                if(damageDone > 0){
                                    System.out.println("\nYour " + currentActivePokemon.getName() + " has attacked with " + abilityChosen + " causing " + damageDone + " damage to " + opponentActivePokemon.getName() + "!");
                                } else{
                                    System.out.println("\nYour " + currentActivePokemon.getName() + " dealt no damage.");
                                }

                                // Update health based on damage dealt (interface utilized)
                                opponentActivePokemon.reduceHealth(damageDone);

                                System.out.println("Enemy's " + opponentActivePokemon.getName() + " health is now " + opponentActivePokemon.getHP());

                                if(opponentActivePokemon.knockedOut()){

                                    //Allow current player to draw from the prize pile
                                    System.out.println("\nThe enemy's " + opponentActivePokemon.getName() + " has fallen. You may draw from the prize pile!");
                                    getPrizeCard();
                                }
                                state = true; //end turn
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Error: Active Pokemon is not a valid Pokemon instance.");
                }

                //Decide player turn end
                if(state){
                    System.out.println("Current player has ended their turn!");
                    endTurn = true;
                    return endTurn;
                } 
            } else{
                System.out.println("You can not attack when your pokemon has fallen.");
                return true;
            }
        } else{
            System.out.println("You can not attack during the first turn.");
            return false;
        }
        return false;
    }
    
}
