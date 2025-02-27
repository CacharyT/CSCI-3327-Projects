/*
 * Cachary Tolentino
 * StatsLibrary is a collection of statistical functions
 * Last updatded: 1/25/2025
 */

//Imports
import java.math.BigDecimal;
import java.math.BigInteger;
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


    /*
     * The function will return the union of the given pair arraylists
     * @param: list1 an  arraylist of strings
     * @param: list2 an  arraylist of strings
     * @return: unionList an arraylist of string values containing the union of the provided pair of arraylist
     */
    public ArrayList<String> Union(ArrayList<String> list1, ArrayList<String> list2){

        //Declared variables
        ArrayList<String> unionList = new ArrayList<>();

        for(String word1 : list1){
            if(!unionList.contains(word1)){ //Checks the arraylist if the current word is already in it, otherwise add it
                unionList.add(word1);
            }
        }

        for(String word2 : list2){
            if(!unionList.contains(word2)){ //Checks the arraylist if the current word is already in it, otherwise add it
                unionList.add(word2);
            }
        }

        return unionList;
    }

    /*
     * The function will return the intersect of the given pair of arraylists
     * @param: list1 an  arraylist of strings
     * @param: list2 an  arraylist of strings
     * @return: result an arraylist of string values containing the intersect of the provided pair of arraylist
     */
    public ArrayList<String> Intersect(ArrayList<String> list1, ArrayList<String> list2){

        //Declared variables
        ArrayList<String> intersectList = new ArrayList<>();

        //Find the longest list
        int list1Length = list1.size();
        int list2Length = list2.size();

        if(list1Length >= list2Length){ //Uses list1 as the length of the list since it contains the most values
            for(String word : list1){
                if(list2.contains(word) && !intersectList.contains(word)){ //checks if the word is in both list1 and list2 but not in the list already
                    intersectList.add(word);
                }
            }
        } else{
            for(String word : list2){ //Uses list2 as the length of the list since it contains the most values
                if(list1.contains(word) && !intersectList.contains(word)){ //checks if the word is in both list1 and list2 but not in the list already
                    intersectList.add(word);
                }
            }
        }

        return intersectList;
    }

    /*
     * The function will return the complement of the given arraylist
     * @param: allList an  arraylist of strings
     * @param: list1 an  arraylist of strings
     * @return: result an arraylist of string values containing the complement of the provided arraylist
     */
    public ArrayList<String> Complement(ArrayList<String> allList, ArrayList<String> list1){

        //Declared variables
        ArrayList<String> complementList = new ArrayList<>();

        for(String word1 : allList){ //Adds all possible strings 
            complementList.add(word1);
        }

        for(String word2 : list1){
            if(complementList.contains(word2)){ //If the list contains the word then remove the word (emulates complement)
                complementList.remove(word2);
            }
        }

        return complementList;
    }


    /*
     * This function calculates the combination value of the given n and r values
     * @param n an int value
     * @param r an int value
     * @return BigInteger value of the combination
     */
    public BigInteger combination(int n, int r){

        BigInteger factN = factorial(n);
        BigInteger factR = factorial(r);
        BigInteger factNMinusR = factorial(n - r);
        BigInteger combinationValue = factN.divide(factR.multiply(factNMinusR));

        return combinationValue;

    }

    /*
     * This function will calculate the permutation value of the given n and r values
     * @param n an int value
     * @param r an int value
     * @return BigInteger value of the permutation
     */
    public BigInteger permutation(int n, int r){
        
        BigInteger factN = factorial(n);
        BigInteger factNMinusR = factorial(n - r);
        BigInteger permutationValue = factN.divide(factNMinusR);

        return permutationValue;

    }

    /*
     * The function will retun the factorial value of the given number
     * @param num the number to find the factorial for
     * @return factorial value as a BigInteger type
     */
    public BigInteger factorial(int num){
        
        //Checks if the current num is 0, if so, can return (base case)
        if(num == 0){
            return BigInteger.ONE;
        }

        return BigInteger.valueOf(num).multiply(factorial(num - 1)); //recursion case (decrease num each call)
    }


    /*
     * The function will compute the given value based on the mn rule
     * @param m an int value
     * @param n an int value
     * @return: the value (m * n)
     */
    public double getMNRule(int m, int n){

        //Return the value (m * n)
        return (double) (m * n);

    }


    /*
     * This function will compute the given value based on the multinomial coefficient formula
     * @param n an int value
     * @param nums an array of int values
     * @return: the value of the multinomial coefficient
     */
    public BigInteger getMultinomialCoefficient(int n, int[] nums){

        //Numerator
        BigInteger numerator = factorial(n);

        //deonominator
        BigInteger denominator = BigInteger.ONE;
        for(int num : nums){
            denominator = denominator.multiply(factorial(num));
        }

        return numerator.divide(denominator);

    }



    /*
    * This function will allow the user to check if a given subset/event fulfills the probability axiom 1
    * @param space an arraylist of strings
    * @param subset an arraylist of strings
    * @param subsetProbabilities an array of double
    * @return boolean value determining if the given subset fulfills probability axiom 1
    */
    public Boolean checkAxiomOne(ArrayList<String> space, ArrayList<String> subset, double[] subsetProbabilities){

        //Count the numbers of element in subset appearing in the space
        int instanceCounter = 0;
        for(String spaceElement : space){
            for(String subsetElement : subset){
                if(spaceElement.equals(subsetElement)){
                    instanceCounter++;
                    break;
                }
            }
        }

        //Check if all elements in the subset appear in the space
        if(instanceCounter == subset.size()){

            //Sum the amount of probabilities 
            double totalProbability = 0;
            for(double num : subsetProbabilities){
                totalProbability+=num;
            }

            //Check if sum equals to one, return true
            if(totalProbability >= 0){
                return true;
            }

        }

        //otherwise return false
        return false;

    }

    /*
    * This function will allow the user to check if the probability of a given space is equal to 1
    * @param spaceProbabilities an array of double
    * @return boolean value determining if the given probabilities of space fulfills probability axiom 2
    */
    public Boolean checkAxiomTwo(double[] spaceProbabilities){

        //Sum the amount of probabilities 
        double totalProbability = 0;
        for(double num : spaceProbabilities){
            totalProbability+=num;
        }

        //Check if sum equals to one, return true
        if(totalProbability == 1){
            return true;
        }


        //otherwise return false
        return false;
    }

    /*
    * This function will allow the user find the total probability of an event based on the Probability Axiom 3
    * @param space an arraylist of strings
    * @param subset an arraylist of strings
    * @param subsetProbabilities an array of double
    * @return a double value which is the porbability of the given event
    */
    public double checkAxiomThree(ArrayList<String> space, ArrayList<String> subset, double[] subsetProbabilities){

        //Count the numbers of element in subset appearing in the space
        int instanceCounter = 0;
        for(String spaceElement : space){
            for(String subsetElement : subset){
                if(spaceElement.equals(subsetElement)){
                    instanceCounter++;
                    break;
                }
            }
        }

        //Check if all elements in the subset appear in the space
        if(instanceCounter == subset.size()){

            //Sum the amount of probabilities 
            double totalProbability = 0;
            for(double num : subsetProbabilities){
                totalProbability+=num;
            }

            return totalProbability/ (double) space.size();

        }

        //otherwise return null (some elements were not in the space)
        return 0;
    }

    /*
     * The function will allow the user to check if the given events, A and B are independent
     * @param eventAProbabilities an array of double
     * @param eventBProbabilities an array of double
     * @param eventAAndBProbabilities an array of double
     * @return boolean value determining of it is independent
     */
    public Boolean checkIfIndependent(double eventAProbabilities, double eventBProbabilities, double eventAAndBProbabilities){

        //Calculate P(A)
        double probabilityOfA = eventAProbabilities;

        //Calculate P(B)
        double probabilityOfB = eventBProbabilities;

        //Calculate P(A AND B)
        double probabilityOfAAndB = probabilityOfA * probabilityOfB;

        //Calculate P(A|B)
        double probabilityOfAGivenB = eventAAndBProbabilities / probabilityOfB;

        //Calculate P(B|A)
        double probabilityOfBGivenA = eventAAndBProbabilities / probabilityOfA;

        if(probabilityOfAGivenB == probabilityOfA){ //Check if P(A|B) = P(A)
            return true;
        } else if(probabilityOfBGivenA == probabilityOfB){ //Check if P(B|A) = P(B)
            return true;
        } else if(probabilityOfAAndB == eventAAndBProbabilities){ //Check if P(A AND B) = P(A) P(B)
            return true;
        }

        //otherwise, return false
        return false;
    }

    /*
     * The function will allow the user to check if the given events, A and B are dependent
     * @param eventAProbabilities an array of double
     * @param eventBProbabilities an array of double
     * @param eventAAndBProbabilities an array of double
     * @return boolean value determining of it is independent
     */
    public Boolean checkIfDependent(double eventAProbabilities, double eventBProbabilities, double eventAAndBProbabilities){

        //Returns the opposite of the return value of checkIfIndependent
        return !checkIfIndependent(eventAProbabilities, eventBProbabilities, eventAAndBProbabilities);

    }

    /*
     * This function will calculate the intersection of given events, depending on if it is dependent or independent
     * @param eventAProbabilities an array of double
     * @param eventBProbabilities an array of double
     * @param eventAAndBProbabilities an array of double
     * @return a double value
     */
    public double calculateInDependentOrDependentIntersection(double eventAProbabilities, double eventBProbabilities, double eventAAndBProbabilities){

        if(checkIfDependent(eventAProbabilities, eventBProbabilities, eventAAndBProbabilities)){ //Dependent events P(A AND B) = P(A)P(B|A)

            //Calculate P(B|A)
            double probabilityOfBGivenA = eventAAndBProbabilities / eventAProbabilities;

            double dependentProbability = (eventAProbabilities * probabilityOfBGivenA);

            System.out.println("The dependent intersection of " + eventAProbabilities + " and " + eventBProbabilities + " is " + dependentProbability);

            return dependentProbability;


        } else{ //Indepedent events P(A AND B) = P(A)P(B)

            double independentProbability = (eventAProbabilities * eventBProbabilities);

            System.out.println("The independent intersection of " + eventAProbabilities + " and " + eventBProbabilities + " is " + independentProbability);

            return independentProbability;

        }

    }


    /*
     * The function will allow users to calculate the exclusive or not exclusive union of the given events
     * @param eventAProbabilities an array of double
     * @param eventBProbabilities an array of double
     * @param eventAAndBProbabilities an array of double
     * @return a double value
     */
    public double calculateExclusiveOrNotExclusiveunion(double eventAProbabilities, double eventBProbabilities, double eventAAndBProbabilities){

        double aAndBIntersection = calculateInDependentOrDependentIntersection(eventAProbabilities, eventBProbabilities, eventAAndBProbabilities);

        //Check first if exclusive P(A AND B) = 0
        if(aAndBIntersection == 0){

            double exclusiveValue = (eventAProbabilities + eventBProbabilities);

            System.out.println("The exclusive union of " + eventAProbabilities + " and " + eventBProbabilities + " is " + exclusiveValue);

            //Simply P(A OR B) = P(A) + P(B)
            return exclusiveValue;


        } else{

            //Simply P(A OR B) = P(A) + P(B) - P(A AND B)
            double notExclusiveValue = (eventAProbabilities + eventBProbabilities - aAndBIntersection);

            System.out.println("The not exclusive union of " + eventAProbabilities + " and " + eventBProbabilities + " is " + notExclusiveValue);

            return notExclusiveValue;

        }

    }


    /*
     * The function will will calculate the binomial distribution
     * @param p the probability of success (double)
     * @param q the probability of failure (double)
     * @param n the number of trials (int)
     * @param y the number of success (int)
     */
    public BigDecimal binomialDistribution(double p, double q, int n, int y){
        double exponent1 = Math.pow(p,y);
        double exponent2 = Math.pow(q, (n-y));
        return new BigDecimal(combination(n, y)).multiply(BigDecimal.valueOf(exponent1)).multiply(BigDecimal.valueOf(exponent2));
    }


}