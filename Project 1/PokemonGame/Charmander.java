/*
 * 
 */

 //Imports



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
     * 
     */
    public Boolean Collect(Energy[] energyRequirements, Player player){

        //Ability requires 1 fire energy

        int fireEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Fire")){
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
     * 
     */
    public int flare(Energy[] energyRequirements){

        //Ability requires 2 fire energy

        int fireEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Fire")){
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
