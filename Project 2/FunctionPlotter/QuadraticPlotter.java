/*
 * Cachary Tolentino
 * The QuadraticPlotter class will allow the user to export the x and y value for the quadratic function for an x amount of times
 */

//Imports
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class QuadraticPlotter {
    
    //Global Variable(s)
    
    /*
     * Default COnstructor
     * @param none
     * @return none
     */
    public QuadraticPlotter(){
        //nothing to initalize
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
     * @return none
     */
    public void generateCSVFile(){
        //Declared variables
        Scanner scan = new Scanner(System.in);
        ArrayList<String> values = new ArrayList<>();
        values.add("X, Y"); //header value

        //Get user input for x, a, b, c values
        System.out.println("Please enter the following values (number)");
        System.out.print("Trial Amount: ");
        double trial = scan.nextDouble();
        System.out.print("a: ");
        double a = scan.nextDouble();
        System.out.print("b: ");
        double b = scan.nextDouble();
        System.out.print("c: ");
        double c = scan.nextDouble();
        System.out.print("Name of file: ");
        String fileName = scan.next();

        //Add all x and y value to the data structure
        for(int x = 0; x < trial; x++){
            double y = quadraticFunction(x, a, b, c);
            values.add(x + ", " + y);
        }

        //Export the data structure to a CSV file
        try {
            FileWriter writer = new FileWriter(fileName + ".csv");
            for(String dataLine : values){
                writer.write(dataLine + "\n"); //each data pair gets their own row
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}