/*
 * 
 */

public class Energy extends Card{
    
    //Global Variable(s)
    private String energyType;

    /*
     * Default Constructor
     */
    public Energy(){
        energyType = "";
    }

    /*
     * 
     */
    public Energy(String type){
        energyType = type;
    }

    //Setters
    
    /*
     * 
     */
    public void setEnergyType(String newType){
        energyType = newType;
    }

    //Getters

    /*
     * 
     */
    public String getEnergyType(){
        return energyType;
    }

    /*
     * 
     */
    @Override
    public String getCardType(){
        return super.getCardType();
    }

}
