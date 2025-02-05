/*
 * Cachary Tolentino
 * The TestBirthday class will:
 * 1. Test BirthdayFinder and see the probabilities of a pair of people having the same birthday
 */


public class TestBirthday {
    public static void main(String[] args) {
        
        //BirthdayFinder Object
        BirthdayFinder birthdays = new BirthdayFinder(33); //33 people in class

        //Finding porbability of two people sharing the same birthday (10,000  trials)
        System.out.println("The probability of 2 people sharing the same birthday is: " + birthdays.findProbabilityOfBirthdayTwins(10000));

    }
}