package game.enemy;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import city.cs.engine.Walker;
import city.cs.engine.World;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author tejas
 */

//Creates class for Enemy
public class Enemy extends Walker {
    
    //Creates Polygon shape for enemy
    private static final Shape shape = new PolygonShape(-0.042f, 1.0f, -0.875f, -0.083f,
            -0.875f, -1.0f, 0.872f, -1.0f, 0.872f, -0.086f, 0.289f, 1.0f);
    private static SoundClip enemySound;

    public static void play() {
        try {
            enemySound = new SoundClip("data/enemySound.wav");
            enemySound.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    //Gets GIF of ememy walking right from data directory
    private static final BodyImage enemy_right_walk
            = new BodyImage("data/enemy_left_run.gif", 2);

    public Enemy(World world){        
        super(world, shape);
        //Initial position of ememy
        addImage(enemy_right_walk); 
    }
       
    @Override
    public void destroy() {
        enemySound.play();
        super.destroy();
    }
}
