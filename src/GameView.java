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
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // Printing instructions
        g.drawString("In this card game, it's you against the dealer. Whoever gets the closest to 21 (but not over) wins!", 75, 500);
        g.drawString("Each player places their bet, then two cards are dealt out to them.", 75, 525);
        g.drawString("You can choose to either HIT (receive another card) or STAND (keep your cards as they are)", 75, 550);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("** make sure you type your choice in ALL CAPS!**", 75, 575);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("All the royals (Jacks, Queens, Kings) are worth 10.", 75, 600);
        g.drawString("If one of your cards is an Ace, you can choose whether to make that card have a value of 1 or 11.", 75, 625);
        g.drawString("If you get closer to 21 than the dealer, you get double of your bet back.", 75, 650);
        g.drawString("If you hit a Blackjack (exactly 21!), you get triple of your bet back.", 75, 675);
        g.drawString("Good luck!", 450, 700);
    }

    public void paintGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawImage(bjPlay, 0, 0, 1000, 900, this);
        player = game.getCurrentPlayer();
        // Draw player name and score
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.drawString(player.getName(), 450, 100);
        // Draw player hand
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Your hand:", 450, 300);
        for(int i = 0; i < player.getHand().size(); i++){
            player.getHand().get(i).draw(g, (i + 1) * 100 + 150, 350);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            g.drawString("Your current total: " + Integer.toString(player.getTotal()), 800, 75);
        }

        // Ace?
        // Print end results for current player
        if (game.getEnd())
        {
            if (player.isEliminated()) {
                g.setColor(Color.RED);
                g.setFont(new Font("Serif", Font.BOLD, 80));
                g.drawString("BUST", 555, 800);
            }
            else if (player.getTotal() == 21) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("Serif", Font.BOLD, 80));
                g.drawString("BLACKJACK!!", 555, 800);
            }
            else {
                g.drawString(player.getName() + "'s total is " + player.getTotal(), 525, 750);
            }
        }

    }

    public void paintEnd(Graphics g) {
//        reset(g);
//        g.drawString("End Page, Winner Winner Chicken Dinner!", 300, 200);

    }

    public void printPlayerResults(Graphics g, Player current, int endState, int i) {
        if (endState == 1) {
            g.drawString("Both" + current.getName() + " and the dealer hit a BLACKJACK! Your score is " + current.getPoints(), 100, (i + 1) * 100 + 50);
        }

        else if (endState == 2) {
            g.drawString(current.getName() + ", you tied with the dealer") // ...
        }

        else if (endState == 3 || endState == 6) {

        }

        else if (endState == 4) {

        }

        else if (endState == 5) {

        }

        else if (endState == 6) {

        }

        else{

        }
    }
}
