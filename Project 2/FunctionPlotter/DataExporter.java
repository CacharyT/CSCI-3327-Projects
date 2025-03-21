/*
 * Cachary Tolentino
 * The data exporter class will simply export the given data as a csv file
 */

//Imports
import java.io.FileWriter;
import java.util.ArrayList;

public class DataExporter {
    
    //Global Variable

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public DataExporter(){
        //nothing to initialize
    }

    /*
     * The function will export the given data into a csv file
     * @param data an arraylist of strings
     * @param fileName a string value
     * @return none
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
