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

        // player.createDeck(10, 10, 40);

        // player.fillHand();
        // Card[] deck = player.getDeck();

        // System.out.println("Current Deck: " + deck.length);
        // for(Card card : deck){

        //     System.out.println(card.getName());
            
        // }

        // player.fillPrize();

        // Card[] prizepile = player.getPrizePile();

        // System.out.println("Current PrizePile: " + prizepile.length);
        // for(Card card : prizepile){

        //     System.out.println(card.getName());
            
        // }

        // Card[] currentHand = player.getHand();

        // System.out.println("Current Hand: " + currentHand.length);
        // for(Card card : currentHand){

        //     System.out.println(card.getName());
            
        // }

        // player.getPrizeCard();

        // Card[] newprizepile = player.getPrizePile();

        // System.out.println("New PrizePile: " + newprizepile.length);
        // for(Card card : newprizepile){

        //     System.out.println(card.getName());
            
        // }

        // Card[] newHand = player.getHand();

        // System.out.println("New Hand: " + newHand.length);
        // for(Card card : newHand){

        //     System.out.println(card.getName());
            
        // }





        // Card[] filledDeck = player.getDeck();
        // int num2 = 0;
        // System.out.println("Deck after filling hand: " + deck.length);
        // for(Card card : filledDeck){
        //     num2++;
        //     System.out.println(card.getName());
            
        // }

        // System.out.println("True amount deck: " + num2);





        // System.out.println(deck.length);

        // int pokeCount = 0;
        // int trainerCOunt = 0;
        // int energyCount = 0;

        // for(Card card : deck){

        //     if(card.getCardType().equals("Pokemon")){
        //         pokeCount++;
        //     } else if(card.getCardType().equals("Trainer")){
        //         trainerCOunt++;
        //     } else{
        //         energyCount++;
        //     }


        //     System.out.println(card.getName());
        // }

        // System.out.println("Pokecount: " + pokeCount);
        // System.out.println("Trainercount: " + trainerCOunt);
        // System.out.println("Energycount: " + energyCount);

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