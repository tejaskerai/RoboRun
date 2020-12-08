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
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel {

    private Enemy TopEnemy;
    private Enemy BottomEnemy;
    private Heart heart;
    private Diamond diamond;
    private Diamond diamond2;
    private Diamond diamond3;
    public Level2(int health) {
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
        BottomEnemy = new Enemy(this);
        Shape LeftWallShape = new BoxShape(13.1f, 0.4f);
        Body LeftWall = new StaticBody(this, LeftWallShape);
        LeftWall.setPosition(new Vec2(-24.6f, 0));
        LeftWall.addCollisionListener(new EnemyCollision(BottomEnemy, true));
        LeftWall.setAngleDegrees(90);

        //Right wall bottom enemy stopper
        Shape RightEnemyStopperShape = new BoxShape(0.2f, 0.4f);
        Body RightEnemyStopper = new StaticBody(this, RightEnemyStopperShape);
        RightEnemyStopper.setPosition(new Vec2(-12, -13.1f));
        RightEnemyStopper.addCollisionListener(new EnemyCollision(BottomEnemy, false));
        RightEnemyStopper.setAngleDegrees(90);

        //Right wall
        platform.MakePlatform(13.2f, 0.4f, 24.6f, 0, 90, this);

        TopEnemy = new Enemy(this);

        //Left wall top ememy stopper 
        Shape LeftEnemyWallShape = new BoxShape(9f, 0.4f);
        Body LeftEnemyWall = new StaticBody(this, LeftEnemyWallShape);
        LeftEnemyWall.setPosition(new Vec2(-8.3f, -4));
        LeftEnemyWall.addCollisionListener(new EnemyCollision(TopEnemy, true));
        LeftEnemyWall.setAngleDegrees(90);

        //Wall 2
        platform.MakePlatform(9, 0.4f, 8.3f, -4, 90, this);

        //Left platform 1 (Left segment)
        platform.MakePlatform(3, 0.4f, -22, -7, 0, this);

        //Left platform 2 (Left segment)
        platform.MakePlatform(3, 0.4f, -22, -2, 0, this);

        //Right platform (Left segment)
        platform.MakePlatform(3, 0.4f, -11, 2, 0, this);

        //Left platform 1(Middle segment)
        platform.MakePlatform(2.5f, 0.4f, 6, -8, 0, this);

        //Left platform 2(Middle segment)
        platform.MakePlatform(2.5f, 0.4f, -6, -5, 0, this);

        //Left platform 3(Middle segment)
        platform.MakePlatform(2.5f, 0.4f, 6, -1, 0, this);

        //Left platform 4 (Middle segment)
        platform.MakePlatform(5, 0.4f, -3, 4.6f, 0, this);

        //Right wall ememy stopper 
        Shape wall2Shape = new BoxShape(0.2f, 0.4f);
        Body wall2 = new StaticBody(this, wall2Shape);
        wall2.setPosition(new Vec2(1.58f, 4.8f));
        wall2.addCollisionListener(new EnemyCollision(TopEnemy, false));
        wall2.setAngleDegrees(90);

        //Right platform 1 (Right segment)
        platform.MakePlatform(3, 0.4f, 23, -8, 0, this);

        //Left platform 2 (Right segment)
        platform.MakePlatform(3, 0.4f, 11, -3, 0, this);

        //Right platform 3 (Right segment)
        platform.MakePlatform(3, 0.4f, 23, 0f, 0, this);

        //Left platform 4 (Right segment)
        platform.MakePlatform(3, 0.4f, 11, 4.6f, 0, this);

        //Right platform 5 (Right segment)
        platform.MakePlatform(1.5f, 0.4f, 23, 4.6f, 0, this);

        //Creating new objects
        heart = new Heart(this);
        diamond = new Diamond(this);
        diamond2 = new Diamond(this);
        diamond3 = new Diamond(this);

        //Set positions of objects          
        //player.setPosition(new Vec2(0, -12));
        TopEnemy.setPosition(new Vec2(-3, 5));
        BottomEnemy.setPosition(new Vec2(-22, -12));
        heart.setPosition(new Vec2(12, -12));
        diamond.setPosition(new Vec2(-10, -12));
        diamond2.setPosition(new Vec2(-6, -4.5f));
        diamond3.setPosition(new Vec2(23, 5f));

        //Collison Listeners
        TopEnemy.addCollisionListener(new ItemPickUp(getPlayer()));
        BottomEnemy.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond2.addCollisionListener(new ItemPickUp(getPlayer()));
        diamond3.addCollisionListener(new ItemPickUp(getPlayer()));
        heart.addCollisionListener(new ItemPickUp(getPlayer()));

        //Setting gravity for Player
        getPlayer().setGravityScale(3f);
        TopEnemy.startWalking(-5);
        BottomEnemy.startWalking(-5);

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -12);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(22, -11.5f);
    }

    @Override
    public boolean isCompleted() {
        return getPlayer().getDiamondCount() == 3;
    }

}
