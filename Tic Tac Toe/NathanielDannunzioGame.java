/*
Nathaniel D'annunzio
Date: 4/19/2024
Class: AP Computer Science A
Program Name: Tic-Tac-Toe Game
Program Description: Allows the user to play Tic-Tac-Toe against another human or a computer
Difficulties: Having the user input a box to place their symbol.
What I Learned: How to combine all the aspects taught in AP Computer Science A, such as loops, arrays, method, classes, etc.
*/

import java.util.Scanner;
import java.util.Random;

public class NathanielDannunzioGame {
    static int[][] board = new int[3][3];
    static boolean end = false;
    static Scanner kbReader = new Scanner(System.in);
    static int bestOf = NathanielDannunzioMenu.getBestOf();
    static double playerOneScore = 0;
    static double playerTwoScore = 0;
    public static Random rand = new Random();
    public static int int1 = 0;
    public static int int2 = 0;
    
    // Main method to run the game
    static void game(int compDiff) {
        while (checkIfNoReturn() == false) {
            match(compDiff);
        }
        
        if (playerOneScore > playerTwoScore) {
            displayFinish(1);
        } else {
            displayFinish(2);
        }
    }
    
    // Check if the game should continue
    static boolean checkIfNoReturn() {
        if ((playerOneScore > (bestOf / 2)) || (playerTwoScore > (bestOf / 2))) {
            return true;
        } else {
            return false;
        }
    }
    
    // Method to reset the game board
    static void refreshBoard() {
        for (int n = 0; n < 3; n++) {
            for (int k = 0; k < 3; k++) {
                board[n][k] = 0;
            }
        }
    }
    
    // Make a move for the computer player
    public static void compMove(int player, int compDiff) {
        if (compDiff == 1) {
            easyMove(player);
        }
    }
    
    // Make an easy move for the computer player
    public static void easyMove(int player) {
        boolean a = true;
        
        while (a) {
            int1 = rand.nextInt(3);
            int2 = rand.nextInt(3);
            if (board[int1][int2] == 0) {
                a = false;
                board[int1][int2] = player;
            }
        }
        
        checks();
    }
        
    // Check if a player has won
    static boolean checkIf3(int player) {
        for (int n = 0; n < 3; n++) {
            if (((board[n][0] == board[n][1]) && (board[n][1] == board[n][2])) && (board[n][0] == player)) {
                System.out.println("horizontal");
                return true;
            } else if (((board[0][n] == board[1][n]) && (board[1][n] == board[2][n])) && (board[0][n] == player)) {
                System.out.println("vertical");
                return true;
            }
        }
        
        if ((((board[0][0] == board[1][1]) && (board[1][1] == board [2][2])) && (board[0][0] == player)) || (((board[0][2] == board [1][1]) && (board[1][1] == board[2][0])) && (board[0][2] == player))) {
            System.out.println("diagonal");
            return true;
        }
        return false;
    }
    
    // Check if the game is a draw
    static boolean checkIfDraw(int b[][]) {
        int count = 0;
        for (int n = 0; n < 3; n++) {
            for (int k = 0; k < 3; k++) {
                if (b[n][k] != 0) {
                    count++;
                }
            }
        }
        
        if (count == 9) {
            return true;
        } else {
            return false;
        }
    }
    
