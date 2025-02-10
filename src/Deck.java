import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft; // Determines which card to deal AND represents how many cards left to deal


    // Constructor
    public Deck (String[] ranks, String[] suits, int[] values, GameView window) {
        cards = new ArrayList<Card>();
        // Variable to keep track of card images
        int cardNum = 1;
        // Create specified cards and add to list
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                Card newCard = new Card(ranks[i], suits[j], values[i], window, cardNum);
                cards.add(newCard);
                cardNum++;
            }
        }
        // Set cardsLeft to number of cards in deck and shuffle
        cardsLeft = cards.size();
        shuffle();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    // The function isEmpty returns true when cardsLeft is 0
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // The function getCardsLeft returns # of cards in deck left to be dealt
    public int getCardsLeft() {
        return cardsLeft;
    }

    // The function deal returns selected card from deck
    public Card deal() {
        // Return null if deck is empty
        if (cardsLeft == 0) {
            return null;
        }
        // Decrement cardsLeft and return card at index cardsLeft
        return cards.get(--cardsLeft);
    }

    // The function shuffle reorders cards in arraylist to create shuffled deck, resets cardsLeft to # of cards in deck
    public void shuffle() {
        for (int i = cardsLeft - 1; i > 0; i--) {
            // Pick a random num r between 0 - i
            int random = (int) (Math.random() * (i + 1));
            Card placeholder;

            // Swap card at i with card at r
            placeholder = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, placeholder);
        }
    }
}
