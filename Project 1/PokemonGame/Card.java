/*
 * 
 */


public class Card {

    //Global Variable(s)
    private String cardType;

    /*
     * Default Constructor
     */
    public Card(){
        cardType = "";
    }

    /*
     * Contructor with parameter
     * @param
     * @return
     */
    public Card(String type){
        cardType = type;
    }


    //Setters

    /*
     * 
     */
    public void setCardType(String newType){
        cardType = newType;
    }

    //Getters

    /*
     * 
     */
    public String getCardType(){
        return cardType;
    }
    
}
