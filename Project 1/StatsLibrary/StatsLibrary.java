/*
 * Cachary Tolentino
 * StatsLibrary is a collection of statistical functions
 * Last updatded: 1/25/2025
 */

//Imports
import java.util.*;

public class StatsLibrary {

    /*
     * The function will return the mean value of the provided array of integers
     * @param: userInputNumber an array of integers
     * @return: result the mean of the array of integers
     */
    public double getMean(int[] userInputNumber){

        //Declared variables
        int total = 0;

        for(int singleNumber: userInputNumber){ //sums all the values in the array
            total = total + singleNumber;
        }

        double result = (double) total / userInputNumber.length; //casted total to take care of int division

        return result;
    }


    /*
     * The function will return the median value of the provided array of integers
     * @param: userInputNumber an array of integers
     * @return: result the median of the array of integers
     */
    public double getMedian(int[] userInputNumber){

        //Declared variables
        int length = userInputNumber.length;

        //Sort the given array into ascending order (using bubble sort)
        bubbleSort(userInputNumber);

        //Check middle value
        if(length % 2 == 0){ //even value - need to average two middle numbers
            return ((userInputNumber[(length / 2) - 1]) + userInputNumber[length / 2]) / (double) 2; //casted to double to take care of int division
        } else{ //odd value - return middle value
            return userInputNumber[length / 2];
        }
    }

     /*
     * The function will use bubble sort to sort the provided array into increasing order
     * @param: userInputNumber an array of integers
     * @return: none
     */
    public static void bubbleSort(int[] userInputNumber){
        for(int i = 0; i < userInputNumber.length; i++){
            for(int j = 0; j < userInputNumber.length - 1 - i; j++){ //loop for comparing
                if(userInputNumber[j] > userInputNumber[j + 1]){ //condition for swapping
                    int tempNum = userInputNumber[j];
                    userInputNumber[j] = userInputNumber[j + 1];
                    userInputNumber[j + 1] = tempNum;
                }
            }
        }
    }


    /*
     * The function will return the mode value(s) of the provided array of integers
     * @param: userInputNumber an array of integers
     * @return: modes the mode(s) of the array of integers
     */
    public Set<Integer> getMode(int[] userInputNumber){

        //Declared variables
        HashMap<Integer, Integer> numFrequency = new HashMap<>();

        //Input the given array of numbers into the hashmap
        for(int num : userInputNumber){
            if (numFrequency.containsKey(num)) { //Check if the num already exist in the hashmap if so then add to its frequency
                numFrequency.put(num, numFrequency.get(num) + 1);
            } else{ //otherwise, create a new key value pair
                numFrequency.put(num, 1);
            }
        }

        //Find the mode (most frequent number)
        Set<Integer> keys = numFrequency.keySet(); //all keys
        Set<Integer> modes = new HashSet<>(); //set that will hold the mode(s)
        Integer max = null; //temp number to find the highest frequency

        for(Integer key : keys){ //loops through each key value pair for the maximum frequency
            if (max == null || numFrequency.get(key) > numFrequency.get(max)) {
                max = key;
            }
        }

        modes.add(max); //adds the max key to the modes

        for(Integer key : keys){ //loops through again to check if there are any multiple maxes
            if (numFrequency.get(key).equals(numFrequency.get(max))) {
                modes.add(key);
            }
        }


        return modes;
    }


    /*
     * The function will return the standard deviation (sample) of the provided array of integers
     * @param: userInputNumber an array of integers
     * @return: std the standard deviation (sample) of the array of integers
     */
    public double getStandardDeviation(int[] userInputNumber){
        
        //Calculate the count
        int count = userInputNumber.length;

        //Calculate the mean
        double mean = getMean(userInputNumber);

        //Calculate the deviation from the mean
        double[] deviations = new double[count];
        for(int i = 0; i < count; i++)
        {
            deviations[i] = userInputNumber[i] - mean;
        }

        //Square the deviations
        double[] squaredDeviations = new double[count];
        for(int i = 0; i < count; i++)
        {
            squaredDeviations[i] = Math.pow(deviations[i],2);
        }

        //Sum of squared deviation
        double deviationSum = 0;
        for(int i = 0; i < count; i++)
        {
            deviationSum += squaredDeviations[i];
        }

        //Calculate variance
        double variance = deviationSum/(count - 1);

        //Calculate standard deviation
        double std = Math.sqrt(variance);

        //Return the value
        return std;
    }

}