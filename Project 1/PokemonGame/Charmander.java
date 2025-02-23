/*
 * Cachary Tolentino
 * This class represents a Charmander card. It contains various stats that a Charmander would have.
 */

public class Charmander extends Pokemon{
    
    //Global Variable(s)
    private int basicAttack;
    private int specialAttack;
    String[] abilityDescription;
    private String weakness;
    private Energy[] retreatCost;
    private String elementType;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Charmander(){
        super.setHP(70);
        super.setName("Charmander");
        basicAttack = 0;
        specialAttack = 30;
        abilityDescription = new String[2];
        abilityDescription[0] = "Collect";
        abilityDescription[1] = "Flare";
        weakness = "Water";
        retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        elementType = "Fire";
    }

    /*
     * This functions activates Charmander's ability Collect
     * @param energyRequirements an aray of energy objects
     * @return a boolean value denoting if the ability was executed
     */
    public Boolean Collect(Energy[] energyRequirements){

        //Ability requires 1 fire energy
        int fireEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Fire")){
                fireEnergyRequirement++;
            }
        }

        if(fireEnergyRequirement >= 1){
            return true;
        } else{
            System.out.println("You need 1 fire energy to perform collect");
        }

        return false; //failed energy requirement
    }



    /*
     * This functions activates Charmander's ability Flare
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
     */
    public int Flare(Energy[] energyRequirements){

        //Ability requires 2 fire energy
        int fireEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Fire")){
                fireEnergyRequirement++;
            }
        }

        if(fireEnergyRequirement >= 2){

            return getSpecial();

        } else{
            System.out.println("You need 2 fire energy to perform flare");
        }

        return 0; //did not meet energy requirements

    }

    /*
     * The function allows for changing the basicAttack value
     * @param newBasic an int value
     * @return none
     */
    public void setBasic(int newBasic){
        basicAttack = newBasic;
    }

    /*
     * The function allows for changing the specialAttack value
     * @param newSpecialAttack an int value
     * @return none
     */
    public void setSpecial(int newSpecial){
        specialAttack = newSpecial;
    }

    /*
     * The function allows for changing the abilityDescription value
     * @param newAbilityDescription an array of string values
     * @return none
     */
    public void setAbilityDescriptions(String[] newAbilityDescriptions){
        abilityDescription = newAbilityDescriptions;
    }

    /*
     * The function allows for changing the weakness value
     * @param newWeakness a string value
     * @return none
     */
    public void setWeakness(String newWeakness){
        weakness = newWeakness;
    }

    /*
     * The function allows for changing the retreatCost value
     * @param newRetreat an array of energy objects
     * @return none
     */
    public void setReatreatCost(Energy[] newRetreatCost){
        retreatCost = newRetreatCost;
    }

    /*
     * The function allows for changing the elementType value
     * @param newElementType a string value
     * @return none
     */
    public void setElementType(String newElementType){
        elementType = newElementType;
    }


    /*
     * The fucntion returns the basicAttack value
     * @param none
     * @return basicAttack an int value
     */
    public int getBasic(){
        return basicAttack;
    }

    /*
     * The fucntion returns the specialAttack value
     * @param none
     * @return specialAttack an int value
     */
    public int getSpecial(){
        return specialAttack;
    }

    /*
     * The fucntion returns the abilityDescription value
     * @param none
     * @return abilityDescription an array of strings
     */
    @Override
    public String[] getAbilityDescriptions(){
        return abilityDescription;
    }

    /*
     * The fucntion returns the weakness value
     * @param none
     * @return weakness a string value
     */
    @Override
    public String getWeakness(){
        return weakness;
    }

    /*
     * The fucntion returns the retreatCost value
     * @param none
     * @return retreatCost an array of energy objects
     */
    @Override
    public Energy[] getRetreatCost(){
        return retreatCost;
    }


    /*
     * The fucntion returns the elementType value
     * @param none
     * @return elementType a string value
     */
    @Override
    public String getElementType(){
        return elementType;
    }


}
