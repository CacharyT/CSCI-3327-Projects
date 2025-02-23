/*
 * Cachary Tolentino
 * The GameSetup class is a helper class for the PokemonGame. It provides all the necessary functions to initiate a game state.
 */

//Imports
import java.util.Scanner;

public class GameSetup {

    //Global Variables
    //None

    /*
     * Default Constructor 
     * @param none
     * @return none
     */
    public GameSetup(){
        //No variables to intiialize
    }

    /*
     * This function prints all the rules, regulations, and how to play the game for the user to read.
     * @param none
     * @return none
     */
    public void printGameRules(){

        System.out.println("\n*************GAME SETUP**************");
        System.out.println("Game Setup: Players will choose which side of the coin they want. A coin will be flipped. Whichever side lands will have the first turn.");
        System.out.println("Game Setup: Both players will then draw 7 cards from their deck.");
        System.out.println("Game Setup: Both players will then place 1 pokemon from their hand to the active pokemon zone.");
        System.out.println("Game Setup: If a player does not have a pokemon on their hand, then they will reveal their hand, place the cards back into the deck to shuffle.");
        System.out.println("Game Setup: And draw 7 new cards. The opponent may draw 1 extra card per shuffle.");
        System.out.println("Game Setup: Both players have a choice of placing down more pokemon in the bench zone (up to 6 pokemon).");
        System.out.println("Game Setup: Lastly, both players will place 6 cards from the deck to the prize pile faced down.");

        System.out.println("\n*************RULES**************");
        System.out.println("Rule 1: Each player must have 60 cards in their deck.");
        System.out.println("Rule 2: Each player's deck must have a minimum of 1 pokemon.");
        System.out.println("Rule 3: Each player's deck may only have a maximum of 4 cards of the same kind (i.e Bill, Charmander, etc...). Except for energy cards.");
        System.out.println("Rule 4: Each player has a discard pile.");
        
        System.out.println("\n*************HOW TO PLAY**************");
        System.out.println("Player Turn: Draw a card.");
        System.out.println("Player Turn: Player can choose from the following...");
        System.out.println("Choice 1: Play exactly 1 energy card (attaching to an active or benched pokemon) for the turn.");
        System.out.println("Choice 2: Play a trainer card (repeatable).");
        System.out.println("Choice 3: Play a pokemon card (attack/use ability or retreat - cost energy based on pokemon requirement).");
        System.out.println("Choice 4: End Turn");
        System.out.println("Player Turn: A player's turn ends either by making a pokemon attack or by ending turn explicitly.");
        System.out.println("Player Turn: For every active pokemon you defeat you earn a prize from the prize pile.");

        System.out.println("\n*************HOW TO WIN**************");
        System.out.println("Scenario 1: You take all 6 of your own prizes from the prize pile.");
        System.out.println("Scenario 2: Your opponent has 0 pokemon in play.");
        System.out.println("Scenario 2: Your opponent can not draw because their deck is empty (*at the start of their turn)");

    }

    /*
     * The function will ask th user for inputs about the deck and then creates the deck filled with card objects.
     * @param player1 a player object
     * @return none
     */
    public void setupPlayerDecks(Player player1){

        Scanner scan = new Scanner (System.in);
        System.out.println("Note: Total # of Cards must add up to 60.");
        System.out.println("Note: The database contains 4 types of pokemons, 4 types of trainer cards, and 5 types of energy cards.");
        System.out.println("Note: Upon entering desired allocation, card types will be evenly distributed based on given amount. (i.e 16 pokemon = 4 charmander, 4 squirtle, etc...)");
        System.out.println("Reminder: You can only have a maximum of 4 cards of the same kind (not type - i.e Trainer, Pokemon, Energy)");

        int pokemonCount = 0;
        int trainerCount = 0;
        int energyCount = 0;

        System.out.print("\nEnter the # of pokemons (maximum: 16): ");
        pokemonCount = scan.nextInt();
        System.out.print("Enter the # of trainers (maximum: 16): ");
        trainerCount = scan.nextInt();
        System.out.print("Enter the # of energies: ");
        energyCount = scan.nextInt();

        //Create player deck based on the user inputs
        Boolean status = createPlayerDeck(player1, pokemonCount, trainerCount, energyCount);

        while(!status){ //ensures player inputs are correct allocation of the deck, otherwise repeat the process until true

            System.out.print("\nEnter the # of pokemons (maximum: 16): ");
            pokemonCount = scan.nextInt();
            System.out.print("Enter the # of trainers (maximum: 16): ");
            trainerCount = scan.nextInt();
            System.out.print("Enter the # of energies: ");
            energyCount = scan.nextInt();

            status = createPlayerDeck(player1, pokemonCount, trainerCount, energyCount);
        }
    }

