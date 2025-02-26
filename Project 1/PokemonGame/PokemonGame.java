/*
 * Cachary Tolentino
 * The PokemonGame is a java class that emulates the popular trading card game, Pokemon.
 * It provides a self or ai game mode. 
 */

//Imports
import java.util.Random;
import java.util.Scanner;

public class PokemonGame {
    
    //Global Variable(s)
    private Player[] players;
    private Boolean turnOne;

    /*
     * Default Constructor (creates two player objects)
     * @param none
     * @return none
     */
    public PokemonGame(){
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        turnOne = true;
    }

    // public void runGame(){
    //     playOrNot();
    //     typeOfGameMode();
    //     initialPlayer(); --> check who go 
    //     customDeckOrNot(); 
    //     gameModeSetup();
    // }

    // public Boolean getTurnOnet(){
    //     System.out.print("Turn One:" + turnOne);
    //     return turnOne;
    // }

    /*
     * The main game mechanic that starts the game for the player to play (player chooses their game mode)
     * @param none
     * @return none
     */
    public void startGame(){

        Boolean play = playOrNot(); //Check if player wants to play before and after rule declaration
        if(play){
            String gameType =  typeOfGameMode();
            int coin = initialPlayer();
            Boolean customized = customDeckOrNot();
            gameModeSetup(play, gameType, coin, customized);
        } else{
            System.out.println("Ok! Have a good day!");
        }
        
    }

    /*
     * 
     */
    public Boolean playOrNot(){
        Scanner scan = new Scanner(System.in);

        //Ask if player wants to play, otherwise end program
        System.out.print("Welcome to Pokemon, a trading card game! Would you like to play? (Y or N): ");
        String playOrNot = "";
        try {
            playOrNot = scan.next().toLowerCase();
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }

        if(playOrNot.equals("y") || playOrNot.equals("yes")){

            //Print Game Rules & Instructions
            playOrNot = null;
            GameSetup ruleSetup = new GameSetup();
            ruleSetup.printGameRules();

            //confirmation before fully initializing the game
            System.out.print("\nWould you like to continue playing? (Y or N): ");
            try {
                playOrNot = scan.next().toLowerCase();
                return true;
            } catch (Exception e) {
                System.out.println("Invalid Option");
            }
        } else{
            return false;
        }
        return false;
    }

    /*
     * 
     */
    public String typeOfGameMode(){
        Scanner scan = new Scanner(System.in);
        System.out.print("How would you like to play? Self or AI: ");
        String gameType = "";
        try {
            gameType = scan.next().toLowerCase();
            return gameType;
        } catch (Exception e) {
            System.out.println("Invalid option");
        }
        return null;
    }

    /*
     * 
     */
    public int initialPlayer(){
        Scanner scan = new Scanner(System.in);

        //Flip a coin: 0 - heads, 1 - tail
        //Check which player goes first
        System.out.print("\nCoin flip: Heads or Tails? (0 - Head, 1 - Tails): ");
        int playerCoin = 0;
        try {
            playerCoin = scan.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }
        int coinFlipped = flipACoin();
        
        if(coinFlipped == playerCoin){
            return 0; //Player1 go first
        } else{
            return 1; //Player2 go first
        }
    }

    /*
     * 
     */
    public Boolean customDeckOrNot(){
        Scanner scan = new Scanner(System.in);

        //Extra credit - Allow player to customize their deck
        System.out.print("\nWould you like to customize the allocation of your deck? (Y or N): ");
        String customize = "";
        try {
            customize = scan.next().toLowerCase();
            if(customize.equals("y")){
                return true;
            } else if(customize.equals("n")){
                return false;
            } else{
                System.out.println("Invalid Option");
            }
        } catch (Exception e) {
            System.out.println("Invalid Option");
        }
        return null;
    }

