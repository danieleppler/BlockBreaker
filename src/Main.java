import javax.swing.*;
import java.io.IOException;

public class Main {


    public static void main(String args[]) throws IOException {
        int _ROWS = 4;//Modify number of columns and rows as you wish
        int _COLS = 8;
        GamePlay gamePlay = new GamePlay(_ROWS,_COLS);
        JFrame frame = new JFrame("Block Breaker");
        frame.add(gamePlay);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(10,10,_COLS * 67+10,600);

        frame.setVisible(true);

        frame.setResizable(false);
    }
}
