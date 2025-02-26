import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Game game;
    private final int WINDOW_HEIGHT = 900;
    private final int WINDOW_WIDTH = 1000;
    private final int HEADER_HEIGHT = 23;
    private Player player;
    private Image bjBackground;
    private Image bjPlay;

    public GameView(Game game) {
        this.game = game;
        bjBackground = new ImageIcon("Resources/blackjack_bg.jpg").getImage();
        bjPlay = new ImageIcon("Resources/blackjack_game_bg.jpg").getImage();

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
        g.fillRect(0,0, 1000, 900);
        g.setFont(new Font ("Serif", Font.ITALIC, 50));
    }

    public void paintInstructions(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawImage(bjBackground, 0, 0, 1000, 900, this);
        g.setFont(new Font("Serif", Font.ITALIC, 50));
        g.drawString("Welcome to BLACKJACK", 250, 200);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        // how do I make a new line in the window?
        g.drawString(new StringBuilder().append("In this card game, it's you against the dealer. Whoever gets the closest to 21 (but not over) wins!\nEach player places their bet, then two cards are dealt out to them.\nYou can choose to").append("either HIT (receive another card) or STAND (keep your cards as they are) ** make sure you type your choice in ALL CAPS!**\n").append("All the royals (Jacks, Queens, Kings) are worth 10.\n").append("If one of your cards is an Ace, you can choose whether to make that card have a value of 1 or 11. Choose wisely!\n").append("If you get closer to 21 than the dealer, you get double of your bet back.\nIf you hit a Blackjack (exactly 21!), you get triple of your bet back. ").append("Good luck!\n").toString(), 100, 500);
    }

    public void paintGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawImage(bjPlay, 0, 0, 1000, 900, this);
        player = game.getCurrentPlayer();
        // Draw player name and score
        g.drawString(player.getName(), 500, 100);
        // Draw player hand
        g.drawString("Your hand:", 450, 300);
        for(int i = 0; i < player.getHand().size(); i++){
            player.getHand().get(i).draw(g, (i + 1) * 100 + 300, 350);
            g.drawString("Your current total: " + Integer.toString(player.getTotal()), 800, 75);
        }

        // Ace?
        // Print end results for current player
    }

    public void paintEnd(Graphics g) {
        reset(g);
        g.drawString("End Page, Winner Winner Chicken Dinner!", 300, 200);
    }
}
