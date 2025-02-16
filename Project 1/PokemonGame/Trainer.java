/*
 * Cachary Tolentino
 * 
 */


public class Trainer extends Card{
    
    //Global Variable(s)
    private String cardName;

    /*
     * Default Constructor
     */
    public Trainer(){
        super.setCardType("Trainer");
        cardName = "";
    }

    /*
     * 
     */
    public void setName(String newName){
        cardName = newName;
    }

    /*
     * 
     */
    @Override
    public String getName(){
        return cardName;
    }

}
