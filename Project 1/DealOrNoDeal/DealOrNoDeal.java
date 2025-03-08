/*
 * Cachary Tolentino
 * The DealOrNoDeal class will emulate the popular game show.
 * It will allow the user to run a number of trials to find the probability of 
 */

//Imports
import java.util.*;

public class DealOrNoDeal{

    //Global Variable(s)
    private ArrayList<Case> cases;
    private int highValueCount;
    private Case playerCase;

    /*
     * Default Constructor (creates an araylist of 26 prefilled cases)
     * @param none
     * @return none
     */
    public DealOrNoDeal(){
        highValueCount = 12;
        playerCase = null;
        cases = new ArrayList<>();
        fillCases();
    }

    /*
     * The function will fill the arraylist of cases with the predetermined cash prizes
     * @param none
     * @return none
     */
    public void fillCases(){
        //First 14 numbers are the low value prizes and the remaining 12 are the high value prizes
        double[] moneyList = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 
            200000, 300000, 400000, 500000, 750000, 1000000};

        int index = 1;
        for(double amount : moneyList){
            Case newCase = new Case();
            newCase.setMoney(amount);
            newCase.setName("Case " + index++);
            cases.add(newCase);
        }

        Collections.shuffle(cases); //shuffle the content with Collections
    }

    /*
     * The function will check if the current case prize is a low or high value prize
     * @param case a case object
     * @return a String value
     */
    public String checkValue(Case currentCase){
        double[] lowValue = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000};
        double[] highValue = {5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000};
        String value = "";

        //Check the value category
        for(double amount : lowValue){
            if(amount == currentCase.getMoney()){
                value = "Low";
                break;
            }
        }
        for(double amount : highValue){
            if(amount == currentCase.getMoney()){
                value = "High";
                break;
            }
        }
        
        return value;
    }

    /*
     * The function will emulate the bank offer given the amount of cases left and the current chosen case
     * @param remianingCases an arraylist of case objects
     * @return a double value (average payout of bank)
     */
    public double bankOffer(ArrayList<Case> remainingCases){

        //Make offer based on the remaining case values and amount (average payout)
        double remainingSum = 0;
        for(Case remainingCase : remainingCases){
            remainingSum += remainingCase.getMoney();
        }
        double offer = Math.round(remainingSum / (double) remainingCases.size());

        System.out.println("The bank offers: $" + offer);

        return offer;
    }

    /*
     * The function will reveal a set amount of cases
     * @param amount an int value
     * @return none
     */
    public void revealCase(int amount){
        //Declarations
        Random random = new Random();
        System.out.println(amount + " cases will be revealed...");

        //Reveal set amount of cases
        for(int i = 0; i < amount; i++){
            Case revealedCase = cases.get(random.nextInt(cases.size()));
            cases.remove(revealedCase);

            System.out.println("A case was revealed: $" + revealedCase.getMoney());

            //Update count of high value remaining
            String value = checkValue(revealedCase);
            if(value.equals("High")){
                highValueCount--;
            }
        }
    }

    /*
     * The function will reveal the current (not their values) available cases
     * @param none
     * @return none
     */
    public void displayCases(){
        System.out.print("\nCurrent available cases: [");
        for(int i = 0; i < cases.size(); i++){
            System.out.print(cases.get(i).getName() + ", ");
        }
        System.out.print("]");
    }

    /*
     * The function will allow the user to choose initial case
     * @param none
     * @return a case object
     */
    public Case playerCaseChoice(){
        Scanner scan = new Scanner(System.in);

        displayCases(); //reveal available cases

        System.out.print("\nChoose a case: ");
        int choice = scan.nextInt();
        Case chosenCase = cases.get(choice);
        System.out.println("Player has chosen: " + chosenCase.getName());
        cases.remove(choice);

        return chosenCase;
    }

    /*
     * The function will calculate the conditional probability of the current case having the highest value
     * @param none
     * @return a double value 
     */
    public double conditionalProbability(){
        return (double) highValueCount / cases.size();
    }

    /*
     * The function will allow the user to make a choice with their case (also gives conditional probability of thier current case having a high value)
     * @param bankOffer a double value
     * @param finalRound a boolean value
     * @param currentPlayerCase a case object
     * @return
     */
    public int playerChoice(double bankOffer, boolean finalRound, Case currentPlayerCase){
        Scanner scan = new Scanner(System.in);

        System.out.println("The probability of the current case having a high value: " + String.format("%.2f", 100 * conditionalProbability()) + "%");
        System.out.println("Choose an action");
        System.out.println("1: Keep case");
        System.out.println("2: Take bank offer");
        System.out.println("3: Choose a new case");
        System.out.print("Choice: ");
        int choice = scan.nextInt();

        if(finalRound){
            if(choice == 1){
                System.out.println("The player has chosen to keep their case!");
                System.out.println("The play has won $" + currentPlayerCase.getMoney());
                return -1;
            } else if(choice == 2){
                System.out.println("The player has chosen to take the bank offer!");
                System.out.println("The player has won $" + bankOffer);
                return -2;
            } else{
                Case finalCase = playerCaseChoice();
                System.out.println("The player has decided to switch to the final case!");
                System.out.println("The player has won $" + finalCase.getMoney());
                return -3;
            }
        } else{
            if(choice == 1){
                System.out.println("The player has chosen to keep their case!");
                return choice;
            } else if(choice == 2){
                System.out.println("The player has chosen to take the bank offer!");
                System.out.println("The player has won $" + bankOffer);
                return choice;
            } else{
                setPlayerCase(playerCaseChoice());
                return choice;
            }
        }
    }

    /*
     * The function will emulate a Deal Or No Deal game.
     * @param none
     * @return none
     */
    public void gameShowSimulation(){

        //Declarations
        boolean gameOver = false;
        boolean firstRound = true;

        //Allow play to choose their initial case
        playerCase = playerCaseChoice();
        int choice = 0;

        while(!gameOver){

            //Reveal cases
            if(firstRound){ //reveal 6
                firstRound = false;
                revealCase(6);
                double offer = bankOffer(cases);
                choice = playerChoice(offer, false, playerCase);
            } else{ //reveal 5 (unless not enough cases then only reveal one per turn)

                //Check if only one case left available then player final decision
                if(cases.size() == 1){
                    System.out.println("A final case is left! What will the player choose...");
                    double offer = bankOffer(cases);
                    choice = playerChoice(offer, true, playerCase);
                    gameOver = true;
                } else if(cases.size() - 5 > 0){ //if not then reveal 5, 
                    revealCase(5);
                    double offer = bankOffer(cases);
                    choice = playerChoice(offer, false, playerCase);
                } else{
                    revealCase(1);
                    double offer = bankOffer(cases);
                    choice = playerChoice(offer, false, playerCase);
                }
            }

            //End game credits
            if(choice == 2 || choice == -1 || choice == -2 || choice == -3){
                gameOver = true;
            }
        }
    }

    /*
     * The function will set the current cases value
     * @param newCases an arraylist of case objects
     * @return none
     */
    public void setCases(ArrayList<Case> newCases){
        cases = newCases;
    }

    /*
     * The function will return the current set of cases 
     * @param none
     * @return cases an arraylist of case objects
     */
    public ArrayList<Case> getCases(){
        return cases;
    }

    /*
     * The function will set the current high value count
     * @param newValue an int value
     * @return none
     */
    public void setHighValueCount(int newValue){
        highValueCount = newValue;
    }

    /*
     * The function will return the current high value count
     * @param none
     * @return highValueCount an int value
     */
    public int getHighValueCount(){
        return highValueCount;
    }

    /*
     * The function will set the current player case
     * @param newCase a case object
     * @return none
     */
    public void setPlayerCase(Case newCase){
        playerCase = newCase;
    }

    /*
     * The function will return the current player case
     * @param none
     * @return playerCase a case object
     */
    public Case getPlayerCase(){
        return playerCase;
    }
}