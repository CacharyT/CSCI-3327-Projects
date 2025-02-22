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
    @Override
    public Energy[] getEnergies(){

        return new Energy[0];

    }

    /*
     * 
     */
    @Override
    public void setEnergies(Energy[] newEnergies){



    }

    /*
     * 
     */
    @Override
    public void activateEffect(Player player){



    }

    /*
     * 
     */
    @Override
    public Energy[] getRetreatCost(){

        return new Energy[0];

    }

    /*
     * 
     */
    @Override
    public String[] getAbilityDescriptions(){

        return new String[0];

    }

    /*
     * 
     */
    @Override
    public String getWeakness(){

        return null;

    }

    /*
     * 
     */
    @Override
    public String getElementType(){

        return null;

    }

}
