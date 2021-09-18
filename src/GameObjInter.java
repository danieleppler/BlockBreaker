import java.awt.*;


public interface GameObjInter {

    //set game object location
    void SetPos(int x, int y);

    //draw game object
    void draw(Graphics g, Component c);
}
