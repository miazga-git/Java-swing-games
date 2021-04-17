
import java.awt.Color;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        BallBouncing b = new BallBouncing();
        obj.setBounds(10, 10, 905, 650);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(3);
        obj.add(b);
    }
}

