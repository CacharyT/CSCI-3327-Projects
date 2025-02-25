/*
 * Cachary Tolentino
 * Battler Arena for Pokemon (check damage effect)
 */

public class Stadium {
    
    public void battle(Pokemon p1, Pokemon p2){
        //p1 attack p2 subract from hp the difference of attack and defense
        //normally check speed first
        if(p1.getSpeed() > p2.getSpeed()){ //put this in a loop until one is knocked outl; Check who attacks first based on higher speed 
            while(p1.getHP() > 0 && p2.getHP() > 0){ //Check that both are alive, if so then continue
                p2.setHP(p2.getHP() - p1.getAttack() - p2.getDefense());
                if(p2.getHP() <= 0){ //check other pokemon if hp over 0;
                    System.out.println("P2 defeated!");
                    break;
                }
                p1.setHP(p1.getHP() - p2.getAttack() - p1.getDefense());
                if(p1.getHP() <= 0){ //check other pokemon if hp over 0;
                    System.out.println("P1 defeated!");
                    break;
                }
            }
        } else{
            while(p1.getHP() > 0 && p2.getHP() > 0){ //p2 attack p1
                p1.setHP(p1.getHP() - p2.getAttack() - p1.getDefense());
                if(p1.getHP() <= 0){
                    System.out.println("P1 defeated!");
                    break;
                }
                p2.setHP(p2.getHP() - p1.getAttack() - p2.getDefense());
                if(p2.getHP() <= 0){
                    System.out.println("P2 defeated!");
                    break;
                }
            }
        }
    }
}
