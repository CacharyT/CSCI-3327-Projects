/*
 * Cachary Tolentino
 * The TestSetOperations class will call the set operation methods from SetOperations:
 * 1) Union
 * 2) Intersect
 * 3) Complement
 * and test their results
 */

//Imports

import java.util.ArrayList;

public class TestSetOperations{
    public static void main(String[] args) {

        //SetOperations Object
        SetOperations operator = new SetOperations();
        
        //ArrayList object
        ArrayList<String> someArrayList1 = new ArrayList<>();
        ArrayList<String> someArrayList2 = new ArrayList<>();
        ArrayList<String> someArrayList3 = new ArrayList<>();
        ArrayList<String> someArrayList4 = new ArrayList<>();

        //Inserting days of the week to list1
        someArrayList1.add("Monday");
        someArrayList1.add("Tuesday");
        someArrayList1.add("Wednesday");
        someArrayList1.add("Thursday");
        someArrayList1.add("Friday");
        someArrayList1.add("Saturday");
        someArrayList1.add("Sunday");


        //Inserting random strings to list2
        someArrayList2.add("Apple");
        someArrayList2.add("Tuesday");
        someArrayList2.add("Weather");
        someArrayList2.add("HELLO!!");
        someArrayList2.add("YAY!!!!");
        someArrayList2.add("bored");
        someArrayList2.add("Sunday");


        //Inserting all the values to list3
        someArrayList3.add("Monday");
        someArrayList3.add("Tuesday");
        someArrayList3.add("Wednesday");
        someArrayList3.add("Thursday");
        someArrayList3.add("Friday");
        someArrayList3.add("Saturday");
        someArrayList3.add("Sunday");
        someArrayList3.add("Apple");
        someArrayList3.add("Tuesday");
        someArrayList3.add("Weather");
        someArrayList3.add("HELLO!!");
        someArrayList3.add("YAY!!!!");
        someArrayList3.add("bored");
        someArrayList3.add("Sunday");

        //Inserting some days and bored to list4
        someArrayList4.add("Monday");
        someArrayList4.add("Wednesday");
        someArrayList4.add("bored");


        System.out.println("The union: " + operator.Union(someArrayList1, someArrayList2));
        System.out.println("The intersect: " + operator.Intersect(someArrayList1, someArrayList2));
        System.out.println("The complement of list4: " + operator.Complement(someArrayList3, someArrayList4));
    }
}