    /*
     * 
     */
    public void gameModeSetup(Boolean play, String gameType, int coin, Boolean customized){

        GameSetup newSetup = new GameSetup(); //setup object

        //Initialize Players and the game mode
        if(gameType.equals("ai") && (coin == 0) && customized){
            System.out.print("\nThe coin landed on " + 0 + "! You will be player 1 and have the first turn! Player 2 will be the AI.");
            Player player1 = getPlayers()[0];
            AIPlayer player2 = new AIPlayer(); //AI is specifically made with an AIPlayer object

            newSetup.setupPlayerDecks(player1); //Player deck (customized)
            newSetup.createPlayerDeck(player2); //AI Deck (non-customized)
            newSetup.setupPlayerHand(player1, player2); //Create both player hands

            //AI auto place pokemon to active zone and every other pokemon to bench
            player2.aiAutoActiveFieldAndBenchPokemon(player2);

            //Create fields (active and prize) for the user player
            newSetup.setupPlayerFields(player1, player2);

            //MAIN GAME LOOP---------------
            gameloopAI(player1, player2); //returns the player object of whoever wins

        } if(gameType.equals("ai") && (coin == 0) && !customized){
            System.out.print("\nThe coin landed on " + 0 + "! You will be player 1 and have the first turn! Player 2 will be the AI.");
            Player player1 = getPlayers()[0];
            AIPlayer player2 = new AIPlayer(); //AI is specifically made with an AIPlayer object

            //Create both players decks (non-customized)
            newSetup.createPlayerDeck(player1);
            newSetup.createPlayerDeck(player2);

            //Create user player hands
            newSetup.setupPlayerHand(player1, player2);

            //AI auto place pokemon to active zone and every other pokemon to bench
            player2.aiAutoActiveFieldAndBenchPokemon(player2);

            //Create fields (active and prize) for the user player
            newSetup.setupPlayerFields(player1, player2);

            //MAIN GAME LOOP---------------
            gameloopAI(player1, player2); //returns the player object of whoever wins

        } else if(gameType.equals("ai") && (coin == 1) && customized){
            System.out.print("\nThe coin landed on " + 0 + "! AI will be player 1 and have the first turn. You will be player 2!");
            AIPlayer player1 = new AIPlayer(); //AI is specifically made with an AIPlayer object
            Player player2 = getPlayers()[1]; 

            newSetup.setupPlayerDecks(player2); //Player deck (customized)
            newSetup.createPlayerDeck(player1); //AI Deck (non-customized)
            newSetup.setupPlayerHand(player1, player2); //Create both player hands

            //AI auto place pokemon to active zone and every other pokemon to bench
            player1.aiAutoActiveFieldAndBenchPokemon(player1);

            //Create fields (active and prize) for the user player
            newSetup.setupPlayerFieldsInverted(player1, player2);

            //MAIN GAME LOOP---------------
            gameLoopAIInverted(player1, player2);
            
        } else if(gameType.equals("ai") && (coin == 1) && !customized){
            System.out.print("\nThe coin landed on " + 0 + "! AI will be player 1 and have the first turn. You will be player 2!");
            AIPlayer player1 = new AIPlayer(); //AI is specifically made with an AIPlayer object
            Player player2 = getPlayers()[1]; 

            newSetup.createPlayerDeck(player2); //Player deck (non-customized)
            newSetup.createPlayerDeck(player1); //AI Deck (non-customized)
            newSetup.setupPlayerHand(player1, player2); //Create both player hands

            //AI auto place pokemon to active zone and every other pokemon to bench
            player1.aiAutoActiveFieldAndBenchPokemon(player1);

            //Create fields (active and prize) for the user player
            newSetup.setupPlayerFieldsInverted(player1, player2);

            //MAIN GAME LOOP---------------
            gameLoopAIInverted(player1, player2); //returns the player object of whoever wins
            
        } else if(customized){
            System.out.print("You will be player 1 and 2!");
            Player player1 = getPlayers()[0];
            Player player2 = getPlayers()[1]; 

            newSetup.setupPlayerDecks(player1); //Player deck 1 (customized)
            newSetup.setupPlayerDecks(player2); //Player deck 2 (customized)
            newSetup.setupPlayerHand(player1, player2); //Create both player hands

            //Create fields (active and prize) for both players
            newSetup.setupSoloPlayerFields(player1);
            newSetup.setupSoloPlayerFields(player2);

            //MAIN GAME LOOP---------------
            gameLoopSelf(player1, player2); //returns the player object of whoever wins

        } else{
            System.out.print("You will be player 1 and 2!");
            Player player1 = getPlayers()[0];
            Player player2 = getPlayers()[1]; 

            newSetup.createPlayerDeck(player1); //Player deck 1 (customized)
            newSetup.createPlayerDeck(player2); //Player deck 2 (customized)
            newSetup.setupPlayerHand(player1, player2); //Create both player hands

            //Create fields (active and prize) for both players
            newSetup.setupSoloPlayerFields(player1);
            newSetup.setupSoloPlayerFields(player2);

            //MAIN GAME LOOP---------------
            gameLoopSelfInverted(player1, player2); //returns the player object of whoever wins
        }
    }

