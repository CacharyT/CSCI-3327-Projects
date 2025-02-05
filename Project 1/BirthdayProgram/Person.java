/*
 * Cachary Tolentino
 * The Person class will:
 * 1. Allow users to create an empty person object
 * 2. Allow users to create a person object with a birthday
 * 3. Allow the user to change the person object's birthday
 * 4. Allow the user to get the current person object's birthday
 */

public class Person{

    //Global Variable(s) (Format: MMDD)
    private int birthday;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Person(){

        birthday = 0000;

    }

    /*
     * The constructor will create a person object with the given birth date
     * @param newBirthday a provied birthday for the person object
     * @return none
     */
    public Person(int newBirthday){

        birthday = newBirthday;

    }

    /*
     * The function will change the current birthday to the new value of birthday provided by the user
     * @param newBirthday an int value for the person object's birthday
     * @return none
     */
    public void setPersonBirthday(int newBirthday){

        birthday = newBirthday;

    }

    /*
     * The function will return the current person's birthday
     * @param none
     * @return birthday an int value indiciating the person's birthday
     */
    public int getPersonBirthday(){

        return birthday;

    }
}