    // Display countdown to next match
    static void nextMatch() {
        System.out.println("Your next match will start in: ");
        for (int n = 3; n > 0; n--) {
            System.out.println(n);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
            }
        }
    }
    
    // Convert integer representation of player symbol to char
    static char toSymbol(int input) {
        if (input == 0) {
            return ' ';
        } else if (input == 1) {
            return 'X';
        }
        return 'O';
    }
    
    // Display current score
    static void displayScore() {
        System.out.print("SCORE: ");
        
        if ((playerOneScore - (int)playerOneScore) != 0) {
            System.out.print(playerOneScore + "-");
        } else {
            System.out.print((int)playerOneScore + "-");
        }
        
        if ((playerTwoScore - (int)playerTwoScore) != 0) {
            System.out.print(playerTwoScore);
        } else {
            System.out.print((int)playerTwoScore);
        }
        System.out.print("\n");
    }
    
    // Display the game board
    static void display() {
        NathanielDannunzioMenu.refresh();
        System.out.println("BEST OF " + bestOf);
        displayScore();
        System.out.println("  -------------");
        System.out.println("3 | " + toSymbol(board[0][0]) + " | " + toSymbol(board[0][1]) + " | " + toSymbol(board[0][2]) + " |");
        System.out.println("  -------------");
        System.out.println("2 | " + toSymbol(board[1][0]) + " | " + toSymbol(board[1][1]) + " | " + toSymbol(board[1][2]) + " |");
        System.out.println("  -------------");
        System.out.println("1 | " + toSymbol(board[2][0]) + " | " + toSymbol(board[2][1]) + " | " + toSymbol(board[2][2]) + " |");
        System.out.println("  -------------");
        System.out.println("    A   B   C");
    }
    
    // Display game over message with final score
    static void displayFinish(int player) {
        System.out.println("\n          GAME OVER");
        System.out.println("-----------------------------");
        if (player == 1) {
            System.out.println("      PLAYER ONE WINS!!!");
        } else {
            System.out.println("      PLAYER TWO WINS!!!");
        }
        System.out.print("         ");
        displayScore();
    }
    
    // Start a new match
    static void match(int compDiff) {
        if ((playerOneScore != 0) || (playerTwoScore != 0)) {
            nextMatch();
        }
        refreshBoard();
        end = false;
        while (end == false) {
            move(1, compDiff);
            if (end == false) {
                move(2, compDiff); 
            }
        }
    }
    
    // Execute a player's move
    static void move(int player, int compDiff) {
        if (player == 1) {
            moveHuman(1);
        } else {
            if (compDiff == 0) {
                moveHuman(2);
            } else {
                compMove(2, compDiff);
            }
        }
    }
    
    // Check game state after each move
    static void checks() {
        if (checkIf3(1)) {
            display();
            System.out.println("\nPLAYER ONE WINS!");
            playerOneScore++;
            end = true;
        } else if (checkIf3(2)) {
            display();
            System.out.println("\nPLAYER TWO WINS!");
            playerTwoScore++;
            end = true;
        } else if (checkIfDraw(board)) {
            display();
            System.out.println("\nDRAW.");
            playerOneScore += 0.5;
            playerTwoScore += 0.5;
            end = true;
        }
    }
    
    // Make a move for a human player
    static void moveHuman(int player) {
        boolean a = true;
        System.out.println("MAKE YOUR MOVE.");
        display();
        
        while (a) {
            String move = null;
            move = kbReader.next();
            kbReader.nextLine();
            if (move.length() < 2) {
                System.out.println("INVALID MOVE. PLEASE INPUT THE CHARACTERS A, B, OR C IMMEDIATELY FOLLOWED BY A NUMBER 1, 2, OR 3. EXAMPLE: \"A3\" IF YOU'RE HAVING TROUBLE, PLEASE SEE THE MANUAL.");
            } else if (((((Math.abs((int)move.toUpperCase().charAt(1) - 51)) < 0) || (Math.abs((int)move.toUpperCase().charAt(1) - 51)) > 3)) || (((int)move.toUpperCase().charAt(0)-65 < 0) || ((int)move.toUpperCase().charAt(0)-65 > 3))) {
                System.out.println("INVALID MOVE. PLEASE INPUT THE CHARACTERS A, B, OR C IMMEDIATELY FOLLOWED BY A NUMBER 1, 2, OR 3. EXAMPLE: \"A3\" IF YOU'RE HAVING TROUBLE, PLEASE SEE THE MANUAL.");
            } else if (board[Math.abs((int)move.toUpperCase().charAt(1) - 51)][(int)move.toUpperCase().charAt(0)-65] != 0) {
                System.out.println("INVALID MOVE. PLACE YOUR MOVE IN A SQUARE THAT IS NOT ALREADY TAKEN.");
            } else {
                a = false;
                board[Math.abs((int)move.toUpperCase().charAt(1) - 51)][(int)move.toUpperCase().charAt(0)-65] = player;
                display();
            }
        }
        
        System.out.println(checkIf3(1));
        
        checks();
    }
    
    public NathanielDannunzioGame() //basic constructor for game object
    {
        
    }
}

/*
Sample Output:
due to clearing of the screen taking up around 100 lines, only the first output will be shown

WELCOME TO TIC-TAC-TOE!!!
WOULD YOU LIKE TO PLAY AGAINST A HUMAN OR A COMPUTER?

1. HUMAN
2. COMPUTER

TYPE "1" FOR HUMAN OR "2" FOR COMPUTER.
 */
