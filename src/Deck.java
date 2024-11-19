import java.util.ArrayList;

public class Deck {
    // instance variables
    private ArrayList<Card> cards;
    private int cardsLeft; // determines which card to deal AND represents how many cards left to deal


    // constructor
    public Deck (String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<Card>();
        // create specified cards and add to list
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {

                Card newCard = new Card(ranks[i], suits[j], values[i]);
                cards.add(newCard);
            }
        }
        // set cardsLeft to number of cards in deck and shuffle
        cardsLeft = cards.size();
        shuffle();
    }

    // isEmpty returns true when cardsLeft is 0
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // getCardsLeft returns # of cards in deck left to be dealt
    public int getCardsLeft() {
        return cardsLeft;
    }

    // deal: returns a randomly selected card from deck
    public Card deal() {
        Card random = new Card("jack", "hearts", 5); // placeholder
        // return null if deck is empty
        // read problem set for more details :)
        return random;
    }

    // shuffle: reorders cards in arraylist to create shuffled deck, resets cardsLeft to # of cards in deck
    public void shuffle() {
        // basically swapping indexes of Cards in ArrayList
        // starting at last index: (for loop)
            // pick a random num r between 0 - i
            // swap card at i with card at r
            // i--;
    }
}
