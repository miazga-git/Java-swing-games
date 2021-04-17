
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame obj = new JFrame();
        Minesweeper gameplay = new Minesweeper();

        obj.setBounds(190,140,530,700);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
