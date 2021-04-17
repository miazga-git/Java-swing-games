
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    public static final int SCREEN_WIDTH = 600;
    private GameScreen gameScreen;

    public GameWindow() {
        super("Java T-Rex game");
        this.setSize(600, 175);
        this.setLocation(400, 200);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.gameScreen = new GameScreen();
        this.addKeyListener(this.gameScreen);
        this.add(this.gameScreen);
    }

    public void startGame() {
        this.setVisible(true);
        this.gameScreen.startGame();
    }

    public static void main(String[] args) {
        (new GameWindow()).startGame();
    }
}
