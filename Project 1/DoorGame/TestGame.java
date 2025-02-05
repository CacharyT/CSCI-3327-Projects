/*
 * Cachary Tolentino
 * The TestGame will test the game of triple doors by:
 * Running the game more than 10,000 times without changing door choice
 * Running the game more then 10,000 times with changing door choice
 */

 /*
  * QUESTION A
  * P(Good) = 1/3
  * P(Dud1) = 1/3
  * P(Dud2) = 1/3
  * Probability of contestant picking the right prize is 1/3 or around 0.33
  */

  /*
   * QUESTION B
   * i. P(Good) = 1/3
   * ii. She would lose because if she had initially selected Good and a dud was shown, then she will get a dud.
   * iii. She would win because if she had iniitially selected a Dud and was shown a dud, then the remainder is the Good prize.
   * iv. P(Dud1/Dud2 OR GOOD) = 1/3 + 1/3 = 2/3
   * v. Switching is the best strategy as it increases the chances of winning the good prize (2/3).
   */

public class TestGame {
    public static void main(String[] args) {
        
        //Creating a GameShow object (initial door configuration is mandatory)
        GameShow game = new GameShow("Dud1", "Good", "Dud2");

        //Results per scenario
        System.out.println("Percentage of Game Wins with no changes in choice: " + game.playGamesOneChoice(10000));
        System.out.println("Percentage of Game Wins with switched choice: " + game.playGamesSwitched(10000));

    }
}