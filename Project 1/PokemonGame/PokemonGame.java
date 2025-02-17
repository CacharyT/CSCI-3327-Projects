/*
 * Cachary Tolentino
 * The PokemonGame is a java class that emulates the popular trading card game, Pokemon.
 */

//Imports
import java.util.Random;
import java.util.Scanner;


public class PokemonGame {
    
    //Global Variable(s)
    private Player[] players;


    /*
     * Default Constructor (creates two player objects)
     * @param none
     * @return none
     */
    public PokemonGame(){
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
    }

    /*
     * The main game mechanic that starts the game for the player to play\
     * @param none
     * @return none
     */
    public void startGame(){

        //Declared variables
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        //Ask if player wants to play, otherwise end program
        System.out.print("Welcome to Pokemon, a trading card game! Would you like to play? (Y or N): ");

        String playOrNot = scan.next().toLowerCase();

        if(playOrNot.equals("y") || playOrNot.equals("yes")){

            //Print Game Rules & Instructions
            playOrNot = null;
            printGameRules();

            System.out.print("\nWould you like to continue playing? (Y or N): ");//confirmation before fully initializing the game
            playOrNot = scan.next().toLowerCase();

            if(playOrNot.equals("y") || playOrNot.equals("yes")){

                System.out.print("How would you like to play? Self or AI: ");

                String gameType = scan.next().toLowerCase();


                //Game type decider (AI or Self v. Self)
                if(gameType.equals("ai")){

                    //Flip a coin: 0 - heads, 1 - tail
                    //Check which player goes first
                    System.out.print("\nCoin flip: Heads or Tails? (0 - Head, 1 - Tails): ");
                    int playerCoin = scan.nextInt();
                    int coinFlipped = flipACoin();

                    if(coinFlipped == playerCoin){

                        System.out.print("\nThe coin landed on " + playerCoin + "! You will be player 1 and have the first turn! Player 2 will be the AI.");

                        Player player1 = getPlayers()[0];
                        Player player2 = getPlayers()[1];

                        //Extra credit - Allow player to create their own deck or not
                        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
                        String customize = scan.next().toLowerCase();

                        if(customize.equals("y")){

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

                            //Create both players decks
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

                            //AI Deck
                            createPlayerDeck(player2);

                            //Boolean round1Identifier = true;
                            //Boolean winnerFound = false;
                            int player1Redraws = 0;
                            int player2Redraws = 0;

                            //Let Player 1 and 2 perform start phase
                            //Player 1 phase:
                            fillPlayerHand(player1);

                            while(!checkIfPokemonInHand(player1)){

                                //Reveal hand
                                System.out.println("\nYou do not have a pokemon on hand. Reveal, shuffle, and redraw until you do.");
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
                                drawPlayerCard(player1);
                                System.out.println("Player 1 has also drawn an extra card!");
                            }
                            for(int num = 0; num < player2Redraws; num++){
                                drawPlayerCard(player2);
                                System.out.println("Player 2 has also drawn an extra card!");
                            }


                            //Players put down a pokemon(allow player to add however many they want, Ai auto adds all)
                            //Player 1 placing pokemons (active zone minimum, bench optional)
                            System.out.println("\nPlayer 1: Please place a pokemon in the active zone");
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

                            //Player 2 auto place pokemon to active zone and every other pokemon to bench
                            aiAutoActiveFieldAndBenchPokemon(player2);


                            //Fill prize piles
                            fillPlayerPrize(player1);
                            System.out.println("\nPlayer 1 has made their prize pile!");
                            fillPlayerPrize(player2);
                            System.out.println("Player 2 has made their prize pile!");

                            //Reveal the active pokemons for both players
                            System.out.println("\nPlayer 1's active pokemon is " + player1.getActiveField().getName());
                            System.out.println("Player 2's active pokemon is " + player2.getActiveField().getName());

                            //Main game loop (does not end until a winner is found)
                            Player winnerFound = null;

                            while(winnerFound == null){

                                //Allow user to make their choice
                                System.out.println("It is now Player 1's turn!\n");
                                playerTurn(player1);
                                winnerFound = checkWinner(player1, player2);
                                if(winnerFound != null){
                                    break;
                                }

                                //AI Turn - randomly chooses what to do
                                System.out.println("It is now Player 2's turn!\n");
                                aiTurn(player2);
                                winnerFound = checkWinner(player1, player2);
                                if(winnerFound != null){
                                    break;
                                }

                            }
                            

                            //Winner has been found, announce who won. And end program








                        } else if(customize.equals("n")){

                            //Create both players decks


                            //Start game loop


                        } else{
                            System.out.println("That is an invalid option. Game restarting.");
                            startGame();
                        }

                    } else{

                        System.out.print("\nThe coin landed on " + coinFlipped + "! AI will be player 1 and have the first turn! You will be player 2.");

                        Boolean winnerFound = false;

                        while(!winnerFound){
                            
                            





                        }

                    }



                } else if(gameType.equals("self")){

                    









                } else{
                    System.out.println("That is an invalid option. Game restarting.");
                    startGame();
                }
            } else{
                System.out.println("Ok! Have a good day!");
            }
        } else{
            System.out.println("Ok! Have a good day!");
        }
        
    }


