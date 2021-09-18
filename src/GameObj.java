import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameObj extends Rectangle implements GameObjInter {

    Image pic;

    GameObj(int w, int h, String path) throws IOException {
        this.width = w;
        this.height = h;

        File folderInput = new File(path);
        this.pic = ImageIO.read(folderInput);
    }

    @Override
    public void SetPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g, Component c) {
            g.drawImage(pic, x, y, width, height, c);
    }
}
