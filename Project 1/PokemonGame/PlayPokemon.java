/*
 * Cachary Tolentino
 * The PlayPokemon Class is the tester class for the entire PokemonGame program. 
 * It will initiate two players (one randomized deck and one with a predetermined deck)
 * The user will be prompted with multiple choices to play the game.
 */

public class PlayPokemon {
    public static void main(String[] args) {

        Player player1 = new Player(); 
        Player player2 = new Player();

        PokemonGame game = new PokemonGame(player1, player2);

        game.startGame();

    }
}