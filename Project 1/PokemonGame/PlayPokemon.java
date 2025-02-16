/*
 * Cachary Tolentino
 * The PlayPokemon Class is the tester class for the entire PokemonGame program. 
 * It will initiate two players (one randomized deck and one with a predetermined deck)
 * The user will be prompted with multiple choices to play the game.
 */

public class PlayPokemon {
    public static void main(String[] args) {

        //PokemonGame object
        PokemonGame game = new PokemonGame();

        //Start game
        game.startGame();

        // Player player = new Player();

        // player.createDeck(10, 14, 36);

        // Card[] deck = player.getDeck();

        // System.out.println(deck.length);

        // for(Card card : deck){
        //     System.out.println(card.getName());
        // }


        //Checking player 2 contents
        // Card[] p2Hand = player2.getHand();
        // Card[] p2Bench = player2.getbench();
        // Card[] p2Deck = player2.getDeck();

        // System.out.print("Player 2 Hand: ");
        // for(Card card : p2Hand){
        //     System.out.print(card.getName() + " ");
        // }
        // System.out.print("\nPlayer 2 Bench: ");
        // for(Card card : p2Bench){
        //     System.out.print(card.getName() + " ");
        // }
        // System.out.print("\nPlayer 2 Deck: ");
        // for(Card card : p2Deck){
        //     System.out.print(card.getName() + " ");
        // }

    }
}