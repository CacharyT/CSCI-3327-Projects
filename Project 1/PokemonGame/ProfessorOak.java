/*
 * Cachary Tolentino
 * This class emulates the effects of the ProfessorOak card which reveals the current hand and redraw hand
 */

public class ProfessorOak extends Trainer{

    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public ProfessorOak(){
        super.setCardType("Trainer");
        super.setName("ProfessorOak");
        super.setTrainerDescription("Discard your hand and draw 7 cards.");
    }


    /*
     * This functions allows the player to reveal their hand and redraw their hand
     * @param player a player object
     * @return none
     */
    @Override
    public void activateEffect(Player player){

        System.out.println("You have activated a ProfessorOak trainer card! The card's effect is: " + getTrainerDescription());

        //Discard hand to discard pile
        Card[] currentHand = player.getHand();

        System.out.print("The current hand will be discarded: [");
        for(Card card : currentHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]\n");

        //Moves hand to deck
        player.reAddHandToDeck();

        //Re-draw 7 cards
        player.fillHand();

        Card[] newHand = player.getHand();
        System.out.print("Your new hand: [");
        for(Card card : newHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]\n");
        player.setHand(newHand);

    }
    
}