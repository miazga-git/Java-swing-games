
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Cactus extends Enemy {
    public static final int Y_LAND = 125;
    private int posX;
    private int width;
    private int height;
    private BufferedImage image;
    private MainCharacter mainCharacter;
    private Rectangle rectBound;

    public Cactus(MainCharacter mainCharacter, int posX, int width, int height, BufferedImage image) {
        this.posX = posX;
        this.width = width;
        this.height = height;
        this.image = image;
        this.mainCharacter = mainCharacter;
        this.rectBound = new Rectangle();
    }

    public void update() {
        this.posX = (int)((float)this.posX - this.mainCharacter.getSpeedX());
    }

    public void draw(Graphics g) {
        g.drawImage(this.image, this.posX, 125 - this.image.getHeight(), (ImageObserver)null);
        g.setColor(Color.red);
    }

    public Rectangle getBound() {
        this.rectBound = new Rectangle();
        this.rectBound.x = this.posX + (this.image.getWidth() - this.width) / 2;
        this.rectBound.y = 125 - this.image.getHeight() + (this.image.getHeight() - this.height) / 2;
        this.rectBound.width = this.width;
        this.rectBound.height = this.height;
        return this.rectBound;
    }

    public boolean isOutOfScreen() {
        return this.posX < -this.image.getWidth();
    }
}
