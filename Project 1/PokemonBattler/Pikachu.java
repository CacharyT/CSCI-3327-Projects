/*
 * Cachary Tolentino
 * Pokemon subclass
 */

public class Pikachu extends Pokemon {

    /*
     * Default Constructor
     */
    public Pikachu(){
        setHP(35);
        setAttack(55);
        setDefense(30);
        setSpeed(90);
    }

    /*
     * Default Constructor with all parameters
     */
    public Pikachu(int newHP, int newAttack, int newDefense, int newSpeed){
        setHP(newHP);
        setAttack(newAttack);
        setDefense(newDefense);
        setSpeed(newSpeed);
    }

}