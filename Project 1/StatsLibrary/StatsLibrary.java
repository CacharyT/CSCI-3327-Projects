/*
 * Cachary Tolentino
 * StatsLibrary is a collection of statistical functions
 */

//Imports
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
     * The function will return the variance of the given set of numbers
     * @param: userInputNumber an array of integers
     * @return: variance the variance of the (sample) of the array of integers
     */
    public double getVariance(int[] userInputNumber){

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

        return variance;

    }

    /*
     * The function will return the standard deviation (sample) of the provided array of integers
     * @param: userInputNumber an array of integers
     * @return: std the standard deviation (sample) of the array of integers
     */
    public double getStandardDeviation(int[] userInputNumber){

        //Calculate variance
        double variance = getVariance(userInputNumber);

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

            //Check if positive, return true
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
     * The function will calculate the probability of mutually exclusive events
     * @param
     * @return
     */
    public double probMutualExclusive(String[] A, String[] B, double probA, double probB){

        //Check first if the events are mutually exclusive (A AND B = empty set; no same values)
        int correctCounter = 0;
        for(String itemA : A){
            for(String itemB : B){
                if(itemA.equals(itemB)){
                    correctCounter++;
                    break;
                }
            }
        }

        //If all item are found, then add probabilites
        if(correctCounter == 0){
            return probA + probB;
        } else{
            return 0; //not mutually exclusive
        }

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
     * The function will calculate the condtional probability (P(A|B))
     * @param probAB a double value representing the probability of A and B
     * @param probB a double value representing the probability of B
     * @return a double value which is the condtional probability
     */
    public double conditionalProbabilityAB(double probAB, double probB){
        return probAB/probB;
    }

    /*
     * The function will calculate the condtional probability (P(B|A))
     * @param probAB a double value representing the probability of A and B
     * @param probB a double value representing the probability of A
     * @return a double value which is the condtional probability
     */
    public double conditionalProbabilityBA(double probAB, double probA){
        return probAB/probA;
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
     * Multiplicative Law of Probability
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
     * General Addition Rule
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
     * The function will check if the partition of a set is part of a sample space (Definition 2.11)
     * @param space an arraylist of string represting all possible values
     * @param setsOfValues an arraylist of arraylist of strings which is the subsets of the space
     * @return a boolean value which decides if it its a partition of s
     */
    public Boolean partitionOfSpace(ArrayList<String> space, ArrayList<ArrayList<String>> setsOfValue){

        //Order them first
        Collections.sort(space);

        //Union all of the given set values
        ArrayList<String> unioned = new ArrayList<>();
        for(ArrayList<String> set : setsOfValue){
            for(String value : set){
                if(!unioned.contains(value)){
                    unioned.add(value);
                }
            }
        }
        Collections.sort(unioned);

        //First check if part of s when unioned
        if(space.equals(unioned)){
            Boolean empty = true;
            //Check if intersect are empty sets
            for(int i = 0; i < setsOfValue.size() - 1; i++){
                if(setsOfValue.get(i).equals(setsOfValue.get(i+1))){
                    empty = false;
                    break;
                }
            }
            if(empty){
                return true;
            }
        }
        return false;
    }

    /*
     * The function will calculate the value for the theorem of total probability
     * @param probAB a double array containing the probabilities of each A given B 
     * @param probB a double array containing the probabilities of each B
     * @return totalProbability a double value of the total probability
     */
    public double theoremOfTotalProbability(double[] probAB, double[] probB){

        double totalProbability = 0;
        for(int i = 0; i < probAB.length; i++){
            totalProbability += probAB[i] * probB[i];
        }

        return totalProbability;
    }


    /*
     * The function will calculate the porbability based on Baye's theorem
     * @param probA a double value which is the probability of A
     * @param probB a double value which is the probability of B
     * @return the probabilty of P(B|A)
     */
    public double bayesTheorem(double probA, double probB){
        double probAB = conditionalProbabilityAB(probA, probB);
        return (probAB*probB)/probA;
    }

    /*
     * The function will caclulate the expected value of a random variable or function of a random variable
     * @param y an array of double (random variable)
     * @param probabiltiies an array of double (probability for each random variable)
     * @return the expected random value (double)
     */
    public double expectedRandom(double[] y, double[] probabilites){
        double totalProbability = 0;
        for(int i = 0; i < y.length; i++){
            totalProbability += y[i]*probabilites[i];
        }
        return totalProbability;
    }

    /*
     * The function will caclulate the variance of a random variable
     * @param y an array of double (random variable)
     * @param probabiltiies an array of double (probability for each random variable)
     * @return the varaince of the random value (double)
     */
    public double varianceRandom(double[] y, double[] probabilites){
        double expected = expectedRandom(y, probabilites);
        double totalProbability = 0;
        for(int i = 0; i < y.length; i++){
            totalProbability += Math.pow(y[i],2)*probabilites[i];
        }
        return totalProbability - Math.pow(expected,2);
    }

    /*
     * The function will calculate the standard deviation for a random variable
     * @param y an array of double (random variable)
     * @param probabiltiies an array of double (probability for each random variable)
     * @return the standard deviation the random value (double)
     */
    public double standardDeviationRandom(double[] y, double[] probabilites){
        return Math.sqrt(varianceRandom(y, probabilites));
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

    /*
     * The function will calculate the expected value for a binomial distribution
     * @param n the number of trials (int)
     * @param p the probability of success (double)
     * @return a double value (the expected value)
     */
    public double expectedBinomial(double n, double p){
        return n*p;
    }

    /*
     * The function will calculate the variance for a binomial distribution
     * @param n the number of trials (int)
     * @param p the probability of success (double)
     * @param q the probability of failure (double)
     * @return a double value (the variance)
     */
    public double varianceBinomial(double n, double p, double q){
        return n*p*q;
    }

    /*
     * The function will calculate the standard deviation for a binomial distribution
     * @param n the number of trials (int)
     * @param p the probability of success (double)
     * @param q the probability of failure (double)
     * @return a double value (the standard deviation value)
     */
    public double standardDeviationBinomial(double n, double p, double q){
        return Math.sqrt(varianceBinomial(n, p, q));
    }

    /*
     * The function will calculate the geometric probability distribution
     * @param p a double value (probability of failure)
     * @param y a double value (# of trials)
     * @param q a double value (probability of success)
     * @return the geometric probability distribution (double)
     */
    public double geometricDistribution(double p, double y, double q){
        return Math.pow(q, y-1)*p;
    }

    /*
     * The function will calculate expected of the geometric probability distribution
     * @param p a double value (probability of failure)
     * @return the expected of the geometric probability distribution (double)
     */
    public double geometricExpected(double p){
        return 1/p;
    }

    /*
     * The function will calculate variance of the geometric probability distribution
     * @param p a double value (probability of failure)
     * @return the variance of the geometric probability distribution (double)
     */
    public double geometricVariance(double p){
        return (1 - p)/Math.pow(p,2);
    }

    /*
     * The function will calculate the standard deviation geometric probability distribution
     * @param p a double value (probability of failure)
     * @return the standard deviation of the geometric probability distribution (double)
     */
    public double geometricStandardDeviation(double p){
        return Math.sqrt(geometricVariance(p));
    }

    /*
     * The function will calculate the geometric probability distribution for a success that occurs on or before the nth trial
     * @param p a double value (probability of failure)
     * @param n an int value (number of trials)
     * @return the probability 
     */
    public double onBeforeGeometric(double p, int n){
        return 1 - Math.pow((1 - p),n);
    }

    /*
     * The function will calculate the geometric probability distribution for a success that occurs before the nth trial
     * @param p a double value (probability of failure)
     * @param n an int value (number of trials)
     * @return the probability 
     */
    public double beforeGeometric(double p, int n){
        return 1 - Math.pow((1 - p), n - 1);
    }

    /*
     * The function will calculate the geometric probability distribution for a success that occurs on or after the nth trial
     * @param p a double value (probability of failure)
     * @param n an int value (number of trials)
     * @return the probability 
     */
    public double onAfterGeometric(double p, int n){
        return Math.pow((1 - p), n - 1);
    }

    /*
     * The function will calculate the geometric probability distribution for a success that occurs after the nth trial
     * @param p a double value (probability of failure)
     * @param n an int value (number of trials)
     * @return the probability 
     */
    public double afterGeometric(double p, int n){
        return Math.pow((1 - p), n);
    }

    /*
     * The function will calculate the hyper geometric probability distribution 
     * @param n an int value (number of selected)
     * @param y an int value (number desired)
     * @param r an int value (total number of desired kind)
     * @param N an int value (total size)
     * @return a BigInteger value of the probability
     */
    public BigDecimal hyperGeometricDistribution(int n, int y, int r, int N){

        //Calculate the numerator
        BigInteger selectFromR = combination(r, y); //# of ways to select from r
        BigInteger selectFromNMinusR = combination(N-r, n-y); //#of ways to select from N-r
        BigInteger numerator = selectFromR.multiply(selectFromNMinusR);

        //Calculate denominator
        BigInteger denominator = combination(N, n);

        if(denominator.equals(BigInteger.ZERO)){
            System.out.println("Denominator is zero.");
            return null;
        } else{
            return new BigDecimal(numerator).divide(new BigDecimal(denominator), 5, RoundingMode.HALF_UP);
        }
    }

    /*
     * The function will calculate the expected value for a hyper geometric distribution
     * @param n an int value (number of selected)
     * @param r an int value (total number of desired kind)
     * @param N an int value (total size)
     * @return the expected value
     */
    public BigDecimal hyperExpected(int n, int r, int N){

        return new BigDecimal((double) (n*r) / (double) N);

    }

    /*
     * The function will calculate the variance value for a hyper geometric distribution
     * @param n an int value (number of selected)
     * @param r an int value (total number of desired kind)
     * @param N an int value (total size)
     * @return the variance value
     */
    public BigDecimal hyperVariance(int n, int r, int N){

        return new BigDecimal((double) n * ((double) r / (double) N) * ((double) (N-r)/ (double) N) * ((double) (N-n) / (double) (N-1)));

    }

    /*
     * The function will calculate the standard deviation value for a hyper geometric distribution
     * @param n an int value (number of selected)
     * @param r an int value (total number of desired kind)
     * @param N an int value (total size)
     * @return the standard deviation value
     */
    public BigDecimal hyperStandardDeviation(int n, int r, int N){

        return new BigDecimal(Math.sqrt(hyperVariance(n, r, N).doubleValue()));

    }

    /*
     * The function will calculate the negative binomial probability distribution 
     * @param y an int value (amount of action)
     * @param r an int value (amount of trial)
     * @param p a double value (success)
     * @param q a double value (failure)
     * @return the probability
     */
    public BigDecimal negativeBinomialDistribution(int y, int r, double p, double q){

        double exponent1 = Math.pow(p,r);
        double exponent2 = Math.pow(q, y-r);
        BigInteger combined = combination(y-1, r-1);
        return new BigDecimal(combined).multiply(BigDecimal.valueOf(exponent1)).multiply(BigDecimal.valueOf(exponent2));

    }

    /*
     * The function will calculate the expected value for a negative binomial probability distribution 
     * @param r an int value (amount of trial)
     * @param p a double value (success)
     * @return the expected value
     */
    public double expectedNegativeBinomial(int r, double p){
        
        return (double) r / p;

    }

     /*
     * The function will calculate the variance value for a negative binomial probability distribution 
     * @param r an int value (amount of trial)
     * @param p a double value (success)
     * @return the variance 
     */
    public double varianceNegativeBinomial(int r, double p){

        return ((double) r*(1-p))/Math.pow(p,2);

    }

     /*
     * The function will calculate the standard deviation value for a negative binomial probability distribution 
     * @param r an int value (amount of trial)
     * @param p a double value (success)
     * @return the standard deviation
     */
    public double standardDeviationNegativeBinomial(int r, double p){

        return Math.sqrt(varianceNegativeBinomial(r, p));

    }

}