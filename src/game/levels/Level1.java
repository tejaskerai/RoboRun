package game.levels;
import city.cs.engine.*;
import game.pickUpItems.Diamond;
import game.enemy.Enemy;
import game.enemy.EnemyCollision;
import game.Game;
import game.GameLevel;
import game.pickUpItems.Heart;
import game.ItemPickUp;
import game.PlatformMaker;
import org.jbox2d.common.Vec2;

/**
 * This class creates level 1 and extend GameLevel
 * @author Tejas Kerai
 */
public class Level1 extends GameLevel {
    private Enemy enemy;
    private Heart heart;
    private Diamond diamond;
    private Diamond diamond2;
    public int health;
    public SoundClip gameMusic1;
    /**
     * This a constructor method of Level 1
     * @param health The health of the player in the current level
     */
    public Level1(int health)
    {
        this.health = health;
    }
    
    /**
     * This method populates level 1 of the game
     * @param game The window to display the level
     */
    
    @Override
    public void populate(Game game) {
       
        this.setHealth(this.health);
        super.populate(game);
        PlatformMaker platform = new PlatformMaker();
        
        //Ground     
        platform.MakePlatform(25, 0.4f, 0, -13.3f, 0, this);
        
        //Ceiling
        platform.MakePlatform(25, 0.4f, 0, 13.3f, 0, this);
        
        //Left wall
        platform.MakePlatform(13.2f, 0.4f, -24.6f, 0, 90, this);
 
        //Right wall
        platform.MakePlatform(13.2f, 0.4f, 24.6f, 0, 90, this);
            
        //Wall 1
        enemy = new Enemy(this);
        Shape wall1Shape = new BoxShape(9, 0.4f);
        Body wall1= new StaticBody(this,wall1Shape);
        wall1.setPosition(new Vec2(-8.3f,-4));
        wall1.addCollisionListener(new EnemyCollision(enemy, true));
        wall1.setAngleDegrees(90);
        
        //Right wall ememy stopper 
        Shape wall2Shape = new BoxShape(0.2f, 0.4f);
        Body wall2= new StaticBody(this,wall2Shape);
        wall2.setPosition(new Vec2(20,-13.105f));
        wall2.addCollisionListener(new EnemyCollision(enemy, false));
        wall2.setAngleDegrees(90);
        
        //Right platform (Left segment)
        platform.MakePlatform(3, 0.4f, -11, -8, 0, this);

        //Left platform  (Left segment)
        platform.MakePlatform(3, 0.4f, -22, -3, 0, this);
       
        //Right platform 2 (Left segment)
        platform.MakePlatform(3, 0.4f, -11, 2, 0, this);
        
        //Left platform (Middle segment)
        platform.MakePlatform(9, 0.4f, 1, 4.6f, 0, this);
        
        //Right diamond platform
        platform.MakePlatform(3, 0.4f, 22, 4.6f, 0, this);
        
        //Floating bottom platform (Right Segment)
        platform.MakePlatform(3, 0.4f, 14.5f, -7f, 0, this);
        
        //Floating top platform (Right Segment)
        platform.MakePlatform(3, 0.4f, 14.5f, -1f, 0, this);
        
        //Heart platform
        platform.MakePlatform(2.5f, 0.4f, -6, -8, 0, this);
        
        //Middle diamond platform
        platform.MakePlatform(2.5f, 0.4f, -6, -3, 0, this);
        
        
  
        
        //Creating new objects
        
        heart = new Heart(this);
        diamond = new Diamond(this);
        diamond2 = new Diamond(this);
                
        //Set positions of objects          
        //player.setPosition(new Vec2(0, -12));
        enemy.setPosition(new Vec2(0, -12));
        heart.setPosition(new Vec2(-6, -7));
        diamond.setPosition(new Vec2(22, 5f));
        diamond2.setPosition(new Vec2(-6, -2));
        
        //Collison Listeners
        enemy.addCollisionListener(new ItemPickUp(getPlayer()));       
        diamond.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond2.addCollisionListener(new ItemPickUp(getPlayer()));
        heart.addCollisionListener(new ItemPickUp(getPlayer()));
        
        
        //Setting gravity for Player
        getPlayer().setGravityScale(3f);
        //Allows enemy to walk
        enemy.startWalking(-5); // Change to make enemy walk left to right continually
        
    }

    /**
     * The method that initialises the start position of the player
     * @return The x and y position of the player
     */
    @Override
    public Vec2 startPosition() {
        return new Vec2(-22, -12);
    }
    

    /**
     * The method the initialises the position of the door
     * @return The x and y position of the door
     */
    @Override
    public Vec2 doorPosition() {
        return new Vec2(22, -11.5f);
    }

    /**
     * The method the initialises the position of the door
     * @return The boolean if level is completed
     */
    @Override
    public boolean isCompleted() {
        return getPlayer().getDiamondCount() == 2;        
    }
    
}