    /*
     * Flips a coin to determine who will goe first (0 - heads, 1 - tails)
     * @param none
     * @return an int value
     */
    public static int flipACoin(){
        Random random = new Random();
        return random.nextInt(2);
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
    private Boolean fillPlayerPrize(Player player){

        player.fillPrize();

        return true;

    }

    /*
     * 
     */
    private Boolean checkIfPokemonInHand(Player player){

        Card[] currentHand = player.getHand();

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
    private Card drawPlayerCard(Player player){

        //Let player draw and return the card
        Card drawnCard = player.drawCard();

        return drawnCard;

    }

    /*
     * 
     */
    public Boolean getPrizeCard(Player player){
        player.getPrizeCard();
        return true;
    }


    /*
     * 
     */
    public void beginningPokemonBench(Player player){

        Scanner scan = new Scanner(System.in);
        Card[] currentHand = player.getHand();

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

    }


    /*
     * 
     */
    public void benchPokemonFromHand(Player player, int arrayPosition){

        //Get the pokemon from the hand
        Card[] currentHand = player.getHand();
        Card pokemonCard = currentHand[arrayPosition];

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

        for(int i = 0; i < currentBench.length; i++){

            if(currentBench[i] != null){
                newBench[i] = currentBench[i];
            } else{
                newBench[i] = pokemonCard;
                break;
            }

        }

        player.setBench(newBench);

    }

    /*
     * 
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
        Card[] newHand = new Card[currentHand.length];
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
        int[] removePokemonPositions = new int[updatedHand.length];

        for(int i = 0; i < updatedHand.length; i++){
            if(updatedHand[i] == null){
                break;
            } else if(updatedHand[i].getCardType().equals("Pokemon")){
                newBench[i] = updatedHand[i];
                removePokemonPositions[i] = i;
            }
        }
        player.setBench(newBench);


        //Remove pokemons from hand
        Card[] newerHand = new Card[updatedHand.length];
        for(int i = 0; i < updatedHand.length; i++){
            int newerIndex = 0; //Allows for shifting the skipped values down
            for(int position : removePokemonPositions){
                if(i != position){
                    newerHand[newerIndex] = updatedHand[i];
                }
            }
            newerIndex++;
        }
        player.setHand(newerHand);


    }


    /*
     * 
     */
    public void placePokemonInActiveField(Player player, int arrayPosition){

        Card[] currentHand = player.getHand();

        //Get the pokemon from the hand
        Card pokemonCard = currentHand[arrayPosition];

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

    }


    /*
     * 
     */
    private Boolean playerTurn(Player player1){

        //KEEP IN MIND FOR SOME POKEMON HAVE SPECIAL EFFECTS (i.e CHARMANDER'S BASIC, MAKE THE PLAYER DRAW A CARD IF TRUE RETURNED BY ABILITY)

        // PLAYER TURN PHASE
        //Player1 turn: Draw 1 card, 
        //Then let user decide (1 -3 are unlimited, 4 only once):
        // 1. Play 1 energy for the turn (aka put the energy on a pokemon)
        // 2. Play a trainer card
        // 3. Play a pokemon card (CAN ONLY HAVE ONE POKEMON ACTIVE - BUT CAN BENCH MORE IF HAS SPACE 6 total spots)
        // 3.5. Play a pokemon card (RETREAT - COST ENERGY, SO REMOVE NECESSARY ENERGY OR CHECK IF EVEN POSSIBLE)
        // 4. (End turn) Attack w/ pokemon or declare pass

        Scanner scan = new Scanner(System.in);


        System.out.println("Player 1 draws a card!");
        Card drawnCard = drawPlayerCard(player1);
        player1.addCardToHand(drawnCard);
        System.out.println("You have drawn a " + drawnCard.getName() + "!");

        Boolean endTurn = false;

        while(!endTurn){

            Card[] currentHand = player1.getHand();
            Card[] currentBench = player1.getbench();
            Card currentActivePokemon = player1.getActiveField();

            System.out.print("This is your current hand: [");
            for(Card card : currentHand){
                System.out.print(card.getName() + " ");
            }
            System.out.print("]\n");

            System.out.println("What would you like to do (enter the #): ");
            System.out.println("1) Play 1 energy for the turn (put the energy on a pokemon)");
            System.out.println("2) Play a trainer card");
            System.out.println("3) Attack with active pokemon (ends turn)");
            System.out.println("4) Retreat active pokemon/swap with a benched pokemon");
            System.out.println("5) End turn");
            System.out.print("Player choice: ");

            int decision = scan.nextInt();

            switch(decision){
                case 1:
                    System.out.println();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Player 1 has ended their turn!");
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
    private Boolean aiTurn(Player player1){

        //KEEP IN MIND FOR SOME POKEMON HAVE SPECIAL EFFECTS (i.e CHARMANDER'S BASIC, MAKE THE PLAYER DRAW A CARD IF TRUE RETURNED BY ABILITY)

        // PLAYER TURN PHASE
        //Player1 turn: Draw 1 card, 
        //Then let user decide (1 -3 are unlimited, 4 only once):
        // 1. Play 1 energy for the turn (aka put the energy on a pokemon)
        // 2. Play a trainer card
        // 3. Play a pokemon card (CAN ONLY HAVE ONE POKEMON ACTIVE - BUT CAN BENCH MORE IF HAS SPACE 6 total spots)
        // 3.5. Play a pokemon card (RETREAT - COST ENERGY, SO REMOVE NECESSARY ENERGY OR CHECK IF EVEN POSSIBLE)
        // 4. (End turn) Attack w/ pokemon or declare pass

        Random random = new Random();

        System.out.println("Player 2 draws a card!");
        Card drawnCard = drawPlayerCard(player1);
        player1.addCardToHand(drawnCard);

        Boolean endTurn = false;

        while(!endTurn){

            Card[] currentHand = player1.getHand();
            Card[] currentBench = player1.getbench();
            Card currentActivePokemon = player1.getActiveField();

            int decision = random.nextInt(1,6);
            Boolean pokemonAvailable = checkIfPokemonInHand(player1);

            System.out.println("What would you like to do (enter the #): ");
            System.out.println("1) Play 1 energy for the turn (put the energy on a pokemon)");
            System.out.println("2) Play a trainer card");
            System.out.println("3) Attack with active pokemon (ends turn)");
            System.out.println("4) Retreat active pokemon/swap with a benched pokemon");
            System.out.println("5) End turn");


            switch(decision){
                case 1:
                    System.out.println();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Player 1 has ended their turn!");
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
    public void playerPokemon(){
        //2 Options
        // Attack
        // Player retreat/swap pokemon (probably need to be a new function)

    }

    /*
     * 
     */
    public void playerTrainer(){

    }

    /*
     * 
     */
    public void playerEnergy(){

    }


    /*
     * 
     */
    public Player checkWinner(Player player1, Player player2){

        //Check if all prize pile for current player is empty, opposite player wins


        //Check if current player's opponent has no active pokemon, no benched pokemon, and no pokemon in hand


        //Check if current player's opponent has an empty deck, if so, current player wins


        //Whoever is the winner, return the player object

        return null;
    }

    /*
     * This function prints all the rules, regulations, and how to play the game for the user to read.
     * @param none
     * @return none
     */
    private void printGameRules(){

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
     * This function will get the players list
     * @param none
     * @return players an array of player objects
     */
    public Player[] getPlayers(){
        return players;
    }

    /*
     * This function set set a new players list
     * @param players an array of player objects
     * @return none
     */
    public void setPlayers(Player[] newPlayers){
        players = newPlayers;
    }

}
