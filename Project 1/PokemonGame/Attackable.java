/*
 * Cachary Tolentino
 * The Attackable interface defines the ability for a pokemon to take damage and be fallen (die)
 */


public interface Attackable {
    
    int reduceHealth(int damage);
    Boolean knockedOut();

}
