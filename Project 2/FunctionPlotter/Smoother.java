/*
 * Cachary Tolentino
 * The smoother class will take in a data file and smoothen the data then it will generate the smoothen data to a new csv file
 */

//Imports
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Smoother {

    //Global Variables
    private File dataFile;
    private double windowValue;

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @param value a double value
     * @return none
     */
    public Smoother(File inputFile, double value){
        dataFile = inputFile;
        windowValue = value;
    }

    /*
     * The function will smoothen the data
     * @param data an arraylist of integers
     * @return newData an array list of double values
     */
    public ArrayList<Double> smoother(ArrayList<Double> data){
        //Declared variabales
        ArrayList<Double> newData = new ArrayList<>();

        //Calculate the average based on the window value
        for (int i = 0; i < data.size(); i++) {
            int start = (int) Math.max(0, i - windowValue); //get value for left side
            int end = (int) Math.min(data.size() - 1, i + windowValue); //get value for right side
            double total = 0;
            int count = 0;

            //total the value within the left, middle(data point), and right side
            for (int j = start; j <= end; j++) {
                total += data.get(j);
                count++;
            }

            newData.add(total / count); //replce old value with the average value
        }
        return newData;
    }

    /*
     * The function will smoothen the data provided and generate a csv file with the new data
     * @param none
     * @return none
     */
    public void smoothenData(){
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

        //The smoothened data
        ArrayList<Double> saltedData = smoother(data);

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
        exporter.exporter(stringedValue, dataFile.toString() + "Smoothened");
    }
}