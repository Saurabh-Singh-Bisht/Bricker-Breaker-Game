import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.RecursiveAction;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score;
    private int totalBricks =28;
    private int delay = 8;
    private Timer timer;
    private int playerX =310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGernator map;
    private int level = 1;
    GamePlay(){

        map = new MapGernator(4, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1, 692, 592);

        //Drawing map
        map.draw((Graphics2D) g);


        //Scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Digital-7 Mono", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        //the paddle
        g.setColor(Color.green);
        g.fillRoundRect(playerX, 550,100,8, 2, 2);

        //the ball
        g.setColor(Color.cyan);
        g.fillOval(ballposX, ballposY, 20,20);

        //border
        g.setColor(Color.GRAY);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(681,0,3,592);
        g.fillRect(0,558,692,3);

        if(play == false && score == 0){
            g.setColor(new Color(11, 255, 0));
            g.setFont(new Font("Robus", Font.PLAIN, 80));
            g.drawString("Press Enter To Start:", 30, 300);
        }

        //If total bricks is equal to 0:
        if(totalBricks <= 0){
            play = false;
            ballXdir =0;
            ballYdir =0;
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("YOU WON", 250, 300);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 100, 350);
            level = 2;
        }
        //If ball goes below bottom border:
        if(ballposY > 570){
            play = false;
//            ballXdir =0;
//            ballYdir =0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("robus", Font.PLAIN, 70));
            g.drawString("Game Over:" + score, 220, 300);
            g.setFont(new Font("robus", Font.PLAIN, 70));
            g.drawString("Press Enter to Restart", 50, 350);
        }
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            //If ball intersects paddle:
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i *map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i , j);
                            totalBricks--;
                            score += 5;
                            if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickWidth){
                                ballXdir = - ballXdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            //For Left Border:
            if(ballposX < 0+3){
                ballXdir = -ballXdir;
            }
            //For Top Border:
            if(ballposY < 0+3){
                ballYdir = -ballYdir;
            }
            //For Right Border:
            if(ballposX > 700-3-35){
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            play = true;

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX > 580){
                playerX = 580;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        //If user completed level 1:
        if((level == 2 && e.getKeyCode() == KeyEvent.VK_ENTER) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if(play){
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 30;
                delay = 9;
                map = new MapGernator(5, 10);

                repaint();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 28;
                map = new MapGernator(4, 7);

                repaint();
            }
        }
        //Restart:
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(play){
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks = 28;
                map = new MapGernator(4, 7);

                repaint();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
