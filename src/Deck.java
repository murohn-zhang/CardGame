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

    // deal: returns selected card from deck
    public Card deal() {
        // return null if deck is empty
        if (cardsLeft == 0){
            return null;
        }
        // decrement cardsLeft and return card at index cardsLeft
        // cardsLeft--; // ???
        return cards.get(--cardsLeft); // ???
    }

    // shuffle: reorders cards in arraylist to create shuffled deck, resets cardsLeft to # of cards in deck
    public void shuffle() {
        for (int i = cardsLeft; i > 0; i--) {
            // pick a random num r between 0 - i
            int random = (int) (Math.random() * i);
            Card placeholder;

            // swap card at i with card at r (???)
            placeholder = cards.get(i);
            cards.get(i) = cards.get(random);
            cards.get(random) = placeholder;
        }
    }
}
