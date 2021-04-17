
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;

public class MainCharacter {
    public static final int LAND_POSY = 80;
    public static final float GRAVITY = 0.4F;
    private static final int NORMAL_RUN = 0;
    private static final int JUMPING = 1;
    private static final int DOWN_RUN = 2;
    private static final int DEATH = 3;
    private float posY = 80.0F;
    private float posX = 50.0F;
    private float speedX;
    private float speedY;
    private Rectangle rectBound = new Rectangle();
    public int score = 0;
    private int state = 0;
    private TRex_GAME.Animation normalRunAnim = new TRex_GAME.Animation(90);
    private BufferedImage jumping;
    private TRex_GAME.Animation downRunAnim;
    private BufferedImage deathImage;
    private AudioClip jumpSound;
    private AudioClip deadSound;
    private AudioClip scoreUpSound;

    public MainCharacter() {
        this.normalRunAnim.addFrame(Resource.getResouceImage("main-character1.png"));
        this.normalRunAnim.addFrame(Resource.getResouceImage("main-character2.png"));
        this.jumping = Resource.getResouceImage("main-character3.png");
        this.downRunAnim = new TRex_GAME.Animation(90);
        this.downRunAnim.addFrame(Resource.getResouceImage("main-character5.png"));
        this.downRunAnim.addFrame(Resource.getResouceImage("main-character6.png"));
        this.deathImage = Resource.getResouceImage("main-character4.png");

        try {
            this.jumpSound = Applet.newAudioClip(new URL("file", "", "jump.wav"));
            this.deadSound = Applet.newAudioClip(new URL("file", "", "dead.wav"));
            this.scoreUpSound = Applet.newAudioClip(new URL("file", "", "scoreup.wav"));
        } catch (MalformedURLException var2) {
            var2.printStackTrace();
        }

    }

    public float getSpeedX() {
        return this.speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = (float)speedX;
    }

    public void draw(Graphics g) {
        switch(this.state) {
            case 0:
                g.drawImage(this.normalRunAnim.getFrame(), (int)this.posX, (int)this.posY, (ImageObserver)null);
                break;
            case 1:
                g.drawImage(this.jumping, (int)this.posX, (int)this.posY, (ImageObserver)null);
                break;
            case 2:
                g.drawImage(this.downRunAnim.getFrame(), (int)this.posX, (int)(this.posY + 20.0F), (ImageObserver)null);
                break;
            case 3:
                g.drawImage(this.deathImage, (int)this.posX, (int)this.posY, (ImageObserver)null);
        }

    }

    public void update() {
        this.normalRunAnim.updateFrame();
        this.downRunAnim.updateFrame();
        if (this.posY >= 80.0F) {
            this.posY = 80.0F;
            if (this.state != 2) {
                this.state = 0;
            }
        } else {
            this.speedY += 0.4F;
            this.posY += this.speedY;
        }

    }

    public void jump() {
        if (this.posY >= 80.0F) {
            if (this.jumpSound != null) {
                this.jumpSound.play();
            }

            this.speedY = -7.5F;
            this.posY += this.speedY;
            this.state = 1;
        }

    }

    public void down(boolean isDown) {
        if (this.state != 1) {
            if (isDown) {
                this.state = 2;
            } else {
                this.state = 0;
            }

        }
    }

    public Rectangle getBound() {
        this.rectBound = new Rectangle();
        if (this.state == 2) {
            this.rectBound.x = (int)this.posX + 5;
            this.rectBound.y = (int)this.posY + 20;
            this.rectBound.width = this.downRunAnim.getFrame().getWidth() - 10;
            this.rectBound.height = this.downRunAnim.getFrame().getHeight();
        } else {
            this.rectBound.x = (int)this.posX + 5;
            this.rectBound.y = (int)this.posY;
            this.rectBound.width = this.normalRunAnim.getFrame().getWidth() - 10;
            this.rectBound.height = this.normalRunAnim.getFrame().getHeight();
        }

        return this.rectBound;
    }

    public void dead(boolean isDeath) {
        if (isDeath) {
            this.state = 3;
        } else {
            this.state = 0;
        }

    }

    public void reset() {
        this.posY = 80.0F;
    }

    public void playDeadSound() {
        this.deadSound.play();
    }

    public void upScore() {
        this.score += 20;
        if (this.score % 100 == 0) {
            this.scoreUpSound.play();
        }

    }
}
