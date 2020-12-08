package game;
import game.player.Player;
import game.enemy.Enemy;
import game.pickUpItems.Heart;
import game.pickUpItems.Diamond;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
/**
 * This class is used for detecting collisions between player and other objects
 * Implements CollisionsListener
 * @author Tejas Kerai
 */
public class ItemPickUp implements CollisionListener {

    //Creates private variable
    private Player player;

    /**
     * Constructor method of ItemPickUp
     * @param player The player in the current level
     */
    public ItemPickUp(Player player) {
        this.player = player;
    }

    /**
     * The method that handles the logic between if the player touches different objects
     * @param e Required parameter for using Collision Event
     */
    @Override
    public void collide(CollisionEvent e) {
        //If player hits diamond, diamond gets destroyed
        if (e.getReportingBody() instanceof Diamond && e.getOtherBody() == player) {
            e.getReportingBody().destroy();
            player.incrementDiamondCount();
            
        //If player hits Enemy, player health decreases        
        } else if (e.getReportingBody() instanceof Enemy && e.getOtherBody() == player) {
            player.decreaseHealth();
                //If player health is <= 0 player dies(destroyed)
                if (player.getHealth() <= 0) {
                e.getOtherBody().destroy();
            }
        //If player hits heart, heart gets destroyed, and health increases by 1
        } else if (e.getReportingBody() instanceof Heart && e.getOtherBody() == player) {
            e.getReportingBody().destroy();
            player.increaseHealth();
        }
    }
}