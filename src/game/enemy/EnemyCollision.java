package game.enemy;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * This class is used to automate the movement of the enemy
 * @author Tejas Kerai
 */
public class EnemyCollision implements CollisionListener{

    private Enemy enemy;
    private boolean border;
    private float walkSpeed = 5;

    /**
     * This method sets the speed of the enemy
     * @param walkSpeed Walking speed of the enemy
     */
    
    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    
    /**
     * This method takes the enemy and a boolean expression border
     * @param enemy The enemy object in the current level
     * @param border The boolean value if the enemy has reached the border
     */
    public EnemyCollision (Enemy enemy, boolean border){
        this.enemy = enemy;
        this.border = border;
    }
    
    /**
     * The method that handles the logic of the enemy changing directions
     * @param e Required parameter for using Collision Event
     */
    @Override
    public void collide(CollisionEvent e){
        if(e.getOtherBody() == enemy){            
            if(border == true){
                //System.out.println("Collided with left wall");
                enemy.startWalking(walkSpeed);
                enemy.removeAllImages();
                enemy.addImage(new BodyImage("data/enemy_right_run.gif", 2));
                
            }if (border == false){
                //System.out.println("Collided right wall");
                enemy.startWalking(-walkSpeed);
                enemy.removeAllImages();
                enemy.addImage(new BodyImage("data/enemy_left_run.gif", 2));
               
            }
        }
    }
}
    

