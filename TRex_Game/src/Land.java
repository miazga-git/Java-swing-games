
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Land {
    public static final int LAND_POSY = 103;
    private List<Land.ImageLand> listLand;
    private BufferedImage land1;
    private BufferedImage land2;
    private BufferedImage land3;
    private MainCharacter mainCharacter;

    public Land(int width, MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
        this.land1 = Resource.getResouceImage("land1.png");
        this.land2 = Resource.getResouceImage("land2.png");
        this.land3 = Resource.getResouceImage("land3.png");
        int numberOfImageLand = width / this.land1.getWidth() + 2;
        this.listLand = new ArrayList();

        for(int i = 0; i < numberOfImageLand; ++i) {
            Land.ImageLand imageLand = new Land.ImageLand();
            imageLand.posX = (float)(i * this.land1.getWidth());
            this.setImageLand(imageLand);
            this.listLand.add(imageLand);
        }

    }

    public void update() {
        Iterator<Land.ImageLand> itr = this.listLand.iterator();
        Land.ImageLand firstElement = (Land.ImageLand)itr.next();
        firstElement.posX -= this.mainCharacter.getSpeedX();

        float previousPosX;
        Land.ImageLand element;
        for(previousPosX = firstElement.posX; itr.hasNext(); previousPosX = element.posX) {
            element = (Land.ImageLand)itr.next();
            element.posX = previousPosX + (float)this.land1.getWidth();
        }

        if (firstElement.posX < (float)(-this.land1.getWidth())) {
            this.listLand.remove(firstElement);
            firstElement.posX = previousPosX + (float)this.land1.getWidth();
            this.setImageLand(firstElement);
            this.listLand.add(firstElement);
        }

    }

    private void setImageLand(Land.ImageLand imgLand) {
        int typeLand = this.getTypeOfLand();
        if (typeLand == 1) {
            imgLand.image = this.land1;
        } else if (typeLand == 3) {
            imgLand.image = this.land3;
        } else {
            imgLand.image = this.land2;
        }

    }

    public void draw(Graphics g) {
        Iterator var2 = this.listLand.iterator();

        while(var2.hasNext()) {
            Land.ImageLand imgLand = (Land.ImageLand)var2.next();
            g.drawImage(imgLand.image, (int)imgLand.posX, 103, (ImageObserver)null);
        }

    }

    private int getTypeOfLand() {
        Random rand = new Random();
        int type = rand.nextInt(10);
        if (type == 1) {
            return 1;
        } else {
            return type == 9 ? 3 : 2;
        }
    }

    private class ImageLand {
        float posX;
        BufferedImage image;

        private ImageLand() {
        }
    }
}
