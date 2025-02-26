/*
 * Cachary Tolentino
 * This class represents a Bulbasaur card. It contains various stats that a Bulbasaur would have.
 */

public class Bulbasaur extends Pokemon{
    
    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Bulbasaur(){
        super.setHP(70);
        super.setName("Bulbasaur");
        super.setBasic(10);
        super.setSpecial(20);
        String[] abilityDescription = new String[2];
        abilityDescription[0] = "VineWhip";
        abilityDescription[1] = "RazorLeaf";
        super.setAbilityDescriptions(abilityDescription);
        super.setWeakness("Fire");
        Energy[] retreatCost = new Energy[2];
        retreatCost[0] = new Basic();
        retreatCost[1] = new Basic();
        super.setReatreatCost(retreatCost);
        super.setElementType("Grass");
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

}
