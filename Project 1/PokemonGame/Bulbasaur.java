/*
 * 
 */

 //Imports



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
     * 
     */
    public int VineWhip(Energy[] energyRequirements){

        //Ability requires 1 grass energy

        int grassEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Grass")){
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
     * 
     */
    public int RazorLeaf(Energy[] energyRequirements){

        //Ability requires 1 grass energy & 1 basic energy

        int grassEnergyRequirement = 0;
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getCardType().equals("Grass")){
                grassEnergyRequirement++;
            } else if(card.getCardType().equals("Basic")){
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
