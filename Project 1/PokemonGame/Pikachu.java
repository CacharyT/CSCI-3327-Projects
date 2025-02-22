/*
 * 
 */

 //Imports


public class Pikachu extends Pokemon{
    
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
    public Pikachu(){
        super.setHP(60);
        super.setName("Pikachu");
        basicAttack = 10;
        specialAttack = 50;
        abilityDescription = new String[2];
        abilityDescription[0] = "QuickAttack";
        abilityDescription[1] = "ElectroBall";
        weakness = "Fighting";
        retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        elementType = "Lightning";
    }

    /*
     * 
     */
    public int QuickAttack(Energy[] energyRequirements){

        //Ability requires 1 lightning energy

        int lightningEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Lightning")){
                lightningEnergyRequirement++;
            }
        }

        if(lightningEnergyRequirement >= 1){

            //Head - 0, Tail - 1
            int coin = PokemonGame.flipACoin();

            if(coin == 0){
                System.out.println("You rolled a head! Quick attack will deal an extra 10 damage!");
                return getBasic() + 10;
            }


        } else{
            System.out.println("You need 1 lightning energy to perform Quick attack");
        }

        return 0; //0 dmg dealt 
    }



    /*
     * 
     */
    public int ElectroBall(Energy[] energyRequirements){

        //Ability requires 2 lightning energy & 1 basic energy

        int lightningEnergyRequirement = 0;
        int basicEnergyRequirement = 0;

        for(Card card : energyRequirements){
            if(card.getName().equals("Lightning")){
                lightningEnergyRequirement++;
            } else if(card.getName().equals("Basic")){
                basicEnergyRequirement++;
            }
        }


        if(lightningEnergyRequirement >= 2 && basicEnergyRequirement >= 1){

            return getSpecial();

        } else{
            System.out.println("You need 2 lightning energy and 1 basic energy to perform electro ball");
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
