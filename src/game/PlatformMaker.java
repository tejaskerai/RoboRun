package game;

import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
 
        
/**
 * This class is the blueprint of making a platform
 * @author Tejas Kerai
 */
public class PlatformMaker {
    /**
     * The is the method that can be called to make a platform
     * @param length The length of the platform
     * @param width The width of the platform
     * @param posx X coordinate of the platform
     * @param posy Y coordinate of the platform
     * @param angle Angle of the platform, 0 is horizontal, 90 is vertical
     * @param world Where platform will be rendered
     */
    public void MakePlatform(float length, float width, float posx, float posy, float angle, World world){
        Shape platformShape = new BoxShape(length, width);
        Body platform = new StaticBody(world,platformShape);
        platform.setPosition(new Vec2(posx,posy));
        platform.setAngleDegrees(angle);
    
    }

}