    /*
     * The function will automatically draws a starting hand for both players, will automatically reshuffle and redraw given a mulligan found
     * @param player1 a player object
     * @param player2 a player object
     * @return none
     */
    public void setupPlayerHand(Player player1, Player player2){

        //Counter for how many redraws to perform due to mulligan found
        int player1Redraws = 0;
        int player2Redraws = 0;

        //Automatica redrawn and reshuffle given mulligan found
        //Player 1 phase:
        fillPlayerHand(player1); //adds 7 cards to the hand
        while(!checkIfPokemonInHand(player1)){ //checks if current hand is a mulligan

            //Reveal hand
            System.out.println("\nPlayer 1 did not have a pokemon on hand. Reveal, shuffle, and redraw.");
            Card[] currentHand = player1.getHand();
            for(Card card : currentHand){
                System.out.println("You are revealing a: " + card.getName());
            }

            player1.reAddHandToDeck(); //Put back the hand into the deck
            player1.shuffleDeck(); //Shuffle deck
            fillPlayerHand(player1); //Draw 7 again
            player2Redraws++; //Allow for player 2 to draw 1 extra
        }

        //Player 2 phase:
        fillPlayerHand(player2); //adds 7 cards to the hand
        while(!checkIfPokemonInHand(player2)){ //checks if current hand is a mulligan

            //Reveal hand
            System.out.println("\nPlayer 2 did not have a pokemon on hand. Reveal, shuffle, and redraw.");
            Card[] currentHand = player2.getHand();
            for(Card card : currentHand){
                System.out.println("Player 2 is revealing a: " + card.getName());
            }

            player2.reAddHandToDeck(); //Put back the hand into the deck
            player2.shuffleDeck(); //Shuffle deck
            fillPlayerHand(player2); //Draw 7 again
            player1Redraws++; //Allow for player 2 to draw 1 extra

        }

        System.out.println(""); //Added space for visual format

        //Allow Players to redraw due to reshuffling
        for(int num = 0; num < player1Redraws; num++){
            player1.drawCard();
            System.out.println("Player 1 has also drawn an extra card!");
        }
        for(int num = 0; num < player2Redraws; num++){
            player2.drawCard();
            System.out.println("Player 2 has also drawn an extra card!");
        }

    }

    /*
     * The function allows the current player to choose the pokemon they want to start with and any pokemon card to bench
     * @param player1 a player object
     * @param player2 a player object
     * @return none
     */
    public void setupPlayerFields(Player player1, Player player2){

        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlayer: Please place a pokemon in the active zone");
        Card[] currentHand = player1.getHand();

        System.out.print("\nThis is your current hand: ");
        System.out.print("[");
        for(Card card : currentHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]");

        System.out.print("\nWhich pokemon would you like to place in the active zone? (O - n; position in array): ");
        int position = scan.nextInt();

        placePokemonInActiveField(player1, position); //Player 1 placing pokemons (active zone minimum)
        beginningPokemonBench(player1);  //optional allow user to bench any additional pokemon

        //Fill prize piles
        fillPlayerPrize(player1);
        System.out.println("\nPlayer 1 has made their prize pile!");
        fillPlayerPrize(player2);
        System.out.println("Player 2 has made their prize pile!");

        //Reveal the active pokemons for both players
        System.out.println("\nPlayer's active pokemon is " + player1.getActiveField().getName());
        System.out.println("Player's active pokemon is " + player2.getActiveField().getName());

    }

    /*
     * The function allows the current player to choose the pokemon they want to start with and any pokemon card to bench but players are inverted
     * @param player1 a player object
     * @param player2 a player object
     * @return none
     */
    public void setupPlayerFieldsInverted(Player player1, Player player2){

        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlayer: Please place a pokemon in the active zone");
        Card[] currentHand = player2.getHand();

        System.out.print("\nThis is your current hand: ");
        System.out.print("[");
        for(Card card : currentHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]");

        System.out.print("\nWhich pokemon would you like to place in the active zone? (O - n; position in array): ");
        int position = scan.nextInt();

        placePokemonInActiveField(player2, position); //Player 1 placing pokemons (active zone minimum)
        beginningPokemonBench(player2); //optional allow user to bench any additional pokemon

        //Fill prize piles
        fillPlayerPrize(player1);
        System.out.println("\nPlayer 1 has made their prize pile!");
        fillPlayerPrize(player2);
        System.out.println("Player 2 has made their prize pile!");

        //Reveal the active pokemons for both players
        System.out.println("\nPlayer's active pokemon is " + player1.getActiveField().getName());
        System.out.println("Player's active pokemon is " + player2.getActiveField().getName());

    }

