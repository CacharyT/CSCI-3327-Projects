/*
 * Cachary Tolentino
 * super class
 */

public class Pokemon {

    //Global variables
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    //Default Pokemon constructor
    public Pokemon(){
        
    }

    //Global variable getters
    public int getHP(){
        return hp;
    }

    public int getAttack(){
        return attack;
    }

    public int getDefense(){
        return defense;
    }

    public int getSpeed(){
        return speed;
    }

    //Global variable setters
    public void setHP(int userHP){
        hp = userHP; //can als use "this." if same name as variable
    }

    public void setAttack(int userAttack){
        attack = userAttack;
    }

    public void setDefense(int userDefense){
        defense = userDefense;
    }

    public void setSpeed(int userSpeed){
        speed = userSpeed;
    }
}