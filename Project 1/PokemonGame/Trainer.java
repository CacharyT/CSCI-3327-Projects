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

    /*
     * 
     */
    public void activateEffect(Player player){


    }

    /*
     * 
     */
    public Energy[] getEnergies(){
        return new Energy[0];
    }

    /*
     * 
     */
    public void setEnergies(Energy[] newEnergies){
        
    }

}
