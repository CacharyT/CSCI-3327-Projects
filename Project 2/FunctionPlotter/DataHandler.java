/*
 * Cachary Tolentino
 * The data handler class is responsible for handling data such as parsing data (string to double), writing into a data structure, and exporting as a csv
 */

//Imports
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {
    
    //Global Variable

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public DataHandler(){
        //nothing to initialize
    }

    /*
     * The function will parse the given data into seperate x, y data and add to a data structure provided (strings)
     * @param dataFile a file object
     * @return return data a arraylist of doubles
     * source(split): https://www.w3schools.com/java/ref_string_split.asp
     * source(trim): https://www.w3schools.com/jsref/jsref_trim_string.asp
     */
    public ArrayList<Double> parser(File dataFile){
        //Declared Variable
        ArrayList<Double> data = new ArrayList<>();

        //Add x and y values seperately as double
        try{
            Scanner scan = new Scanner(new File(dataFile.getPath() + ".csv"));
            while(scan.hasNextLine()){

                //X,Y and Line values
                int x = 0;
                double y = 0;
                String line = scan.nextLine();
                String[] seperated = line.split(",");

                //Parse the data into seperate ints or double (x are ints because they never change values unlike Y)
                x = Integer.parseInt(seperated[0].trim());
                y = Double.parseDouble(seperated[1].trim());

                //Add data into the data structure
                data.add((double) x);
                data.add(y);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }

    /*
     * The function will write the given data into a data structure (ArrayList<String>)
     * @param data an arraylist of double values
     * @return value an arraylist of strings
     */
    public ArrayList<String> writer(ArrayList<Double> data){
        //Declared Variable
        ArrayList<String> value = new ArrayList<>();

        //Revert data into strings and write into the data structure
        for (int i = 0; i < data.size(); i += 2) {
            int xValue = data.get(i).intValue();
            double yValue = data.get(i + 1);
            value.add(xValue + ", " + yValue);
        }

        return value;
    }

    /*
     * The function will export the given data into a csv file
     * @param data an arraylist of strings
     * @param fileName a string value
     * @return none
     * source: https://www.geeksforgeeks.org/filewriter-class-in-java/ 
     */
    public void exporter(ArrayList<String> data, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName + ".csv");
            for(String dataLine : data){
                writer.write(dataLine + "\n"); //each data pair gets their own row
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
