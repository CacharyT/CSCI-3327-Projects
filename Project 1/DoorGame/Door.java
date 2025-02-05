/*
 * Cachary Tolentino
 * The Door class will contain constructors or functions that can 
 * 1. Construct a default object with no values
 * 2. Construct a door object with a given door content
 * 3. Set a new content value for the door
 * 4. Get the current content value for the door
 */

public class Door {

    //Global Variable(s)
    private String doorContent;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Door(){
        doorContent = "";
    }

    /*
     * Constructor a door object with doorContent assigned using the given provided content
     * @param content a string containing the content for the door
     * @return none
     */
    public Door(String content){
        doorContent = content;
    }

    /*
     * The setter function to change door content
     * @param newContent a string value for the new content
     * @return none
     */
    public void setDoor(String newContent){
        doorContent = newContent;
    }

    /*
     * The getter function to return the door content
     * @param none
     * @return doorContent a string containing the content of the door
     */
    public String getDoor(){
        return doorContent;
    }
    
}