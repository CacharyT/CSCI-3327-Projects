/*
 * Cachary Tolentino
 * 
 */

public class Pokemon extends Card{
    
    //Global Variable(s)
    private int hp;
    private Energy[] energies;
    private String cardName;

    /*
     * Default Constructor
     */
    public Pokemon(){
        hp = 0;
        energies = new Energy[0];
        super.setCardType("Pokemon");
        cardName = "";
    }

    /*
     * Default Constructor
     */
    public Pokemon(int newHP, Energy[] newEnergies){
        hp = newHP;
        energies = newEnergies;
        super.setCardType("Pokemon");
    }


    /*
     * 
     */
    public void setHP(int newHP){
        hp = newHP;
    }

    /*
     * 
     */
    public void setEnergies(Energy[] newEnergies){
        energies = newEnergies;
    }

    /*
     * 
     */
    public void setName(String newName){
        cardName = newName;
    }

    /*
     * 
     */
    @Override
    public String getName(){
        return cardName;
    }

    /*
     * 
     */
    public int getHP(){
        return hp;
    }


    /*
     * 
     */
    @Override
    public Energy[] getEnergies(){
        return energies;
    }

    /*
     * 
     */
    @Override
    public void activateEffect(Player player){


    }

    /*
     * 
     */
    @Override
    public Energy[] getRetreatCost(){

        return new Energy[0];

    }

    /*
     * 
     */
    @Override
    public String[] getAbilityDescriptions(){

        return new String[0];

    }

    /*
     * 
     */
    @Override
    public String getWeakness(){

        return null;

    }

    /*
     * 
     */
    @Override
    public String getElementType(){

        return null;

    }

}