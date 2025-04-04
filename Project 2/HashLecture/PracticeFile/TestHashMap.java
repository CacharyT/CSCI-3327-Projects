/*
 * Cachary Tolentino
 * The TestHashMap class is another class elicited file (learned more about HashMaps)
 */

//Imports
import java.util.ArrayList;
import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        //TreeMap<String, Integer> hm = new TreeMap<>(); --> alphabetize the keys unlike a HashMap...BUT lose some speed

        hm.put("Tom", 1);
        hm.put("Brandon", 22);
        hm.put("Lisa", 3);
        hm.put("Brandon", 2);
        hm.put("Jerry", 3);

        //sometimes helpful to have a "super" data structure
        HashMap<String, ArrayList<String>> shm = new HashMap<>(); //becomes like a many to one mapping, instead of one to one mapping using <string, integer>
        //Can even have another HashMap inside the HashMap

        System.out.println("The size of my map is: " + hm.size()); //map can only have unique keys 
        System.out.println("What is Lisa? " + hm.get("Lisa")); //same value as Jerry

        for(String singleValue : hm.keySet()){ //prints every key
            System.out.println(singleValue);
        }
    }
}