    /*
     * The function allows the current player to choose the pokemon they want to start with and any pokemon card to bench (specific for self v. self)
     * @param player1 a player object
     * @param player2 a player object
     * @return none
     */
    public void setupSoloPlayerFields(Player player1){

        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlayer: Please place a pokemon in the active zone");
        Card[] currentHand = player1.getHand();

        System.out.print("\nThis is your current hand: ");
        System.out.print("[");
        for(Card card : currentHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]");

        System.out.print("\nWhich pokemon would you like to place in the active zone? (O - n; position in array): ");
        int position = scan.nextInt();

        placePokemonInActiveField(player1, position); //Player 1 placing pokemons (active zone minimum, bench optional)
        beginningPokemonBench(player1); //optional allow user to bench any additional pokemon

        //Fill prize piles
        fillPlayerPrize(player1);
        System.out.println("\nPlayer has made their prize pile!");

        //Reveal the active pokemons for both players
        System.out.println("\nPlayer active pokemon is " + player1.getActiveField().getName());

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
     * The function will allow for the current player to place a pokemon into their active zone
     * @param player a player object
     * @param arrayPosition an int value
     * @return none
     */                                                                         
    public void placePokemonInActiveField(Player player, int arrayPosition){

        Scanner scan = new Scanner(System.in);

        //Get the pokemon from the hand
        Card[] currentHand = player.getHand();
        Card pokemonCard = currentHand[arrayPosition];

        Boolean done = false;
        while(!done){
            //Check first if the chosen card is of type pokemon, otherwise invalid
            if(pokemonCard.getName().equals("Pikachu") || pokemonCard.getName().equals("Bulbasaur") || pokemonCard.getName().equals("Charmander") || pokemonCard.getName().equals("Squirtle")){

                //Remove from the hand, update hand
                Card[] newHand = new Card[currentHand.length - 1];
                int newIndex = 0; //Allows for shifting the skipped values down
                for(int i = 0; i < currentHand.length; i++){
                    if(i != arrayPosition){
                        newHand[newIndex++] = currentHand[i];
                    }
                }
                player.setHand(newHand);
    
                //Add pokemon to active zone then update the active zone
                player.setActiveField(pokemonCard);
                done = true;
    
            } else{//restart for invalid choice
                System.out.print("Non-pokemon chosen. Pick again: ");
                int newChoice = scan.nextInt();
                System.out.print("\n");
                placePokemonInActiveField(player, newChoice);
                done = true;
            }
        }
    }

    /*
     * The function is a helper function that calls on the createDeck function of player objects
     * @param player a player object
     * @return none
     */
    public void createPlayerDeck(Player player){
        player.setDeck(player.createDeck());
    }

    /*
     * The function is a helper function that calls on the createDeck function of player objects using the given parameters
     * @param player a player object
     * @param pokemonCount an int value
     * @param trainerCount an int value
     * @param energyCount an int value
     * @return a boolan value
     */
    public Boolean createPlayerDeck(Player player, int pokemonCount, int trainerCount, int energyCount){
        Card[] value = player.createDeck(pokemonCount, trainerCount, energyCount);
        if(value == null){
            return false; //invalid inputs, must redo
        } 
        return true;
    }

    /*
     * The function is a helper function that calls on the player object fillhand method
     * @param player a player object
     * @return newHand an array of card objects
     */
    public static Card[] fillPlayerHand(Player player){
        Card[] newHand = player.fillHand();
        return newHand;
    }

    /*
     * The function is a helper function that callson the player's fillPrize function
     * @param player a player object
     * @return a boolean value
     */
    public Boolean fillPlayerPrize(Player player){
        player.fillPrize();
        return true;
    }

    /*
     * The function is a helper function that callson the player's checkIfPokemonInHand function
     * @param player a player object
     * @return a boolean value
     */
    public Boolean checkIfPokemonInHand(Player player){ 

        return player.checkPokemonInHand();

    }
    
}