/*
 * Cachary Tolentino
 * The JATest is a class meant to test the updated variant of the pss program now using JfreeCharts and Apache Commons Math
 */

//Imports

package com.example;

import java.io.File;

public class JATest {
    public static void main(String[] args) {
        JAPlotter plot = new JAPlotter(); //plotter object
        String fileName = plot.plotData();

        JASalter salt = new JASalter(new File(fileName)); //salter object
        String saltedFile = salt.salterData();

        JASmoother smooth = new JASmoother(new File(saltedFile)); //smoother object
        smooth.smoothenData();
    }
}
