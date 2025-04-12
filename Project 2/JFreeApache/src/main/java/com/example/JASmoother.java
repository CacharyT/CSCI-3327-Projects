/*
 * Cachary Tolentino'
 * The JASmoother class is the same as the Smoother, but will be using JFreeCharts to create graphs and Apache Commons to handle rolling average
 */

//Imports
package com.example;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JASmoother {
    //Global Variables
    private File dataFile;
    private XYSeries graphData;
    private XYSeriesCollection dataset;

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @return none
     */
    public JASmoother(File inputFile){
        dataFile = inputFile;
        graphData = new XYSeries("Data");
        dataset = new XYSeriesCollection();
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
        DescriptiveStatistics stats = new DescriptiveStatistics();
        stats.setWindowSize(windowValue); //setup the window value for Apache

        //Calculate the average based on the window value
        for (int i = 0; i < data.size(); i++) {
            if(i%2 != 0){ //ignore x
                double average = rollingAverage(data, i, windowValue, stats);
                newData.add(average);
            } else{
                newData.add(data.get(i));
            }
        }

        //add x and y value to the series object
        for(int i = 0; i < newData.size() - 1; i+=2){
            graphData.add(newData.get(i), newData.get(i + 1));
        }
        dataset.addSeries(graphData); //add the series (data) to the dataset object

        return newData;
    }

    /*
    * The function will return the rolling average (uses Apache for mean)
    * @param data - an arraylist od double
    * @param middle - an int value
    * @param windowValue - an int value
    * @param stats - a descriptivestats object
    * @return a double value
    */
    public double rollingAverage(ArrayList<Double> data, int middle, int windowValue, DescriptiveStatistics stats){
        //Array positioning
        int left = (windowValue - 1) / 2;
        int right = (windowValue - 1) - left;
        int start = Math.max(0, middle - left);
        int end = Math.min(middle + right, data.size() - 1);

        stats.clear();//make sure memory is clean for each calculation of average

        //Making the subset containing all Y values to be averaged
        for(int i = start; i <= end; i++){
            if(i%2 != 0){//ignore X values
                stats.addValue(data.get(i));
            }
        }
        
        return stats.getMean();
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

        //Graph the data
        JFrame frame = handler.grapher(dataFile.toString() + " Smoothened", dataset);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows program to exit

        //Write and Exports the data
        stringedValue = handler.writer(smoothenData); //convert data to string and added to data structure of type string 
        handler.exporter(stringedValue, dataFile.toString() + "Smoothened");
    }
}