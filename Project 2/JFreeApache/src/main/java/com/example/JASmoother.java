package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class JASmoother {
    //Global Variables
    private File dataFile;

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @return none
     */
    public JASmoother(File inputFile){
        dataFile = inputFile;
    }

    /*
     * The function will smoothen the data
     * @param data an arraylist of integers
     * @param windowValue an int value
     * @return newData an array list of double values
     */
    public ArrayList<Double> smoother(ArrayList<Double> data, int windowValue){
        //Declared variabales
        ArrayList<Double> newData = new ArrayList<>();

        //Calculate the average based on the window value
        for (int i = 0; i < data.size(); i++) {
            if(i%2 != 0){ //ignore x
                int start = (int) Math.max(0, i - windowValue); //leftmost value position (can't go lower than 0)
                int end = (int) Math.min(data.size() - 1, i + windowValue); //rightmost value position (can't go beyond size of data)
                double total = 0;
                int count = 0;
    
                //total the values starting from the left to the right (including focus point/value meant to be changed with average)
                for (int j = start; j <= end; j++) {
                    total += data.get(j);
                    count++;
                }
                newData.add(total / count); //replace old value with the average value

            } else{
                newData.add(data.get(i));
            }
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
        Scanner scan = new Scanner(System.in);
        JADataHandler handler = new JADataHandler(); 
        ArrayList<Double> data = handler.parser(dataFile); //parsed data from string to double

        //Get the user window value
        System.out.println("Please enter the following value (number)");
        System.out.print("Window Value: ");
        int windowValue = scan.nextInt();

        //The smoothened and stringed data
        ArrayList<Double> smoothenData = smoother(data, windowValue);
        ArrayList<String> stringedValue;

        //Write and Exports the data
        stringedValue = handler.writer(smoothenData); //convert data to string and added to data structure of type string 
        handler.exporter(stringedValue, dataFile.toString() + "Smoothened");
    }
}
