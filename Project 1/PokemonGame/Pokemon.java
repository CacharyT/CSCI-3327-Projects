/*
 * Cachary Tolentino
 * This class is a child class of card. It frames the necessary data of more specified pokemon cards.
 */

public class Pokemon extends Card implements Attackable{
    
    //Global Variable(s)
    private int hp;
    private Energy[] energies;
    private String cardName;
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
    public Pokemon(){
        super.setCardType("Pokemon");
        hp = 0;
        energies = new Energy[0];
        cardName = "";
        basicAttack = 0;
        specialAttack = 0;
        abilityDescription = new String[0];
        weakness = "";
        retreatCost = new Energy[0];
        elementType = "";
    }

    /*
     * Constructor with parameters
     * @param newHp an int value
     * @param newEnergies an array of energy objects
     * @return none
     */
    public Pokemon(int newHP, Energy[] newEnergies){
        hp = newHP;
        energies = newEnergies;
        super.setCardType("Pokemon");
    }


    /*
     * Changes the HP
     * @param newHp an int value
     * @return none
     */
    public void setHP(int newHP){
        hp = newHP;
    }

    /*
     * Changes the energies
     * @param newEnergies an array of energy objects
     * @return none
     */
    @Override
    public void setEnergies(Energy[] newEnergies){
        energies = newEnergies;
    }

    /*
     * The function allows cardName to be changed
     * @param newName a string value
     * @return none
     */
    public void setName(String newName){
        cardName = newName;
    }

    /*
     * Returns the cardName
     * @param none
     * @return cardName a string value
     */
    @Override
    public String getName(){
        return cardName;
    }

    /*
     * Returns the HP
     * @param none
     * @return hp an int value
     */
    public int getHP(){
        return hp;
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

    //Interface Method Implementations
    /*
     * 
     */
    @Override
    public int reduceHealth(int damage){
        this.hp -= damage;
        return this.hp;
    }

    /*
     * 
     */
    @Override
    public Boolean knockedOut(){
        if(this.hp <= 0){
            return true;
        }
        return false;
    }


    /*
     * Returns the energies (abstract implementation
     * @param none
     * @return energies an array of energy ojects
     */
    @Override
    public Energy[] getEnergies(){
        return energies;
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return none
     */
    @Override
    public void activateEffect(Player player){
        //Does nothing
    }

    // /*
    //  * Implementation of the abstract method (mandatory implementation)
    //  * @param none
    //  * @return an array of energy objects
    //  */
    // @Override
    // public Energy[] getRetreatCost(){
    //     return new Energy[0];
    // }

    // /*
    //  * Implementation of the abstract method (mandatory implementation)
    //  * @param none
    //  * @return an array of strings
    //  */
    // @Override
    // public String[] getAbilityDescriptions(){
    //     return new String[0];
    // }

    // /*
    //  * Implementation of the abstract method (mandatory implementation)
    //  * @param none
    //  * @return a string
    //  */
    // @Override
    // public String getWeakness(){
    //     return null;
    // }

    // /*
    //  * Implementation of the abstract method (mandatory implementation)
    //  * @param none
    //  * @return a string
    //  */
    // @Override
    // public String getElementType(){
    //     return null;
    // }

}