package game;

import game.player.Player;
import game.player.PlayerController;
import game.levels.Level3;
import game.levels.Level1;
import game.levels.Level2;
import city.cs.engine.SoundClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A class that creates the game 
 * @author Tejas Kerai
 */
public class Game implements ActionListener {

    /** The World in which the bodies move and interact. */
    private GameLevel world;

    /** A graphical display of the world (a specialised JPanel). */
    
    private MyView view;
    private int level;
    private JMenuItem level1 = new JMenuItem("Level 1");
    private JMenuItem level2 = new JMenuItem("Level 2");
    private JMenuItem level3 = new JMenuItem("Level 3");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu levels = new JMenu("Levels");
    private JMenu speed = new JMenu("Speed");
    private JMenuItem slow = new JMenuItem("Slow");
    private JMenuItem medium = new JMenuItem("Medium");
    private JMenuItem fast = new JMenuItem("Fast");
    private PlayerController controller;
    private SoundClip gameMusic1;
    private SoundClip gameMusic2;
    private SoundClip gameMusic3;
    /**
     * Initialises a new game
     */
    public Game() {
        
        // Make the world
        level = 1;
        world = new Level1(5);
        world.populate(this);
        
        // Make a view
        view = new MyView(world, world.getPlayer(), 1000, 550);
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // Display the view in a frame
        final JFrame frame = new JFrame("RoboRun");
        
        // Display the buttons of the Control Panel in the frame
        Container buttons = new ControlPanel(this);
        frame.add(buttons, BorderLayout.NORTH);
        
        // Add Menu options in the menu bar
        menuBar.add(file);
        menuBar.add(levels);
        menuBar.add(speed);
        
        // Add Menu items in the menu bar
        file.add(exit);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        speed.add(slow);
        speed.add(medium);
        speed.add(fast);
        
        // Add collision listerner to all menu items
        level1.addActionListener(this);
        level2.addActionListener(this);
        level3.addActionListener(this);
        slow.addActionListener(this);
        medium.addActionListener(this);
        fast.addActionListener(this);
        exit.addActionListener(this);
        
        
        // Set Menu bar in the frame
        frame.setJMenuBar(menuBar);
        
        // Initialise background sounds of levels
        try {
            gameMusic2 = new SoundClip("data/music2.wav");
            gameMusic3 = new SoundClip("data/music3.wav"); // Open an audio input stream
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        } 
        
        // Loop the sound of level 1 background 
        try {
            gameMusic1 = new SoundClip("data/music.wav");   // Open an audio input stream
            gameMusic1.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        } 
        

        // Quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // Display the world in the window
        frame.add(view);
        // Don't let the game window be resized
        frame.setResizable(false);
        // Size the game window to fit the world view
        frame.pack();
        // Make the window visible
        frame.setVisible(true);
        // Get keyboard focus
        frame.requestFocus();
        // Give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));
        
        // Add controller for the player
        controller = new PlayerController(world.getPlayer());
        frame.addKeyListener(controller);
        // start!
        world.start();
        
    }
    
    /**
     * A method to handle events when items in the menu bar are pressed
     * @param e Required parameter for using Collision Event
     */
     public void actionPerformed(ActionEvent e) {
        if (e.getSource()==level1){
            level = 1;
            // Remove the sound of level 2 and 3 when level 1 is pressed
            gameMusic2.stop();
            gameMusic3.stop();
            try {
                gameMusic1 = new SoundClip("data/music.wav"); // Open an audio input stream
                gameMusic1.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            world = new Level1(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }
        if (e.getSource()==level2){
            
            level = 2;
            // Remove the sound of level 1 and 3 when level 2 is pressed
            gameMusic1.stop();
            gameMusic3.stop();
            try {
                gameMusic2 = new SoundClip("data/music2.wav"); // Open an audio input stream
                gameMusic2.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            
            world = new Level2(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }
        if (e.getSource()==level3){
            level = 3;
            // Remove the sound of level 1 and 2 when level 3 is pressed
            gameMusic1.stop();
            gameMusic2.stop();
            try {
                gameMusic3 = new SoundClip("data/music3.wav"); // Open an audio input stream
                gameMusic3.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            world = new Level3(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }
        if (e.getSource() == exit){
            System.exit(0);
        }
        if(e.getSource() == slow){
            controller.setJumpSpeed(20);
            controller.setWalkSpeed(8);
            
        }
        if(e.getSource() == medium){
            controller.setJumpSpeed(22);
            controller.setWalkSpeed(10);
        }
        if(e.getSource() == fast){
            controller.setJumpSpeed(25);
            controller.setWalkSpeed(12);
        }
       
    }
    
    /**
     * Get player in current level
     * @return Player in world
     */
    public Player getPlayer() {
        return world.getPlayer();
    }
    /**
     * Method to pause the game
     */
    public void pause(){
        world.stop();
    }
    /**
     * Method the resume the game
     */
    public void resume(){
        world.start();
    }
    /**
     * Method to restart the game
     */
    public void restart(){
        if (level == 1){
            level = 1;
            gameMusic1.stop();
            try {
                gameMusic1 = new SoundClip("data/music.wav"); // Open an audio input stream
                gameMusic1.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            world = new Level1(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }else if (level ==2 ){
            level = 2;
            gameMusic2.stop();
            try {
                gameMusic2 = new SoundClip("data/music2.wav"); // Open an audio input stream
                gameMusic2.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            world = new Level2(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }else if(level ==3 ){
            level = 3;
            gameMusic3.stop();
            try {
                gameMusic3 = new SoundClip("data/music3.wav"); // Open an audio input stream
                gameMusic3.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            world = new Level3(world.health);
            world.populate(this);
            controller.setBody(world.getPlayer());
            view.setPlayer(world.getPlayer());
            view.setWorld(world);
            world.start();
            view.setPlayer(world.getPlayer());
        }
    }
    
    
    /** Is the current level of the game finished? */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }
    
    /** Advance to the next level of the game. */
    public void goNextLevel() {
        world.stop();
        if (level == 3) {
            System.exit(0);
        } else if (level == 1) {
            
            level++; 
            gameMusic1.stop();
            gameMusic3.stop();
            try {
                gameMusic2 = new SoundClip("data/music2.wav"); // Open an audio input stream
                gameMusic2.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            // get a new world
            world = new Level2(world.getPlayer().health);
            // fill it with bodies
            
            world.populate(this);
            world.stop();
            view.setPlayer(world.getPlayer());
           
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
            
            world.start();
        }else if(level == 2){
            level++;
            gameMusic1.stop();
            gameMusic2.stop();
            try {
                gameMusic3 = new SoundClip("data/music3.wav"); // Open an audio input stream
                gameMusic3.loop();  // Set it to continous playback (looping)
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
                System.out.println(f);
            }
            // get a new world
            
            world = new Level3(world.getPlayer().health);
            // fill it with bodies
            world.populate(this);
            
           
            view.setPlayer(world.getPlayer());
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            // show the new world in the view
            view.setWorld(world);
           
            world.start();
        }
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
