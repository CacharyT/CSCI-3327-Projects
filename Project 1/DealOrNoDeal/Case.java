/*
 * Cachary Tolentino
 * The case class will emulate what a case would represent in the DealOrNoDeal game show.
 * It will contain a set amount of money.
 */

public class Case{

    //Global Variables
    private String name;
    private double money;

    /*
     * Default constructor
     * @param none
     * @return none
     */
    public Case(){
        name = "";
        money = 0.0;
    }

    /*
     * The function will set the amount of money for the case object
     * @param amount an int value
     * @return none
     */
    public void setMoney(double amount){
        money = amount;
    }

    /*
     * The function will get the amount of moeny inside the case
     * @param none
     * @return money an int value
     */
    public double getMoney(){
        return money;
    }

    /*
     * The function will set the name of the case object
     * @param newName a string value
     * @return none
     */
    public void setName(String newName){
        name = newName;
    }

    /*
     * The function will return the case's name
     * @param none
     * @return name a string value
     */
    public String getName(){
        return name;
    }

}