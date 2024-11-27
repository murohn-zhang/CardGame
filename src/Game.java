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
    private final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final String[] suits = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private final int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    // constructor
    public Game() {
        // get player info
        players = new ArrayList<Player>();
        getPlayers();
        deck = new Deck(ranks, suits, values);
    }

    // get
    public void getPlayers() {
        System.out.println("How many players?");
        int numPlayers = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Name of Player: ");
            String name = input.nextLine();
            Player newPlayer = new Player(name);
            players.add(newPlayer);
        }
        // deal out cards??
    }

    // print instructions
    // each player attempts to beat dealer by getting as close to 21 as possible, without going over 21
    // dealer class
    public void dealerWork() { // should the dealer deal the cards? or should that be a part of the game
        // declare new player as dealer
        Player dealer = new Player("dealer");
        // dealer gets two cards, find total of cards
        int total = 0;
        for (int i = 0; i < 2; i++) {
            Card newCard = deck.deal();
            total += newCard.getValue();
            dealer.addCard(newCard);
        }
        // print out one of dealers cards (up card)
        Card upCard = dealer.getCard(0);
        int upValue = upCard.getValue();
        System.out.println("Dealer's Up Card: " + upValue);
        // if total is 17 or more, cannot draw any more cards (stand)
        if (total >= 17) {
            System.out.println("Dealer STANDS");
        }
        // otherwise (16 and under), they must draw a card and keep drawing until total is greater than or equal to 17
        else {
            while (total < 17) {
                Card newCard = deck.deal();
                total += newCard.getValue();
                dealer.addCard(newCard);
            }
            System.out.println("Dealer's final is: " + total);
        }
    }

    // play game


    // main
    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.dealerWork();
//        printInstructions();
//        playGame();
    }


}
// NOTES:
// - computer is dealer (code computer section)
// - deal 2 cards to each player
// - each player place bet
// - give players option of standing (no more) or hitting (deal another card)
// if i have time:
// - naturals
// - splitting pairs
// - choosing value of aces
// - double down
// - surrender

// on the bets:
// player places bet, then gets cards
// if players cards get over 21 (BUST), then dealer collects bet (add to dealer's points)
// if dealer BUSTs or has less than player
    // player gets double of their bet if more than dealer
    // gets triple of their bet if perfect 21 (BLACKJACK)