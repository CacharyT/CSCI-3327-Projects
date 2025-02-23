/*
 * Cachary Tolentino
 * This class represents a Squirtle card. It contains various stats that a Squirtle would have.
 */

public class Squirtle extends Pokemon{
    
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
    public Squirtle(){
        super.setHP(70);
        super.setName("Squirtle");
        basicAttack = 10;
        specialAttack = 20;
        abilityDescription = new String[2];
        abilityDescription[0] = "Tackle";
        abilityDescription[1] = "RainSplash";
        weakness = "Grass";
        retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        elementType = "Water";
    }

    /*
     * This functions activates squirtle's ability Tackle
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
     */
    public int Tackle(Energy[] energyRequirements){

        //Ability requires 1 basic energy
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Basic")){
                basicEnergyRequirement++;
            }
        }

        if(basicEnergyRequirement >= 1){
            return getBasic();
        } else{
            System.out.println("You need 1 basic energy to perform tackle");
        }

        return 0; //0 dmg dealt 
    }

    /*
     * This functions activates Squirtle's ability RainSplash
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
     */
    public int RainSplash(Energy[] energyRequirements){

        //Ability requires 1 water energy & 1 basic energy
        int waterEnergyRequirement = 0;
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Water")){
                waterEnergyRequirement++;
            } else if(card.getName().equals("Basic")){
                basicEnergyRequirement++;
            }
        }

        if(waterEnergyRequirement >= 1 && basicEnergyRequirement >= 1){
            return getSpecial();
        } else{
            System.out.println("You need 1 water energy and 1 basic energy to perform rain splash");
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
