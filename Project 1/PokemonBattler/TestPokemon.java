/*
 * Cachary Tolentino
 * Tester class
 */

//NOTE-----------------------------------------------------------------------------------------------------
//HAS A Charmander
//the charmander got the PUBLIC INTERFACE of Pokemon (aka anything declared public) --> this is inheritance

public class TestPokemon {
    public static void main(String[] args) {

        Charmander charmanderSteve = new Charmander(); 
        Pikachu ummhmm = new Pikachu();

        charmanderSteve.getSpeed(); //has this function because of its superclass

        Stadium tester = new Stadium();

        tester.battle(ummhmm, charmanderSteve); //This is polymorphism becuase battle takes in Pokemons
    }
}
