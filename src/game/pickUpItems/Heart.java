package game.pickUpItems;
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

//Creates class for Heart
public class Heart extends Walker {
    
    //Creates Polygon shape for heart
    private static final Shape shape = new PolygonShape(-0.733f,0.597f, 0.736f,0.603f, 0.004f,-0.603f);
    private static SoundClip heartSound;
    
    static {
        try {
           heartSound = new SoundClip("data/heartSound.wav");
         } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
           System.out.println(e);
         }     
        
    }
    
    //Gets GIF of heart from data directory
    private static final BodyImage heart =
        new BodyImage("data/heart.gif", 2);
   
    public Heart(World world) {
        super(world, shape);
        addImage(heart);
    }
    @Override
    public void destroy() {
        heartSound.play();
        super.destroy();
    }
}
