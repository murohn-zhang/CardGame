import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    // Declaring instance variables
    private Game game;
    private final int WINDOW_HEIGHT = 900;
    private final int WINDOW_WIDTH = 1000;
    private Player player;
    private Image bjBackground;
    private Image bjPlay;
    private Image bjEnd;

    // Constructor
    public GameView(Game game) {
        // Initialiing instance variables
        this.game = game;
        bjBackground = new ImageIcon("Resources/blackjack_bg.jpg").getImage();
        bjPlay = new ImageIcon("Resources/blackjack_game_bg.jpg").getImage();
        bjEnd = new ImageIcon("Resources/red_bg.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Blackjack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Paint function, different window pages
    public void paint(Graphics g) {

        // Start page (instructions)
        if (game.getState() == 1) {
            paintInstructions(g);
        }

        // Playing game
        else if (game.getState() == 2) {
            paintGame(g);
        }

        // End page (results)
        else if (game.getState() == 3) {
            paintEnd(g);
        }

    }

    // Design for start page
    public void paintInstructions(Graphics g) {
        // Setting basic info (color, font, window background)
        g.setColor(Color.WHITE);
        g.drawImage(bjBackground, 0, 0, 1000, 900, this);

        g.setFont(new Font("Serif", Font.ITALIC, 50));
        g.drawString("Welcome to BLACKJACK", 250, 200);

        // Printing instructions
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        g.drawString("In this card game, it's you against the dealer. Whoever gets the closest to 21 (but not over) wins!", 75, 500);
        g.drawString("Each player places their bet, then two cards are dealt out to them.", 75, 525);
        g.drawString("You can choose to either HIT (receive another card) or STAND (keep your cards as they are)", 75, 550);
        // Making this next line bold, then resetting
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("** make sure you type your choice in ALL CAPS!**", 75, 575);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        g.drawString("All the royals (Jacks, Queens, Kings) are worth 10.", 75, 600);
        g.drawString("If one of your cards is an Ace, you can choose whether to make that card have a value of 1 or 11.", 75, 625);
        g.drawString("If you get closer to 21 than the dealer, you get double of your bet back.", 75, 650);
        g.drawString("If you hit a Blackjack (exactly 21!), you get triple of your bet back.", 75, 675);
        g.drawString("Good luck!", 450, 700);
    }

    // Design for playing page
    public void paintGame(Graphics g) {
        // Setting basic info (color, font, window background), getting current player
        g.setColor(Color.WHITE);
        g.drawImage(bjPlay, 0, 0, 1000, 900, this);
        player = game.getCurrentPlayer();
        // Draw player name
        g.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g.drawString(player.getName(), 450, 100);
        // Draw player hand
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Your hand:", 450, 300);
        // For each card in the hand (including ones added later), add it to the window, and change the total
        for(int i = 0; i < player.getHand().size(); i++){
            player.getHand().get(i).draw(g, (i + 1) * 100 + 150, 350);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            g.drawString("Your current total: " + Integer.toString(player.getTotal()), 800, 75);
        }

        // Print end results for current player
        if (game.getEnd())
        {
            // Print BUST in red if they bust
            if (player.getTotal() > 21) {
                g.setColor(Color.RED);
                g.setFont(new Font("Serif", Font.BOLD, 80));
                g.drawString("BUST", 325, 800);
            }

            // Print BLACKJACK in blue if they hit a blackjack
            else if (player.getTotal() == 21) {
                g.setColor(Color.BLUE);
                g.setFont(new Font("Serif", Font.BOLD, 80));
                g.drawString("BLACKJACK!!", 355, 800);
            }

            // Otherwise just print their score
            else {
                g.drawString(player.getName() + "'s total is " + player.getTotal(), 525, 750);
            }
        }

    }
    // Design for end page
    public void paintEnd(Graphics g) {
        g.drawImage(bjEnd, 0, 0, 1000, 900, this);
        // Go through each player and display their final result compared to the dealer
        for (int i = 0; i < game.getPlayers().size(); i++) {
            printPlayerResults(g, game.getPlayers().get(i), game.getEndState(), i);
        }


    }

    // Specifics in displaying player results
    public void printPlayerResults(Graphics g, Player current, int endState, int i) {
        g.setColor(Color.WHITE);
        // Print dealer total
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("Dealer's total is: " + game.getDealerTotal(), 400, 100);

        // Different scenarios (from backend conditions), and their respective result statements
        if (endState == 1) {
            g.drawString("Both" + current.getName() + " and the dealer hit a BLACKJACK! Your score is " + current.getPoints(), 300, (i + 1) * 100 + 50);
        }

        else if (endState == 2) {
            g.drawString(current.getName() + ", you tied with the dealer", 400,  (i + 1) * 100 + 50);
        }

        else if (endState == 3) {
            g.drawString(current.getName() + ", nice try, the dealer won! You lost your bet of " + current.getBet() + " dollars :(", 100, (i + 1) * 100 + 50);
        }

        else if (endState == 4) {
            g.drawString(current.getName() + ", you beat the dealer! Your score is " + current.getPoints(), 250, (i + 1) * 100 + 50);
        }

        else {
            g.drawString("Both " + current.getName() + " and the dealer BUSTED :(", 300, (i + 1) * 100 + 50);
        }
    }
}