/*
 * Cachary Tolentino
 * The class will test the functions of the CacharysimpleHashMap
 */

public class CacharyHashMapTester {
    public static void main(String[] args) {
        //Declared Variables
        CacharySimpleHashMap map1 = new CacharySimpleHashMap(); //initial size 10
        CacharySimpleHashMap map2 = new CacharySimpleHashMap(15);

        //Check current size of both maps
        System.out.println("Map 1 size: " + map1.getSize());
        System.out.println("Map 2 size: " + map2.getSize());

        //Adding some values to both maps
        map1.put("Kiwi");
        map1.put("Apple");
        map1.put("Mango"); //inserted same size value
        map1.put("Banana");
        map1.put("Blueberry");
        map1.put("Strawberry");
        map1.put("Pomegranate");
        map1.put("Hi");
        map1.put("Tan");
        map1.put("Inconvenience");
        map1.put("Incompatibility");

        System.out.println("Map 1 Allocation");
        map1.printMap();

        map2.put("Kiwi");
        map2.put("Apple");
        map2.put("Mango"); //inserted same size value
        map2.put("Banana");
        map2.put("Blueberry");
        map2.put("Strawberry");
        map2.put("Pomegranate");
        map2.put("Hi");
        map2.put("Tan");
        map2.put("Inconvenience");
        map2.put("Incompatibility");

        System.out.println("Map 2 Allocation");
        map2.printMap();

        //Check current size of both maps after adding values
        System.out.println("Map 1 size after populating: " + map1.getSize());
        System.out.println("Map 2 size after populating: " + map2.getSize());

        //Manual resizing
        map1.resize();
        map2.resize();
        System.out.println("Map 1 size after manually resizing: " + map1.getSize());
        System.out.println("Map 2 size after manually resizing: " + map2.getSize());

        //Check if either map contains a value (Ex: Mango or Kiwi)
        System.out.println("Does Map 1 contain Mango? " + map1.contains("Mango"));
        System.out.println("Does Map 2 contain Kiwi? " + map2.contains("Kiwi"));

        //Checking for a value that does not exist
        System.out.println("Does Map 1 contain Airplane? " + map1.contains("Airplane"));
        System.out.println("Does Map 2 contain Airplane? " + map2.contains("Airplane"));

        //Check if map can remove a value
        System.out.println("Does Map 1 contain Kiwi? " + map1.contains("Kiwi"));
        map1.remove("Kiwi");
        System.out.println("Does Map 1 contain Kiwi after removal? " + map1.contains("Kiwi"));


        //New Test with loading large datasets and tracking speed and memory usage
        CacharySimpleHashMap map3 = new CacharySimpleHashMap(); //initial size 10
        CacharySimpleHashMap map4 = new CacharySimpleHashMap(); //initial size 10
        CacharySimpleHashMap map5 = new CacharySimpleHashMap(); //initial size 10

        //Ran seperately (to lessen interference with one another)
        System.out.println("Load Time and Memory Usage for 100 Words");
        map3.trackLoadTimeAndMemory("100_words");

        System.out.println("Load Time and Memory Usage for 500 Words");
        map4.trackLoadTimeAndMemory("500_words");

        System.out.println("Load Time and Memory Usage for 1000 Words");
        map5.trackLoadTimeAndMemory("1000_words");

        //Ran seperately (to lessen interference with one another)
        String[] oneWord = {"cat"};
        String[] fiveWord = {"cat", "calm", "boat", "cabinet", "car"};
        String[] tenWord = {"cat", "calm", "boat", "cabinet", "car", "fish", "calm", "sad", "cat", "tiger"};

        System.out.println("Check Time and Memory Usage for 100 Words, Checking for 1 Word");
        map3.trackCheckTimeAndMemory("100_words", oneWord);

        System.out.println("Check Time and Memory Usage for 100 Words, Checking for 5 Word");
        map3.trackCheckTimeAndMemory("100_words", fiveWord);

        System.out.println("Check Time and Memory Usage for 100 Words, Checking for 10 Word");
        map3.trackCheckTimeAndMemory("100_words", tenWord);

        System.out.println("Remove Time and Memory Usage for 100 Words, Removing for 1 Word");
        map3.trackRemoveTimeAndMemory("100_words", oneWord);

        System.out.println("Remove Time and Memory Usage for 100 Words, Removing for 5 Word");
        map3.trackRemoveTimeAndMemory("100_words", fiveWord);

        System.out.println("Remove Time and Memory Usage for 100 Words, Removing for 10 Word");
        map3.trackRemoveTimeAndMemory("100_words", tenWord);

        System.out.println("Check Time and Memory Usage for 500 Words, Checking for 1 Word");
        map4.trackCheckTimeAndMemory("500_words", oneWord);

        System.out.println("Check Time and Memory Usage for 500 Words, Checking for 5 Word");
        map4.trackCheckTimeAndMemory("500_words", fiveWord);

        System.out.println("Check Time and Memory Usage for 500 Words, Checking for 10 Word");
        map4.trackCheckTimeAndMemory("500_words", tenWord);

        System.out.println("Remove Time and Memory Usage for 500 Words, Removing for 1 Word");
        map4.trackRemoveTimeAndMemory("500_words", oneWord);

        System.out.println("Remove Time and Memory Usage for 500 Words, Removing for 5 Word");
        map4.trackRemoveTimeAndMemory("500_words", fiveWord);

        System.out.println("Remove Time and Memory Usage for 500 Words, Removing for 10 Word");
        map4.trackRemoveTimeAndMemory("500_words", tenWord);

        System.out.println("Check Time and Memory Usage for 1000 Words, Checking for 1 Word");
        map5.trackCheckTimeAndMemory("1000_words", oneWord);

        System.out.println("Check Time and Memory Usage for 1000 Words, Checking for 5 Word");
        map5.trackCheckTimeAndMemory("1000_words", fiveWord);

        System.out.println("Check Time and Memory Usage for 1000 Words, Checking for 10 Word");
        map5.trackCheckTimeAndMemory("1000_words", tenWord);

        System.out.println("Remove Time and Memory Usage for 1000 Words, Removing for 1 Word");
        map5.trackRemoveTimeAndMemory("1000_words", oneWord);

        System.out.println("Remove Time and Memory Usage for 1000 Words, Removing for 5 Word");
        map5.trackRemoveTimeAndMemory("1000_words", fiveWord);

        System.out.println("Remove Time and Memory Usage for 1000 Words, Removing for 10 Word");
        map5.trackRemoveTimeAndMemory("1000_words", tenWord);
    }
}