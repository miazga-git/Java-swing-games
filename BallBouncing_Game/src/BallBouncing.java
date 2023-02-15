
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallBouncing extends JPanel implements KeyListener, ActionListener {
    private boolean rightMovesUp = false;
    private boolean rightMovesDown = false;
    private boolean leftMovesUp = false;
    private boolean leftMovesDown = false;
    private int leftxlength;
    private int leftylength;
    private int rightxlength;
    private int rightylength;
    private boolean start = true;
    private int ballxpos; //= 437;
    private int ballypos;
    private ImageIcon ballImage;
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private int player1 = 0;
    private int player2 = 0;
    private boolean ballActivated = true;
    private boolean koniec = false;
    private Timer timer;
    private int delay = 1;

    public BallBouncing() {
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(this.delay, this);
        this.timer.start();
    }

    public void paint(Graphics g) {
        if (this.start) {
            this.rightxlength = 825;
            this.rightylength = 200;
            this.leftxlength = 50;
            this.leftylength = 200;
            this.ballxpos = 437;
            this.ballypos = 300;
            this.down = true;
            this.left = true;
        }

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 904, 649);
        g.setColor(Color.RED);
        g.drawRect(24, 24, 851, 577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 25, 850, 575);
        g.setColor(Color.WHITE);
        g.drawLine(450, 25, 450, 600);
        g.setFont(new Font("Consolas", 0, 50));
        g.drawString(String.valueOf(this.player1), 375, 75);
        g.drawString(String.valueOf(this.player2), 500, 75);
        g.setColor(Color.RED);
        g.fillRect(this.rightxlength, this.rightylength, 25, 100);
        g.setColor(Color.BLUE);
        g.fillRect(this.leftxlength, this.leftylength, 25, 100);
        this.ballImage = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\BallBouncing_Game\\ball.png");
        this.ballImage.paintIcon(this, g, this.ballxpos, this.ballypos);
        g.setFont(new Font("Consolas", 0, 20));
        if (!this.ballActivated) {
            g.drawString("Press SPACE to restart the game", 275, 300);
        }

        if (!this.start) {
            if (this.ballypos >= 575) {
                this.down = false;
                this.up = true;
            }

            if (this.ballypos < 25) {
                this.up = false;
                this.down = true;
            }
            //tu jest odbijanie piłki przez prawą paletkę
            if (this.ballxpos >= this.rightxlength - 25 && this.ballxpos < this.rightxlength && this.ballypos >= this.rightylength && this.ballypos < this.rightylength + 100) {
                this.right = false;
                this.left = true;
            }
            //tu jest odbijanie piłki przez lewą paletkę
            if (this.ballxpos <= this.leftxlength + 25 && this.ballxpos > this.leftxlength && this.ballypos >= this.leftylength && this.ballypos < this.leftylength + 100) {
                this.left = false;
                this.right = true;
            }
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        this.timer.start();
        if (this.up) {
            this.ballypos -= 2;
            this.repaint();
        }

        if (this.down) {
            this.ballypos += 2;
            this.repaint();
        }

        if (this.left) {
            this.ballxpos -= 2;
            this.repaint();
        }

        if (this.right) {
            this.ballxpos += 2;
            this.repaint();
        }

        if (this.rightMovesUp) {
            this.start = false;
            if (this.rightylength > 26) {
                this.rightylength -= 6;
            }

            this.repaint();
        }

        if (this.rightMovesDown) {
            this.start = false;
            if (this.rightylength < 500) {
                this.rightylength += 6;
            }

            this.repaint();
        }

        if (this.leftMovesUp) {
            this.start = false;
            if (this.leftylength > 26) {
                this.leftylength -= 6;
            }

            this.repaint();
        }

        if (this.leftMovesDown) {
            this.start = false;
            if (this.leftylength < 500) {
                this.leftylength += 6;
            }

            this.repaint();
        }

        if (this.ballActivated && this.ballxpos > 875) {
            ++this.player1;
            this.ballActivated = false;
        }

        if (this.ballActivated && this.ballxpos < 0) {
            ++this.player2;
            this.ballActivated = false;
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.start = true;
            this.repaint();
            this.up = false;
            this.down = false;
            this.left = false;
            this.right = false;
            this.ballActivated = true;


        }

        if (e.getKeyCode() == 38) {
            if (!this.rightMovesDown) {
                this.rightMovesUp = true;
            } else {
                this.rightMovesUp = false;
                this.rightMovesDown = true;
            }
        }

        if (e.getKeyCode() == 40) {
            if (!this.rightMovesUp) {
                this.rightMovesDown = true;
            } else {
                this.rightMovesDown = false;
                this.rightMovesUp = true;
            }
        }

        if (e.getKeyCode() == 87) {
            if (!this.leftMovesDown) {
                this.leftMovesUp = true;
            } else {
                this.leftMovesUp = false;
                this.leftMovesDown = true;
            }
        }

        if (e.getKeyCode() == 83) {
            if (!this.leftMovesUp) {
                this.leftMovesDown = true;
            } else {
                this.leftMovesDown = false;
                this.leftMovesUp = true;
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38) {
            this.rightMovesDown = false;
            this.rightMovesUp = false;
        }

        if (e.getKeyCode() == 40) {
            this.rightMovesDown = false;
            this.rightMovesUp = false;
        }

        if (e.getKeyCode() == 87) {
            this.leftMovesDown = false;
            this.leftMovesUp = false;
        }

        if (e.getKeyCode() == 83) {
            this.leftMovesDown = false;
            this.leftMovesUp = false;
        }

    }
}