    /*
     * The funtion creates a gameloop specifically for player (p1) vs AI (p2)
     * @param player1 a player object
     * @param player2 a player object
     * @return a player object of who won the game
     */
    public void gameloopAI(Player player1, AIPlayer player2){

        Player winnerFound = null;

        while(winnerFound == null){ //runs until a winner is found

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1); //At the start of each each player turn, check if player deck contains any cards, if not then enemy wins
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            player1.playerTurn(player1, player2, getTurnOne()); //starts player loop
            setTurnOne(false); //signifies turn one is over, restrictions on attack lifted
            winnerFound = checkWinner(player1); //checks if drawn all prize pile card, if so then payer wins
            if(winnerFound != null){
                break;
            }

            //AI Turn - randomly chooses what to do
            System.out.println("It is now Player 2's turn!\n");
            winnerFound = checkDrawableDeck(player2); //At the start of each each player turn, check if player deck contains any cards, if not then enemy wins
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            player2.aiTurn(player2, player1, getTurnOne()); //starts aiplayer loop
            winnerFound = checkWinner(player2);  //checks if drawn all prize pile card, if so then payer wins
            if(winnerFound != null){
                break;
            }

        }

        //Winner has been found, announce who won. And end program
        if(winnerFound == player1){
            System.out.println("Player 1 has won the game!");
        }   else{
            System.out.println("Player 2 has won the game!");
        }
    }

    /*
     * The funtion creates a gameloop specifically for player (p2) vs AI (p1)
     * @param player1 a player object
     * @param player2 a player object
     * @return a player object of who won the game
     */
    public void gameLoopAIInverted(AIPlayer player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //AI Turn - randomly chooses what to do
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player2); //player 2 is the ai (player 1)
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            player1.aiTurn(player1, player2, getTurnOne());
            setTurnOne(false);
            winnerFound = checkWinner(player2);
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
            player2.playerTurn(player2, player1, getTurnOne());
            winnerFound = checkWinner(player1);
            if(winnerFound != null){
                break;
            }

        }
        
        //Winner has been found, announce who won. And end program
        if(winnerFound == player1){
            System.out.println("Player 1 has won the game!");
        }   else{
            System.out.println("Player 2 has won the game!");
        }
    }

    /*
     * The funtion creates a gameloop specifically for player (p1) vs player (p2)
     * @param player1 a player object
     * @param player2 a player object
     * @return a player object of who won the game
     */
    public void gameLoopSelf(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            player1.playerTurn(player1, player2, getTurnOne());
            setTurnOne(false);
            winnerFound = checkWinner(player1);
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
            player2.playerTurn(player2, player1, getTurnOne());
            winnerFound = checkWinner(player2);
            if(winnerFound != null){
                break;
            }

        }
        
        //Winner has been found, announce who won. And end program
        if(winnerFound == player1){
            System.out.println("Player 1 has won the game!");
        }   else{
            System.out.println("Player 2 has won the game!");
        }
    }

    /*
     * The funtion creates a gameloop specifically for player (p2) vs player (p1)
     * @param player1 a player object
     * @param player2 a player object
     * @return a player object of who won the game
     */
    public void gameLoopSelfInverted(Player player1, Player player2){

        Player winnerFound = null;

        while(winnerFound == null){

            //Allow user to make their choice
            System.out.println("It is now Player 1's turn!\n");
            winnerFound = checkDrawableDeck(player1);
            if(winnerFound != null){
                System.out.println("Player has no more cards to draw!");
                break;
            }
            player1.playerTurn(player1, player2, getTurnOne());
            setTurnOne(false);
            winnerFound = checkWinner(player1);
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
            player2.playerTurn(player2, player1, getTurnOne());
            winnerFound = checkWinner(player2);
            if(winnerFound != null){
                break;
            }

        }
        
        //Winner has been found, announce who won. And end program
        if(winnerFound == player1){
            System.out.println("Player 1 has won the game!");
        }   else{
            System.out.println("Player 2 has won the game!");
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
     * The function checks the current player's deck if there are any cards left
     * @param player a player object
     * @return player object or null
     */
    public Player checkDrawableDeck(Player player){

        Card[] deck = player.getDeck();

        if(deck.length == 0){
            return player;
        }

        return null;

    }

    /*
     * The function checks the current player's prize pile, if empty return player (player won)
     * @param player1 a player object
     * @return a player object or null
     */
    public Player checkWinner(Player player1){

        //Check if all prize pile for current player is empty, player wins
        Card[] p1PrizePile = player1.getPrizePile();
        if(p1PrizePile.length == 0){
            return player1;
        }
        
        return null;
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

    /*
     * This function will get the turn one
     * @param none
     * @return boolean value
     */
    public Boolean getTurnOne(){
        return turnOne;
    }

    /*
     * This function sets whether its turn one or not
     * @param boolean value
     * @return none
     */
    public void setTurnOne(Boolean turn){
        turnOne = turn;
    }

}