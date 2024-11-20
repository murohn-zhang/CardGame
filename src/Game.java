import java.util.Scanner;

public class Game {
    Scanner input = new Scanner(System.in);
    private final String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final String[] suit = {"Spades", "Diamonds", "Hearts", "Clubs"};
    private final int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    // constructor
    public Game () {
        // get player info
        System.out.println("How many players?");
        int numPlayers = input.nextInt();
        input.nextLine();
        for (int i = 0; i < numPlayers; i++) {
            System.out.println ("Name of Player: ");
            String name = input.nextLine();
            Player newPlayer = new Player(name);
        }
    }

    // print instructions

    // play game
    // main
    public static void main(String[] args) {
//        printInstructions();
//        playGame();
    }
}

