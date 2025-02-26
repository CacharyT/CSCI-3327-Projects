/*
 * Cachary Tolentino
 * This class represents a Charmander card. It contains various stats that a Charmander would have.
 */

public class Charmander extends Pokemon{
    
    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Charmander(){
        super.setHP(70);
        super.setName("Charmander");
        super.setBasic(0);
        super.setSpecial(30);
        String[] abilityDescription = new String[2];
        abilityDescription[0] = "Collect";
        abilityDescription[1] = "Flare";
        super.setAbilityDescriptions(abilityDescription);
        super.setWeakness("Water");
        Energy[] retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        super.setReatreatCost(retreatCost);
        super.setElementType("Fire");
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

}
