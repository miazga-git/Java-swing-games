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

    private PolaGry[][] tab = new PolaGry[20][20];//tablica obiektów czyli pól


    private ImageIcon bomb;
    private ImageIcon flag;
    private ImageIcon happyhead;
    private ImageIcon sadhead;
    private ImageIcon gornypanel;
    private ImageIcon czilerahead;

    private boolean przegrana=false;
    private boolean wygrana=false;
    private int liczba_poprawnie_zazn_bomb=0;

    private int plus = 0;
    private int iloscFlag;

    private int[] rectxpos = {13, 38, 63, 88, 113, 138, 163, 188, 213, 238, 263, 288, 313, 338, 363, 388, 413,
            438, 463, 488};

    private int[] rectypos = {153, 178, 203, 228, 253, 278, 303, 328, 353, 378, 403, 428, 453, 478
            , 503, 528, 553, 578, 603, 628};

    private int numberofbombs = 290;

    private Random random = new Random();
    private int xpos;
    private int ypos;


    public Minesweeper() {

        iloscFlag=numberofbombs;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tab[i][j] = new PolaGry();
            }
        }
        addMouseListener(this);//podajemy jako arg obiekt klasy implementującej ActionListener
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

        tab[1][2].wspolrzednaY = 1;

        int licznikX = 10;
        int licznikY;
        for (int i = 0; i < 20; i++) {
            licznikY = 150;
            for (int j = 0; j < 20; j++) {
                tab[i][j].wspolrzednaY = licznikY;
                tab[i][j].wspolrzednaX = licznikX;
                licznikY += 25;
            }
            licznikX += 25;

        }

        //ustawianie bomb
        for (int i = 0; i < numberofbombs; i++) {
            xpos = random.nextInt(20);
            ypos = random.nextInt(20);
            if (tab[xpos][ypos].czyJestBomba == false) {
                tab[xpos][ypos].czyJestBomba = true;
            } else {
                i--;
            }

        }
        zliczanieBomb(tab);
    }

    public void zliczanieBomb(PolaGry[][] tab1) {
        int licznik = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if (i - 1 >= 0 && j - 1 >= 0 && tab[i - 1][j - 1].czyJestBomba == true) {
                    licznik++;
                }
                if (i - 1 >= 0 && tab[i - 1][j].czyJestBomba == true) {
                    licznik++;
                }
                if (i - 1 >= 0 && j + 1 < 20 && tab[i - 1][j + 1].czyJestBomba == true) {
                    licznik++;
                }
                if (j - 1 >= 0 && tab[i][j - 1].czyJestBomba == true) {
                    licznik++;
                }
                if (j + 1 < 20 && tab[i][j + 1].czyJestBomba == true) {
                    licznik++;
                }
                if (i + 1 < 20 && j - 1 >= 0 && tab[i + 1][j - 1].czyJestBomba == true) {
                    licznik++;
                }
                if (i + 1 < 20 && tab[i + 1][j].czyJestBomba == true) {
                    licznik++;
                }
                if (i + 1 < 20 && j + 1 < 20 && tab[i + 1][j + 1].czyJestBomba == true) {
                    licznik++;
                }
                tab[i][j].liczba = licznik;
                licznik = 0;
            }
        }
    }
    public void odslanianiePrzestrzenii(int i,int j){

        //dla tab[i][j].liczba>0 nie wywolujemy tej funkcji znowu
        //dla tab[i][j].liczba==0 wywolujemy funkcje przekazując do niej
        if (tab[i][j].czyOdkryte == true&&tab[i][j].czyJestBomba==false) {

            if (i - 1 >= 0 && j - 1 >= 0 &&tab[i-1][j-1].czyJestBomba==false&&tab[i-1][j-1].czyOdkryte==false) {// tab[i - 1][j - 1].liczba == 0&&
                tab[i - 1][j - 1].czyOdkryte = true;
                if(tab[i-1][j-1].liczba==0)
                    odslanianiePrzestrzenii(i-1,j-1);
            }
            if (i - 1 >= 0 && tab[i-1][j].czyJestBomba==false&&tab[i-1][j].czyOdkryte==false) {//tab[i - 1][j].liczba == 0&&

                tab[i - 1][j].czyOdkryte = true;
                if(tab[i-1][j].liczba==0)
                    odslanianiePrzestrzenii(i-1,j);

            }
            if (i - 1 >= 0 && j + 1 < 20&&tab[i-1][j+1].czyJestBomba==false&&tab[i-1][j+1].czyOdkryte==false) {// && tab[i - 1][j + 1].liczba == 0
                tab[i - 1][j + 1].czyOdkryte = true;
                if(tab[i-1][j+1].liczba==0)
                    odslanianiePrzestrzenii(i-1,j+1);
            }
            if (j - 1 >= 0 && tab[i][j-1].czyJestBomba==false&&tab[i][j-1].czyOdkryte==false) {//tab[i][j - 1].liczba == 0&&
                tab[i][j - 1].czyOdkryte = true;
                if(tab[i][j-1].liczba==0)
                    odslanianiePrzestrzenii(i,j-1);
            }
            if (j + 1 < 20 && tab[i][j+1].czyJestBomba==false&&tab[i][j+1].czyOdkryte==false) {//tab[i][j + 1].liczba == 0&&
                tab[i][j + 1].czyOdkryte = true;
                if(tab[i][j+1].liczba==0)
                    odslanianiePrzestrzenii(i,j+1);
            }
            if (i + 1 < 20 && j - 1 >= 0 &&tab[i+1][j-1].czyJestBomba==false&&tab[i+1][j-1].czyOdkryte==false) {//&& tab[i + 1][j - 1].liczba == 0
                tab[i + 1][j - 1].czyOdkryte = true;
                if(tab[i+1][j-1].liczba==0)
                    odslanianiePrzestrzenii(i+1,j-1);
            }
            if (i + 1 < 20 && tab[i+1][j].czyJestBomba==false&&tab[i+1][j].czyOdkryte==false) {//tab[i + 1][j].liczba == 0&&
                tab[i + 1][j].czyOdkryte = true;
                if(tab[i+1][j].liczba==0)
                    odslanianiePrzestrzenii(i+1,j);
            }
            if (i + 1 < 20 && j + 1 < 20 && tab[i+1][j+1].czyJestBomba==false&&tab[i+1][j+1].czyOdkryte==false) {//tab[i + 1][j + 1].liczba == 0&&
                tab[i + 1][j + 1].czyOdkryte = true;
                if(tab[i+1][j+1].liczba==0)
                    odslanianiePrzestrzenii(i+1,j+1);
            }


        }
        repaint();

    }


    public void paint(Graphics g) {

        bomb = new ImageIcon("bomb4.png");//20x22pix

        flag = new ImageIcon("flag2.png");//20x22pix
        gornypanel = new ImageIcon("gornypanel.png");//24x24pix
        sadhead = new ImageIcon("sadhead.png");//24x24pix
        happyhead = new ImageIcon("happyhead.png");//24x24pix
        czilerahead = new ImageIcon("czilerahead.png");//24x24pix




        // flag.paintIcon(this,g,38,178);//35+3/10+3

        //Color o=new Color(190,190,190);
        // g.setColor(o);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1, 1, 692, 692);

        if(!przegrana&&wygrana==false){
            happyhead.paintIcon(this, g,235,90);
        }
        if(przegrana){
            sadhead.paintIcon(this, g,235,90);
        }
        if(wygrana){
            czilerahead.paintIcon(this, g,235,90);
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
                if (tab[i][j].czyOdkryte == false) {
                    gornypanel.paintIcon(this, g, tab[i][j].wspolrzednaX+1 , tab[i][j].wspolrzednaY+1);
                }
            }
        }

        //rysowanie ilosci flag
        g.setColor(Color.RED);
        g.setFont(new Font("Consolas",Font.PLAIN,50));
        g.drawString(String.valueOf(iloscFlag),40,140);//y125


        //rysowanie flag
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tab[i][j].czyJestFlaga == true) {
                    flag.paintIcon(this, g, tab[i][j].wspolrzednaX + 3, tab[i][j].wspolrzednaY + 3);
                }
            }
        }

        //rysowanie bomb
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (przegrana&&tab[i][j].czyJestBomba == true) {//tab[i][j].czyJestBomba == true && tab[i][j].czyOdkryte == true
                    bomb.paintIcon(this, g, tab[i][j].wspolrzednaX + 3, tab[i][j].wspolrzednaY + 3);
                }
            }
        }
        g.setColor(Color.BLUE);


        //rysowanie numerkow
        g.setFont(new Font("serif", Font.BOLD, 23));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (tab[i][j].liczba > 0 && tab[i][j].czyJestBomba == false && tab[i][j].czyOdkryte == true) {
                    if(tab[i][j].liczba==1){
                        g.setColor(Color.BLUE);
                    }
                    if(tab[i][j].liczba==2){
                        g.setColor(new Color(0,100,0));
                    }
                    if(tab[i][j].liczba==3){
                        g.setColor(Color.RED);
                    }
                    if(tab[i][j].liczba==4){
                        g.setColor(new Color(0,0,100));
                    }
                    if(tab[i][j].liczba==5){
                        g.setColor(new Color(100,0,0));
                    }
                    if(tab[i][j].liczba==6){
                        g.setColor(new Color(0,120,100));
                    }
                    g.drawString("" + tab[i][j].liczba, tab[i][j].wspolrzednaX + 9, tab[i][j].wspolrzednaY + 20);
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
                    if(tab[i][j].wspolrzednaX<=e.getX()&&e.getX()<=tab[i][j].wspolrzednaX+24){
                        if(tab[i][j].wspolrzednaY<=e.getY()&&e.getY()<=tab[i][j].wspolrzednaY+24) {
                            if(tab[i][j].czyJestFlaga ==false&&iloscFlag>0){
                                tab[i][j].czyJestFlaga = true;
                                if(tab[i][j].czyJestBomba==true&&tab[i][j].czyJestFlaga==true){
                                    liczba_poprawnie_zazn_bomb++;
                                }
                                iloscFlag--;

                            }
                            else if(tab[i][j].czyJestFlaga ==true){
                                tab[i][j].czyJestFlaga = false;
                                iloscFlag++;
                                if(tab[i][j].czyJestBomba==true&&tab[i][j].czyJestFlaga==false){
                                    liczba_poprawnie_zazn_bomb--;
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
                    if(tab[i][j].wspolrzednaX<=e.getX()&&e.getX()<=tab[i][j].wspolrzednaX+24){
                        if(tab[i][j].wspolrzednaY<=e.getY()&&e.getY()<=tab[i][j].wspolrzednaY+24) {
                            tab[i][j].czyOdkryte = true;
                            if(tab[i][j].czyJestBomba == true)
                                przegrana=true;

                            //odsłanianie wolnych przestrzenii
                            //dla tab[i][j].liczba>0 nie wywolujemy tej funkcji znowu
                            //dla tab[i][j].liczba==0 wywolujemy funkcje przekazując do niej

                            odslanianiePrzestrzenii(i,j);


                        }
                    }
                }
            }

            if(235<=e.getX()&&e.getX()<=285){

                if(90<=e.getY()&&e.getY()<=140){

                    liczba_poprawnie_zazn_bomb=0;
                    iloscFlag=numberofbombs;
                    for(int i=0;i<20;i++){
                        for(int j=0;j<20;j++){
                            tab[i][j].czyJestFlaga=false;
                            tab[i][j].czyJestBomba=false;
                            tab[i][j].czyOdkryte=false;
                            tab[i][j].liczba=0;
                            przegrana=false;
                            wygrana=false;


                        }
                    }
                    for (int i = 0; i < numberofbombs; i++) {
                        xpos = random.nextInt(20);
                        ypos = random.nextInt(20);
                        if (tab[xpos][ypos].czyJestBomba == false) {
                            tab[xpos][ypos].czyJestBomba = true;
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

        if(liczba_poprawnie_zazn_bomb==numberofbombs){
            wygrana=true;
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
