/*
 * Cachary Tolentino
 * 
 */




public class ProfessorOak extends Trainer{

    //Global Variable(s)
    private String trainerDescription;

    /*
     * Default Constructor
     */
    public ProfessorOak(){
        super.setCardType("Trainer");
        super.setName("ProfessorOak");
        trainerDescription = "Discard your hand and draw 7 cards.";
    }


    /*
     * 
     */
    public void activateEffect(Player player){

        System.out.println("You have activated a ProfessorOak trainer card! The card's effect is: " + trainerDescription);

        //Discard hand to discard pile
        Card[] currentHand = player.getHand();

        System.out.print("The current hand will be discarded: [");
        for(Card card : currentHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]\n");

        player.reAddHandToDeck();

        player.fillHand();


        Card[] newHand = player.getHand();
        System.out.print("Your new hand: [");
        for(Card card : newHand){
            System.out.print(card.getName() + " ");
        }
        System.out.print("]\n");
        player.setHand(newHand);

    }

    /*
     * 
     */
    public void setTrainerDescription(String newDescription){
        trainerDescription = newDescription;
    }

    /*
     * 
     */
    public String getTrainerDescription(){
        return trainerDescription;
    }
    
}