/*
 * Cachary Tolentino
 * The salter class will allow a user to salt a provided dataset and generate the newly salted data as a csv file
 */

//Imports
import java.io.File;
import java.util.*;

public class Salter {

    //Global Variables
    private File dataFile;
    private int lowerBound;
    private int upperBound;

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @param lower an int value
     * @param upper an int value
     * @return none
     */
    public Salter(File inputFile, int lower, int upper){
        dataFile = inputFile;
        lowerBound = lower;
        upperBound = upper;
    }

    /*
     * The function will salt the data
     * @param data an arraylist of integers
     * @return newData an array list of double values
     */
    public ArrayList<Double> salter(ArrayList<Double> data){

        //Declared variabales
        Random random = new Random();
        ArrayList<Double> newData = new ArrayList<>();

        for(int i = 0; i < data.size(); i++){
            if(i%2 != 0){ //skip x values
                int saltValue = lowerBound + random.nextInt(upperBound - lowerBound); //ensures different salt value per y value
                int operation = random.nextInt(2);
                if(operation == 0){ //add
                    newData.add(data.get(i) + saltValue);
                } else{ //subtract
                    newData.add(data.get(i) - saltValue);
                }
            } else{
                newData.add(data.get(i)); //x values remain unchanged
            }
        }
        return newData;
    }

    /*
     * The function will salt the given data set and generate it as a new csv file
     * @param none
     * @return a String value
     */
    public String salterData(){

        //Parsed data
        DataHandler handler = new DataHandler(); 
        ArrayList<Double> data = handler.parser(dataFile); //parsed data from string to double;

        //salted data and stringed data
        ArrayList<Double> saltedData = salter(data);
        ArrayList<String> stringedValue;

        //Write and Exports the data
        stringedValue = handler.writer(saltedData); //convert data to string and added to data structure of type string
        handler.exporter(stringedValue, dataFile.toString() + "Salted");

        return dataFile.toString() + "Salted"; //return file name
    }

}