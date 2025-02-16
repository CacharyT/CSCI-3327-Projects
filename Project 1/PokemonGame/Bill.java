/*
 * Cachary Tolentino
 * 
 */


public class Bill extends Trainer{
    

    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     */
    public Bill(){
        super.setCardType("Trainer");
        super.setName("Bill");
        trainerDescription = "Draw 2 cards.";
    }

    /*
     * 
     */
    public Card[] activateEffect(Player player){
        Card[] drawnCard = new Card[2];
        drawnCard[0] = player.drawCard();
        drawnCard[1] = player.drawCard();
        return drawnCard;
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