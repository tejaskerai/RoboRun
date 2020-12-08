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
//Creates class for Diamond
public class Diamond extends Walker {

    //Creates Polygon shape for diamond
    private static final Shape shape = new PolygonShape(-0.541f,0.59f, 0.859f,0.593f, 1.21f,0.052f, 0.1f,-0.993f, -1.031f,0.052f);
    private static SoundClip diamondSound; 
    
    static {
        try {
           diamondSound = new SoundClip("data/diamondSound.wav");
         } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
           System.out.println(e);
         }     
        
    }
    

    //Gets Diamond GIF file from data directory
    private static final BodyImage diamond
            = new BodyImage("data/diamond.gif", 1.5f);

    public Diamond(World world) {
        super(world, shape);
        addImage(diamond);
    }

    @Override
    public void destroy() {
        diamondSound.play();
        super.destroy();
    }
}
