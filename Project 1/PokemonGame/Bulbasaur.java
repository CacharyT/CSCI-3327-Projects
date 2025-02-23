/*
 * Cachary Tolentino
 * This class represents a Bulbasaur card. It contains various stats that a Bulbasaur would have.
 */

public class Bulbasaur extends Pokemon{
    
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
    public Bulbasaur(){
        super.setHP(70);
        super.setName("Bulbasaur");
        basicAttack = 10;
        specialAttack = 20;
        abilityDescription = new String[2];
        abilityDescription[0] = "VineWhip";
        abilityDescription[1] = "RazorLeaf";
        weakness = "Fire";
        retreatCost = new Energy[2];
        retreatCost[0] = new Basic();
        retreatCost[1] = new Basic();
        elementType = "Grass";
    }

    /*
     * This functions activates Bulbasaur's ability VineWhip
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
     */
    public int VineWhip(Energy[] energyRequirements){

        //Ability requires 1 grass energy
        int grassEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Grass")){
                grassEnergyRequirement++;
            }
        }

        if(grassEnergyRequirement >= 1){

            return getBasic();


        } else{
            System.out.println("You need 1 grass energy to perform vine whip");
        }

        return 0; //0 dmg dealt 

    }

    /*
     * This functions activates Bulbasaur's ability RazorLeaf
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
     */
    public int RazorLeaf(Energy[] energyRequirements){

        //Ability requires 1 grass energy & 1 basic energy
        int grassEnergyRequirement = 0;
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Grass")){
                grassEnergyRequirement++;
            } else if(card.getName().equals("Basic")){
                basicEnergyRequirement++;
            }
        }

        if(grassEnergyRequirement >= 1 && basicEnergyRequirement >= 1){

            return getSpecial();

        } else{
            System.out.println("You need 1 grass energy and 1 basic energy to perform razor leaf");
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
