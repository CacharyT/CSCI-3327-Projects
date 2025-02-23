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
     * The function allows cardName to be changed
     * @param newName a string value
     * @return none
     */
    public void setName(String newName){
        cardName = newName;
    }

    /*
     * Returns the cardName
     * @param none
     * @return cardName a string value
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

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of energy objects
     */
    @Override
    public Energy[] getEnergies(){
        return new Energy[0];
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of energy objects
     */
    @Override
    public void setEnergies(Energy[] newEnergies){
        //Does nothing
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return none
     */
    @Override
    public void activateEffect(Player player){
        //Does nothing
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of energy objects
     */
    @Override
    public Energy[] getRetreatCost(){
        return new Energy[0];
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of strings
     */
    @Override
    public String[] getAbilityDescriptions(){
        return new String[0];
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return a string
     */
    @Override
    public String getWeakness(){
        return null;
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return a string
     */
    @Override
    public String getElementType(){
        return null;
    }

    
}