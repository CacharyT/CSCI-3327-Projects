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
                int saltValue = lowerBound + random.nextInt(upperBound - lowerBound);
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
    public String saltData(){
        //Declared variables
        ArrayList<Double> data = new ArrayList<>();
        ArrayList<Integer> xData = new ArrayList<>();

        //Add x and y values seperately as int or double
        try{
            Scanner scan = new Scanner(new File(dataFile.getPath() + ".csv"));
            while(scan.hasNextLine()){

                //X,Y and Line values
                int x = 0;
                double y = 0;
                String line = scan.nextLine();
                String[] seperated = line.split(",");

                //Parse the data into seperate ints
                x = Integer.parseInt(seperated[0].trim());
                y = Double.parseDouble(seperated[1].trim());

                //Add data into the data structure
                xData.add(x);
                data.add((double) x);
                data.add(y);
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        //The salted data
        ArrayList<Double> saltedData = salter(data);

        //stringed data
        ArrayList<String> stringedValue = new ArrayList<>();

        //Revert salted data into strings
        for (int i = 0; i < saltedData.size(); i += 2) {
            int xValue = 0;
            if(i == 0){
                xValue = xData.get(i);
            } else{
                xValue = xData.get(i/2);
            }
            double yValue = saltedData.get(i + 1);
            stringedValue.add(xValue + ", " + yValue);
        }

        //Exports the data
        DataExporter exporter = new DataExporter();
        exporter.exporter(stringedValue, dataFile.toString() + "Salted");

        return dataFile.toString() + "Salted";
    }
}
