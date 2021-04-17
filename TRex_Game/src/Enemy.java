
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy {
    public Enemy() {
    }

    public abstract void update();

    public abstract void draw(Graphics var1);

    public abstract Rectangle getBound();

    public abstract boolean isOutOfScreen();
}
