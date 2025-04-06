/*
 * Cachary Tolentino
 * The TestPlotter class will test the functions of the quadraticplotter, salter, and smoother classes
 */

//Imports
import java.io.File;

public class TestPlotter {
    public static void main(String[] args) {
        QuadraticPlotter plot = new QuadraticPlotter(); //plotter object
        String fileName = plot.plotData();

        Salter salt = new Salter(new File(fileName)); //salter object
        String saltedFile = salt.salterData();

        Smoother smooth = new Smoother(new File(saltedFile)); //smoother object
        smooth.smoothenData();
    }
}