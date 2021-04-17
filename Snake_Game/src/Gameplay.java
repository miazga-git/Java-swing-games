//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private int[] enemyxpos = new int[]{25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] enemyypos = new int[]{75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
    private ImageIcon enemyImage;
    private Random random = new Random();
    private int xpos;
    private int ypos;
    private int moves;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;
    private int lengthofsnake;
    private Timer timer;
    private int delay;
    private ImageIcon snakeimage;
    private ImageIcon titleImage;
    private int score;
    private boolean endGame;

    public Gameplay() {
        this.xpos = this.random.nextInt(34);
        this.ypos = this.random.nextInt(23);
        this.moves = 0;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.lengthofsnake = 3;
        this.delay = 100;
        this.score = 0;
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.timer = new Timer(this.delay, this);
        this.timer.start();
        this.endGame=false;
    }

    public void paint(Graphics g) {
        if (this.moves == 0) {
            this.snakexlength[2] = 50;
            this.snakexlength[1] = 75;
            this.snakexlength[0] = 100;
            this.snakeylength[2] = 100;
            this.snakeylength[1] = 100;
            this.snakeylength[0] = 100;
        }

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 904, 699);
        g.setColor(Color.RED);
        g.drawRect(24, 10, 851, 55);
        this.titleImage = new ImageIcon("snaketitle.jpg");
        this.titleImage.paintIcon(this, g, 25, 11);
        g.setColor(Color.RED);
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 14));
        g.drawString("Scores: " + this.score, 780, 30);
        g.setColor(Color.white);
        g.setFont(new Font("arial", 0, 14));
        g.drawString("Length: " + this.lengthofsnake, 780, 50);
        g.setColor(Color.RED);
        g.fillRect(150, 300, 300, 25);
        this.rightmouth = new ImageIcon("rightmouth.png");
        this.rightmouth.paintIcon(this, g, this.snakexlength[0], this.snakeylength[0]);

        int a;
        for(a = 0; a < this.lengthofsnake; ++a) {
            if (a == 0 && this.right) {
                this.rightmouth = new ImageIcon("rightmouth.png");
                this.rightmouth.paintIcon(this, g, this.snakexlength[a], this.snakeylength[a]);
            }

            if (a == 0 && this.left) {
                this.leftmouth = new ImageIcon("leftmouth.png");
                this.leftmouth.paintIcon(this, g, this.snakexlength[a], this.snakeylength[a]);
            }

            if (a == 0 && this.down) {
                this.downmouth = new ImageIcon("downmouth.png");
                this.downmouth.paintIcon(this, g, this.snakexlength[a], this.snakeylength[a]);
            }

            if (a == 0 && this.up) {
                this.upmouth = new ImageIcon("upmouth.png");
                this.upmouth.paintIcon(this, g, this.snakexlength[a], this.snakeylength[a]);
            }

            if (a != 0) {
                this.snakeimage = new ImageIcon("snakeimage.png");
                this.snakeimage.paintIcon(this, g, this.snakexlength[a], this.snakeylength[a]);
            }
        }

        this.enemyImage = new ImageIcon("enemy.png");
        if (this.enemyxpos[this.xpos] == this.snakexlength[0] && this.enemyypos[this.ypos] == this.snakeylength[0]) {
            ++this.lengthofsnake;
            ++this.score;
            this.xpos = this.random.nextInt(34);
            this.ypos = this.random.nextInt(23);
        }

        this.enemyImage.paintIcon(this, g, this.enemyxpos[this.xpos], this.enemyypos[this.ypos]);

        for(a = 1; a < this.lengthofsnake; ++a) {
            if (this.snakexlength[a] == this.snakexlength[0] && this.snakeylength[a] == this.snakeylength[0]) {
                this.right = false;
                this.left = false;
                this.down = false;
                this.up = false;
                g.setColor(Color.white);
                g.setFont(new Font("arial", 1, 50));
                g.drawString("Game Over", 300, 300);
                g.setFont(new Font("arial", 1, 20));
                g.drawString("Space to RESTART", 350, 340);
            }
        }

        if (this.snakexlength[0] > 850 || this.snakexlength[0] < 25 || this.snakeylength[0] < 75 || this.snakeylength[0] > 625) {
            this.right = false;
            this.left = false;
            this.down = false;
            this.up = false;
            g.setColor(Color.white);
            g.setFont(new Font("arial", 1, 50));
            g.drawString("Game Over", 300, 300);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Space to RESTART", 350, 340);
            endGame=true;
        }

        if (this.snakexlength[0] >= 150 && this.snakexlength[0] < 450 && this.snakeylength[0] >= 300 && this.snakeylength[0] < 325) {
            this.right = false;
            this.left = false;
            this.down = false;
            this.up = false;
            g.setColor(Color.white);
            g.setFont(new Font("arial", 1, 50));
            g.drawString("Game Over", 300, 300);
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Space to RESTART", 350, 340);
            endGame=true;
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        this.timer.start();
        int r;
        if (this.right) {
            for(r = this.lengthofsnake - 1; r >= 1; --r) {
                this.snakeylength[r] = this.snakeylength[r - 1];
            }

            for(r = this.lengthofsnake - 1; r >= 0; --r) {
                if (r == 0) {
                    this.snakexlength[r] += 25;
                } else {
                    this.snakexlength[r] = this.snakexlength[r - 1];
                }
            }

            this.repaint();
        }

        if (this.left) {
            for(r = this.lengthofsnake - 1; r >= 1; --r) {
                this.snakeylength[r] = this.snakeylength[r - 1];
            }

            for(r = this.lengthofsnake - 1; r >= 0; --r) {
                if (r == 0) {
                    this.snakexlength[r] -= 25;
                } else {
                    this.snakexlength[r] = this.snakexlength[r - 1];
                }
            }

            this.repaint();
        }

        if (this.up) {
            for(r = this.lengthofsnake - 1; r >= 1; --r) {
                this.snakexlength[r] = this.snakexlength[r - 1];
            }

            for(r = this.lengthofsnake - 1; r >= 0; --r) {
                if (r == 0) {
                    this.snakeylength[r] -= 25;
                } else {
                    this.snakeylength[r] = this.snakeylength[r - 1];
                }
            }

            this.repaint();
        }

        if (this.down) {
            for(r = this.lengthofsnake - 1; r >= 1; --r) {
                this.snakexlength[r] = this.snakexlength[r - 1];
            }

            for(r = this.lengthofsnake - 1; r >= 0; --r) {
                if (r == 0) {
                    this.snakeylength[r] += 25;
                } else {
                    this.snakeylength[r] = this.snakeylength[r - 1];
                }
            }

            this.repaint();
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.moves = 0;
            this.score = 0;
            this.lengthofsnake = 3;
            this.repaint();
            endGame=false;
        }

        if (e.getKeyCode() == 39&&endGame==false) {
            ++this.moves;
            if (!this.left) {
                this.right = true;
            } else {
                this.right = false;
                this.left = true;
            }

            this.up = false;
            this.down = false;
        }

        if (e.getKeyCode() == 37&&endGame==false) {
            ++this.moves;
            if (!this.right) {
                this.left = true;
            } else {
                this.left = false;
                this.right = true;
            }

            this.up = false;
            this.down = false;
        }

        if (e.getKeyCode() == 38&&endGame==false) {
            ++this.moves;
            if (!this.down) {
                this.up = true;
            } else {
                this.up = false;
                this.down = true;
            }

            this.right = false;
            this.left = false;
        }

        if (e.getKeyCode() == 40&&endGame==false) {
            ++this.moves;
            if (!this.up) {
                this.down = true;
            } else {
                this.down = false;
                this.up = true;
            }

            this.right = false;
            this.left = false;
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}
