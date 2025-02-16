/*
 * Cachary Tolentino
 * The Card class will be an object that contains a card type.
 */


abstract class Card {

    //Global Variable(s)
    private String cardType;

    /*
     * Default Constructor
     * @param none
     * @return none
     */
    public Card(){
        cardType = "";
    }

    /*
     * Contructor with parameter
     * @param type a string value for the type of card
     * @return none
     */
    public Card(String type){
        cardType = type;
    }

    /*
     * The function will update the current value of the cardType
     * @param newType a string value of the new type
     * @return none
     */
    public void setCardType(String newType){
        cardType = newType;
    }

    /*
     * The function will return the type of card
     * @param none
     * @return cardType a string value of the type of card
     */
    public String getCardType(){
        return cardType;
    }

    public abstract String getName();
    
}
