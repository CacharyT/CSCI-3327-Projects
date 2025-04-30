/*
 * Cachary Tolentino
 * The class creates an emulation of the HashMap class but it uses a custom hashing function (# of characters of a string)
 */

//Imports
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

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
        if(loadFactor > 0.8){ //predetermined load factor
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
     * The function will print out the current hash map
     * @param none
     * @return none
     */
    public void printMap(){
        for(int i = 0; i < data.length; i++){
            System.out.print("Node " + i + ": ");
            if(data[i].isEmpty()){
                System.out.println("Empty.");
            } else{
                for(String value : data[i]){
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        }
    }

    /*
     * The function will load a csv to the hash map structure
     * @param data - a string value (data file name)
     * @return none
     */
    public void loadData(String data){
        try{
            Scanner scan = new Scanner(new File(data + ".csv"));
            while(scan.hasNextLine()){ //iterates through each row for each word
                String line = scan.nextLine();
                String[] seperated = line.split(","); 
                put(seperated[0]); //adds to the hash structure
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * The function will track the times and memory usage when loading the data into the data structure, this will output the tracked values
     * @param dataName - a string value (data file name)
     * @return none
     * @sources: https://www.geeksforgeeks.org/java-runtime-getruntime-method/ 
     * @sources: https://www.geeksforgeeks.org/garbage-collection-java/ 
     * @sources: https://www.geeksforgeeks.org/java-system-nanotime-vs-system-currenttimemillis/
     */
    public void trackLoadTimeAndMemory(String dataName){
        try{
            Runtime runtime = Runtime.getRuntime(); //get an instance of the java runtime to get memory values
            System.gc(); //runs the garbage cleaner to free up memory to get accurate memory usage readings
            Thread.sleep(100); //gives the garbage cleaner some time to perform collection completely, results in better memory reading

            long memoryBeforeTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the currently used memory
            long start = System.nanoTime();

            loadData(dataName); //task to measure

            long end = System.nanoTime();
            long memoryAfterTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the change in memory usage 

            //Converts the memory to KB from Bytes and Time from Nanoseconds to Miliseconds
            long memoryInKB = (memoryAfterTask - memoryBeforeTask) / 1024;
            long timeInMillis = (end - start) / 1000000;

            System.out.println("[" + dataName + ".csv] Load time: " + timeInMillis + " ms");
            System.out.println("[" + dataName + ".csv] Approx. memory used: " + memoryInKB + " KB");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * The function will track the times and memory usage when checking a data value from the data structure
     * @param dataName - a string value (data file name)
     * @return none
     * @sources: https://www.geeksforgeeks.org/java-runtime-getruntime-method/ 
     * @sources: https://www.geeksforgeeks.org/garbage-collection-java/ 
     * @sources: https://www.geeksforgeeks.org/java-system-nanotime-vs-system-currenttimemillis/
     */
    public void trackCheckTimeAndMemory(String dataName, String[] wordList){
        try{
            Runtime runtime = Runtime.getRuntime(); //get an instance of the java runtime to get memory values
            System.gc(); //runs the gc to free up memory to get accurate memory usage readings
            Thread.sleep(100); //gives the gc some time to perform collection completely, results in better memory reading

            long memoryBeforeTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the currently used memory
            long start = System.nanoTime();

            for(String word : wordList){
                contains(word); //task to be tracked
            }

            long end = System.nanoTime();
            long memoryAfterTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the change in memory usage 

            //No conversion (runtimes for checking or removing are assumed to be quick)
            long timeInNanos = end - start;
            long memoryInBytes = memoryAfterTask - memoryBeforeTask;

            System.out.println("[" + dataName + ".csv] Check time: " + timeInNanos + " ns");
            System.out.println("[" + dataName + ".csv] Approx. memory used: " + memoryInBytes + " bytes");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * The function will track the times and memory usage when removing a data value from the data structure
     * @param dataName - a string value (data file name)
     * @return none
     * @sources: https://www.geeksforgeeks.org/java-runtime-getruntime-method/ 
     * @sources: https://www.geeksforgeeks.org/garbage-collection-java/ 
     * @sources: https://www.geeksforgeeks.org/java-system-nanotime-vs-system-currenttimemillis/
     */
    public void trackRemoveTimeAndMemory(String dataName, String[] wordList){
        try{
            Runtime runtime = Runtime.getRuntime(); //get an instance of the java runtime to get memory values
            System.gc(); //runs the gc to free up memory to get accurate memory usage readings
            Thread.sleep(100); //gives the gc some time to perform collection completely, results in better memory reading

            long memoryBeforeTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the currently used memory
            long start = System.nanoTime();

            for(String word : wordList){
                remove(word); //task to be tracked
            }

            long end = System.nanoTime();
            long memoryAfterTask = runtime.totalMemory() - runtime.freeMemory(); //calculates the change in memory usage 

            //No conversion (runtimes for checking or removing are assumed to be quick)
            long timeInNanos = end - start;
            long memoryInBytes = memoryAfterTask - memoryBeforeTask;

            System.out.println("[" + dataName + ".csv] Remove time: " + timeInNanos + " ns");
            System.out.println("[" + dataName + ".csv] Approx. memory used: " + memoryInBytes + " bytes");

        } catch(Exception e){
            e.printStackTrace();
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