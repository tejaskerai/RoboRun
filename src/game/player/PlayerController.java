package game.player;
import city.cs.engine.BodyImage;
import city.cs.engine.Walker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.jbox2d.common.Vec2;
/**
 *
 * @author tejas
 */

//Creates class for PlayerController
public class PlayerController extends KeyAdapter {
    
    //Creates unchangeable jumping and walking speeds
    public float jumpSpeed = 20;
    public float walkSpeed = 8;

    public void setJumpSpeed(float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    
    
    private Walker body;
    
    public PlayerController(Walker body) {
        this.body = body;
    }
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    
    //Keyboard events
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_UP) { 
            Vec2 v = body.getLinearVelocity();
            //<0.00000001f becuase when (v.y) = 0.0f player can jum mid air
            if (Math.abs(v.y) < 0.0000001f) {
                body.jump(jumpSpeed);
            }
        } else if (code == KeyEvent.VK_LEFT) {
            body.startWalking(-walkSpeed);
            //Changes image of player when walking left
            body.removeAllImages();
            body.addImage(new BodyImage("data/player_left_run.gif", 2));
        } else if (code == KeyEvent.VK_RIGHT) {
            body.startWalking(walkSpeed);
            //Changes image of player when walking right
            body.removeAllImages();
            body.addImage(new BodyImage("data/player_right_run.gif", 2));    
        }
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            //Stops player instantaneously
            body.startWalking(0f);
            //Changes image of player when player is left idle
            body.removeAllImages();
            body.addImage(new BodyImage("data/player_left_idle.gif", 2));
        } else if (code == KeyEvent.VK_RIGHT) {
            //Stops player instantaneously
            body.startWalking(0f);
            //Changes image of player when player is right idle
            body.removeAllImages();
            body.addImage(new BodyImage("data/player_right_idle.gif", 2));
        }
        
    }
    public void setBody(Walker body) {
        this.body = body;
    }
}
