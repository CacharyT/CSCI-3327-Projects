/*
 * Cachary Tolentino
 * The Energy class is a type of Card object that will contain a type of energy.
 */

public class Energy extends Card{
    
    //Global Variable(s)
    private String energyType;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Energy(){
        super.setCardType("Energy");
        super.setName("");
        energyType = "";
    }

    /*
     * Constructor with parameters
     * @param type a string value of the energy type
     * @return none
     */
    public Energy(String type){
        super.setCardType("Energy");
        super.setName("");
        energyType = type;
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
     * The function will return the current energy card's type
     * @param none
     * @return energyType a string value of the energy type
     */
    public String getEnergyType(){
        return energyType;
    }

}