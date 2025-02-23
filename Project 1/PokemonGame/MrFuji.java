/*
 * Cachary Tolentino
 * This class emulates the effects of the Mr.Fuji card which allows a player to reshuffle a pokemon card from the bench into the deck
 */

//Imports
import java.util.Scanner;

public class MrFuji extends Trainer{

    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public MrFuji(){
        super.setCardType("Trainer");
        super.setName("Mr.Fuji");
        trainerDescription = "Choose a pokemon on your bench. Shuffle it and any cards attached to it into your deck.";
    }

    /*
     * This functions allows the player to reshuffle a benched pokemon into the deck
     * @param player a player object
     * @return none
     */
    @Override
    public void activateEffect(Player player){

        System.out.println("You have activated a Mr.Fuji trainer card! The card's effect is: " + trainerDescription);
        Scanner scan = new Scanner(System.in);

        //Allow for user to choose a pokemon from the bench
        Card[] playerBench = player.getbench();

        System.out.print("This is your current bench: [");
        for(Card card : playerBench){
            if(card == null){
                break;
            } else{
                System.out.print(card.getName() + " ");
            }
        }
        System.out.print("]\n");

        System.out.print("Choose a pokemon from the bench (0 - N; position in array): ");
        int benchSpot = 0;
        try {
            benchSpot = scan.nextInt();
        } catch (Exception e) {
        }

        Card cardPicked = playerBench[benchSpot];
        Energy[] cardPickedEnergies = cardPicked.getEnergies();

        //Remove pokemon from bench and update bench
        Card[] newBench = new Card[playerBench.length - 1];
        int newIndex = 0;
        for(int i = 0; i < playerBench.length; i++){
            if(i != benchSpot){
                newBench[newIndex++] = playerBench[i];
            }
        }
        player.setBench(newBench);


        //Add picked card and energies to the deck and shuffle
        Card[] currentDeck = player.getDeck();
        Card[] newDeck = new Card[currentDeck.length + cardPickedEnergies.length + 1]; //card could have no energy

        //Copying
        for(int i = 0; i < currentDeck.length; i++){
            newDeck[i] = currentDeck[i];
        }

        //Adding
        if(cardPickedEnergies.length == 0){
            newDeck[newDeck.length - 1] = cardPicked;
        } else{

            int newerIndex = 0;
            for(int i = 0; i < newDeck.length; i++){
                if(newDeck[i] == null){
                    newDeck[i] = cardPickedEnergies[newerIndex++];
                }
            }
        }

        player.setDeck(newDeck);
        player.shuffleDeck();

    }

    /*
     * The function allows for changing the card description
     * @param newDescription a string
     * @return none
     */
    public void setTrainerDescription(String newDescription){
        trainerDescription = newDescription;
    }

    /*
     * The function returns the card description
     * @param none
     * @return trainerDescription a string value
     */
    public String getTrainerDescription(){
        return trainerDescription;
    }
    
}