import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // constructors
    public Player (String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        points = 0;
    }

    public Player (String name, ArrayList<Card> hand) {
        this.name = name;
        for (int i = 0; i < hand.size(); i++) {
            this.hand.add(i, hand.get(i));
        }
    }

    // getter methods
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    // addPoints: takes int number of points and add to existing points
    public void addPoints(int newPoints) {
        points += newPoints;
    }

    // addCard: take a Card and add to players hand
    public void addCard (Card newCard) {
        hand.add(newCard);
    }

    // toString
    public String toString() {
        return name + " has " + points + "points\n" + name + "'s cards: " + hand;
    }
}
