/*
 * Cachary Tolentino
 * The BirthdayFinder class will:
 * 1. Allow users to create an empty BirthdayFinder object
 * 2. Allow users to create a BirthdayFinder object with a given amount of people
 * 3. Allow the user to find the probability of a pair of person object with the same birthday
 */

//Imports
import java.util.Random;

public class BirthdayFinder {

    //Global Variable(s)
    private Person[] peopleList;
    private int peopleAmount;
    
    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public BirthdayFinder(){

        peopleList = new Person[0];
        peopleAmount = 0;

    }

    /*
     * The constructor will create a Birthday object filled with the given amount of people with randomized birthdays and set the peopleAmount
     * @param peopleCount the amount of people needed to make
     * @return none
     */
    public BirthdayFinder(int peopleCount){

        peopleAmount = peopleCount;

        Random random = new Random();
        peopleList = new Person[peopleCount];

        //Preemtively fill the Person array
        for(int i = 0; i < peopleCount; i++){

            //Creates a random birthday
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(30) + 1;
            int birthday = month * 100 + day;

            peopleList[i] = new Person(birthday); //Assigns random birthday per new person
        }

    }

    /*
     * The function will find the probability of 2 people having the same birthday, x amount of trials
     * @param numTrial the int value indicating the amount of trial to repeat
     * @return twinProbabiity a double value indicating the probability of two people having the same birthday
     */
    public double findProbabilityOfBirthdayTwins(int numTrial){
        int totalSharedBirthday = 0;

        //Iterate the checking and add to total per pair found
        for(int i = 0; i < numTrial; i++){
            if(checkForTwins()){
                totalSharedBirthday++;
            }
        }

        return (double) totalSharedBirthday / (double) numTrial;
    }

    /*
     * The function checks if there are any pair of persons object in the list with the same birthday
     * @param none
     * @return boolean determines whether there are any pair that share the same birthday
     */
    public boolean checkForTwins(){

        Random random = new Random();
        Person[] newPeopleList = new Person[peopleAmount];

        for(int i = 0; i < peopleAmount; i++){ //new assignmentsss of random people to the peopleList
            //Creates a random birthday
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(30) + 1;
            int birthday = month * 100 + day;

            newPeopleList[i] = new Person(birthday); //Assigns random birthday per new person
        }

        setPeopleBirthday(newPeopleList); //updates peopleList

        //Checks for any pair of Person object with the same birthday
        for(int i = 0; i < peopleList.length - 1; i++){
            for(int j = i + 1; j < peopleList.length; j++){
                if(peopleList[i].getPersonBirthday() == peopleList[j].getPersonBirthday()){
                    return true;
                }
            }
        }

        return false; //no pair found
    }

    /*
     * The function will change the value of peopleList with the new list
     * @param newPeopleList an array of Person objects
     * @return none
     */
    public void setPeopleBirthday(Person[] newPeopleList){
        peopleList = newPeopleList;
    }

}