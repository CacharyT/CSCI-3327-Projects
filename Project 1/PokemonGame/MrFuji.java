/*
 * Cachary Tolentino
 * 
 */

//Imports
import java.util.Scanner;

public class MrFuji extends Trainer{


    //Global Variable(s)

    /*
     * Default Constructor
     */
    public MrFuji(){
        super.setCardType("Trainer");
    }


    /*
     * 
     */
    public void activateEffect(Player player){

        Scanner scan = new Scanner(System.in);

        //Allow for user to choose a pokemon from the bench
        System.out.println("Choose a pokemon from the bench (type the bench spot number): ");

        Card[] playerBench = player.getbench();

        for(int i = 0; i < playerBench.length; i++){
            System.out.println("Bench Spot #" + i + ": " + playerBench[i].getCardType()); //change this to show the card type AS in Charmander, etc.. not 'Pokemon'
        }

        int benchSpot = scan.nextInt();
        scan.close();
        Card cardPicked = playerBench[benchSpot];


        //Remove pokemon from bench
        Card[] newBench = new Card[playerBench.length];
        for(int i = 1; i < newBench.length; i++){
            if(i == benchSpot){
                break;
            } else{
                newBench[i - 1] = playerBench[i];
            }
        }
        player.setBench(newBench);


        //Add picked card to the deck and shuffle
        Card[] currentDeck = player.getDeck();
        Card[] newDeck = new Card[currentDeck.length + 10];

        for(int i = 0; i < newDeck.length; i++){
            newDeck[i] = currentDeck[i];
        }

        newDeck[currentDeck.length + 1] = cardPicked;

        player.setDeck(newDeck);
        player.shuffleDeck();

    }
    
}