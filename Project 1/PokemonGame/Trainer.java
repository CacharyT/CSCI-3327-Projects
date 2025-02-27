/*
 * Cachary Tolentino
 * This class is a child class of card. It frames the necessary data of more specified trainer cards.
 */

public class Trainer extends Card{
    
    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Trainer(){
        super.setCardType("Trainer");
        super.setName("");
        trainerDescription = "";
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
     * The function will activate the card effect (none)
     * @param none
     * @return none
     */
    public void activateEffect(Player player){
        //Does nothing
    }

}
