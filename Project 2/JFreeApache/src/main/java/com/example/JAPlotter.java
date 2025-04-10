/*
 * Cachary Tolentino'
 * The JAPlotter class is the same as the Quadratic Plotter, but will also use JFreeCharts to create graphs
 */

//Imports
package com.example;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JAPlotter {
    //Global Variable(s)
    private XYSeries graphData;
    private XYSeriesCollection dataset;
    
    /*
     * Default COnstructor
     * @param none
     * @return none
     */
    public JAPlotter(){
        graphData = new XYSeries("Data");
        dataset = new XYSeriesCollection();
    }

    /*
     * The function will calculate the quadratic function value
     * @param x a double value
     * @param a a double value
     * @param b a double value
     * @param c a double value
     * @return y the quadratic function value
     */
    public double quadraticFunction(double x, double a, double b, double c){
        double y = (a * Math.pow(x, 2)) + (b * x) + c;
        return y;
    }

    /*
     * The function will export the x and y values of the quadratic function given the user's a, b, and c values to a csv file
     * @param none
     * @return fileName a string value
     */
    public String plotData(){
        //Declared variables
        Scanner scan = new Scanner(System.in);
        ArrayList<String> values = new ArrayList<>();

        //Get user input for x, a, b, c values
        System.out.println("Please enter the following values (number)");
        System.out.print("Trial Amount (start at 0 and end at trial amount for x): ");
        double trial = scan.nextDouble();
        System.out.print("a: ");
        double a = scan.nextDouble();
        System.out.print("b: ");
        double b = scan.nextDouble();
        System.out.print("c: ");
        double c = scan.nextDouble();
        System.out.print("Name of file: ");
        String fileName = scan.next();

        //Add all x and y value to the data structure and series object
        for(int x = 0; x < trial; x++){
            double y = quadraticFunction(x, a, b, c);
            values.add(x + ", " + y);
            graphData.add(x, y); //add x and y value to the series object
        }
        dataset.addSeries(graphData); //add the series (data) to the dataset object

        //Export the data structure to a CSV file
        JADataHandler handler = new JADataHandler();
        handler.exporter(values, fileName);

        //Graph the data
        handler.grapher(fileName, dataset);

        return fileName; //return the name used
    }
}