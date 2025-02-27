/*
 * Cachary Tolentino
 * The Card class will be an abstract object that contains a card type.
 */

abstract class Card { //CHANGEEEEEEEEEEEEEEEEEE TO INHERTIANCE

    //Global Variable(s)
    private String cardType;
    private String cardName;

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

    /*
     * The function allows cardName to be changed
     * @param newName a string value
     * @return none
     */
    public void setName(String newName){
        cardName = newName;
    }

    /*
     * Returns the cardName
     * @param none
     * @return cardName a string value
     */
    public String getName(){
        return cardName;
    }
    
}