/*
 * Cachary Tolentino'
 * The JASalter class is the same as the Salter, but will be using JFreeCharts to create graphs and Apache Commons Math for random generation
 */

//Imports
package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JASalter {
    //Global Variables
    private File dataFile;
    private XYSeries graphData;
    private XYSeriesCollection dataset;

    /*
     * Constructor with parameters
     * @param inputFile a file value
     * @return none
     */
    public JASalter(File inputFile){
        dataFile = inputFile;
        graphData = new XYSeries("Data");
        dataset = new XYSeriesCollection();
    }

    /*
     * The function will salt the data
     * @param data an arraylist of integers
     * @param lowerBound an int value
     * @param upperBound an int value
     * @return newData an array list of double values
     * @source: https://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/index.html
     */
    public ArrayList<Double> salter(ArrayList<Double> data, int lowerBound, int upperBound){
        //Declared variabales
        RandomDataGenerator random = new RandomDataGenerator(); //uses Apache built in random number generator
        ArrayList<Double> newData = new ArrayList<>();

        for(int i = 0; i < data.size(); i++){
            if(i%2 != 0){ //skip x values
                int saltValue = random.nextInt(lowerBound, upperBound); //ensures different salt value per y value (Apache randomizer)
                int operation = random.nextInt(0,1); //Apache randomizer
                if(operation == 0){ //add
                    newData.add(data.get(i) + saltValue);
                } else{ //subtract
                    newData.add(data.get(i) - saltValue);
                }
            } else{
                newData.add(data.get(i)); //x values remain unchanged
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
     * The function will salt the given data set and generate it as a new csv file
     * @param none
     * @return a String value
     */
    public String salterData(){
        //Declared Variables
        Scanner scan = new Scanner(System.in);

        //Parsed data
        JADataHandler handler = new JADataHandler(); 
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

        //Graph the data
        handler.grapher(dataFile.toString() + " Salted", dataset);

        return dataFile.toString() + "Salted"; //return file name
    }
}