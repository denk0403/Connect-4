import java.util.*;

public class Connect4 {

    static ConnectFourGame game;
    static Connect4Graphics graphics;
    public static void main(String[] args) {
        game = new ConnectFourGame();
        graphics = new Connect4Graphics(game);
        new Timer().schedule(new TimerTask() {
            public void run() {
                graphics.frame.repaint();
            }
        }, 0, 50);
    }

}