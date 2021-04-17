
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class EnemiesManager {
    private BufferedImage cactus1 = Resource.getResouceImage("cactus1.png");
    private BufferedImage cactus2 = Resource.getResouceImage("cactus2.png");
    private Random rand = new Random();
    private List<Enemy> enemies = new ArrayList();
    private MainCharacter mainCharacter;

    public EnemiesManager(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        this.enemies.add(this.createEnemy());
    }

    public void update() {
        Iterator var1 = this.enemies.iterator();

        while(var1.hasNext()) {
            Enemy e = (Enemy)var1.next();
            e.update();
        }

        Enemy enemy = (Enemy)this.enemies.get(0);
        if (enemy.isOutOfScreen()) {
            this.mainCharacter.upScore();
            this.enemies.clear();
            this.enemies.add(this.createEnemy());
        }

    }

    public void draw(Graphics g) {
        Iterator var2 = this.enemies.iterator();

        while(var2.hasNext()) {
            Enemy e = (Enemy)var2.next();
            e.draw(g);
        }

    }

    private Enemy createEnemy() {
        int type = this.rand.nextInt(2);
        return type == 0 ? new Cactus(this.mainCharacter, 800, this.cactus1.getWidth() - 10, this.cactus1.getHeight() - 10, this.cactus1) : new Cactus(this.mainCharacter, 800, this.cactus2.getWidth() - 10, this.cactus2.getHeight() - 10, this.cactus2);
    }

    public boolean isCollision() {
        Iterator var1 = this.enemies.iterator();

        Enemy e;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            e = (Enemy)var1.next();
        } while(!this.mainCharacter.getBound().intersects(e.getBound()));

        return true;
    }

    public void reset() {
        this.enemies.clear();
        this.enemies.add(this.createEnemy());
    }
}
