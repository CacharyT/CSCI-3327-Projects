/*
 * Cachary Tolentino
 * The GameShow class will contain constructors or functions that can 
 * 1. Create an empty GameShow Object
 * 2. Create a GameShow object filled with 3 door objects containing some value
 * 3. Play a game in which a door is picked and it would not change, the game can be played based on the given amount of times
 * 4. Pay a game in which the choice can be switched to the remaining non-revealed door, the game can be played based on the given amount of times
 */

//Imports
import java.util.Random;

public class GameShow {

    //Global Variable(s)
    private Door[] doors;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public GameShow(){
        doors = new Door[3];
    }

    /*
     * Constructs a GameShow object with an array of doors that contains the given door values each 
     * @param door1Content a string value for door 1 content
     * @param door2Content a string value for door 2 content
     * @param door3Content a string value for door 3 content
     * @return none
     */
    public GameShow(String door1Content, String door2Content, String door3Content){
        doors = new Door[3];
        doors[0] = new Door(door1Content);
        doors[1] = new Door(door2Content);
        doors[2] = new Door(door3Content);
    }

    /*
     * A function that allows the GameShow to perform a number of games in which an initial choice will not change
     * @param numTrials an int value indicating the amount of times the games have to be repeated
     * @return a double value which represents the percentage of winnings
     */
    public double playGamesOneChoice(int numTrials){

        Random random = new Random();
        int totalWins = 0;

        for(int i = 0; i < numTrials; i++){
            
            int doorChoice = random.nextInt(3); //chooses a random door (choice does not change)

            if(doors[doorChoice].getDoor().equals("Good")){ //Checks if the chosen door is correct, if so add 1 to winning total
                totalWins += 1;
            }
        }

        return (double) totalWins/ (double) numTrials; //the percentage of winnings
    }

    /*
     * A function that allows the GameShow to perform a number of games in which an initial choice can change when a door is revealed
     * @param numTrials an int value indicating the amount of times the games have to be repeated
     * @return a double value which represents the percentage of winnings
     */
    public double playGamesSwitched(int numTrials){

        Random random = new Random();
        int totalWins = 0;

        for(int i = 0; i < numTrials; i++){
            
            int doorChoice = random.nextInt(3); //chooses a random door

            //emulates opening a door (ensures door "opened" is not the correct door nor the current chosen door)
            int openedDoor = random.nextInt(3);
            while(doors[openedDoor].getDoor().equals("Good") || doorChoice == openedDoor){
                openedDoor = random.nextInt(3);
            }

            //emulates contestant switching their door
            int newChosenDoor = 3 - openedDoor - doorChoice; //the other non revealed door besides the currently chosen door
            if(doors[newChosenDoor].getDoor().equals("Good")){
                totalWins += 1;
            }
        }
        return (double) totalWins/ (double) numTrials; //percenatge of winnings
    }

}