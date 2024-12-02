import java.util.Scanner;

public class Card {
    // declare instance variables
    private String rank;
    private String suit;
    private int value;

    // constructor
    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    // getter and setter for rank
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // getter and setter for suit
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    // getter and setter for value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // the player gets to choose if the ace card is 1 or 11
    public void setAce(Card ace) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want your Ace to hold a 1 or an 11?");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            ace.setValue(1);
        }
        else if (choice == 11) {
            ace.setValue(11);
        }
    }

    // to string
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
