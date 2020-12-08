package game;

import game.player.Player;
import game.pickUpItems.Door;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World {
    private Player player;
    public int health;
    
    public Player getPlayer() {
        return player;
    }    
    
    
    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     * @param game
     */
    public void populate(Game game) {
        player = new Player(this, this.health);
        player.setPosition(startPosition());
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));
        
        
        
    }
    
    /** The initial position of the player. */
    public abstract Vec2 startPosition();
    
    /** The position of the exit door. */
    public abstract Vec2 doorPosition();
    
    /** Is this level complete? */
    public abstract boolean isCompleted();
    
    public void setHealth(int health)
    {
        this.health = health;
    }

}
