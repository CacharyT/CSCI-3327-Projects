/*
 * 
 */

import java.util.Scanner;

public class GameSetup {

    //Global Variables

    /*
     * Default Constructor 
     * @param none
     * @return none
     */
    public GameSetup(){

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
     * 
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

        //GAME SETUP PHASE---------------

        //Create players deck
        Boolean status = createPlayerDeck(player1, pokemonCount, trainerCount, energyCount);

        while(!status){ //ensures player inputs correcting allocation of the deck

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
     * 
     */
    public void setupPlayerHand(Player player1, Player player2){

        //Boolean round1Identifier = true;
        //Boolean winnerFound = false;
        int player1Redraws = 0;
        int player2Redraws = 0;

        //Let Player 1 and 2 perform start phase
        //Player 1 phase:
        fillPlayerHand(player1);

        while(!checkIfPokemonInHand(player1)){

            //Reveal hand
            System.out.println("\nPlayer 1 did not have a pokemon on hand. Reveal, shuffle, and redraw.");
            Card[] currentHand = player1.getHand();

            for(Card card : currentHand){
                System.out.println("You are revealing a: " + card.getName());
            }

            //Put back the hand into the deck
            player1.reAddHandToDeck();

            //Shuffle deck
            player1.shuffleDeck();

            //Draw 7 again
            fillPlayerHand(player1);

            //Allow for player 2 to draw 1 extra
            player2Redraws++;
        }

        //Player 1 phase:
        fillPlayerHand(player2);

        while(!checkIfPokemonInHand(player2)){

            //Reveal hand
            System.out.println("\nPlayer 2 did not have a pokemon on hand. Reveal, shuffle, and redraw.");
            Card[] currentHand = player2.getHand();

            for(Card card : currentHand){
                System.out.println("Player 2 is revealing a: " + card.getName());
            }

            //Put back the hand into the deck
            player2.reAddHandToDeck();

            //Shuffle deck
            player2.shuffleDeck();

            //Draw 7 again
            fillPlayerHand(player2);

            //Allow for player 2 to draw 1 extra
            player1Redraws++;

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
     * 
     */
    public void setupPlayerFields(Player player1, Player player2){

        Scanner scan = new Scanner(System.in);

        //Players put down a pokemon(allow player to add however many they want, Ai auto adds all)
        //Player 1 placing pokemons (active zone minimum, bench optional)
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

        placePokemonInActiveField(player1, position);

        //optional allow user to bench any additional pokemon
        beginningPokemonBench(player1); 

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
     * 
     */
    public void setupPlayerFieldsInverted(Player player1, Player player2){

        Scanner scan = new Scanner(System.in);

        //Players put down a pokemon(allow player to add however many they want, Ai auto adds all)
        //Player 1 placing pokemons (active zone minimum, bench optional)
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

        placePokemonInActiveField(player2, position);

        //optional allow user to bench any additional pokemon
        beginningPokemonBench(player2); 

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
     * 
     */
    public void setupSoloPlayerFields(Player player1){

        Scanner scan = new Scanner(System.in);

        //Players put down a pokemon(allow player to add however many they want, Ai auto adds all)
        //Player 1 placing pokemons (active zone minimum, bench optional)
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

        placePokemonInActiveField(player1, position);

        //optional allow user to bench any additional pokemon
        beginningPokemonBench(player1); 

        //Fill prize piles
        fillPlayerPrize(player1);
        System.out.println("\nPlayer has made their prize pile!");

        //Reveal the active pokemons for both players
        System.out.println("\nPlayer active pokemon is " + player1.getActiveField().getName());

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
            String decision = scan.next().toLowerCase();

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
    public void placePokemonInActiveField(Player player, int arrayPosition){

        Scanner scan = new Scanner(System.in);

        Card[] currentHand = player.getHand();

        //Get the pokemon from the hand
        Card pokemonCard = currentHand[arrayPosition];

        Boolean done = false;

        while(!done){

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
    
            } else{
    
                System.out.print("Non-pokemon chosen. Pick again: ");
                int newChoice = scan.nextInt();
                System.out.print("\n");
                placePokemonInActiveField(player, newChoice);
                done = true;
    
            }

        }
    }

    /*
     * 
     */
    public void createPlayerDeck(Player player){

        player.setDeck(player.createDeck());

    }

    /*
     * 
     */
    public Boolean createPlayerDeck(Player player, int pokemonCount, int trainerCount, int energyCount){

        Card[] value = player.createDeck(pokemonCount, trainerCount, energyCount);

        if(value == null){
            return false; //invalid inputs, must redo
        } 

        return true;

    }

    /*
     * 
     */
    public static Card[] fillPlayerHand(Player player){

        Card[] newHand = player.fillHand();

        return newHand;
    }


    /*
     * 
     */
    public Boolean fillPlayerPrize(Player player){

        player.fillPrize();

        return true;

    }

    /*
     * 
     */
    public Boolean checkIfPokemonInHand(Player player){ 

        return player.checkPokemonInHand();

    }
    
}