/*
 * Cachary Tolentino
 * The SetOperations class will contain functions that can 
 * 1) Union two array list 
 * 2) intersect two array lists 
 * 3) returns the complement of an array list
 * This class will only work for ArrayLists containing Strings
 */

//Imports
import java.util.*;

public class SetOperations {

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

}
