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

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @return none
     */
    public Salter(File inputFile){
        dataFile = inputFile;
    }

    /*
     * The function will salt the data
     * @param data an arraylist of integers
     * @param lowerBound an int value
     * @param upperBound an int value
     * @return newData an array list of double values
     */
    public ArrayList<Double> salter(ArrayList<Double> data, int lowerBound, int upperBound){
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
        //Declared Variables
        Scanner scan = new Scanner(System.in);

        //Parsed data
        DataHandler handler = new DataHandler(); 
        ArrayList<Double> data = handler.parser(dataFile); //parsed data from string to double;

        //Get the user salting range
        System.out.println("Please enter the following salting ranges (number; LowerBound < UpperBound)");
        System.out.print("Lower Bound: ");
        int lowerBound = scan.nextInt();
        System.out.print("Upper Bound: ");
        int upperBound = scan.nextInt();

        //salted data and stringed data
        ArrayList<Double> saltedData = salter(data, lowerBound, upperBound);
        ArrayList<String> stringedValue;

        //Write and Exports the data
        stringedValue = handler.writer(saltedData); //convert data to string and added to data structure of type string
        handler.exporter(stringedValue, dataFile.toString() + "Salted");

        return dataFile.toString() + "Salted"; //return file name
    }
}