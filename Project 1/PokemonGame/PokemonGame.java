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

                    if(coinFlipped == playerCoin){ //user is plyer 1 and deck is customized

                        System.out.print("\nThe coin landed on " + playerCoin + "! You will be player 1 and have the first turn! Player 2 will be the AI.");

                        Player player1 = getPlayers()[0];
                        Player player2 = getPlayers()[1];

                        //Extra credit - Allow player to create their own deck or not
                        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
                        String customize = scan.next().toLowerCase();

                        if(customize.equals("y")){

                            //GAME SETUP PHASE---------------

                            //Player deck (customized)
                            setupPlayerDecks(player1);

                            //AI Deck (non-customized)
                            createPlayerDeck(player2);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //AI auto place pokemon to active zone and every other pokemon to bench
                            aiAutoActiveFieldAndBenchPokemon(player2);

                            //Create fields (active and prize) for both players
                            setupPlayerFields(player1, player2);

                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameloopAI(player1, player2);
                            
                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else if(customize.equals("n")){ //user is player 1 but deck is not customized

                            //GAME SETUP PHASE---------------

                            //Create both players decks (non-customized)
                            createPlayerDeck(player1);
                            createPlayerDeck(player2);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //AI auto place pokemon to active zone and every other pokemon to bench
                            aiAutoActiveFieldAndBenchPokemon(player2);

                            //Create fields (active and prize) for both players
                            setupPlayerFields(player1, player2);

                            //Main game loop (does not end until a winner is found)
                            Player winnerFound = gameloopAI(player1, player2);
                            
                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else{
                            System.out.println("That is an invalid option. Game restarting.");
                            startGame();
                        }

                    } else{ //User player becomes player 2 and the Ai is player 1

                        System.out.print("\nThe coin landed on " + coinFlipped + "! AI will be player 1 and have the first turn! You will be player 2.");

                        Player player1 = getPlayers()[0];
                        Player player2 = getPlayers()[1];

                        //Extra credit - Allow player to create their own deck or not
                        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
                        String customize = scan.next().toLowerCase();

                        if(customize.equals("y")){ //User player becomes player 2 and the Ai is player 1 but deck is customized

                            //GAME SETUP PHASE---------------

                            //Player deck (customized)
                            setupPlayerDecks(player2);

                            //AI Deck (non-customized)
                            createPlayerDeck(player1);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //AI auto place pokemon to active zone and every other pokemon to bench
                            aiAutoActiveFieldAndBenchPokemon(player1);

                            //Create fields (active and prize) for both players
                            setupPlayerFieldsInverted(player1, player2);


                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopAIInverted(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else if(customize.equals("n")){ //User player becomes player 2 and the Ai is player 1 but deck is not customized

                            //GAME SETUP PHASE---------------

                            //Create both players decks (non-customized)
                            createPlayerDeck(player2);
                            createPlayerDeck(player1);

                            //Create both player hands
                            setupPlayerHand(player2, player1);

                            //AI auto place pokemon to active zone and every other pokemon to bench
                            aiAutoActiveFieldAndBenchPokemon(player1);

                            //Create fields (active and prize) for both players
                            setupPlayerFieldsInverted(player1, player2);

                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopAIInverted(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else{
                            System.out.println("That is an invalid option. Game restarting.");
                            startGame();
                        }

                    }


                } else if(gameType.equals("self")){ //allow player to verse themselves


                    //Flip a coin: 0 - heads, 1 - tail
                    //Check which player goes first
                    System.out.print("\nCoin flip: Heads or Tails? (0 - Head, 1 - Tails): ");
                    int playerCoin = scan.nextInt();
                    int coinFlipped = flipACoin();

                    if(coinFlipped == playerCoin){ //user is player 1 and 2 and deck is customized

                        System.out.print("\nThe coin landed on " + playerCoin + "! You will be player 1 and have the first turn! Player 2 will also be you.");

                        Player player1 = getPlayers()[0];
                        Player player2 = getPlayers()[1];

                        //Extra credit - Allow player to create their own deck or not
                        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
                        String customize = scan.next().toLowerCase();

                        if(customize.equals("y")){

                            //GAME SETUP PHASE---------------

                            //Player deck 1 (customized)
                            setupPlayerDecks(player1);

                            //Player deck 2 (customized)
                            setupPlayerDecks(player2);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //Create fields (active and prize) for both players
                            setupSoloPlayerFields(player1);
                            setupSoloPlayerFields(player2);


                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopSelf(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else if(customize.equals("n")){ //user is plyer 1 but deck is not customized

                            //GAME SETUP PHASE---------------

                            //Create both players decks (non-customized)
                            createPlayerDeck(player1);
                            createPlayerDeck(player2);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //Create fields (active and prize) for both players
                            setupSoloPlayerFields(player1);
                            setupSoloPlayerFields(player2);

                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopSelf(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else{
                            System.out.println("That is an invalid option. Game restarting.");
                            startGame();
                        }

                    } else{ 

                        System.out.print("\nThe coin landed on " + coinFlipped + "! You will be player 2 and have the second turn! You will also be player 1.");

                        Player player1 = getPlayers()[0];
                        Player player2 = getPlayers()[1];

                        //Extra credit - Allow player to create their own deck or not
                        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
                        String customize = scan.next().toLowerCase();

                        if(customize.equals("y")){ //User is both players but flipped turns and customized decks


                            //GAME SETUP PHASE---------------

                            //Player deck 1 (customized)
                            setupPlayerDecks(player2);

                            //Player deck 2 (customized)
                            setupPlayerDecks(player1);

                            //Create both player hands
                            setupPlayerHand(player2, player1);

                            //Create fields (active and prize) for both players
                            setupSoloPlayerFields(player1);
                            setupSoloPlayerFields(player2);


                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopSelfInverted(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else if(customize.equals("n")){ //User is both players but flipped turns and non customized decks

                            //GAME SETUP PHASE---------------

                            //Create both players decks (non-customized)
                            createPlayerDeck(player1);
                            createPlayerDeck(player2);

                            //Create both player hands
                            setupPlayerHand(player1, player2);

                            //Create fields (active and prize) for both players
                            setupSoloPlayerFields(player1);
                            setupSoloPlayerFields(player2);

                            //MAIN GAME LOOP---------------
                            Player winnerFound = gameLoopSelfInverted(player1, player2);
                            

                            //Winner has been found, announce who won. And end program
                            if(winnerFound == player1){
                                System.out.println("Player 1 has won the game!");
                            }   else{
                                System.out.println("Player 2 has won the game!");
                            }


                        } else{
                            System.out.println("That is an invalid option. Game restarting.");
                            startGame();
                        }
                    }
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
     * 
     */
    public Player gameloopAI(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player1, player2);
            winnerFound = checkWinner(player1, player2);
            if(winnerFound != null){
                break;
            }

            //AI Turn - randomly chooses what to do
            System.out.println("It is now Player 2's turn!\n");
            winnerFound = checkDrawableDeck(player2);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            aiTurn(player2, player1);
            winnerFound = checkWinner(player2, player1);
            if(winnerFound != null){
                break;
            }

        }

        return winnerFound;

    }

    /*
     * 
     */
    public Player gameLoopAIInverted(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //AI Turn - randomly chooses what to do
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player2); //player 2 is the ai (player 1)
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            aiTurn(player1, player2);
            winnerFound = checkWinner(player2, player1);
            if(winnerFound != null){
                break;
            }

            //Allow user to make their choice
            System.out.println("It is now Player 2's turn!\n");
            winnerFound = checkDrawableDeck(player1); //player 1 is the player (player 2)
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player2, player1);
            winnerFound = checkWinner(player1, player2);
            if(winnerFound != null){
                break;
            }

        }

        return winnerFound;

    }

    /*
     * 
     */
    public Player gameLoopSelf(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player1, player2);
            winnerFound = checkWinner(player1, player2);
            if(winnerFound != null){
                break;
            }

            //Allow user to make their choice
            System.out.println("It is now Player 2's turn!\n");
            winnerFound = checkDrawableDeck(player2);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player2, player1);
            winnerFound = checkWinner(player2, player1);
            if(winnerFound != null){
                break;
            }

        }

        return winnerFound;

    }

    /*
     * 
     */
    public Player gameLoopSelfInverted(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player1, player2);
            winnerFound = checkWinner(player1, player2);
            if(winnerFound != null){
                break;
            }

            //Allow user to make their choice
            System.out.println("It is now Player 2's turn!\n");
            winnerFound = checkDrawableDeck(player2);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            playerTurn(player2, player1);
            winnerFound = checkWinner(player2, player1);
            if(winnerFound != null){
                break;
            }

        }

        return winnerFound;

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


    /*
     * 
     */
    public Card drawPlayerCard(Player player){

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
    public void addPokemonEnergy(Player player, int arrayPosition){

        player.addEnergyToPokemon(arrayPosition);

    }

    /*
     * 
     */
    public void playTrainerCard(Player player, int arrayPosition){

        player.useTrainerCard(player, arrayPosition);

    }


    /*
     * 
     */
    public void retreatPokemon(Player player){

        player.allowRetreatPokemon();

    }

    /*
     * 
     */
    public Boolean attackPokemon(Player player1, Player player2){

        Boolean state = player1.allowPokemonAttack(player2);
        return state;

    }

    /*
     * 
     */
    public Boolean attackPokemonAI(Player player1, Player player2){

        Boolean state = player1.allowPokemonAttackAI(player2);
        return state;

    }

    /*
     * 
     */
    public Player checkDrawableDeck(Player player){

        Card[] deck = player.getDeck();

        if(deck.length == 0){
            return player;
        }

        return null;

    }

    /*
     * 
     */
    public Player checkWinner(Player player1, Player player2){

        //Check if all prize pile for current player is empty, player wins
        Card[] p1PrizePile = player1.getPrizePile();
        if(p1PrizePile.length == 0){
            return player1;
        }
        
        return null;
    }


    /*
     * 
     */
    public Boolean checkIfPokemonWinCondition(Player player1, Player player2){

        //At the start of each turn, check if active pokemon still alive, otherwise make the player add one to the active zone
        //if no pokemon available, end game
        Pokemon activePokemon1 = (Pokemon) player1.getActiveField();

        if(activePokemon1.getHP() == 0){

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
    public void aiAutoActiveFieldAndBenchPokemon(Player player){ //NOTE: MIGHT NEED TO MOVE TO PLAYER

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
    private Boolean playerTurn(Player player1, Player player2){

        Scanner scan = new Scanner(System.in);

        System.out.println("Current player draws a card!");
        Card drawnCard = drawPlayerCard(player1);
        player1.addCardToHand(drawnCard);
        System.out.println("You have drawn a " + drawnCard.getName() + "!");


        if(!checkIfPokemonWinCondition(player1, player2)){
            return false;
        }

        Boolean endTurn = false;

        while(!endTurn){

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
                    Boolean energyDone = false;

                    while(!energyDone){
                        Card[] newHand = player1.getHand();
                        System.out.print("This is your current hand: [");
                        for(Card card : newHand){
                            System.out.print(card.getName() + " ");
                        }
                        System.out.print("]\n");
                        System.out.print("Pick an energy card to place on the current active pokemon (0 - N; position in array; if done then enter -1): ");
                        int arrayPosition = scan.nextInt();

                        if(arrayPosition == -1){
                            energyDone = true;
                        } else{
                            addPokemonEnergy(player1, arrayPosition);

                            Card activePokemon = player1.getActiveField();
                            System.out.print("\nYour active pokemon " + activePokemon.getName() + " now has [");
                            Energy[] pokemonEnergies = activePokemon.getEnergies();
                            for(Energy energy: pokemonEnergies){
                                System.out.print(energy.getName() + " ");
                            }
                            System.out.println("]");

                        }
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

                            playTrainerCard(player1, arrayPosition);

                        }
                    }

                    break;
                case 3:

                    beginningPokemonBench(player1);

                    break;
                case 4:

                    retreatPokemon(player1);

                    break;
                case 5:

                    Boolean state = attackPokemon(player1, player2);
                    if(state){
                        System.out.println("Current player has ended their turn!");
                        endTurn = true;
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
    private Boolean aiTurn(Player player1, Player player2){ //CHECK FULLY IF CHECKING ALL ALLOWED POSSIBILITY!!!!!!!!!!!!!!!!!!!

        Random random = new Random();

        System.out.println("Current player draws a card!");
        Card drawnCard = drawPlayerCard(player1);
        player1.addCardToHand(drawnCard);

        if(!checkIfPokemonWinCondition(player1, player2)){
            return false;
        }

        Boolean endTurn = false;

        while(!endTurn){

            Card[] currentHand = player1.getHand();
            Card[] currentBench = player1.getbench();

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

            if(hasEnergy && hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(decisions.length);
                decision = decisions[randomDecision];

            } else if(!hasEnergy && !hasTrainer && !hasPokemonForAttack && !hasPokemonForBench && !canEndTurn){

                int randomDecision = random.nextInt(noneAll.length);
                decision = decisions[randomDecision];

            } else if(!hasEnergy && hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(noEnergy.length);
                decision = decisions[randomDecision];

            } else if(hasEnergy && !hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(noTrainer.length);
                decision = decisions[randomDecision];

            } else if(hasEnergy && hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(noBench.length);
                decision = decisions[randomDecision];

            } else if(!hasEnergy && !hasTrainer && hasPokemonForAttack && hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(noEnergyAndTrainer.length);
                decision = decisions[randomDecision];

            } else if(!hasEnergy && hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(noEnergyAndBench.length);
                decision = decisions[randomDecision];

            } else if(hasEnergy && !hasTrainer && hasPokemonForAttack && !hasPokemonForBench && canEndTurn){

                int randomDecision = random.nextInt(notrainerAndBench.length);
                decision = decisions[randomDecision];

            } else{ //case not covered, still allow for them to exit turn or attack

                int randomDecision = random.nextInt(noneAll.length);
                decision = decisions[randomDecision];

            }

            switch(decision){
                case 1:

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

                    if(fulfilled){

                        addPokemonEnergy(player1, energyPosition);
                        Card activePokemon = player1.getActiveField();
                        System.out.print("\nYour active pokemon " + activePokemon.getName() + " now has [");
                        Energy[] pokemonEnergies = activePokemon.getEnergies();
                        for(Energy energy: pokemonEnergies){
                            System.out.print(energy.getName() + " ");
                        }
                        System.out.println("]");

                    } else{

                        System.out.println("Found no energy.");
                        endTurn = true;

                    }


                    break;
                case 2:

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
                    if(foundValue){
                        Card trainerCard = trainerHand[arrayPosition];
                        if(trainerCard instanceof Bill || trainerCard instanceof ProfessorOak){
                            playTrainerCard(player1, arrayPosition);
                        }
                    } else{
                        System.out.println("Found no trainer card.");
                        endTurn = true;
                    }

                    break;
                case 3:

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

                    if(found){
                        benchPokemonFromHand(player1, arrayPositionBench);
                    } else{
                        System.out.println("Found no pokemon.");
                        endTurn = true;
                    }

                    break;
                case 4:

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

                    } else{
                        System.out.print("Your " + currentPokemon.getName() + " does not have enough energy to retreat!");
                        endTurn = true;
                    }

                    break;
                case 5:

                    Boolean state = attackPokemonAI(player1, player2);
                    if(state){
                        System.out.println("Current player has ended their turn!");
                        endTurn = true;
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
            drawPlayerCard(player1);
            System.out.println("Player 1 has also drawn an extra card!");
        }
        for(int num = 0; num < player2Redraws; num++){
            drawPlayerCard(player2);
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