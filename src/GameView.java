import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    private final int WINDOW_HEIGHT = 900;
    private final int WINDOW_WIDTH = 1200;
    private final int HEADER_HEIGHT = 23;
    private int state;

    public GameView(Game game) {
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void setState(int state) {
        this.state = state;
        this.repaint();
    }
    public void paint(Graphics g) {

        if (game.getState() == 1) {
            paintInstructions(g);
        }

        else if (game.getState() == 2) {
            paintGame(g);
        }

        else if (game.getState() == 3) {
            paintEnd(g);
        }

    }

    public void reset(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 1200, 900);
        g.setFont(new Font ("Serif", Font.ITALIC, 50));
        g.setColor(Color.BLACK);
    }

    public void paintInstructions(Graphics g) {
        reset(g);
        g.drawString("Welcome to BLACKJACK", 300, 200);
    }

    public void paintGame(Graphics g) {
        reset(g);
        g.drawString("Playing Game...", 300, 200);
        Deck deck = game.getCards();
        deck.deal().draw(g);
    }

    public void paintEnd(Graphics g) {
        reset(g);
        g.drawString("End Page, Winner Winner Chicken Dinner!", 300, 200);
    }
}
