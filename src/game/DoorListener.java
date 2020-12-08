package game;

import game.player.Player;
import city.cs.engine.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Listener for collision with a door.  When the player collides with a door,
 * if the current level is complete the game is advanced to the next level. 
 */
public class DoorListener implements CollisionListener {
    private Game game;
    private static SoundClip doorSound;
     
    
    public DoorListener(Game game) {
        this.game = game;
    }
    
    

    @Override
    public void collide(CollisionEvent e) {
        Player player = game.getPlayer();
        if (e.getOtherBody() == player && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            game.goNextLevel();
            
        try {
           doorSound = new SoundClip("data/doorSound.wav");
           doorSound.play();
         } catch (UnsupportedAudioFileException|IOException|LineUnavailableException f) {
           System.out.println(f);
         }     
        
    
        }
    }
}
