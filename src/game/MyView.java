package game;

import game.player.Player;
import city.cs.engine.UserView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MyView extends UserView {
    Player player;
    GameLevel world;
    private Image background;

    public MyView(GameLevel world, Player player, int width, int height) {
        super(world, width, height);
        this.player = player;
        this.world = world;
        this.background = new ImageIcon("data/game_background.jpg").getImage();
    }

   
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }
    
    @Override
    protected void paintForeground(Graphics2D g) {
        g.setPaint(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Diamond Count: "+ player.getDiamondCount(),25, 35);
        g.drawString("Lives: "+ player.getHealth(),25, 55);        
        }
    }

       
    