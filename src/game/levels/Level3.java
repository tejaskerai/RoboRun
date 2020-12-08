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
 * Level 3 of the game
 */
public class Level3 extends GameLevel {
    private Enemy enemy;
    private Heart heart;
    private Diamond diamond_top_left;
    private Diamond diamond_middle_left;
    private Diamond diamond_bottom_left;
    private Diamond diamond_top_right;
    private Diamond diamond_middle_right;
    private Diamond diamond_bottom_right;
    
    public int health;
    
    public Level3(int health)
    {
        this.health = health;
    }
    
    
     /**
     * Populate the world.
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
        
        //Middle
        platform.MakePlatform(7, 0.2f, 0, 0f, 0, this);
        
        //door platform
        platform.MakePlatform(5, 0.2f, 0, -8f, 0, this);
        
        enemy = new Enemy(this);        
        //Middle enemy left wall stopper
        Shape LeftEnemyStopperShape = new BoxShape(0.2f, 0.4f);
        Body LeftEnemyStopper= new StaticBody(this,LeftEnemyStopperShape);
        LeftEnemyStopper.setPosition(new Vec2(-6.5f,0));
        LeftEnemyStopper.addCollisionListener(new EnemyCollision(enemy, true));
        LeftEnemyStopper.setAngleDegrees(90);
        
        //Middle enemy right wall stopper
        Shape RightEnemyStopperShape = new BoxShape(0.2f, 0.4f);
        Body RightEnemyStopper= new StaticBody(this,RightEnemyStopperShape);
        RightEnemyStopper.setPosition(new Vec2(6.5f,0));
        RightEnemyStopper.addCollisionListener(new EnemyCollision(enemy, false));
        RightEnemyStopper.setAngleDegrees(90);
        
        //Left side
       
        //Left jump platforms
        platform.MakePlatform(3, 0.2f, -10, -4f, 0, this);
        platform.MakePlatform(3, 0.2f, -10, 4f, 0, this);
        //Top left
        platform.MakePlatform(5, 0.2f, -20, 8f, 0, this);
        //middle left
        platform.MakePlatform(5, 0.2f, -20, 0f, 0, this);
        //bottom to top flat platforms
        platform.MakePlatform(5, 0.2f, -20, -8f, 0, this);
        
                
        
        //Right side
        
        
        //Right jump platforms
        platform.MakePlatform(3, 0.2f, 10, -4f, 0, this);
        platform.MakePlatform(3, 0.2f, 10, 4f, 0, this);
        
        //Top right
        platform.MakePlatform(5, 0.2f, 20, 8f, 0, this);
        //Middle right       
        platform.MakePlatform(5, 0.2f, 20, 0f, 0, this);
        //Bottom right
        platform.MakePlatform(5, 0.2f, 20, -8f, 0, this);
        
        
        
        //Creating new objects
        
        heart = new Heart(this);
        diamond_top_left  = new Diamond(this);
        diamond_middle_left  = new Diamond(this);
        diamond_bottom_left  = new Diamond(this);
        diamond_top_right  = new Diamond(this);
        diamond_middle_right  = new Diamond(this);
        diamond_bottom_right  = new Diamond(this);
                
        //Set positions of objects          
        //player.setPosition(new Vec2(0, -12));
        enemy.setPosition(new Vec2(0, 1));
        heart.setPosition(new Vec2(0, -12));
        diamond_top_left.setPosition(new Vec2(-20, 8.5f));
        diamond_middle_left.setPosition(new Vec2(-20, 0.5f));
        diamond_bottom_left.setPosition(new Vec2(-20, -7.5f));
        diamond_top_right.setPosition(new Vec2(20, 8.5f));
        diamond_middle_right.setPosition(new Vec2(20, 0.5f));
        diamond_bottom_right.setPosition(new Vec2(20, -7.5f));
        
        //Collison Listeners
        enemy.addCollisionListener(new ItemPickUp(getPlayer()));       
        diamond_top_left.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond_middle_left.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond_bottom_left.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond_top_right.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond_middle_right.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond_bottom_right.addCollisionListener(new ItemPickUp(getPlayer()));
        heart.addCollisionListener(new ItemPickUp(getPlayer()));
        
        
        //Setting gravity for Player
        getPlayer().setGravityScale(3f);
        //Allows enemy to walk
        enemy.startWalking(-5); // Change to make enemy walk left to right continually
        
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-22, -12);
    }
    

    @Override
    public Vec2 doorPosition() {
        return new Vec2(0, -6.5f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getDiamondCount() == 6;
        
    }
    
}
