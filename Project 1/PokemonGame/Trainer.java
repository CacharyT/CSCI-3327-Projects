/*
 * Cachary Tolentino
 * This class is a child class of card. It frames the necessary data of more specified trainer cards.
 */

public class Trainer extends Card{
    
    //Global Variable(s)
    private String cardName;
    private String trainerDescription;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Trainer(){
        super.setCardType("Trainer");
        cardName = "";
        trainerDescription = "";
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
     * The function allows trainerDescription to be changed
     * @param newTrainerDecription a string value
     * @return none
     */
    public void setTrainerDescription(String newTrainerDescription){
        trainerDescription = newTrainerDescription;
    }

    /*
     * Returns the trainer description
     * @param none
     * @return trainerDescription a string value
     */
    public String getTrainerDescription(){
        return trainerDescription;
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
