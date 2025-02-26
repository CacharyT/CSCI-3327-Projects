/*
 * Cachary Tolentino
 * This class is a child class of card. It frames the necessary data of more specified pokemon cards.
 */

public class Pokemon extends Card implements Attackable{
    
    //Global Variable(s)
    private int hp;
    private Energy[] energies;
    private String cardName;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Pokemon(){
        hp = 0;
        energies = new Energy[0];
        super.setCardType("Pokemon");
        cardName = "";
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

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of energy objects
     */
    @Override
    public Energy[] getRetreatCost(){
        return new Energy[0];
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return an array of strings
     */
    @Override
    public String[] getAbilityDescriptions(){
        return new String[0];
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return a string
     */
    @Override
    public String getWeakness(){
        return null;
    }

    /*
     * Implementation of the abstract method (mandatory implementation)
     * @param none
     * @return a string
     */
    @Override
    public String getElementType(){
        return null;
    }

}