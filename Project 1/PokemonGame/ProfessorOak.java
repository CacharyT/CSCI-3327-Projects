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

        //Discard hand to discard pile
        Card[] currentHand = player.getHand();
        Card[] currentDiscardPile = player.getDiscardPile();
        int count = 0;


        for(Card card : currentDiscardPile){
            count++;
        }

        if(currentHand.length < currentDiscardPile.length){
            for(int i = count; i < currentDiscardPile.length; i++){
                currentDiscardPile[i + 1] = currentHand[i - count];
            }
            player.setDiscardPile(currentDiscardPile);
            PokemonGame.fillPlayerHand(player);
        } else{
            Card[] newDiscardPile = new Card[currentDiscardPile.length + 10];
            for(int i = 0; i < newDiscardPile.length; i++){
                newDiscardPile[i] = currentDiscardPile[i];
            }

            for(int i = count; i < newDiscardPile.length; i++){
                newDiscardPile[i + 1] = currentHand[i - count];
            }
            player.setDiscardPile(newDiscardPile);
            PokemonGame.fillPlayerHand(player);
        }

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