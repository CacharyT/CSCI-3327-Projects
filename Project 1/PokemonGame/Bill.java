/*
 * Cachary Tolentino
 * This class emulates the effects of the Bill card which draws 2 extra cards
 */

public class Bill extends Trainer{
    
    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Bill(){
        super.setCardType("Trainer");
        super.setName("Bill");
        trainerDescription = "Draw 2 cards.";
    }

    /*
     * This functions allows the player to gain 2 extra cards
     * @param player a player object
     * @return none
     */
    @Override
    public void activateEffect(Player player){
        Card[] drawnCard = new Card[2];

        //Draw 2 card
        drawnCard[0] = player.drawCard();
        drawnCard[1] = player.drawCard();

        //Update hand
        Card[] currentHand = player.getHand();
        Card[] newHand = new Card[currentHand.length + 2];
        for(int i = 0; i < currentHand.length; i++){
            newHand[i] = currentHand[i];
        }
        newHand[currentHand.length] = drawnCard[0];
        newHand[currentHand.length + 1] = drawnCard[1];
        player.setHand(newHand);

        System.out.println("You have activated a Bill trainer card! The card's effect is: " + trainerDescription);
        System.out.println("You have drawn a " + drawnCard[0].getName() + " and a " + drawnCard[1].getName());
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