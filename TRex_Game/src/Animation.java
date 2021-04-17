//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package TRex_GAME;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    private List<BufferedImage> list;
    private long deltaTime;
    private int currentFrame = 0;
    private long previousTime;

    public Animation(int deltaTime) {
        this.deltaTime = (long)deltaTime;
        this.list = new ArrayList();
        this.previousTime = 0L;
    }

    public void updateFrame() {
        if (System.currentTimeMillis() - this.previousTime >= this.deltaTime) {
            ++this.currentFrame;
            if (this.currentFrame >= this.list.size()) {
                this.currentFrame = 0;
            }

            this.previousTime = System.currentTimeMillis();
        }

    }

    public void addFrame(BufferedImage image) {
        this.list.add(image);
    }

    public BufferedImage getFrame() {
        return (BufferedImage)this.list.get(this.currentFrame);
    }
}
