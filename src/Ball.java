import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Ball extends GameObj {
    private Color color;

    Ball(int w, int h, String path) throws IOException {
        super(w, h, path);
    }

    void ChangeColor()
    {
        Random rand = new Random();
        float r = rand. nextFloat();
        float g = rand. nextFloat();
        float b = rand. nextFloat();
        this.color = new Color(r, g, b);
    }
}
