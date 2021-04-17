
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Clouds {
    private List<Clouds.ImageCloud> listCloud;
    private BufferedImage cloud;
    private MainCharacter mainCharacter;

    public Clouds(int width, MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        this.cloud = Resource.getResouceImage("cloud.png");
        this.listCloud = new ArrayList();
        Clouds.ImageCloud imageCloud = new Clouds.ImageCloud();
        imageCloud.posX = 0.0F;
        imageCloud.posY = 30;
        this.listCloud.add(imageCloud);
        imageCloud = new Clouds.ImageCloud();
        imageCloud.posX = 150.0F;
        imageCloud.posY = 40;
        this.listCloud.add(imageCloud);
        imageCloud = new Clouds.ImageCloud();
        imageCloud.posX = 300.0F;
        imageCloud.posY = 50;
        this.listCloud.add(imageCloud);
        imageCloud = new Clouds.ImageCloud();
        imageCloud.posX = 450.0F;
        imageCloud.posY = 20;
        this.listCloud.add(imageCloud);
        imageCloud = new Clouds.ImageCloud();
        imageCloud.posX = 600.0F;
        imageCloud.posY = 60;
        this.listCloud.add(imageCloud);
    }

    public void update() {
        Iterator<Clouds.ImageCloud> itr = this.listCloud.iterator();
        Clouds.ImageCloud firstElement = (Clouds.ImageCloud)itr.next();

        Clouds.ImageCloud element;
        for(firstElement.posX -= this.mainCharacter.getSpeedX() / 8.0F; itr.hasNext(); element.posX -= this.mainCharacter.getSpeedX() / 8.0F) {
            element = (Clouds.ImageCloud)itr.next();
        }

        if (firstElement.posX < (float)(-this.cloud.getWidth())) {
            this.listCloud.remove(firstElement);
            firstElement.posX = 600.0F;
            this.listCloud.add(firstElement);
        }

    }

    public void draw(Graphics g) {
        Iterator var2 = this.listCloud.iterator();

        while(var2.hasNext()) {
            Clouds.ImageCloud imgLand = (Clouds.ImageCloud)var2.next();
            g.drawImage(this.cloud, (int)imgLand.posX, imgLand.posY, (ImageObserver)null);
        }

    }

    private class ImageCloud {
        float posX;
        int posY;

        private ImageCloud() {
        }
    }
}
