/*
 * Cachary Tolentino
 * 
 */

import java.util.*;

public class Recylce extends Trainer{


    //Global Variable(s)

    /*
     * Default Constructor
     */
    public Recylce(){
        super.setCardType("Trainer");
    }


    /*
     * 
     */
    public void activateEffect(Player player){

        Scanner scan = new Scanner(System.in);


        //Flip a coin (o - heads, 1 - tails)
        int coin = PokemonGame.flipACoin();


        //If head, then put a card from the discard pile to the top of the deck
        if(coin == 0){
            System.out.println("Choose a card from the discard pile (enter card #): ");

            Card[] playerCards = player.getDiscardPile();

            for(int i = 0; i < playerCards.length; i++){
                System.out.println("Discarded Card #" + i + ": " + playerCards[i].getCardType()); //change this to one where it shows the kind of card (i.e Charmander, etc...)
            }

            int cardNum = scan.nextInt();
            scan.close();


            //Add card to top of the deck
            int count = 0;
            Card[] currentDeck = player.getDeck();
            for(Card card : currentDeck){
                count++;
            }

            if(count < currentDeck.length){
                currentDeck[count + 1] = playerCards[cardNum];
                player.setDeck(currentDeck);
            } else{
                Card[] newDeck = new Card[currentDeck.length + 10];
                newDeck[count + 1] = playerCards[cardNum];
                player.setDeck(newDeck);
            }



            //Remove card from discard pile
            Card[] newDiscardPile = new Card[playerCards.length];
            for(int i = 0; i < newDiscardPile.length; i++){
                if(i != cardNum){
                    newDiscardPile[i] = playerCards[i];
                } else{
                    break;
                }
            }



        }



    }


}