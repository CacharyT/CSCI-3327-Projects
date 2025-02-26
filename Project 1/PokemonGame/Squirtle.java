/*
 * Cachary Tolentino
 * This class represents a Squirtle card. It contains various stats that a Squirtle would have.
 */

public class Squirtle extends Pokemon{
    
    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Squirtle(){
        super.setHP(70);
        super.setName("Squirtle");
        super.setBasic(10);
        super.setSpecial(20);
        String[] abilityDescription = new String[2];
        abilityDescription[0] = "Tackle";
        abilityDescription[1] = "RainSplash";
        super.setAbilityDescriptions(abilityDescription);
        super.setWeakness("Grass");
        Energy[] retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        super.setReatreatCost(retreatCost);
        super.setElementType("Water");
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

}
