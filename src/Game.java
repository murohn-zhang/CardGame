// Blackjack by Murohn
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // instance variables
    private Deck deck;
    private ArrayList<Player> players;

    Scanner input = new Scanner(System.in);
    // create deck w/ assigned values for each card
    // ace can be 1 or 11 (do this after finishing game)
    private final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final String[] suits = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    // constructor
    public Game() {
        // initialize variables, shuffle deck
        players = new ArrayList<Player>();
        deck = new Deck(ranks, suits, values);
    }

    // prints instructions
    public static void printInstructions() {
        System.out.println("Welcome to Blackjack!\nIn this card game, it's you against the dealer. Whoever gets the closest "
                + "to 21 (but not over) wins!\nEach player places their bet, then two cards are dealt out to them.\nYou can choose to"
                + "either HIT (receive another card) or STAND (keep your cards as they are) ** make sure you type your choice in ALL CAPS!**\n"
                + "If one of your cards is an Ace, you can choose whether to make that card have a value of 1 or 11. Choose wisely!\n"
                + "If you get closer to 21 than the dealer, you get double of your bet back.\nIf you hit a Blackjack (exactly 21!), you get triple of your bet back. "
                + "Good luck!\n");
    }

    // goes through each player
    public void playerWork() {
        // get number of players
        System.out.println("How many players?");
        int numPlayers = input.nextInt();
        input.nextLine();
        // for each player:
        for (int i = 0; i < numPlayers; i++) {
            // get their name and bet
            System.out.println("Name of Player: ");
            String name = input.nextLine();
            System.out.println(name + "'s  bet: ");
            int bet = input.nextInt();
            input.nextLine();

            // create a new player from attained info
            Player newPlayer = new Player(name, bet);
            players.add(newPlayer);
            // deal out cards to the player
            for (int j = 0; j < 2; j++) {
                Card newCard = deck.deal();
                System.out.println("You have a " + newCard.getRank());
                // if they draw an Ace
                if (newCard.getRank().equals("Ace")) {
                    newCard.setAce(newCard);
                }
                // add card value to their total
                newPlayer.addTotal(newCard.getValue());
                // add card to their hand
                newPlayer.addCard(newCard);
                // print out what card they got
            }
            // ask if they want to hit or stand, keep on asking if they input HIT, deal card accordingly
            String choice;
            do {
                System.out.println("Do you want to HIT or STAND?");
                choice = input.nextLine();
                // if they hit
                if (choice.equals("HIT")) {
                    // deal out a new card, show value
                    Card newCard = deck.deal();
                    System.out.println("New Card: " + newCard.getRank());
                    // if they draw an Ace
                    if (newCard.getRank().equals("Ace")) {
                        newCard.setAce(newCard);
                    }
                    newPlayer.addTotal(newCard.getValue());
                    newPlayer.addCard(newCard);
                }
            }
            while (choice.equals("HIT"));

            // print their total at the end
            System.out.println(newPlayer.getName() + "'s total is " + newPlayer.getTotal());
            // if it's greater than 21, set eliminated variable to true
            if (newPlayer.getTotal() > 21) {
                System.out.println("You BUSTED! Nice try :)");
                newPlayer.setEliminated(true);
            }
            // check for blackjack
            else if (newPlayer.getTotal() == 21) {
                System.out.println("You hit a BLACKJACK! Congrats " + name + "!");
            }
            System.out.println();
        }
    }

    // declare new player as dealer
    Player dealer = new Player("dealer", 0);
    int dealerTotal = 0; // ????
    public void dealerWork() {
        // dealer gets two cards, find total of cards, add cards to dealers hand
        for (int i = 0; i < 2; i++) {
            Card newCard = deck.deal();
            dealerTotal += newCard.getValue();
        }
        // if total is 17 or more, cannot draw any more cards (stand)
        // otherwise (16 and under), they must draw a card and keep drawing until total is greater than or equal to 17
        while (dealerTotal < 17) {
            Card newCard = deck.deal();
            dealerTotal += newCard.getValue();
        }

        // print out dealer's total
        System.out.println("Dealer's total is: " + dealerTotal);
        // check if dealer got blackjack
        if (dealerTotal == 21) {
            dealer.Blackjack();
            System.out.println("The dealer hit a BLACKJACK!");
        }
        // check if the dealer is eliminated
        else if (dealerTotal > 21) {
            System.out.println("Dealer BUSTED");
            dealer.setEliminated(true);
        }
    }

    // calculate winner
    public void findWinner() {
        // for each player
        int currentTotal;
        for (Player current: players) {
            currentTotal = current.getTotal();
            // if both aren't eliminated
            if (!current.isEliminated() && !dealer.isEliminated()) {

                // if both hit a blackjack
                if (currentTotal == 21 && dealerTotal == 21) {
                    System.out.println("Both " + current.getName() + " and the dealer hit a BLACKJACK!");
                    System.out.println(current.getName() + ", your score is " + current.getPoints());
                }
                // if both have the same score
                else if (currentTotal == dealerTotal) {
                    System.out.println(current.getName() + ", you tied with the dealer.");
                }
                // if the dealer has more points
                else if (currentTotal < dealerTotal) {
                    System.out.println(current.getName() + ", nice try, the dealer won! You lost your bet of " + current.getBet() + " dollars :(");
                }
                // if the player has more points
                else if (currentTotal > dealerTotal) {
                    current.won();
                    if (currentTotal == 21)
                    {
                        current.Blackjack();
                    }
                    System.out.println(current.getName() + ", you beat the dealer! Your score is " + current.getPoints());
                }
            }

            // if one of them is eliminated
            else if (!current.isEliminated() && dealer.isEliminated()) {
                current.won();
                System.out.println(current.getName() + ", you beat the dealer! Your score is " + current.getPoints());
            }

            else if (current.isEliminated() && !dealer.isEliminated()) {
                System.out.println(current.getName() + ", nice try, the dealer won! You lost your bet of " + current.getBet() + " dollars :(");
            }

            // if both are eliminated
            else if (current.isEliminated() && dealer.isEliminated()) {
                System.out.println("Both " + current.getName() + " and the dealer BUSTED :(");
            }
        }
    }

    // main
    public static void main(String[] args) {
        Game newGame = new Game();
        printInstructions();
        newGame.playerWork();
        newGame.dealerWork();
        newGame.findWinner();
    }
}
