import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    private final int WINDOW_HEIGHT = 900;
    private final int WINDOW_WIDTH = 1200;
    private final int HEADER_HEIGHT = 23;
    private Player player;

    public GameView(Game game) {
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
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
        player = game.getCurrentPlayer();
        // Draw player name and score
        g.drawString(player.getName(), 450, 50);
//        g.drawString(player.get)
        // Draw player hand
        // Add new Card
        // Ace?

        // REPAINT:
        // - every player
        // - every time a card is drawn (new card and score changes)

        // Deck playerCards = game.getCards();
        // deck.deal().draw(g, 10, 20); // temporary values
    }

    public void paintEnd(Graphics g) {
        reset(g);
        g.drawString("End Page, Winner Winner Chicken Dinner!", 300, 200);
    }
}
