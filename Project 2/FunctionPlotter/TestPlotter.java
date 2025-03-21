/*
 * Cachary Tolentino
 * The TestPlotter class will test the functions of the quadraticplotter, salter, and smoother classes
 */

//Imports
import java.io.File;

public class TestPlotter {
    public static void main(String[] args) {
        QuadraticPlotter plot = new QuadraticPlotter();
        String fileName = plot.generateCSVFile();

        Salter salt = new Salter(new File(fileName), 100, 500); //add a file name getter for reuSe
        String saltedFile = salt.saltData();

        Smoother smooth = new Smoother(new File(saltedFile), 3);
        smooth.smoothenData();
    }
}