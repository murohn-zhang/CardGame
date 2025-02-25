import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Card {
    // Declare instance variables
    private String rank;
    private String suit;
    private int value;
    private GameView window;
    private Image face;
    public int xLoc;
    public int yLoc;

    // Constructor
    public Card(String rank, String suit, int value, GameView window, int cardNum) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.window = window;
        face = new ImageIcon("Resources/Cards/" + cardNum + ".png").getImage();
        // x and y loc should be defined in game and passed in, depends on how and when card is made

    }

    // Getter and setter for rank
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // Getter and setter for suit
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Getter and setter for value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // The player gets to choose if the ace card is 1 or 11
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

    // Have the card draw itself
    public void draw(Graphics g, int xLoc, int yLoc) {
        g.drawImage(face, xLoc, yLoc, 250, 350, window);
    }




    // to string
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
