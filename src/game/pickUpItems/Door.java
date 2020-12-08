package game.pickUpItems;

import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Doors in a game. When the actor collides with a door, if
 * the current level is complete the game is advanced to the
 * next level. 
 */
public class Door extends StaticBody {   
    
    private static final Shape shape = new PolygonShape(-0.39f,1.5f, -1.55f,0.45f, -1.55f,-1.5f, 
            1.45f,-1.5f, 1.46f,0.47f, 0.42f,1.49f);
   
    
    
    
    private static final BodyImage door
            = new BodyImage("data/door.png", 3f);
    
    public Door(World world) {
        super(world, shape);
        addImage(door);
    }
    
}
