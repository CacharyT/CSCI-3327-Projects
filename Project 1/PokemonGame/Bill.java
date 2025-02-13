/*
 * Cachary Tolentino
 * 
 */


public class Bill extends Trainer{
    

    //Global Variable(s)

    /*
     * Default Constructor
     */
    public Bill(){
        super.setCardType("Trainer");
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

}