import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener {

    private boolean movesRight=false;
    private boolean movesLeft=false;

    private boolean play=false;
    private int score=0;

    private int totalBricks=21;
    private Timer timer;
    private int delay=10;

    private int playerX=310;//polozenie X paletki
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;

    private MapGenerator map;

    private boolean goLow[]=new boolean[22];
    private int brickGoingLowX[]=new int[22];
    private int brickGoingLowY[]=new int[22];
    private int goingLow[]=new int[22];
    private int counter=0;


    public BrickBreaker(){
        for(int i=0;i<22;i++){
            goingLow[i] = 1;
            goLow[i] = false;
        }
        map=new MapGenerator(3,7);
        addKeyListener(this);//podajemy jako arg obiekt klasy implementującej ActionListener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        //drawing map
        map.draw((Graphics2D)g);//
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(" "+score,590,30);
        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX,ballposY,20,20);

        for(int j=0;j<22;j++){
            if(goLow[j]==true){
                g.setColor(Color.white);
                g.fillRect(brickGoingLowX[j],brickGoingLowY[j]+goingLow[j],map.brickWidth,map.brickHeight);

            }
        }


        if(totalBricks<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.YELLOW);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Win! ",260,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart ",230,350);
        }
        if(ballposY>570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over , Scores: "+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press Enter to Restart ",230,350);

        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir=-ballYdir;
                if(movesRight&&ballXdir<0){
                    ballXdir=-ballXdir;
                }
                if(movesLeft&&ballXdir>0){
                    ballXdir=-ballXdir;
                }

            }
            A: for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;

                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect=rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;

                            counter++;
                            goLow[counter]=true;
                            brickGoingLowY[counter]=brickY;
                            brickGoingLowX[counter]=brickX;

                            //counter++;

                            if(ballposX+19<=brickRect.x||ballposX+1>=brickRect.x+brickRect.width){
                                ballXdir=-ballXdir;
                            }
                            else{
                                ballYdir=-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }



            ballposX+=ballXdir;
            ballposY+=ballYdir;
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){
                ballXdir=-ballXdir;
            }
            if(ballposY<0){
                ballYdir=-ballYdir;
            }
            if(ballposX>670){
                ballXdir=-ballXdir;
            }
            for(int j=0;j<22;j++){
                if(goLow[j]==true){
                    goingLow[j]++;
                }
            }
            if(movesRight){

                if(playerX<590)
                    playerX+=6;

                repaint();
            }
            if(movesLeft){

                if(playerX>=10)
                    playerX-=6;
                repaint();
            }

        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            movesLeft=false;
            movesRight=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            movesLeft=false;
            movesRight=false;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ballposX=120;
                ballposY=350;
                ballXdir=-1;
                ballYdir=-2;
                playerX=310;
                score=0;
                totalBricks=21;
                map=new MapGenerator(3,7);
                repaint();
                counter=0;
                for(int i=0;i<22;i++){
                    goingLow[i] = 1;
                    goLow[i] = false;
                }

            }
        }
        if(e.getKeyCode()== MouseEvent.BUTTON3){

        }
        if(e.getKeyCode()== MouseEvent.BUTTON1){

        }
        if(e.getKeyCode()== MouseEvent.BUTTON2){

        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){

            play=true;

            if(!movesLeft){

                movesRight=true;
            }
            else{

                movesRight=false;
                movesLeft=true;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            play=true;
            if(!movesRight){

                movesLeft=true;
            }
            else{

                movesLeft=false;
                movesRight=true;
            }
        }

    }
}




