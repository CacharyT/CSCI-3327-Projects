/*
 * Cachary Tolentino
 * This class emulates the effects of the Recycle card which allows the player to picka a card from the discard pile and return it into their deck
 */

//Imports
import java.util.*;

public class Recylce extends Trainer{

    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Recylce(){
        super.setCardType("Trainer");
        super.setName("Recycle");
        super.setTrainerDescription("Flip a coin. If heads, put a card in your discard pile on top of your deck.");
    }

    /*
     * This functions allows the player to move a card from the discard pile into their deck
     * @param player a player object
     * @return none
     */
    @Override
    public void activateEffect(Player player){

        Scanner scan = new Scanner(System.in);

        System.out.println("You have activated a Recycle trainer card! The card's effect is: " + getTrainerDescription());
        System.out.println("Flipping a coin...");

        //Flip a coin (o - heads, 1 - tails)
        int coin = PokemonGame.flipACoin();

        //If head, then put a card from the discard pile to the top of the deck
        if(coin == 0){

            System.out.println("It landed on head!");

            Card[] currentDiscardPile = player.getDiscardPile();

            //Allow user to choose what card to take from the pile
            System.out.print("This is your current discard pile: [");
            for(Card card : currentDiscardPile){
                if(card == null){
                    break;
                } else{
                    System.out.print(card.getName() + " ");
                }
            }
            System.out.print("]\n");
            System.out.print("Pick a card (0 - N; position in array; if none then enter -1): ");
            int arrayPosition = 0;
            try {
                arrayPosition = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Option");
            }

            if(arrayPosition == -1){
                //Does nothing
            } else{

                //Add card to top of deck
                Card[] currentDeck = player.getDeck();
                Card[] newDeck = new Card[currentDeck.length + 1];

                for(int i = 0; i < currentDeck.length; i++){
                    newDeck[i] = currentDeck[i];
                }
                newDeck[newDeck.length - 1] = currentDiscardPile[arrayPosition];

                //Update deck
                player.setDeck(newDeck);

            }

            //Remove card from discard pile
            Card[] newDiscardPile = new Card[currentDiscardPile.length - 1];
            int newIndex = 0;
            for(int i = 0; i < currentDiscardPile.length; i++){
                if(i != arrayPosition){
                    newDiscardPile[newIndex++] = currentDiscardPile[i];
                }
            }

            //Update discard pile
            player.setDiscardPile(newDiscardPile);

        } else{
            System.out.println("Unfortunately, it landed on tails. No effect will be granted.");
        }
    }
}