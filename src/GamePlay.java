import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GamePlay extends JPanel implements ActionListener, KeyListener {
    private boolean isPlay;
    private int score = 0;

    private boolean restart;

    private int TotalBricks;

    private Timer timer;
    private int delay = 8;

    private int playerX;
    private int BallPosX;
    private int BallPosY;
    private int BallXDir;
    private int BallYDir;

    private int rows;
    private int cols;

    private final int _BLOCK_WIDTH = 67;
    private final int _BLOCK_HEIGHT = 37;

    private GameObj paddle = new GameObj(100,8,"paddle.png");
    private Ball Ball = new Ball(20,20,"Ball.png");

    private ArrayList<Block> Blocks = new ArrayList<Block>();


    /**
     * Main frame of the gameplay. Gameobjects ball and paddle initialized as global variables, blocks initialized here.
     * @param r is no. of rows, c is no. of column.
     **/
    GamePlay(int r, int c) throws IOException {

        restartAttr();
        restart = false;
        addKeyListener(this);
        setFocusable(true);

        setFocusTraversalKeysEnabled(false);

        cols = c;
        rows = r;
        TotalBricks = cols * rows;

        GameObj block = new Block(540 / cols, 150 / rows, "Blue_Block.png", BlockType.Blue);
        ;

        for( int i = 0;i < cols ;i++) {
            for(int j = 0; j < rows;j++)
            {
                block.SetPos(1 + (i*66+5),3 + (j * _BLOCK_HEIGHT));
                Block block_temp = (Block) block.clone();
                Blocks.add(block_temp);
            }
        }

        timer = new Timer(delay,this);
        timer.start();
    }

    /**
     * Overrided function of jframe, called every time change is made and paints the frame again
     * @param g game Graphic
     **/
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);

        //borders
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,(cols * 67+10) - 8,3);
        int Border_Right = (cols * 67+10) - 10;
        g.fillRect(Border_Right,0,3,592);

        //Blocks
        for (GameObjInter block:Blocks
             ) {
            block.draw(g,this);
        }

        //paddle
        paddle.SetPos(playerX,550);
        paddle.draw(g,this);

        //ball
        Ball.SetPos(BallPosX,BallPosY);
        Ball.draw(g,this);

        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,30,500);

        //if the user won the game
        if(TotalBricks == 0 ) {
            isPlay =false;
            BallXDir = 0;
            BallYDir = 0;
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Congrats!",150,200);
            g.drawString(" You won",150,240);
        }

        //if the ball "fall" - passed the lowest game border
        if(BallPosY > 570)
        {
            isPlay = false;
            BallXDir = 0;
            BallYDir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Game Over",150,200);
            g.drawString(" Press Enter to play again",150,240);
            restart = true;
        }

        g.dispose();
    }


    /**
     * overrided function of ActionListener, called every time action is made
     * @param e is current action
     **/
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(isPlay)  // if the game is being played
        {
            //if ball intersects with paddle
            if(Ball.intersects(paddle)) {
                BallYDir = - BallYDir; }

            //checks for every block in the blocks arrays, if not destroyed, check if the ball intersect with it.
            A : for (Block block:Blocks
                 ) {
                if(!(block.isDestroyed))
                {
                    if(block.intersects(Ball))
                    {
                        block.isDestroyed = true;
                        TotalBricks -- ;
                        score +=5;
                        BallXDir =- BallXDir;
                        BallYDir = -BallYDir;
                    }
                }
            }

            //empower ball movement
            BallPosX +=BallXDir;
            BallPosY += BallYDir;

            //if ball intersect with left,right and upper borders
            if(BallPosX < 0 )
                BallXDir =- BallXDir;

            if(BallPosY < 0 )
                BallYDir =- BallYDir;

            if(BallPosX >  (cols * 67+10) - 90)
                BallXDir =- BallXDir;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * overried function of Keylistener called every time key in the keyboard is pressed
     * @param e is current key pressed
     **/
    @Override
    public void keyPressed(KeyEvent e) {

        //move right, check if paddle intersects with borders
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX >= (cols * 67+10) - 110 )
            {
                playerX = (cols * 67+10) - 110 ;}
            else {
                MoveRight(); }
        }

        //move left
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX < 10)
            {
                playerX = 10; }
            else {
                MoveLeft(); }
        }

        //if enter pressed, restart the game
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(restart) {
                restartAttr();
                score = 0;
                for (Block b : Blocks) {
                    b.isDestroyed = false;
                }
                restart = false;
                repaint();
            }
        }

    }

    /**
     * restarts game object location on board
     **/
    private void restartAttr() {
        playerX = 310;
        BallPosX= 120;
        BallPosY = 350;
        BallXDir = -1;
        BallYDir = -2;
    }

    private void MoveLeft() {
        isPlay = true;
        playerX -=20;
    }

    private void MoveRight() {
        isPlay = true;
        playerX +=20;
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
