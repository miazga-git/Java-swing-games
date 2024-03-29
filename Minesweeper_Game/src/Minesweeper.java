import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


public class Minesweeper extends JPanel implements MouseListener, ActionListener {
    private Timer timer;
    private int delay = 6;

    private PlayingFields[][] tab = new PlayingFields[20][20];//tablica obiektów czyli pól


    private ImageIcon bomb;
    private ImageIcon flag;
    private ImageIcon happyhead;
    private ImageIcon sadhead;
    private ImageIcon panel;
    private ImageIcon chillhead;

    private boolean defeat=false;
    private boolean win=false;
    private int numberOfCorrectlySelectedBombs=0;

    private int plus = 0;
    private int numberOfFlags;

    private int[] rectxpos = {13, 38, 63, 88, 113, 138, 163, 188, 213, 238, 263, 288, 313, 338, 363, 388, 413,
            438, 463, 488};

    private int[] rectypos = {153, 178, 203, 228, 253, 278, 303, 328, 353, 378, 403, 428, 453, 478
            , 503, 528, 553, 578, 603, 628};

    private int numberofbombs = 15;

    private Random random = new Random();
    private int xpos;
    private int ypos;


    public Minesweeper() {

        numberOfFlags=numberofbombs;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tab[i][j] = new PlayingFields();
            }
        }
        addMouseListener(this);//podajemy jako arg obiekt klasy implementującej ActionListener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

        tab[1][2].coordinateY = 1;

        int licznikX = 10;
        int licznikY;
        for (int i = 0; i < 20; i++) {
            licznikY = 150;
            for (int j = 0; j < 20; j++) {
                tab[i][j].coordinateY = licznikY;
                tab[i][j].coordinateX = licznikX;
                licznikY += 25;
            }
            licznikX += 25;

        }

        //ustawianie bomb
        for (int i = 0; i < numberofbombs; i++) {
            xpos = random.nextInt(20);
            ypos = random.nextInt(20);
            if (tab[xpos][ypos].isBomb == false) {
                tab[xpos][ypos].isBomb = true;
            } else {
                i--;
            }

        }
        zliczanieBomb(tab);
    }

    public void zliczanieBomb(PlayingFields[][] tab1) {
        int licznik = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if (i - 1 >= 0 && j - 1 >= 0 && tab[i - 1][j - 1].isBomb == true) {
                    licznik++;
                }
                if (i - 1 >= 0 && tab[i - 1][j].isBomb == true) {
                    licznik++;
                }
                if (i - 1 >= 0 && j + 1 < 20 && tab[i - 1][j + 1].isBomb == true) {
                    licznik++;
                }
                if (j - 1 >= 0 && tab[i][j - 1].isBomb == true) {
                    licznik++;
                }
                if (j + 1 < 20 && tab[i][j + 1].isBomb == true) {
                    licznik++;
                }
                if (i + 1 < 20 && j - 1 >= 0 && tab[i + 1][j - 1].isBomb == true) {
                    licznik++;
                }
                if (i + 1 < 20 && tab[i + 1][j].isBomb == true) {
                    licznik++;
                }
                if (i + 1 < 20 && j + 1 < 20 && tab[i + 1][j + 1].isBomb == true) {
                    licznik++;
                }
                tab[i][j].number = licznik;
                licznik = 0;
            }
        }
    }
    public void odslanianiePrzestrzenii(int i,int j){

        //dla tab[i][j].number>0 nie wywolujemy tej funkcji znowu
        //dla tab[i][j].number==0 wywolujemy funkcje przekazując do niej
        if (tab[i][j].isDiscovered == true&&tab[i][j].isBomb==false) {

            if (i - 1 >= 0 && j - 1 >= 0 &&tab[i-1][j-1].isBomb==false&&tab[i-1][j-1].isDiscovered==false) {// tab[i - 1][j - 1].number == 0&&
                tab[i - 1][j - 1].isDiscovered = true;
                if(tab[i-1][j-1].number==0)
                    odslanianiePrzestrzenii(i-1,j-1);
            }
            if (i - 1 >= 0 && tab[i-1][j].isBomb==false&&tab[i-1][j].isDiscovered==false) {//tab[i - 1][j].number == 0&&

                tab[i - 1][j].isDiscovered = true;
                if(tab[i-1][j].number==0)
                    odslanianiePrzestrzenii(i-1,j);

            }
            if (i - 1 >= 0 && j + 1 < 20&&tab[i-1][j+1].isBomb==false&&tab[i-1][j+1].isDiscovered==false) {// && tab[i - 1][j + 1].number == 0
                tab[i - 1][j + 1].isDiscovered = true;
                if(tab[i-1][j+1].number==0)
                    odslanianiePrzestrzenii(i-1,j+1);
            }
            if (j - 1 >= 0 && tab[i][j-1].isBomb==false&&tab[i][j-1].isDiscovered==false) {//tab[i][j - 1].number == 0&&
                tab[i][j - 1].isDiscovered = true;
                if(tab[i][j-1].number==0)
                    odslanianiePrzestrzenii(i,j-1);
            }
            if (j + 1 < 20 && tab[i][j+1].isBomb==false&&tab[i][j+1].isDiscovered==false) {//tab[i][j + 1].number == 0&&
                tab[i][j + 1].isDiscovered = true;
                if(tab[i][j+1].number==0)
                    odslanianiePrzestrzenii(i,j+1);
            }
            if (i + 1 < 20 && j - 1 >= 0 &&tab[i+1][j-1].isBomb==false&&tab[i+1][j-1].isDiscovered==false) {//&& tab[i + 1][j - 1].number == 0
                tab[i + 1][j - 1].isDiscovered = true;
                if(tab[i+1][j-1].number==0)
                    odslanianiePrzestrzenii(i+1,j-1);
            }
            if (i + 1 < 20 && tab[i+1][j].isBomb==false&&tab[i+1][j].isDiscovered==false) {//tab[i + 1][j].number == 0&&
                tab[i + 1][j].isDiscovered = true;
                if(tab[i+1][j].number==0)
                    odslanianiePrzestrzenii(i+1,j);
            }
            if (i + 1 < 20 && j + 1 < 20 && tab[i+1][j+1].isBomb==false&&tab[i+1][j+1].isDiscovered==false) {//tab[i + 1][j + 1].number == 0&&
                tab[i + 1][j + 1].isDiscovered = true;
                if(tab[i+1][j+1].number==0)
                    odslanianiePrzestrzenii(i+1,j+1);
            }


        }
        repaint();

    }


    public void paint(Graphics g) {

        bomb = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\bomb.png");//20x22pix

        flag = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\flag.png");//20x22pix
        panel = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\panel.png");//24x24pix
        sadhead = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\sadhead.png");//24x24pix
        happyhead = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\happyhead.png");//24x24pix
        chillhead = new ImageIcon("C:\\Users\\ACER\\Documents\\GitHub\\Java-swing-games\\Minesweeper_Game\\chillhead.png");//24x24pix




        // flag.paintIcon(this,g,38,178);//35+3/10+3

        //Color o=new Color(190,190,190);
        // g.setColor(o);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1, 1, 692, 692);

        if(!defeat&&win==false){
            happyhead.paintIcon(this, g,235,90);
        }
        if(defeat){
            sadhead.paintIcon(this, g,235,90);
        }
        if(win){
            chillhead.paintIcon(this, g,235,90);
        }

        // g.setColor(Color.RED);
        // g.setFont(new Font("Consolas",Font.PLAIN,50));

        g.setColor(Color.BLACK);

        for (int i = 0; i < 22; i++) {
            g.drawLine(10, 150 + plus, 510, 150 + plus);
            g.drawLine(10 + plus, 150, 10 + plus, 650);
            plus += 25;
        }
        plus = 0;
        //rysowanie gornegopanelu
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tab[i][j].isDiscovered == false) {
                    panel.paintIcon(this, g, tab[i][j].coordinateX+1 , tab[i][j].coordinateY+1);
                }
            }
        }

        //rysowanie ilosci flag
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas",Font.PLAIN,50));
        g.drawString(String.valueOf(numberOfFlags),40,140);//y125


        //rysowanie flag
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tab[i][j].isFlag == true) {
                    flag.paintIcon(this, g, tab[i][j].coordinateX + 3, tab[i][j].coordinateY + 3);
                }
            }
        }

        //rysowanie bomb
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (defeat&&tab[i][j].isBomb == true) {//tab[i][j].isBomb == true && tab[i][j].isDiscovered == true
                    bomb.paintIcon(this, g, tab[i][j].coordinateX + 3, tab[i][j].coordinateY + 3);
                }
            }
        }
        g.setColor(Color.BLUE);


        //rysowanie numerkow
        g.setFont(new Font("serif", Font.BOLD, 23));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tab[i][j].number > 0 && tab[i][j].isBomb == false && tab[i][j].isDiscovered == true) {
                    if(tab[i][j].number==1){
                        g.setColor(Color.BLUE);
                    }
                    if(tab[i][j].number==2){
                        g.setColor(new Color(0,100,0));
                    }
                    if(tab[i][j].number==3){
                        g.setColor(Color.RED);
                    }
                    if(tab[i][j].number==4){
                        g.setColor(new Color(0,0,100));
                    }
                    if(tab[i][j].number==5){
                        g.setColor(new Color(100,0,0));
                    }
                    if(tab[i][j].number==6){
                        g.setColor(new Color(0,120,100));
                    }
                    g.drawString("" + tab[i][j].number, tab[i][j].coordinateX + 9, tab[i][j].coordinateY + 20);
                }
            }
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {//Prawy Przycisk Myszki
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    if(tab[i][j].coordinateX<=e.getX()&&e.getX()<=tab[i][j].coordinateX+24){
                        if(tab[i][j].coordinateY<=e.getY()&&e.getY()<=tab[i][j].coordinateY+24) {
                            if(tab[i][j].isFlag ==false&&numberOfFlags>0){
                                tab[i][j].isFlag = true;
                                if(tab[i][j].isBomb==true&&tab[i][j].isFlag==true){
                                    numberOfCorrectlySelectedBombs++;
                                }
                                numberOfFlags--;

                            }
                            else if(tab[i][j].isFlag ==true){
                                tab[i][j].isFlag = false;
                                numberOfFlags++;
                                if(tab[i][j].isBomb==true&&tab[i][j].isFlag==false){
                                    numberOfCorrectlySelectedBombs--;
                                }
                            }

                        }
                    }
                }
            }
            repaint();
        }

        if (e.getButton() == MouseEvent.BUTTON1) {//Lewy Przycisk Myszki
            System.out.println("Mouse clicked.Your such a good programmer! x = " + e.getX() + " ,y = " + e.getY());
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    if(tab[i][j].coordinateX<=e.getX()&&e.getX()<=tab[i][j].coordinateX+24){
                        if(tab[i][j].coordinateY<=e.getY()&&e.getY()<=tab[i][j].coordinateY+24) {
                            tab[i][j].isDiscovered = true;
                            if(tab[i][j].isBomb == true)
                                defeat=true;

                            //odsłanianie wolnych przestrzenii
                            //dla tab[i][j].number>0 nie wywolujemy tej funkcji znowu
                            //dla tab[i][j].number==0 wywolujemy funkcje przekazując do niej

                            odslanianiePrzestrzenii(i,j);


                        }
                    }
                }
            }

            if(235<=e.getX()&&e.getX()<=285){

                if(90<=e.getY()&&e.getY()<=140){

                    numberOfCorrectlySelectedBombs=0;
                    numberOfFlags=numberofbombs;
                    for(int i=0;i<20;i++){
                        for(int j=0;j<20;j++){
                            tab[i][j].isFlag=false;
                            tab[i][j].isBomb=false;
                            tab[i][j].isDiscovered=false;
                            tab[i][j].number=0;
                            defeat=false;
                            win=false;


                        }
                    }
                    for (int i = 0; i < numberofbombs; i++) {
                        xpos = random.nextInt(20);
                        ypos = random.nextInt(20);
                        if (tab[xpos][ypos].isBomb == false) {
                            tab[xpos][ypos].isBomb = true;
                        } else {
                            i--;
                        }

                    }
                    zliczanieBomb(tab);
                    repaint();
                }
            }

            repaint();



        }

        if(numberOfCorrectlySelectedBombs==numberofbombs){
            win=true;
        }





    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
