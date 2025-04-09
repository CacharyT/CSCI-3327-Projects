/*
 * Cachary Tolentino
 */

//Imports
package com.example;
import javax.swing.JFrame;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, "Sales", "January");
        dataset.addValue(150, "Sales", "February");
        dataset.addValue(180, "Sales", "March");
        dataset.addValue(260, "Sales", "April");
        dataset.addValue(300, "Sales", "May");

        JFreeChart chart = ChartFactory.createLineChart("Monthly Sales", "Month", "Sales",dataset);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setContentPane(chartPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




        DescriptiveStatistics stats = new DescriptiveStatistics();
        // Add some data to the statistics object
        stats.addValue(10);
        stats.addValue(20);
        stats.addValue(30);
        
        // Compute and print basic statistics
        System.out.println("Mean: " + stats.getMean());
        System.out.println("Variance: " + stats.getVariance());
    }
}
