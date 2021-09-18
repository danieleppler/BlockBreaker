import java.awt.*;
import java.io.IOException;

class Block extends GameObj {

    private BlockType bt;
    int NumberOfColls;
    boolean isDestroyed;

    Block(int w, int h, String path, BlockType bt) throws IOException {
        super(w, h, path);
        isDestroyed = false;
        this.bt = bt;
        NumberOfColls = 0;
    }

    @Override
    public void draw(Graphics g, Component c) {
        if (!isDestroyed) {
            g.drawImage(pic, x, y, width, height, c);
        }
    }
}
