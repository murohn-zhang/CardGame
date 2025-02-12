import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    private final int WINDOW_HEIGHT = 900;
    private final int WINDOW_WIDTH = 1200;
    private final int HEADER_HEIGHT = 23;

    public GameView(Game game) {
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        Deck deck = game.getCards();
        deck.deal().draw(g);

    }
}
