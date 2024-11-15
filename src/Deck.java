import java.util.ArrayList;

public class Deck {
    // instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // constructor
    public Deck (String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
    }
}
