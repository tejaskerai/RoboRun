package game.player;
import game.enemy.Enemy;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
/**
 * This class includes all methods and attributes that a Player object has
 * @author Tejas Kerai
 */

//Creates player class

public class Player extends Walker {
    
   //Creates polygon body of player
    private static final Shape shape = new PolygonShape(-0.378f,0.997f, -0.958f,0.083f, -0.622f,-0.997f, 0.958f,-0.997f, 1.039f,-0.247f, -0.125f,1.0f);
       
    
    //new -0.378f,0.997f, -0.958f,0.083f, -0.622f,-0.997f, 0.958f,-0.997f, 1.039f,-0.247f, -0.125f,1.0f
    //old -0.042f,1.0f, -0.875f,-0.083f, -0.875f,-1.0f, 0.872f,-1.0f, 0.872f,-0.086f, 0.289f,1.0f
    //Creates new body image
    private static final BodyImage player_right_idle =
        new BodyImage("data/player_right_idle.gif", 2);
    
    //Creates private variables
    
    private int diamondCount;
    public int health;
    public Enemy enemy;
    //Adds polygon shape and player into world
    /**
     * This is the constructor method of player
     * @param world - Where the player will be made
     * @param health The health of the player in the current level
     */
    public Player(World world, int health) {
        super(world, shape);
        //Initial state of player
        addImage(player_right_idle);
        diamondCount = 0;
        this.health = health;
    }
    
    
    /**
     * Method to get the diamond count
     * @return Diamond count. Value will always be none negative
     */
    public int getDiamondCount() {
        return diamondCount;
    }
    
    /**
     * Method to get the health
     * @return Health value. Value will always be none negative
     */
    public int getHealth(){
        return (this.health);
    }

    /**
     * Method to set health 
     * @param health Health of the character in the current level
     */
    public void setHealth(int health) {
        this.health = health;
    }
    

    /**
     * Method for increasing diamond count
     * Increments diamond count by 1
     */
    public void incrementDiamondCount() {
        diamondCount++;
        System.out.println("Diamond count: " + diamondCount);
    }
    
    //Method for decreasing health
    /**
     * Method for decreasing health
     * Decreases health of play in current level by 1, Plays a Sound clip when health is decreased
     */
    public void decreaseHealth(){
        this.health = this.health -1;
        System.out.println("Oops, " + this.health + " lives remaining");
        enemy.play();
    }
    
    //Method for increasing health
    /**
     * Method for increasing health
     * Method to increase health by 1, does not increase if health value is equal to 5
     */
    public void increaseHealth(){
        if (this.health <= 4) {
            this.health = this.health + 1;
            System.out.println("Health gained, health is now: " + this.health);
        }
    }
}
