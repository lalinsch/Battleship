package battleship;

import java.util.Scanner;
/*
This class represents the general logic of the game
 */
public class Game {
    private boolean playerHasWon;
    private final Player player1;
    private final Player player2;

    //the game initialises objects of the player class
    public Game() {
        Scanner scan = new Scanner(System.in);
        player1 = new Player("Player 1", scan);
        player2 = new Player("Player 2", scan);
        playerHasWon = false;
    }

    //The main steps that are taken on each game
    public void start() {
        player1.placeShips();
        pressAnyKeyToContinue();
        player2.placeShips();
        pressAnyKeyToContinue();
        playGame();
    }

    //checks if either of the players have been declared winners
    public void checkIfWinner() {
        if (player1.isWinner() || player2.isWinner()) {
            playerHasWon = true;
        }
    }

    //Players take turns taking shots on each others boards
    public void playGame() {
        while (!playerHasWon) {
            player1.attack(player2);
            checkIfWinner();
            if(playerHasWon) {
                break;
            }
            pressAnyKeyToContinue();
            player2.attack(player1);
            checkIfWinner();
            if(playerHasWon) {
                break;
            }
            pressAnyKeyToContinue();
        }
    }

    //this mehtod allows for players to pass the device to the other player after each turn
    private void pressAnyKeyToContinue() {
        System.out.println("Press Enter and pass the move to another player \n ...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
