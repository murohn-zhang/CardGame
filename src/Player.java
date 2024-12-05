import java.util.ArrayList;

public class Player {
    // instance variables
    private String name;
    private ArrayList<Card> hand;
    private int bet;
    private int points;
    private boolean eliminated;
    private int total;

    // constructors
    public Player (String name, int bet) {
        this.name = name;
        hand = new ArrayList<Card>();
        this.bet = bet;
        points = bet;
        total = 0;
        eliminated = false;
    }

    // this was part of the assignment
    public Player (String name, ArrayList<Card> subHand) {
        this.name = name;
        hand = new ArrayList<Card>();
        for (int i = 0; i < subHand.size(); i++) {
            this.hand.add(i, subHand.get(i));
        }
    }

    // getter methods for instance variables
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getBet() {
        return bet;
    }

    // the points they have is how much money they have at the end of the game
    public int getPoints() {
        return points;
    }

    // getCard: gets a card from inputted index from the hand
    public Card getCard(int index) {
        return hand.get(index);
    }
    // "total" is the sum of the players current hand throughout the round
    public int getTotal() {
        return total;
    }
    // _________________________________________

    // addCard: take a Card and add to players hand
    public void addCard (Card newCard) {
        hand.add(newCard);
    }

    // addPoints: adds card value to player's points
    public void addTotal (int newPoints) {
        total += newPoints;
    }

    // variable for if the player is eliminated (over 21 points) or not
    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // if the player beats the dealer they receive double their bet
    public void won() {
        points = bet * 2;
    }

    // if the player hits a blackjack they receive triple their bet
    public void Blackjack() {
        points = bet * 3;
    }

    // toString
    public String toString() {
        return name + " has " + bet + "points\n" + name + "'s cards: " + hand + "\n";
    }
}
