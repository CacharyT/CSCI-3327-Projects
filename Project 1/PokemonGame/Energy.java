/*
 * Cachary Tolentino
 * The Energy class is a type of Card object that will contain a type of energy.
 */

public class Energy extends Card{
    
    //Global Variable(s)
    private String energyType;
    private String cardName;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Energy(){
        energyType = "";
        super.setCardType("Energy");
        cardName = "";
    }

    /*
     * Constructor with parameters
     * @param type a string value of the energy type
     * @return none
     */
    public Energy(String type){
        energyType = type;
        super.setCardType("Energy");
        cardName = "";
    }
    
    /*
     * The function will update the current energy type
     * @param newType  a string value of the new energy type
     * @return none
     */
    public void setEnergyType(String newType){
        energyType = newType;
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
     * The function will return the current energy card's type
     * @param none
     * @return energyType a string value of the energy type
     */
    public String getEnergyType(){
        return energyType;
    }
    
}