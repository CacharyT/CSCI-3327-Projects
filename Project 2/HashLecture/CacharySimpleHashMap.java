/*
 * Cachary Tolentino
 * The class creates an emulation of the HashMap class but it uses a custom hashing function (# of characters of a string)
 */

//Imports
import java.util.LinkedList;

public class CacharySimpleHashMap{

    //Global Variable(s)
    private LinkedList<String>[] data;
    private int dataSize;
    private int currentCount;

    /*
     * Default Constructor (Base size of 10)
     * @param none
     * @return none
     */
    public CacharySimpleHashMap(){
        data = new LinkedList[10];
        for(int i = 0; i < data.length; i++){ //populate the array with LinkedList objects
            data[i] = new LinkedList<>();
        }
        dataSize = 10;
        currentCount = 0;
    }

    /*
     * Constructor with parameters
     * @param inputSize - an int value (user-specific size)
     * @return none
     */
    public CacharySimpleHashMap(int inputSize){
        data = new LinkedList[inputSize];
        for(int i = 0; i < data.length; i++){ //populate the array with LinkedList objects
            data[i] = new LinkedList<>();
        }
        dataSize = inputSize;
        currentCount = 0;
    }

    /*
     * The function will take a string value and return the number of characters it contains
     * @param input - a string value
     * @return value - an int value
     */
    public int dumbHash(String input){
        return input.length();
    }

    /*
     * The function will add a provided value (String) into the hash map
     * @param input - a string value
     * @return none
     */
    public void put(String input){
        //Declared Variables
        int index = dumbHash(input) % dataSize; //necessary mod to ensure it does not go beyond the index of the array

        //Check first if the current array has enough space, if not then resize (uses load factor to check for density of content)
        double loadFactor = (double) currentCount / dataSize;
        if(loadFactor > 0.8){
            resize();
        }

        //Add the input
        data[index].add(input);
        currentCount++;
    }

    /*
     * The function will return a boolean value if the hashmap contains a certain string value
     * @param input - a string value
     * @return a boolean value
     */
    public boolean contains(String input){
        //Declared Variables
        int index = dumbHash(input) % dataSize; //Need the key value based on the given value

        return data[index].contains(input); //use built in contain function of LinkedList if the value exist in the LinkedList of the index
    }

    /*
     * The function will dynamically update resize the array (by 2)
     * @param none
     * @return none
     */
    public void resize(){
        //Declared Variables
        LinkedList<String>[] newData = new LinkedList[data.length * 2]; //make a new array that will be twice the size of the current array
        for(int i = 0; i < newData.length; i++){
            newData[i] = new LinkedList<>();
        }

        //Rehash pre-existing values from original list, then add into the new array with new index (using mod)
        for(LinkedList<String> list : data){
            for(String value : list){
                int newIndex = dumbHash(value) % newData.length;
                newData[newIndex].add(value);
            }
        }

        //Update variables with new values
        data = newData;
        dataSize = newData.length;
    }

    /*
     * The function will remove the value at the designated key
     * @param input - a string value
     * @return none
     */
    public void remove(String input){
        int index = dumbHash(input) % dataSize; //hashed index

        if(data[index].contains(input)){ //check first if the value even exist at the index, if so remove it
            data[index].remove(input);
        }
    }

    /*
     * The function will get the current data value
     * @param none
     * @return data - an array of linkedlist
     */
    public LinkedList<String>[] data(){
        return data;
    }

    /*
     * The function will set a new value for the data
     * @param newData - an array of linkedlist
     * @return none
     */
    public void setData(LinkedList<String>[] newData){
        data = newData;
    }

    /*
     * The function will get the current size of the data
     * @param none
     * @return dataSize
     */
    public int getSize(){
        return dataSize;
    }

    /*
     * The function will set a new value for the data size
     * @param newSize - an int value
     * @return none
     */
    public void setSize(int newSize){
        dataSize = newSize;
    }

    /*
     * The function will return the current amount of elements
     * @param none
     * @return currentCount - an int value
     */
    public int getCurrentCount(){
        return currentCount;
    }

    /*
     * The function will set a new amount of current elements
     * @param newCount - an int value
     * @return none
     */
    public void setCurrentCount(int newCount){
        currentCount = newCount;
    }
}