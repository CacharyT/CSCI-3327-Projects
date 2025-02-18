/*
 * Cachary Tolentino
 * 
 */

import java.util.*;

public class Recylce extends Trainer{


    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     */
    public Recylce(){
        super.setCardType("Trainer");
        super.setName("Recycle");
        trainerDescription = "Flip a coin. If heads, put a card in your discard pileon top of your deck.";
    }


    /*
     * 
     */
    public void activateEffect(Player player){

        Scanner scan = new Scanner(System.in);

        System.out.println("You have activated a Recycle trainer card! The card's effect is: " + trainerDescription);
        System.out.println("Flipping a coin...");

        //Flip a coin (o - heads, 1 - tails)
        int coin = PokemonGame.flipACoin();




        //If head, then put a card from the discard pile to the top of the deck
        if(coin == 0){

            System.out.println("It landed on head!");

            //Allow user to choose what card to take from the pile
            Card[] currentDiscardPile = player.getDiscardPile();
            System.out.print("This is your current discard pile: [");
            for(Card card : currentDiscardPile){
                if(card == null){
                    break;
                } else{
                    System.out.print(card.getName() + " ");
                }
            }
            System.out.print("]\n");
            System.out.print("Pick a card (0 - N; position in array; if done then enter -1): ");
            int arrayPosition = scan.nextInt();

            //Add card to top of deck
            Card[] currentDeck = player.getDeck();
            Card[] newDeck = new Card[currentDeck.length + 1];

            for(int i = 0; i < currentDeck.length; i++){
                newDeck[i] = currentDeck[i];
            }
            newDeck[newDeck.length - 1] = currentDiscardPile[arrayPosition];

            //Update deck
            player.setDeck(newDeck);

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

    /*
     * 
     */
    public void setTrainerDescription(String newDescription){
        trainerDescription = newDescription;
    }

    /*
     * 
     */
    public String getTrainerDescription(){
        return trainerDescription;
    }


}