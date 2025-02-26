

/*
 * Cachary Tolentino
 * This class represents a Pikachu card. It contains various stats that a Pikachu would have.
 */

public class Pikachu extends Pokemon{
    
    //Global Variable(s)

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Pikachu(){
        super.setHP(60);
        super.setName("Pikachu");
        super.setBasic(10);
        super.setSpecial(50);
        String[] abilityDescription = new String[2];
        abilityDescription[0] = "QuickAttack";
        abilityDescription[1] = "ElectroBall";
        super.setAbilityDescriptions(abilityDescription);
        super.setWeakness("Fighting");
        Energy[] retreatCost = new Energy[1];
        retreatCost[0] = new Basic();
        super.setReatreatCost(retreatCost);
        super.setElementType("Lightning");
    }

    /*
     * This functions activates Pikachu's ability QuickAttack
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
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

            if(coin == 0){ //if head then deal extra damage
                System.out.println("You rolled a head! Quick attack will deal an extra 10 damage!");
                return getBasic() + 10;
            } else{
                System.out.println("You rolled a tail! Quick attack will deal no extra damage!");
                return getBasic() + 10;
            }

        } else{
            System.out.println("You need 1 lightning energy to perform Quick attack");
        }

        return 0; //0 dmg dealt 
    }



    /*
     * This functions activates Pikachu's ability ElectroBall
     * @param energyRequirements an aray of energy objects
     * @return an int value representing the damage dealt
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

}
