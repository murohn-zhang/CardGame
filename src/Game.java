// Blackjack by Murohn
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Instance variables
    private Deck deck;
    private ArrayList<Player> players;
    private GameView window;
    private Player dealer;
    private int dealerTotal;
    private int state;

    Scanner input = new Scanner(System.in);
    // Create deck w/ assigned values for each card
    // Ace can be 1 or 11
    private final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    // Constructor
    public Game() {
        // Initialize variables, shuffle deck
        players = new ArrayList<Player>();
        window = new GameView(this);
        deck = new Deck(ranks, suits, values, window);
        // Declare new player as dealer
        dealer = new Player("dealer", 0);
        dealerTotal = 0;
        window.repaint();

    }

    // Getter for deck
    public Deck getCards() {
        return deck;
    }

    public int getState() {
        return state;
    }

    // Prints instructions
    public void printInstructions() {
        System.out.println("Welcome to Blackjack!\nIn this card game, it's you against the dealer. Whoever gets the closest "
                + "to 21 (but not over) wins!\nEach player places their bet, then two cards are dealt out to them.\nYou can choose to"
                + "either HIT (receive another card) or STAND (keep your cards as they are) ** make sure you type your choice in ALL CAPS!**\n"
                + "All the royals (Jacks, Queens, Kings) are worth 10.\n"
                + "If one of your cards is an Ace, you can choose whether to make that card have a value of 1 or 11. Choose wisely!\n"
                + "If you get closer to 21 than the dealer, you get double of your bet back.\nIf you hit a Blackjack (exactly 21!), you get triple of your bet back. "
                + "Good luck!\n");
    }

    // Draw cards
    public void drawCard(Player newPlayer, int check) {
        // Deal out a new card, show value
        Card newCard = deck.deal();
        if (check == 0) {
            System.out.println("You have a " + newCard.getRank());
        }
        else {
            System.out.println("New Card: " + newCard.getRank());
        }
        // If they draw an Ace
        if (newCard.getRank().equals("Ace")) {
            newCard.setAce(newCard);
        }
        newPlayer.addTotal(newCard.getValue());
        newPlayer.addCard(newCard);
    }

    // Goes through each player
    public void playerWork() {
        // Get number of players
        System.out.println("How many players?");
        int numPlayers = input.nextInt();
        input.nextLine();
        // For each player:
        for (int i = 0; i < numPlayers; i++) {
            // Get their name and bet
            System.out.println("Name of Player: ");
            String name = input.nextLine();
            System.out.println(name + "'s  bet: ");
            int bet = input.nextInt();
            input.nextLine();

            // Create a new player from attained info
            Player newPlayer = new Player(name, bet);
            players.add(newPlayer);
            // Deal out cards to the player
            // Create a variable so the drawCard function knows what to print
            int check = 0;
            for (int j = 0; j < 2; j++) {
                drawCard(newPlayer, check);
            }
            // Ask if they want to hit or stand, keep on asking if they input HIT, deal card accordingly
            String choice;
            do {
                System.out.println("Do you want to HIT or STAND?");
                choice = input.nextLine();
                // If they hit
                if (choice.equals("HIT")) {
                    check = 1;
                    drawCard(newPlayer, check);
                }
            }
            while (choice.equals("HIT"));

            // Print their total at the end
            System.out.println(newPlayer.getName() + "'s total is " + newPlayer.getTotal());
            // If it's greater than 21, set eliminated variable to true
            if (newPlayer.getTotal() > 21) {
                System.out.println("You BUSTED! Nice try :)");
                newPlayer.setEliminated(true);
            }
            // Check for blackjack
            else if (newPlayer.getTotal() == 21) {
                System.out.println("You hit a BLACKJACK! Congrats " + name + "!");
            }
            System.out.println();
        }
    }

    public void dealerWork() {
        // Dealer gets two cards, find total of cards, add cards to dealers hand
        for (int i = 0; i < 2; i++) {
            Card newCard = deck.deal();
            dealerTotal += newCard.getValue();
        }
        // If total is 17 or more, cannot draw any more cards (stand)
        // Otherwise (16 and under), they must draw a card and keep drawing until total is greater than or equal to 17
        while (dealerTotal < 17) {
            Card newCard = deck.deal();
            dealerTotal += newCard.getValue();
        }

        // Print out dealer's total
        System.out.println("Dealer's total is: " + dealerTotal);
        // Check if dealer got blackjack
        if (dealerTotal == 21) {
            dealer.Blackjack();
            System.out.println("The dealer hit a BLACKJACK!");
        }
        // Check if the dealer is eliminated
        else if (dealerTotal > 21) {
            System.out.println("Dealer BUSTED");
            dealer.setEliminated(true);
        }
    }

    // Calculate winner
    public void findWinner() {
        // For each player
        int currentTotal;
        for (Player current: players) {
            currentTotal = current.getTotal();
            // If both aren't eliminated
            if (!current.isEliminated() && !dealer.isEliminated()) {

                // If both hit a blackjack
                if (currentTotal == 21 && dealerTotal == 21) {
                    System.out.println("Both " + current.getName() + " and the dealer hit a BLACKJACK!");
                    System.out.println(current.getName() + ", your score is " + current.getPoints());
                }
                // If both have the same score
                else if (currentTotal == dealerTotal) {
                    System.out.println(current.getName() + ", you tied with the dealer.");
                }
                // If the dealer has more points
                else if (currentTotal < dealerTotal) {
                    System.out.println(current.getName() + ", nice try, the dealer won! You lost your bet of " + current.getBet() + " dollars :(");
                }
                // If the player has more points
                else {
                    current.won();
                    if (currentTotal == 21)
                    {
                        current.Blackjack();
                    }
                    System.out.println(current.getName() + ", you beat the dealer! Your score is " + current.getPoints());
                }
            }

            // If one of them is eliminated
            else if (!current.isEliminated() && dealer.isEliminated()) {
                current.won();
                System.out.println(current.getName() + ", you beat the dealer! Your score is " + current.getPoints());
            }

            else if (current.isEliminated() && !dealer.isEliminated()) {
                System.out.println(current.getName() + ", nice try, the dealer won! You lost your bet of " + current.getBet() + " dollars :(");
            }

            // If both are eliminated
            else if (current.isEliminated() && dealer.isEliminated()) {
                System.out.println("Both " + current.getName() + " and the dealer BUSTED :(");
            }
        }
    }

    // Function to play game
    public void playGame() {
        state = 1;
        printInstructions();
        state = 2;
        window.repaint();
        playerWork();
        dealerWork();
        state = 3;
        window.repaint();
        findWinner();
    }

    // Main
    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.playGame();

    }
}
