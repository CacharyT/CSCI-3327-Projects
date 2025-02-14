/*
 * 
 */

//Imports
import java.util.Random;
import java.util.Scanner;



public class PokemonGame {
    
    //Global Variable(s)
    private Player[] players;


    /*
     * Default Constructor
     */
    public PokemonGame(){
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
    }

    /*
     * Constructor with parameters
     */
    public PokemonGame(Player player1, Player player2){
        players = new Player[2];
        players[0] = player1;
        players[1] = player2;

    }


    //startGame
    /*
     * 
     */
    public void startGame(){

        //CHECK NEW CONDITIONS HE ADDED FROM LAST CLASS






        Scanner scan = new Scanner(System.in);

        //PRINT GAME RULE
        //Ask user if want to continue playing

        //CREATE PLAYER DECKS


        //KEEP IN MIND FOR CHARMANDER'S BASIC, MAKE THE PLAYER DRAW A CARD IF TRUE RETURNED BY ABILITY






        //Prompt user with all the Game Instructions






        // BEGINNING PHASE
        //Flip coin (decide who goes first) - ask current payer which coin side (head = 0, tail = 1)
        //Draw 7 cards per player, check if has minimum of 1 pokemon on hand
        //if not then reveal(print out) current hand, put back hand into deck then shuffle, then draw 7 cards again (repeate if necessary)
        //then let enemy draw one extra card
        //each player moves top 6 cards from the deck into the prize pile
        //Player 1 must play 1 pokemon on the active field
        //Let player decide how many to bench (if any)
        //Then end turn(no attacking allowed on turn 1), repeat scenario for player 2

        // GAME LOOP 
        // Player1 Turn
        // Check if win
        // Player2 Turn
        // Check if win


        // PLAYER TURN PHASE
        //Player1 turn: Draw 1 card, 
        //Then let user decide (1 -3 are unlimited, 4 only once):
        // 1. Play 1 energy for the turn (aka put the energy on a pokemon)
        // 2. Play a trainer card
        // 3. Play a pokemon card (CAN ONLY HAVE ONE POKEMON ACTIVE - BUT CAN BENCH MORE IF HAS SPACE 6 total spots)
        // 3.5. Play a pokemon card (RETREAT - COST ENERGY, SO REMOVE NECESSARY ENERGY OR CHECK IF EVEN POSSIBLE)
        // 4. (End turn) Attack w/ pokemon or declare pass
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
            if(card.getCardType().equals("Pokemon"))
            return true;
        }

        return false;
    }

    /*
     * 
     */
    private Card drawPlayerCard(Player player){
        //Print out that card that was drawn
        Card drawnCard = player.drawCard();

        if(drawnCard.getCardType().equals("Pokemon")){

            //Check what type of pokemon it is


        } else if(drawnCard.getCardType().equals("Energy")){

            //Check what type of energy it is

        } else if(drawnCard.getCardType().equals("Trainer")){

            //Check what type of trainer it is

        }

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
    public Boolean checkWinner(Player player1, Player player2){

        //Check if all prize pile for current player is empty, opposite player wins


        //Check if current player's opponent has no active pokemon, no benched pokemon, and no pokemon in hand


        //Check if current player's opponent has an empty deck, if so, current player wins




        return true;
    }




    /*
     * 
     */
    public void playerPokemon(){
        //2 Options
        // Attack
        // Player retreat/swap pokemon

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



}
