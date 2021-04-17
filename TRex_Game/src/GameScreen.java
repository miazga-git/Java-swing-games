
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    private static final int START_GAME_STATE = 0;
    private static final int GAME_PLAYING_STATE = 1;
    private static final int GAME_OVER_STATE = 2;
    private Land land;
    private MainCharacter mainCharacter = new MainCharacter();
    private EnemiesManager enemiesManager;
    private Clouds clouds;
    private Thread thread;
    private boolean isKeyPressed;
    private int gameState = 0;
    private BufferedImage replayButtonImage;
    private BufferedImage gameOverButtonImage;

    public GameScreen() {
        this.land = new Land(600, this.mainCharacter);
        this.mainCharacter.setSpeedX(4);
        this.replayButtonImage = Resource.getResouceImage("replay_button.png");
        this.gameOverButtonImage = Resource.getResouceImage("gameover_text.png");
        this.enemiesManager = new EnemiesManager(this.mainCharacter);
        this.clouds = new Clouds(600, this.mainCharacter);
    }

    public void startGame() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void gameUpdate() {
        if (this.gameState == 1) {
            this.clouds.update();
            this.land.update();
            this.mainCharacter.update();
            this.enemiesManager.update();
            if (this.enemiesManager.isCollision()) {
                this.mainCharacter.playDeadSound();
                this.gameState = 2;
                mainCharacter.score=0;
                this.mainCharacter.dead(true);
            }
        }

    }

    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        switch(this.gameState) {
            case 0:
                this.mainCharacter.draw(g);
                break;
            case 1:
            case 2:

                this.clouds.draw(g);
                this.land.draw(g);
                this.enemiesManager.draw(g);
                this.mainCharacter.draw(g);
                g.setColor(Color.BLACK);
                g.drawString("HI " + this.mainCharacter.score, 500, 20);
                if (this.gameState == 2) {
                    g.drawImage(this.gameOverButtonImage, 200, 30, (ImageObserver)null);
                    g.drawImage(this.replayButtonImage, 283, 50, (ImageObserver)null);
                }
        }

    }

    public void run() {
        int fps = 100;
        long msPerFrame = (long)(1000000000 / fps);
        long lastTime = 0L;
        long var12 = 0L;

        while(true) {
            while(true) {
                this.gameUpdate();
                this.repaint();
                long endProcessGame = System.nanoTime();
                long elapsed = lastTime + msPerFrame - System.nanoTime();
                int msSleep = (int)(elapsed / 1000000L);
                int nanoSleep = (int)(elapsed % 1000000L);
                if (msSleep <= 0) {
                    lastTime = System.nanoTime();
                } else {
                    try {
                        Thread.sleep((long)msSleep, nanoSleep);
                    } catch (InterruptedException var15) {
                        var15.printStackTrace();
                    }

                    lastTime = System.nanoTime();
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!this.isKeyPressed) {
            this.isKeyPressed = true;
            switch(this.gameState) {
                case 0:
                    if (e.getKeyCode() == 32) {
                        this.gameState = 1;
                    }
                    break;
                case 1:
                    if (e.getKeyCode() == 32) {
                        this.mainCharacter.jump();
                    } else if (e.getKeyCode() == 40) {
                        this.mainCharacter.down(true);
                    }
                    break;
                case 2:
                    if (e.getKeyCode() == 32) {
                        this.gameState = 1;
                        this.resetGame();
                    }
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        this.isKeyPressed = false;
        if (this.gameState == 1 && e.getKeyCode() == 40) {
            this.mainCharacter.down(false);
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    private void resetGame() {
        this.enemiesManager.reset();
        this.mainCharacter.dead(false);
        this.mainCharacter.reset();
    }
}
