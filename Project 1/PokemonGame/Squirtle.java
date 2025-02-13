/*
 * 
 */

 //Imports



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
        basicAttack = 10;
        specialAttack = 20;
        abilityDescription = new String[2];
        abilityDescription[0] = "Tackle";
        abilityDescription[1] = "Rain Splash";
        weakness = "Grass";
        retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        elementType = "Water";
    }

    /*
     * 
     */
    public int tackle(Energy[] energyRequirements){

        //Ability requires 1 basic energy

        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Basic")){
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
     * 
     */
    public int rainSplash(Energy[] energyRequirements){

        //Ability requires 1 water energy & 1 basic energy

        int waterEnergyRequirement = 0;
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Water")){
                waterEnergyRequirement++;
            } else if(card.getCardType().equals("Basic")){
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
     * 
     */
    public void setBasic(int newBasic){
        basicAttack = newBasic;
    }

    /*
     * 
     */
    public void setSpecial(int newSpecial){
        specialAttack = newSpecial;
    }

    /*
     * 
     */
    public void setAbilityDescriptions(String[] newAbilityDescriptions){
        abilityDescription = newAbilityDescriptions;
    }

    /*
     * 
     */
    public void setWeakness(String newWeakness){
        weakness = newWeakness;
    }

    /*
     * 
     */
    public void setReatreatCost(Energy[] newRetreatCost){
        retreatCost = newRetreatCost;
    }

    /*
     * 
     */
    public void setElementType(String newElementType){
        elementType = newElementType;
    }


    /*
     * 
     */
    public int getBasic(){
        return basicAttack;
    }

    /*
     * 
     */
    public int getSpecial(){
        return specialAttack;
    }

    /*
     * 
     */
    public String[] getAbilityDescriptions(){
        return abilityDescription;
    }

    /*
     * 
     */
    public String getWeakness(){
        return weakness;
    }

    /*
     * 
     */
    public Energy[] getRetreatCost(){
        return retreatCost;
    }


    /*
     * 
     */
    public String getElementType(){
        return elementType;
    }


}
